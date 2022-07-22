package techaholic.recruited.Utils;

public class Validator {

	private static final String[] upperChar = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
	private static final String[] lowerChar = "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" ");
	private static final String[] digits = "0 1 2 3 4 5 6 7 8 9".split(" ");

	private static final String[] specialCharacters = "! @ # & ( ) – [ { } ]: ; ' , ? / * ~ $ ^ + = < > -"
			.split(" ");
	private static String emailPattern = "";

	public static boolean hasUpperCase(String string) {

		return true;
	}

	public static boolean hasLowerCase(String string) {

		return true;
	}

	public static boolean hasDigit(String string) {

		return true;
	}

	public static boolean isEmpty(String string) {

		return true;
	}

	public static boolean hasSpecialCharactes(String string) {

		return true;
	}

	public static boolean passwordValidation(String password) {

		return true;
	}

	public static boolean emailValidation(String email) {
		return true;
	}
}
