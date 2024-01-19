package br.com.erudio.converters;

public class MathConverter {

	public static  Double convertToNumber(String strNumber) {
		if(strNumber == null) {
			return 0D;
		}
		String number = strNumber.replaceAll(",", ".");
		if(isStrNumber(number)) return Double.parseDouble(number);
		return 0D;	
	}

	public static  boolean isStrNumber(String strNumber) {
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", "."); 
		
		return number.matches("[+-]?[0-9]*\\.?[0-9]+");
	}
}
