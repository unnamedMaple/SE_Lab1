import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCase9 {
    private static UserIn user=new UserIn();
	@Before
	public void setUp() throws Exception {
		user.userinput="";
	}

	@Test
	public void testMatch() 
	{
		user.userinput("5b");
		assertEquals("input error!",user.match());
	}
}
