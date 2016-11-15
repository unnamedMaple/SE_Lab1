import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCase2 {
    private static UserIn user=new UserIn();
	@Before
	public void setUp() throws Exception {
		user.userinput="";
	}

	@Test
	public void testMatch() 
	{
		user.userinput("Zoo*2");
		assertEquals("Zoo*2",user.match());
	}
}
