package com.elephant.data;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
	String Polynomial;
	
	//创建一个空的表达式
	public Expression()
	{
		Polynomial=" ";
	}
	
	//将当前表达式代替为用户输入
	public String getExpression(String in)
	{
		Polynomial = in;//表达式末尾加一个空格，方便操作
		return  Polynomial;
	}
	
	
	//表达式化简求值，参数为用户输入命令
	public String simplify(String command_one)
	{
		String[] cmds = command_one.split(" ");//解析命令
		String[] ve_and_value;
		String[] factor_array;
		String simPoly = Polynomial+" ";
		String sign="";//存储符号位
		String mono="";//存储单项式
		String newPoly="";//存储新的表达式
		int i,j,k,n,value,m;
		int var_num = cmds.length,factor=1,len,num_m = 0,num=simPoly.length();
		boolean matched = false;                                                 
/*1*/   for(i=0;i<num;i++)           
		{
			//遇到+-号则表示，mono已经存储了一个单项式
/*2、3、4*/ if(simPoly.substring(i, i+1).equals("+") || 
		      simPoly.substring(i, i+1).equals("-") ||
		      i == num-1)
			{
				    
					factor_array=mono.split("\\*");//对单向式分割
					len=factor_array.length;
/*5*/				String new_mono="";//存储处理后的单项式              
		            for(j=0;j<len;j++)//                                 
		            {
/*7*/		            if( factor_array[j].matches("\\d+") )      
		            	{
/*8*/		            	factor*= Integer.parseInt(factor_array[j]); 
		            	}
		            	else
		            	{
/*9*/		            	matched = false;
/*10*/		            	for(k = 1;k<var_num;k++)
		            		{
		            			ve_and_value = cmds[k].split("=");
/*11*/		            		if(factor_array[j].matches(ve_and_value[0])) 
		            			{ 
		            				factor *= Integer.parseInt(ve_and_value[1]);
/*12*/		            			matched = true;                            
		            			}
/*13*/		            		else if(factor_array[j].matches(ve_and_value[0]+"\\^\\d+")) 
		            			{
		            				n = Integer.parseInt(factor_array[j].split("\\^")[1]);
/*14*/		            				value = Integer.parseInt(ve_and_value[1]);                
/*15*/		            			for(m=0;m<n;m++)                                     
		            				{
/*16*/		            				factor *= value;                                 
		            				}
/*17*/		            			matched = true;                                     
		            			}
		            		}
/*18*/		            	if(matched == false)                                      
		            		{
/*19*/		            		new_mono += "*" + factor_array[j];                     
		            		}
		            		
						}
		            }
		            
		            
		            //对系数的处理
/*20*/		        if(factor!=1)                                                   
		            {
/*21*/		            new_mono=factor+new_mono;                                   
		            }
					else 
		            {
/*22*/		            if( new_mono.equals(""))                                    
		            	{
/*23*/		            	new_mono="1";                                           
		            	}
		            	else
		            	{
/*24*/		            	new_mono=new_mono.substring(1);                          
		            	}
		            	
		           }
		            
		           
		            //对纯整数项的处理
/*25*/		       if(new_mono.matches("\\d+"))                                    
		           {
/*26 27*/		       if(sign.equals("+")||(sign.equals("")))                    
		        	   {
/*28*/		        		num_m+=Integer.parseInt(new_mono);                    
		        	   }
		        	   else
		        	   {
/*29*/		        	 	num_m-=Integer.parseInt(new_mono);                 
		        	   }   
		           }
		           else                                                        
		           {
/*30*/		           if(factor != 0)                                    
		        	   {
/*31*/		        		newPoly+=sign+new_mono;//y+4-4                
		        	   }
		           }
		           
		           //处理下个单项式前，数据初始化
/*32*/		       new_mono = "";
		           factor = 1;
		           mono = "";
		           sign=simPoly.substring(i, i+1);             
			
			}
/*33*/		else if(!simPoly.substring(i, i+1).equals(" "))//构建单项式      
			{
/*34*/			mono+=simPoly.substring(i, i+1);                             
			}
			
		}
		
		//将整数项追加到新的表达式末尾
/*35*/	if(num_m < 0)                                                      
		{
/*36*/ 		newPoly = newPoly+num_m;                                        
		}
/*37*/	else if(num_m>0)                                                     
		{
/*38*/		newPoly =newPoly+"+"+ num_m;                                     
		}
		else
		{
/*39*/		if(newPoly.isEmpty())                                            
			{
/*40*/			newPoly = "0";                                                
			}
		}
		
		
		//打印最后的表达式
/*41*/	if(newPoly.substring(0, 1).equals("+"))                             
		{
/*42*/		newPoly = newPoly.substring(1);                               
		}
/*43*/	return newPoly;                                                 
		
	
	}

	
	
	//表达式求导，参数为用户输入命令
	 public String derivative(String command_two)
	 {
		 
		 String[] factor_array;
		 String mono="";
		 String newmono="";
	     String newPoly="";
		 String[] cmd=command_two.split(" ");//命令解析
		 String sign ="";
		 
		 int len=Polynomial.length(),i,L,j;
		 int factor = 1;
		 int n=0;
		 for(i=0;i<len;i++)
		 {
			 if(Polynomial.substring(i, i+1).equals("+") || Polynomial.substring(i, i+1).equals("-") || i == len-1) 
			 {
				
				 Pattern monoP= Pattern.compile("("+cmd[1]+"(\\^\\d+)*)|(.*\\*("+cmd[1]+"(\\^\\d+)*))|(.*\\*("+cmd[1]+"(\\^\\d+)*)\\*.*)|(("+cmd[1]+"(\\^\\d+)*)+\\*.*)");
				 Matcher monoM=monoP.matcher(mono);
				 
				 
				    if(monoM.matches())
				    {
				    	factor_array=mono.split("\\*");
				    	L = factor_array.length;
				    	for(j = 0;j<L;j++)
				    	{
				    		if(factor_array[j].matches("\\d+"))
				    		{
				    			factor *= Integer.parseInt(factor_array[j]);
				    		}
				    		else if(factor_array[j].matches(cmd[1]+".*"))
				    		{
				    			
				    			if(factor_array[j].matches(cmd[1]))
				    			{
				    				n++;
				    			}
				    			else
				    			{	
				    				n+=Integer.parseInt(factor_array[j].split("\\^")[1]);
				    			}
				    			
				    		}
				    		else//不是待求导的未知变量的情况
				    		{
				    			newmono+="*"+factor_array[j];
				    		}
				    	}
				    	
				    	
				    	factor*=n;
				    	newmono=factor+newmono;
				    	
				    	if(newmono.matches("1\\*.*"))
				    	{
				    		newmono = newmono.substring(2);
				    	}
				    	
				    	if(n-1!=0)
				    	{
				    		if(n>2)
				    		{
				    			newmono=newmono+"*"+cmd[1]+"^"+(n-1);
				    		}
				    		else
				    		{
				    			newmono=newmono+"*"+cmd[1];
				    		}
				    	}
				    }
				    
				    
				    if(!newmono.equals(""))
				    {
				    	newPoly += sign + newmono;
				    }
				    
				    //处理下个单项式前，数据初始化
				    sign = Polynomial.substring(i, i+1);
			        factor = 1;
			        mono = "";
			        newmono = "";
			        n = 0;	 
			 }
			 
			 else if(!Polynomial.substring(i, i+1).equals(" "))
			 {
				 
				 mono+=Polynomial.substring(i, i+1);
			 }
			 
			 
			 
		 }
		 if(newPoly.equals(""))
		 {
			 return "0";
		 }
		 else
		 {
			 if(newPoly.substring(0, 1).equals("+"))
			 {
				 newPoly=newPoly.substring(1);
			 }
			 return newPoly;
		 }

	 }
	
}
