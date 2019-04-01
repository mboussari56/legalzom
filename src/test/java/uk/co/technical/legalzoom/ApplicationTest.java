package uk.co.technical.legalzoom;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

import uk.co.technical.legalzoom.util.NumberEncoder;

public class ApplicationTest {

	@Test
	public void hideNumberTest() {
	    BigInteger number = new BigInteger("1234232334342233");
	    BigInteger number2 = new BigInteger("1234232334341234");
	    String expectedResult = "1234-xxxx-xxxx-xxxx";
	    assertEquals(expectedResult,NumberEncoder.hideNumber(number));
	    assertEquals(expectedResult,NumberEncoder.hideNumber(number2));

	}

	
	
}
