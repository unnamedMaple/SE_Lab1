package com.elephant.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testcasew2 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.getExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.getExpression("35+a*7-6");
		assertEquals("43",myexpression.simplify("!Simplify a=2"));
	}

}
