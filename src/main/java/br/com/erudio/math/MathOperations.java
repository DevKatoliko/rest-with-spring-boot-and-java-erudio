package br.com.erudio.math;

import br.com.erudio.converters.MathConverter;

public class MathOperations {

	public MathOperations() {
		
	}
	
	public Double sum(String number1, String number2) {
		
		return MathConverter.convertToNumber(number1) + MathConverter.convertToNumber(number2);
	}

	public Double sub(String number1, String number2) {

		return MathConverter.convertToNumber(number1) - MathConverter.convertToNumber(number2);

	}

	public Double mult(String number1, String number2) {

		return MathConverter.convertToNumber(number1) * MathConverter.convertToNumber(number2);

	}

	public Double frac(String number1, String number2) {

		return MathConverter.convertToNumber(number1) / MathConverter.convertToNumber(number2);

	}

	public Double porcent(String number1, String number2) {

		return MathConverter.convertToNumber(number1) * (MathConverter.convertToNumber(number2) / 100);

	}

	public Double avarage(String number1, String number2) {

		return (MathConverter.convertToNumber(number1) + MathConverter.convertToNumber(number2)) / 2;

	}

	public Double square(String number1) {

		return Math.sqrt(MathConverter.convertToNumber(number1));

	}
}
