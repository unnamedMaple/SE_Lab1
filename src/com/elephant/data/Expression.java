package com.elephant.data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
  String Polynomial;

  //创建一个空的表达式
  public Expression() {
    Polynomial = " ";
  }

  //将当前表达式代替为用户输入
  public void getExpression(String in) {
    Polynomial = in + " ";//表达式末尾加一个空格，方便操作
  }


  /**
   * 表达式化简求值，参数为用户输入命令.
   * @param commandOne 输入的命令
   */
  public void simplify(String commandOne) {

    String[] cmds = commandOne.split(" ");//解析命令
    String[] veAndValue;
    String[] factorArray;
    String simPoly = Polynomial;
    String sign = "";//存储符号位
    String mono = "";//存储单项式
    String newPoly = "";//存储新的表达式
    int i,j,k,n,value,m;
    int var_num = cmds.length,factor=1,len,num_m = 0,num=simPoly.length();
    boolean matched = false;


    for (i = 0;i < num;i++) {
      //遇到+-号则表示，mono已经存储了一个单项式
      if (simPoly.substring(i, i + 1).equals("+") || simPoly.substring(i, i + 1).equals("-") 
          || i == num - 1) {
        factorArray = mono.split("\\*");//对单向式分割
        len = factorArray.length;
        String newMono = "";//存储处理后的单项式
        
        for (j = 0;j < len;j++) {
        
          if (factorArray[j].matches("\\d+")) {
            factor *= Integer.parseInt(factorArray[j]);
          } else {
            matched = false;
            
            for (k = 1;k < var_num;k++) {
              veAndValue = cmds[k].split("=");
              
              if (factorArray[j].matches(veAndValue[0])) {
                factor *= Integer.parseInt(veAndValue[1]);
                matched = true;
              } else if (factorArray[j].matches(veAndValue[0] + "\\^\\d+")) {
                n = Integer.parseInt(factorArray[j].split("\\^")[1]);
                value = Integer.parseInt(veAndValue[1]);
                
                for (m = 0;m < n;m++) {
                  factor *= value;
                }
                
                matched = true;
              }
            }
            
            if (matched == false) {
              newMono += "*" + factorArray[j];
            }
          
        }
      
      }

	
	//对系数的处理
	if (factor!=1) {
		newMono=factor+newMono;
	}
	else {
		if ( newMono.equals("")) {
	      newMono="1";
	    } else {
	      newMono=newMono.substring(1);
	    	}
	    	
	   }
	    
	   
	    //对纯整数项的处理
	   if(newMono.matches("\\d+"))
	   {
		   if(sign.equals("+")||(sign.equals("")))
		   {
			   num_m+=Integer.parseInt(newMono);
		   }
		   else
		   {
			   num_m-=Integer.parseInt(newMono);
		   }   
	   }
	   else
	   {
		   if(factor != 0)
		   {
			  newPoly+=sign+newMono;//y+4-4
		   }
	   }
	   
	   
	   //处理下个单项式前，数据初始化
	   newMono = "";
	   factor = 1;
	   mono = "";
	       sign=simPoly.substring(i, i+1);

	}
	else if(!simPoly.substring(i, i+1).equals(" "))//构建单项式
		{
			mono+=simPoly.substring(i, i+1);
		}
		
	}
	
	
	//将整数项追加到新的表达式末尾
	if(num_m < 0)
	{
		newPoly = newPoly+num_m;
	}
	else if(num_m>0)
	{
		newPoly =newPoly+"+"+ num_m;
	}
	else
	{
		if(newPoly.isEmpty())
		{
			newPoly = "0";
		}
	}
	
	
	//打印最后的表达式
	if(newPoly.substring(0, 1).equals("+"))
		{
			newPoly = newPoly.substring(1);
		}
		System.out.println(newPoly);
		
	
	}
	
	
	//表达式求导，参数为用户输入命令
	 public void derivative(String command_two)
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
		 System.out.println(0);
	 }
	 else
	 {
		 if(newPoly.substring(0, 1).equals("+"))
			 {
				 newPoly=newPoly.substring(1);
			 }
			 System.out.println(newPoly);
		 }
	
	 }

}
//修改2