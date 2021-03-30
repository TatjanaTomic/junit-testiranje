package calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exception.NotSupportedOperationException;
import exception.NumberNotInAreaException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class CalculatorAdvancedTest {

	private CalculatorAdvanced calculator = new CalculatorAdvanced();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(Double.valueOf(0));
	}

	@AfterEach
	void tearDown() throws Exception {
		calculator.setCurrentValue(Double.valueOf(0));
	}

	@Test
	@DisplayName("Testiranje konstruktora")
	void testCalculatorAdvanced() {
		assertThat(null, not(calculator));
	}

	@ParameterizedTest
	@MethodSource("methodWithParameters1")
	@DisplayName("Testiranje metode calculateAdvanced()") 
	void testCalculateAdvanced(Double result, Double currentValue, char action) throws NumberNotInAreaException, NotSupportedOperationException {
		
		calculator.setCurrentValue(currentValue);
		calculator.calculateAdvanced(action);
		assertThat(result, is(calculator.getCurrentValue()));
	}
	
	private static Stream<Arguments> methodWithParameters1() {
	    return Stream.of(
	      Arguments.of(1.0, 0.0, '!'),
	      Arguments.of(1.0, 1.0, '!'),
	      Arguments.of(3628800.0, 10.0, '!'),
	      Arguments.of(1.0, 0.0, '0'),
	      Arguments.of(1.0, 12.4, '0'),
	      Arguments.of(0.0, 0.0, '1'),
	      Arguments.of(-3.0, -3.5, '1'),
	      Arguments.of(9.0, -3.5, '2'),
	      Arguments.of(27.0, 3.5, '3'),
	      Arguments.of(81.0, 3.5, '4'),
	      Arguments.of(-243.0, -3.5, '5'),
	      Arguments.of(729.0, 3.5, '6'),
	      Arguments.of(2187.0, 3.5, '7'),
	      Arguments.of(6561.0, 3.5, '8'),
	      Arguments.of(-19683.0, -3.5, '9')
	);
	}
	
	
	@ParameterizedTest
	@DisplayName("Testiranje izuzetaka za caluculateAdvanced()")
	@MethodSource("methodWithParameters2")
	void testExceptions1(Double currentValue, char value, Class<Exception> exceptionType) {
		
		calculator.setCurrentValue(currentValue);
		Exception exception = assertThrows(
				exceptionType, 
				() -> calculator.calculateAdvanced(value));
		assertThat(exception, is(instanceOf(exceptionType)));
	}
	
	private static Stream<Arguments> methodWithParameters2() {
	    return Stream.of(
	      Arguments.of(Double.valueOf(-1), '!', NumberNotInAreaException.class),
	      Arguments.of(Double.valueOf(11), '!', NumberNotInAreaException.class),
	      Arguments.of(Double.valueOf(5), 'X', NotSupportedOperationException.class)	     
	);
	}

	
	@ParameterizedTest
	@DisplayName("Testiranje metode hasCharacteristic()")
	@MethodSource("methodWithParameters3")
	void testHasCharacteristic(Boolean result, Double currentValue, char action)throws NumberNotInAreaException, NotSupportedOperationException {
		
		calculator.setCurrentValue(currentValue);
		
		assertThat(result, is(calculator.hasCharacteristic(action)));
	}
	
	private static Stream<Arguments> methodWithParameters3() {
	    return Stream.of(
	      Arguments.of(true, 1.0, 'A'),
	      Arguments.of(true, 1634.0, 'A'),
	      Arguments.of(false, 10.0, 'A'),
	      Arguments.of(true, 6.0, 'P'),
	      Arguments.of(false, 1.0, 'P'),
	      Arguments.of(false, 5.0, 'P')
	     
	);
	}
	
	
	@ParameterizedTest
	@DisplayName("Testiranje izuzetaka za hasCharacteristic()")
	@MethodSource("methodWithParameters4")
	void testExceptions2(Double currentValue, char value, Class<Exception> exceptionType) {
		
		calculator.setCurrentValue(currentValue);
		Exception exception = assertThrows(
				exceptionType, 
				() -> calculator.hasCharacteristic(value));
		assertThat(exception, is(instanceOf(exceptionType)));
	}
	
	private static Stream<Arguments> methodWithParameters4() {
	    return Stream.of(
	      Arguments.of(Double.valueOf(-1), 'P', NumberNotInAreaException.class),
	      Arguments.of(Double.valueOf(0), 'A', NumberNotInAreaException.class),
	      Arguments.of(Double.valueOf(5), 'X', NotSupportedOperationException.class)	     
	);
	}
}
