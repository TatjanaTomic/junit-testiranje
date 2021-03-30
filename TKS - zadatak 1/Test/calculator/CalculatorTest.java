package calculator;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Executable;
import java.util.stream.Stream;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.MethodSource;

import exception.DivisionByZeroException;
import exception.NotSupportedOperationException;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.hamcrest.*;
//import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class CalculatorTest {

	private Calculator calculator = new Calculator(); 
	
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
	void testCalculator() {
		assertThat(null, not(calculator));
	}

	@ParameterizedTest
	@DisplayName("Testiranje metode calculate()")
	@MethodSource("methodWithParameters")
	void testCalculate(Double result, Double currentValue, Double value, char operator)throws DivisionByZeroException, NotSupportedOperationException {
		
		calculator.setCurrentValue(currentValue);
		calculator.calculate(value, operator);
		assertThat(result, is(closeTo(calculator.getCurrentValue(),0.000000000000003)));
		//assertThat(result, is(calculator.getCurrentValue()));
	}
	
	
	private static Stream<Arguments> methodWithParameters() {
	    return Stream.of(
	      Arguments.of(0.0, 0.0, 0.0, '+'),
	      Arguments.of(4.3, 1.1, 3.2, '+'),
	      Arguments.of(2.1, -1.1, 3.2, '+'),
	      Arguments.of(-2.1, 1.1, -3.2, '+'),
	      Arguments.of(-4.3, -1.1, -3.2, '+'),
	      Arguments.of(0.0, 0.0, 0.0, '-'),
	      Arguments.of(-2.1, 1.1, 3.2, '-'),
	      Arguments.of(-4.3, -1.1, 3.2, '-'),
	      Arguments.of(4.3, 1.1, -3.2, '-'),
	      Arguments.of(2.1, -1.1, -3.2, '-'),
	      Arguments.of(2.1, 3.2, 1.1, '-'),
	      Arguments.of(0.0, 0.0, 0.0, '*'),
	      Arguments.of(3.52, 1.1, 3.2, '*'),
	      Arguments.of(-3.52, -1.1, 3.2, '*'),
	      Arguments.of(-3.52, 1.1, -3.2, '*'),
	      Arguments.of(3.52, -1.1, -3.2, '*'),
	      Arguments.of(0.0, 0.0, 1.0, '/'),
	      Arguments.of(5.0, 10.0, 2.0, '/'),
	      Arguments.of(-5.0, -10.0, 2.0, '/'),
	      Arguments.of(-5.0, 10.0, -2.0, '/'),
	      Arguments.of(5.0, -10.0, -2.0, '/')
	    );
	}

	@ParameterizedTest
	@DisplayName("Testiranje exception-a")
	@MethodSource("methodWithParameters2")
	void testExceptions(Class<Exception> exceptionType, Double value, char operator) {
		
		Exception exception = assertThrows(
				exceptionType,
				() -> calculator.calculate(value, operator));
		assertThat(exception, is(instanceOf(exceptionType)));
	}
	
	private static Stream<Arguments> methodWithParameters2() {
	    return Stream.of(
	      Arguments.of(DivisionByZeroException.class, Double.valueOf(0), '/'),
	      Arguments.of(NotSupportedOperationException.class, Double.valueOf(5), 'x'),
	      Arguments.of(NullPointerException.class, null, '*')
	      );
	}
	
	@Test
	@DisplayName("Testiranje getera")
	void testGetCurrentValue() {
		assertThat(Double.valueOf(0), is(calculator.getCurrentValue()));
	}

	
	@Test
	@DisplayName("Testiranje setera")
	void testSetCurrentValue() {
		calculator.setCurrentValue(Double.valueOf(5));
		assertThat(Double.valueOf(5), is(calculator.getCurrentValue()));
	}

}
