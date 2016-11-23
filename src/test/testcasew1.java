package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.Expression;

public class testcasew1 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.setExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.setExpression("X +Y*2-X^2");
		assertEquals("2*Y-2",myexpression.simplify("!simplify X=2"));
	}

}