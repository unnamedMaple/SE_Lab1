import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCase7 {
    private static UserIn user=new UserIn();
	@Before
	public void setUp() throws Exception {
		user.userinput="";
	}

	@Test
	public void testMatch() 
	{
		user.userinput("+a*");
		assertEquals("input error!",user.match());
	}
}
