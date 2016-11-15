import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.elephant.data.Expression;
public class UserIn {
	
	String userinput = "";
	Expression myexpression=new Expression();
	
	public void userinput(String in)
	{
		
		userinput = in;
		//这是第一次修改的结果
	}
	
	
	//匹配用户输入数据
	public String  match()
	{
		String monomial = "(\\s*)(\\d+|[a-zA-Z]+(\\^\\d+)?)(\\s*)((\\*(\\s*)(\\d+|[a-zA-Z]+(\\^\\d+)?)(\\s*))*)";
		
		Pattern expressionP = Pattern.compile("(("+monomial+"){1}?)(((\\+|\\-)"+monomial+")*)");
		Matcher expressionM = expressionP.matcher(userinput);
		
		Pattern simplifyP = Pattern.compile("!simplify((\\s*)([a-zA-Z]+=\\d+))*");
		Matcher simplifyM = simplifyP.matcher(userinput);
		
		Pattern derivativeP = Pattern.compile("!d/d(\\s*)[a-zA-Z]+");
		Matcher derivativeM = derivativeP.matcher(userinput);
		
		if(expressionM.matches())
		{
			 return myexpression.getExpression(userinput);
			
        }
		else if(simplifyM.matches())
		{
			return myexpression.simplify(userinput);
		}
		else if(derivativeM.matches())
		{
			return myexpression.derivative(userinput);
		}
		else
		{
			return "input error!";
		}
		
		
	}
}
