import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCase4 {
    private static UserIn user=new UserIn();
	@Before
	public void setUp() throws Exception {
		user.userinput="";
	}

	@Test
	public void testMatch() 
	{
		user.userinput(" ");
		assertEquals("input error!",user.match());
	}
}
