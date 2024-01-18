package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOparationException;

@RestController
public class MathController {
	
	private static final AtomicLong cont = new AtomicLong();
	
	//--> @RequestMapping(value ="/sum/{number1}/{number2}", method = RequestMethod.GET).   
	@GetMapping(value ="/sum/{number1}/{number2}") //No RequestMapping seria conveniente explicitar o tipo de requisição, no caso GET.
	public Double sum (@PathVariable(value = "number1")String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException{
		if(!isStrNumber(number1) || !isStrNumber(number2) ) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return convertToNumber(number1) + convertToNumber(number2);
	} //PathVariable associa o valor do parâmetro com o valor da PathVariable(value = number1) por exemplo. 

	
	@GetMapping(value = "/sub/{number1}/{number2}")
	public Double sub(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!isStrNumber(number1) || !isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return convertToNumber(number1) - convertToNumber(number2);
	
	} 
	
	@GetMapping(value = "/mult/{number1}/{number2}")
	public Double mult (@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!isStrNumber(number1) || !isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return convertToNumber(number1) * convertToNumber(number2);
	
	} 
	
	@GetMapping(value = "/frac/{number1}/{number2}")
	public Double frac(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!isStrNumber(number1) || !isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return convertToNumber(number1) / convertToNumber(number2);
	
	} 
	
	@GetMapping(value = "/porcent/{number1}/{number2}")
	public Double porcent(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!isStrNumber(number1) || !isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return convertToNumber(number1) * (convertToNumber(number2)/100) ;
	
	} 
	
	@GetMapping(value = "/avarage/{number1}/{number2}")
	public Double avarage(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!isStrNumber(number1) || !isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return (convertToNumber(number1) + convertToNumber(number2)) /2 ;
	
	} 
	
	@GetMapping(value = "/square/{number1}")
	public Double square(@PathVariable(value = "number1") String number1) throws UnsupportedMathOparationException {
		if(!isStrNumber(number1)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return Math.sqrt(convertToNumber(number1)) ;
	
	} 
	
	
	
	
	private Double convertToNumber(String strNumber) {
		if(strNumber == null) {
			return 0D;
		}
		String number = strNumber.replaceAll(",", ".");
		if(isStrNumber(number)) return Double.parseDouble(number);
		return 0D;	
	}

	private boolean isStrNumber(String strNumber) {
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", "."); 
		
		return number.matches("[+-]?[0-9]*\\.?[0-9]+");
	}
}
