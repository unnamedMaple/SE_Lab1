package com.elephant.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testcasew6 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.getExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.getExpression("X+3-3");
		assertEquals("X",myexpression.simplify("!Simplify"));
	}

}




