package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.Expression;

public class testcasew6 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.setExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.setExpression("X+3-3");
		assertEquals("X",myexpression.simplify("!simplify"));
	}

}
