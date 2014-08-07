package com.a140.util;

public class NormalizeStrings {
	
	public static String removeNewlines(String text){
		String cleanText = text.replaceAll("\n", " ");
		cleanText = cleanText.replaceAll("(\\\\n)+", " ");
		cleanText = cleanText.replaceAll("(\\\\r)+", " ");
		return cleanText;
	}
	
	/**
	 * Check if the string is one those with mostly ???? garbage.
	 * Sometimes they also have a little extra junk that's not.
	 * @param text
	 * @return
	 */
	public static String manyQuestions(String text){
		if(text.indexOf("????")<0){
			return text;
		}
		int len = text.length();
		if(len<10){
			return "";
		}
		String tmp = text.replaceAll("[?' ']", "");
		int lenTmp=tmp.length();
		if((double)lenTmp/len<0.6){
			return "";
		}
		return text;
	}
	
	public static String removeHTMLChars(String text){
		String cleanText=text.replaceAll("<U\\+[0-9A-Za-z]+>","");
		return cleanText;
	}
	
	/**
	 * Assumes the input is string with no whitespace.
	 * If the string contains no letters, return null.
	 * Otherwise, return the input.
	 * Rule is, if there's not at least one alpha, it's not 
	 * a word.
	 * @return
	 */
	public static String mustHaveAlpha(String word){
		if(word==null){
			return word;
		}
		word=word.trim();
		String newWord = word.replaceAll("[a-zA-z]", "");
		if(newWord.equals(word)){
			return null;
		}
		return word;
	}
	
	public static String removeCRLF(String str){
		if(str==null){
			return str;
		}
		str=str.trim();
		String newWord = str.replaceAll("\\r\\n", " ");
		newWord = newWord.replaceAll("\\n", " ");
		newWord = newWord.replaceAll("\n", " ");
		newWord = newWord.replaceAll("\\r", " ");
		newWord = newWord.replaceAll("\r", " ");
		if(newWord.equals("")){
			return null;
		}
		return newWord;
	}
	
	
	/**
	 * Return the string with junk removed. If the string reduced to nothing,
	 * return null. If the what remains is too short, return null.
	 */
	public static String cleanupString(String text){
		if(text==null){
			return null;
		}
		String cleanText=text.replaceAll("<.*>","");
		cleanText=text.replaceAll("[,\\[\\]'*.\"><,!-():_=+`?;]","");
		cleanText=cleanText.replaceAll("[\\x00\\x08\\x0B\\x0C\\x0E-\\x1F]", "");
		cleanText=cleanText.trim();
		cleanText=detectShort(cleanText);
		if(cleanText.length()==0){
			return null;
		}
		return cleanText.toLowerCase();
	}
	
	private static String OkShort = "aioy";
	
	/**
	 * If the input string is too short, and is not one of a few defined exceptions, 
	 * return the empty string.
	 * Otherwise, return the input string.
	 * @param str
	 * @return
	 */
	private static String detectShort(String str){
		if(str.length()>1){
			return str;
		}
		if(OkShort.indexOf(str)>=0){
			return str;
		}
		return "";
	}
	
	
	
	public static String escape(String text){
		return UtilityAlt.escapeAField(text);
	}
	
}
