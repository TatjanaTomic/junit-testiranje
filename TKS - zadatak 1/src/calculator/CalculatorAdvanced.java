package calculator;

import exception.NumberNotInAreaException;
import exception.NotSupportedOperationException;

/** 
 * The CalculatorAdvanced class implements an application that calculates advanced operations: 
 * factorial of a number '!' and exponential value of a number.
 * It also checks if number is Armstrong or Perfect number.
 * 
 * @author Tatjana Tomic
 * @version 1.0
 * @since 2020-12-24 
 */
public class CalculatorAdvanced extends Calculator {

	/**
	 * Constructs a new CalculatorAdvanced object. CalculatorAdvanced is inherited from Calculator class. 
	 */
	public CalculatorAdvanced() {
		super();
	}

	/**
	 * The method calculates advanced mathematical operations: factorial of a number and exponential value of a number.
	 * The operand of operation is integer value od ottribute currentValue.
	 * @param action If passed parameter is '!', it calculates factorial. If passed parameter is number in range [0, 9], it calculates exponential value. Passed value is exponent.
	 * @throws NumberNotInAreaException Method throws NumberNotInAreaException if the currentValue is out of the assigned range.
	 * @see NumberNotInAreaException
	 * @throws NotSupportedOperationException Method throws NotSupportedOperationException if passed value is not equal to some of implemented operations.
	 * @see NotSupportedOperationException
	 */
	public void calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException {
		
		Integer currentValue = getCurrentValue().intValue();
		
		if (action == '!') {
			
			if(currentValue >= 0 && currentValue <= 10)	{
				
				Double fact = 1.0;
				
				if(currentValue != 0) //Ako je veæe od nula, racuna faktorijel. Ako je 0, fakrotijel je 1.
					for(int i = 1; i <= currentValue; i++)
						fact *= i;
				
				setCurrentValue(fact);
			}
			else
				throw new NumberNotInAreaException("Vrijednost mora biti iz opsega [0,10]!"); 
		}
		else if (action >= 0x30 && action <= 0x39) {
			
			Integer exponent = Character.getNumericValue(action);
			Integer result = 1;
			
			if(exponent >= 1)
				for(int i=1; i <= exponent; i++)
					result *= currentValue;
			
			setCurrentValue((double)result);
		}
		else 
			throw new NotSupportedOperationException();
	} 
	
	/** 
	 * Method calculates if the currentValue is Armstrong or perfect number.
	 *  
	 * @param value In case of 'A', the method checks if currentValue is Armstrong number. In case of 'P', the method checks if currentValue is Perfect number.
	 * @return True if currentValue is Armstrong/Perfect.
	 * @throws NumberNotInAreaException Method throws NumberNotInAreaException if the currentValue is less than 1.
	 * @see NumberNotInAreaException
	 * @throws NotSupportedOperationException Method throws NotSupportedOperationException if passed value is not equal to 'A' or 'P'.
	 * @see NotSupportedOperationException
	 */
	public Boolean hasCharacteristic(char value) throws NumberNotInAreaException, NotSupportedOperationException {
		
		Integer currentValue = getCurrentValue().intValue();
		
		if(currentValue < 1)
			throw new NumberNotInAreaException("Vrijednost ne smije biti manja od 1!");
		else {
			if(value == 'A') {
				return isArmstrong(currentValue.intValue());
			}
			else if(value == 'P') {
				return isPerfect(currentValue.intValue());
			}
			else
				throw new NotSupportedOperationException();
		}
	}
	
	/**
	 * Help method that checks if number is Perfect.
	 * The number is perfect if it is equal to sum of its divisors. 
	 * The method checks for each number in range [1, number) if it is divisor of passed number and summarizes values. 
	 * @param number Passed value needed to be checked if it is perfect number. The type of parameter is Integer.
	 * @return True if the number is perfect, false if it is not.
	 */
	private Boolean isPerfect(int number) {
		
		int sum = 0;
		
		for(int i = 1; i < number; i++)
			if(number % i == 0)
				sum += i;
		
		if(sum == number)
			return true;
		return false;
	}
	
	/**
	 * Help method that checks if number is Armstrong number.
	 * The method calculates number of digits in the passed number, dividing it by 10.
	 * It calculates exponential value for each digit and summerizes the values. The value of exponent is the number of digits.
	 * If the sum is equal to the number, passed number is Armstrong.
	 * Otherwise, it is not Armstrong number.  
	 * @param number Passed value needed to be checked if it is Armstrong number. The type of parameter is Integer.
	 * @return True if the number is Armstrong, false if it is not.
	 */
	private Boolean isArmstrong(int number) {
		
		int sum = 0, numOfDigits = 0, temp;
	    
		temp = number;
		while (temp >= 1) {
			temp /= 10;
			numOfDigits++;
		}
		
		temp = number;
		while(temp >= 1) {
			int digit = temp % 10;
			int tempSum = 1;
				
			for(int i=1; i <= numOfDigits; i++)
				tempSum *= digit;
				
			sum += tempSum;
			temp /= 10;
		}
		
	    if (sum == number)
	    	return true;
	    return false;
	}
}
