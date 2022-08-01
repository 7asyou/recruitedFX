package techaholic.recruited.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Transition {
	
	private TranslateTransition translateUp = new TranslateTransition(Duration.millis(200));
	private TranslateTransition translateDown = new TranslateTransition(Duration.millis(200));
	public static Transition instance ;
	
	public boolean isActive = false;

	private Transition() {
	
	}
	
	public static Transition getInstance(Node node){
		if(instance == null){
			instance = new Transition();
			instance.setPopUpTransition();
		}
		instance.translateUp.setNode(node);
		instance.translateDown.setNode(node);
		return instance;
	}


	
	
	public void setPopUpTransition(){
		translateUp.setByY(-320);
		translateUp.setCycleCount(1);
		translateUp.setAutoReverse(true);
		translateUp.setOnFinished(e ->{
			translateDown.setByY(320);
			translateDown.setCycleCount(1);
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			translateDown.setOnFinished((e0)->isActive=false);
			service.schedule(
				translateDown::play
			, 1500, TimeUnit.MILLISECONDS);
				
		});
	}

	public void play(){
		if(isActive){
			return;
		}
		translateUp.playFromStart();
		isActive = true;
		
	}

	
	
}
