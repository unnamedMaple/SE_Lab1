package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.Expression;

public class testcasew2 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.setExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.setExpression("35+a*7-6");
		assertEquals("43",myexpression.simplify("!simplify a=2"));
	}

}