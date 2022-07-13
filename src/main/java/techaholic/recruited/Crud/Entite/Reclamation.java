package techaholic.recruited.Crud.Entite;

public class Reclamation {
	private int id;
	private String objet;
	private String description;
	private String attachment;

	public Reclamation(int id, String objet, String description, String attachment) {
		this.id = id;
		this.objet = objet;
		this.description = description;
		this.attachment = attachment;
	}

	public Reclamation(String objet, String description, String attachment) {
		this.objet = objet;
		this.description = description;
		this.attachment = attachment;
	}

}
