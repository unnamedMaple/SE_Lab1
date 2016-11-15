package com.elephant.data;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
	String Polynomial;
	
	//����һ���յı��ʽ
	public Expression()
	{
		Polynomial=" ";
	}
	
	//����ǰ���ʽ����Ϊ�û�����
	public String getExpression(String in)
	{
		Polynomial = in;//���ʽĩβ��һ���ո񣬷������
		return  Polynomial;
	}
	
	
	//���ʽ������ֵ������Ϊ�û���������
	public String simplify(String command_one)
	{
		String[] cmds = command_one.split(" ");//��������
		String[] ve_and_value;
		String[] factor_array;
		String simPoly = Polynomial;
		String sign="";//�洢����λ
		String mono="";//�洢����ʽ
		String newPoly="";//�洢�µı��ʽ
		int i,j,k,n,value,m;
		int var_num = cmds.length,factor=1,len,num_m = 0,num=simPoly.length();
		boolean matched = false;
		
		
		
	
		
		for(i=0;i<num;i++)
		{
			//����+-�����ʾ��mono�Ѿ��洢��һ������ʽ
			if(simPoly.substring(i, i+1).equals("+") || simPoly.substring(i, i+1).equals("-") || i == num-1)
			{
				    
					factor_array=mono.split("\\*");//�Ե���ʽ�ָ�
					len=factor_array.length;
					String new_mono="";//�洢�����ĵ���ʽ
		            for(j=0;j<len;j++)
		            {
		            	if( factor_array[j].matches("\\d+") )
		            	{
		            		factor*= Integer.parseInt(factor_array[j]);
		            	}
		            	else
		            	{
		            		matched = false;
		            		for(k = 1;k<var_num;k++)
		            		{
		            			ve_and_value = cmds[k].split("=");
		            			if(factor_array[j].matches(ve_and_value[0]))
		            			{
		            				factor *= Integer.parseInt(ve_and_value[1]);
		            				matched = true;
		            			}
		            			else if(factor_array[j].matches(ve_and_value[0]+"\\^\\d+"))
		            			{
		            				n = Integer.parseInt(factor_array[j].split("\\^")[1]);
		            				value = Integer.parseInt(ve_and_value[1]);
		            				for(m=0;m<n;m++)
		            				{
		            					factor *= value;
		            				}
		            				matched = true;
		            			}
		            		}
		            		if(matched == false)
		            		{
		            			new_mono += "*" + factor_array[j];
		            		}
		            		
		            	}
		            }
		            
		            
		            //��ϵ���Ĵ���
		            if(factor!=1)
		            {
		            	new_mono=factor+new_mono;
		            }
		            else 
		            {
		            	if( new_mono.equals(""))
		            	{
		            		 new_mono="1";
		            	}
		            	else
		            	{
		            		 new_mono=new_mono.substring(1);
		            	}
		            	
		           }
		            
		           
		            //�Դ�������Ĵ���
		           if(new_mono.matches("\\d+"))
		           {
		        	   if(sign.equals("+")||(sign.equals("")))
		        	   {
		        		   num_m+=Integer.parseInt(new_mono);
		        	   }
		        	   else
		        	   {
		        		   num_m-=Integer.parseInt(new_mono);
		        	   }   
		           }
		           else
		           {
		        	   if(factor != 0)
		        	   {
		        		  newPoly+=sign+new_mono;//y+4-4
		        	   }
		           }
		           
		           //�����¸�����ʽǰ�����ݳ�ʼ��
		           new_mono = "";
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
		
		
		//��ӡ���ı��ʽ
		if(newPoly.substring(0, 1).equals("+"))
		{
			newPoly = newPoly.substring(1);
		}
		return newPoly;
		
	
	}
	
	
	//���ʽ�󵼣�����Ϊ�û���������
	 public String derivative(String command_two)
	 {
		 
		 String[] factor_array;
		 String mono="";
		 String newmono="";
	     String newPoly="";
		 String[] cmd=command_two.split(" ");//�������
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
