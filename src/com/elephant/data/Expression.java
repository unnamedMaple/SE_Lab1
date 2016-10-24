package com.elephant.data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
  String polyNomial;

  /**
   * ����һ���յı��ʽ
   */
  public Expression() {
    polyNomial = " ";
  }

  /**
   * ����ǰ���ʽ����Ϊ�û�����
   * @param theNowPoly ��ǰ���ʽ
   */
  public void getExpression(final String theNowPoly) {
    polyNomial = theNowPoly + " ";//���ʽĩβ��һ���ո񣬷������
  }

  /**
   * ��ӡ���ı��ʽ.
   * @param newPoly ���ʽ
   */
  public void toPrint(String newPoly) {
		if(newPoly.substring(0, 1).equals("+"))
			{
				newPoly = newPoly.substring(1);
			}
			System.out.println(newPoly);
  }
  
  
  /**
   * ���ʽ������ֵ������Ϊ�û���������.
   * @param commandOne ���������
   */
  public void simplify(String commandOne) {

    String[] cmds = commandOne.split(" ");//��������
    String[] veAndValue;
    String[] factorArray;
    final String simPoly = polyNomial;
    String sign = "";//�洢����λ
    String mono = "";//�洢����ʽ
    String newPoly = "";//�洢�µı��ʽ
    int i,j,k,n,value,m;
    int var_num = cmds.length,factor=1,len,num_m = 0,num=simPoly.length();
    boolean matched = false;


    for (i = 0;i < num;i++) {
      //����+-�����ʾ��mono�Ѿ��洢��һ������ʽ
      if (simPoly.substring(i, i + 1).equals("+") || simPoly.substring(i, i + 1).equals("-") 
          || i == num - 1) {
        factorArray = mono.split("\\*");//�Ե���ʽ�ָ�
        len = factorArray.length;
        String newMono = "";//�洢�����ĵ���ʽ
        //StringBuffer newMono = new StringBuffer();
        
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

	
	//��ϵ���Ĵ���
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
	    
	   
	    //�Դ�������Ĵ���
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
	   
	   
	   //�����¸�����ʽǰ�����ݳ�ʼ��
	   newMono = "";
	   factor = 1;
	   mono = "";
	       sign=simPoly.substring(i, i+1);

	}
	else if(!simPoly.substring(i, i+1).equals(" "))//��������ʽ
		{
			mono+=simPoly.substring(i, i+1);
		}
		
	}
	
	
	//��������׷�ӵ��µı��ʽĩβ
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
	
	toPrint(newPoly);
			
	}
	
	
	//���ʽ�󵼣�����Ϊ�û���������
	 public void derivative(String command_two)
	 {
		 String[] factor_array;
		 String mono="";
	 String newmono="";
	 String newPoly="";
	 String[] cmd=command_two.split(" ");//�������
	 String sign ="";
	 
	 int len=polyNomial.length(),i,L,j;
	 int factor = 1;
	 int n=0;
	 for(i=0;i<len;i++)
	 {
		 if(polyNomial.substring(i, i+1).equals("+") || polyNomial.substring(i, i+1).equals("-") || i == len-1) 
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
			else//���Ǵ��󵼵�δ֪���������
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
	
	//�����¸�����ʽǰ�����ݳ�ʼ��
	sign = polyNomial.substring(i, i+1);
	factor = 1;
	mono = "";
	newmono = "";
	        n = 0;	 
	 }
	 
	 else if(!polyNomial.substring(i, i+1).equals(" "))
		 {
			 
			 mono+=polyNomial.substring(i, i+1);
		 }
		 
		 				 
	 }
	 if(newPoly.equals(""))
	 {
		 System.out.println(0);
	 }
	 else
	 {
		 toPrint(newPoly);
		 }
	
	 }

}
//�޸�2