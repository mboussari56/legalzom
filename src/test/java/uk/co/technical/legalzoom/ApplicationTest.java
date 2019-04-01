package uk.co.technical.legalzoom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.co.technical.legalzoom.util.NumberEncoder;

public class ApplicationTest {

	@Test
	public void hideNumberTest() {
	    String number = "1234-2323-3434-2233";
	    String number2 = "1234-2323-3434-1234";
	    String number3 = "123884-232666663-34366664-123888884";
	    String expectedResult = "1234-xxxx-xxxx-xxxx";
	    String expectedResult3 = "123884-xxxxxxxxx-xxxxxxxx-xxxxxxxxx";
	    assertEquals(expectedResult,NumberEncoder.hideString(number, "-"));
	    assertEquals(expectedResult,NumberEncoder.hideString(number2, "-"));
	    assertEquals(expectedResult3,NumberEncoder.hideString(number3, "-"));

	}

	
	
}
