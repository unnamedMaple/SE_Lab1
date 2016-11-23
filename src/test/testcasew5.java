package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.Expression;

public class testcasew5 {
	private Expression myexpression=new Expression();

	@Before
	public void setUp() throws Exception {
		myexpression.setExpression("");
	}

	@Test
	public void testSimplify() {
		myexpression.setExpression("0*X");
		assertEquals("0",myexpression.simplify("!simplify"));
	}

}
