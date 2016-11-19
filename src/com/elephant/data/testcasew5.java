package com.elephant.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testcasew5 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.getExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.getExpression("0*X");
		assertEquals("0",myexpression.simplify("!Simplify"));
	}

}




 
