package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.Expression;

public class testcasew4 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.setExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.setExpression("3+5");
		assertEquals("8",myexpression.simplify("!simplify"));
	}

}