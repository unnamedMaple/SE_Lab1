package com.elephant.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testcasew1 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.getExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.getExpression("X +Y*2-X^2");
		assertEquals("2*Y-2",myexpression.simplify("!Simplify X=2"));
	}

}
