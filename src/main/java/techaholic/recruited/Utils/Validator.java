package techaholic.recruited.Utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;
import org.apache.commons.validator.routines.EmailValidator;

public class Validator {

	private static String upperCase = "[A-Z]+";
	private static String lowerCase = "[a-z]+";
	private static String digit = "[\\d]+";
	private static String special = "[!@#&()–[{}]:;',?/*~$^+=<>-]+";
	// private static String space = " ";
	
	static String whitespace_chars =  ""       /* dummy empty string for homogeneity */
	+ "\\u0009" // CHARACTER TABULATION
                        + "\\u000A" // LINE FEED (LF)
                        + "\\u000B" // LINE TABULATION
                        + "\\u000C" // FORM FEED (FF)
                        + "\\u000D" // CARRIAGE RETURN (CR)
                        + "\\u0020" // SPACE
                        ;        
	/* A \s that actually works for Java’s native character set: Unicode */
	static String     whitespace_charclass = "["  + whitespace_chars + "]";    
	/* A \S that actually works for  Java’s native character set: Unicode */
	static String not_whitespace_charclass = "[^" + whitespace_chars + "]";
						
						
	private static String allowedPassword = "[A-Za-z\\d!@#&()–[{}]:;',?/*~$^+=<>-"+"["+whitespace_charclass+"]"+"]*";
	private static String allowedWord = "[A-Za-z"+"["+whitespace_charclass+"]"+"]*";
		
		
	public static boolean isUpperCase(String string){
		return Pattern.matches(upperCase, string) ;
	}
	public static boolean isLowerCase(String string){
		return Pattern.matches(lowerCase, string) ;
	}
	public static boolean isDigit(String string){
		return Pattern.matches(digit, string) ;
	}
	public static boolean isSpecialCharacter(String string){
		return Pattern.matches(special, string) ;
	}
	public static boolean isSpace(String string){
		return string.equals(whitespace_charclass+"+");
	}
	public static boolean isAllowedPassword(String string){
		return Pattern.matches(allowedPassword,string);
	}
	public static boolean isAllowedWord(String string){
		return Pattern.matches(allowedWord,string);
	}

	public static boolean hasUpperCase(String string) {
		for (int i = 0; i < string.length(); i++) {
			if(isUpperCase(string.substring(i,i+1))){
				return true;
			}

		}
		return false;

	}
	
	public static boolean hasLowerCase(String string) {
		for (int i = 0; i < string.length(); i++) {
			if(isLowerCase(string.substring(i,i+1))){
				return true;
			}

		}
		return false;

	}

	public static boolean hasDigit(String string) {
		for (int i = 0; i < string.length(); i++) {
			if(isDigit(string.substring(i,i+1))){
				return true;
			}
		}
		return false;

	}

		
	public static boolean hasSpecialCharacters(String string) {	
		for (int i = 0; i < string.length(); i++) {
			if(isSpecialCharacter(string.substring(i,i+1))){
				return true;
			}

		}
		return false;

	}
		
	public static boolean hasNCharecters(String string, int n){
		return string.length()>=n;
	}

	public static boolean isBlank(String string) {
		return string.isBlank();		
	}


	public static boolean validatePassword(String password) {
		
		
				 System.out.println("is allowed :"+isAllowedPassword(password));
				System.out.println("has 8 char :"+hasNCharecters(password, 8));
				System.out.println("has digit :"+hasDigit(password));
				System.out.println("has lower case :"+hasLowerCase(password));
				System.out.println("has upper case :"+hasUpperCase(password));
				System.out.println("hase special :"+hasSpecialCharacters(password));
		return isAllowedPassword(password)
				&&hasNCharecters(password, 8)
				&&hasDigit(password)
				&&hasLowerCase(password)
				&&hasUpperCase(password)
				&&hasSpecialCharacters(password);
	}
	public static boolean validateEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
	}
	public static boolean validateWord(String string) {
		return isAllowedWord(string)
				&&hasNCharecters(string, 1)
				&&(hasUpperCase(string)||hasLowerCase(string));
	}
}
