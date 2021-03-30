package calculator;

import exception.DivisionByZeroException;
import exception.NotSupportedOperationException;

/** 
 * The Calculator class implements an application that calculates one of four basic operations:
 * addition '+', 
 * subtraction '-',
 * multiplication '*',
 * division '/'.
 * 
 * @author Tatjana Tomic
 * @version 1.0
 * @since 2020-12-24
 */
public class Calculator {
	
	private Double currentValue;
	
	/**
	 * Constructs a new Calculator object and initializes attribute cuurentValue to a initial value 0.
	 */
	public Calculator() {
		currentValue = Double.valueOf(0);
	}
	
	/**
	 * The method applies one of four basic operations '+', '-', '*' or '/'.
	 * First operand is currentValue, the attribute of class. Second operand is passed value.
	 *
	 * @param value Second operand of operation. The type of parameter is Double.
	 * @param operator Parameter that determines which operation is going to be applied. The type of parameter is char.
	 * @throws DivisionByZeroException Method throws DivisionByZeroException when second operand is 0 and operation is '/'.
	 * @see DivisionByZeroException
	 * @throws NotSupportedOperationException Method throws NotSupportedOperationException when parameter operator is not one  of '+', '-', '*' or '/'.
	 * @see NotSupportedOperationException
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		
		if(value == null)
			throw new NullPointerException();
	
		else {
			switch(operator) {
			case '+':
				currentValue += value;
				break;
			case '-':
				currentValue -= value;
				break;
			case '*':
				currentValue *= value;
				break;
			case '/':
				if(value == 0) 
					throw new DivisionByZeroException();
				else
					currentValue /= value;
				break;
			default:
				throw new NotSupportedOperationException();
			}
		}
	}
	
	/**
	 * The method returns currentValue which is private attribute of Calculator class.
	 * @return currentValue Value of attribute currentValue. The type of attribute is Double.
	 */
	public Double getCurrentValue() {
		return currentValue;
	}
	
	/**
	 * The method sets value of private attribute currentValue to a passed currentValue.
	 * @param currentValue Value that is going to be assigned to attribute currentValue. The type of parameter is Double.
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
}
