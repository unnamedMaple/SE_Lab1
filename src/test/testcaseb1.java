package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.MatchClass;
import entity.Expression;

public class testcaseb1 {
	MatchClass mymatchclass=new MatchClass();
	Expression myexpression=new Expression();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetExpression() {
		mymatchclass.setUserIn("1+ 2*a - a^3");
		if(mymatchclass.match().equals("expression"))
		{
			myexpression.setExpression(mymatchclass.getUserIn());
		}
		else
		{
			myexpression.setExpression("input error!");
		}
		assertEquals("1+ 2*a - a^3",myexpression.getExpression());
	}

}
