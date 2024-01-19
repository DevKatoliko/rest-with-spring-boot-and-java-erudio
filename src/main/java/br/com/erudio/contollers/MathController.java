package br.com.erudio.contollers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converters.MathConverter;
import br.com.erudio.exceptions.UnsupportedMathOparationException;
import br.com.erudio.math.MathOperations;

@RestController
public class MathController {
	
	private static final AtomicLong cont = new AtomicLong();
	private MathOperations math = new MathOperations();
	
	//--> @RequestMapping(value ="/sum/{number1}/{number2}", method = RequestMethod.GET).   
	@GetMapping(value ="/sum/{number1}/{number2}") //No RequestMapping seria conveniente explicitar o tipo de requisição, no caso GET.
	public Double sum (@PathVariable(value = "number1")String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException{
		if(!MathConverter.isStrNumber(number1) || !MathConverter.isStrNumber(number2) ) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return math.sum(number1, number2);
	} //PathVariable associa o valor do parâmetro com o valor da PathVariable(value = number1) por exemplo. 

	
	@GetMapping(value = "/sub/{number1}/{number2}")
	public Double sub(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!MathConverter.isStrNumber(number1) || !MathConverter.isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return math.sub(number1, number2);
	
	} 
	
	@GetMapping(value = "/mult/{number1}/{number2}")
	public Double mult (@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!MathConverter.isStrNumber(number1) || !MathConverter.isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return math.mult(number1, number2);
	
	} 
	
	@GetMapping(value = "/frac/{number1}/{number2}")
	public Double frac(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!MathConverter.isStrNumber(number1) || !MathConverter.isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return math.frac(number1,number2);
	
	} 
	
	@GetMapping(value = "/porcent/{number1}/{number2}")
	public Double porcent(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!MathConverter.isStrNumber(number1) || !MathConverter.isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return math.porcent(number1, number2);
	
	} 
	
	@GetMapping(value = "/avarage/{number1}/{number2}")
	public Double avarage(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws UnsupportedMathOparationException {
		if(!MathConverter.isStrNumber(number1) || !MathConverter.isStrNumber(number2)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return math.avarage(number1, number2);
	
	} 
	
	@GetMapping(value = "/square/{number1}")
	public Double square(@PathVariable(value = "number1") String number1) throws UnsupportedMathOparationException {
		if(!MathConverter.isStrNumber(number1)) {
			throw new UnsupportedMathOparationException("Please set a numeric value");
		}
		
		return math.square(number1) ;
	
	} 
	
	
	
	
	
}