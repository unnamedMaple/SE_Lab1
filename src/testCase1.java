import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCase1 {
    private static UserIn user=new UserIn();
	@Before
	public void setUp() throws Exception {
		user.userinput="";
	}

	@Test
	public void testMatch() 
	{
		
		user.userinput("1+ 2*a - a^3");
		assertEquals("1+ 2*a - a^3",user.match());
	}
}
