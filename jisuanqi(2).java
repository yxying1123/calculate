import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class jisuanqi extends JFrame implements ActionListener
    {  
       JTextArea memoryArea=new JTextArea(" ",1,3);//记忆区
       JTextArea process=new JTextArea(" ",1,20);
       JTextArea dispresult=new JTextArea("0.   ",1,20);//显示结果
       //设置字体
       Font font1=new Font("微软雅黑",Font.BOLD,18 );
       Font font2=new Font("微软雅黑",Font.PLAIN,10 );
       Font font3=new Font("微软雅黑",Font.PLAIN,30 );
       Font font4=new Font("微软雅黑",Font.PLAIN,15);

       JButton[]jbuttons=new JButton[28];
       
       double result=0,first=0,second=0;//计算结果 ，第一个数字， 第二个数字
       double memery=0;//记忆值
       char firstsymbol='\0',secondsymbol='\0';
       boolean prev=true,repeat=true,dot=true;
     jisuanqi()
    {
	       super("Calculator");
	       this.setTitle("计算器");//设置标题
	       try
	       {  
	    	   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//windows风格
	       } catch(Exception e)
	       {
	           System.out.println(e);
	       }
	       JPanel resultField=new JPanel();
	       resultField.setLayout(new GridLayout(3,1));
	       
	       JPanel buttonField=new JPanel();
	       Container all=getContentPane();
	
	       GridLayout grid1;
	       grid1=new GridLayout(7,4,0,0);
	
	       memoryArea.setFont(font3);
	       dispresult.setFont(font3);
	       process.setFont(font4);
	       memoryArea.setEditable(false);
	       process.setEditable(false);
	       dispresult.setEditable(false);
	      
	
	       resultField.add(memoryArea);
	       resultField.add(process);
	       resultField.add(dispresult);
	       
	       all.add(resultField,"North");
	       //所有按键
	       String[]buttonname={"1/x","x2","√","lg","%","sin","cos","tan","CE","C","back","/","7","8","9","*","4","5","6","-","1","2","3","+","±","0",".","="};
	       buttonField.setLayout(grid1);
	       //按照4列7行进行布局
	       for(int i=0;i<4;i++)
		   {
	    	   
		    	   for(int j=0;j<7;j++)
			       {
		    		   jbuttons[i*7+j]=new JButton(buttonname[i*7+j]);
			           jbuttons[i*7+j].addActionListener(this);
			           //jbuttons[i*8+j].setBackground(Color.red);
			           buttonField.add(jbuttons[i*7+j]);
			           
			       	}
	       }
	       //给按钮设置字体
	       for(int j=0;j<4;j++){
	    	   jbuttons[j].setFont(font2);
	    	   jbuttons[j].setBackground(new Color(250,250,250));
	       }
	       for(int j=4;j<16;j++){
	    	   jbuttons[j].setFont(font4);
	       }
	       for(int j=16;j<28;j++){
	    	   jbuttons[j].setFont(font1);
	       }
	       jbuttons[15].setFont(font4);
	       jbuttons[19].setFont(font4);
	
	       all.add(buttonField,"Center");//窗体居中
	       setSize(422,395);//设置窗体大小
	       setResizable(true);//可以跳转大小
	       setVisible(true);//显示窗口
	       //this.setResizable(false);
	       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭按钮
     }

/*按下数字*/
public void pressNumber(String n)
{
	process.setText(process.getText()+n);
	if(prev)
	{
		dispresult.setText(n);
		prev=false;
	}
	else dispresult.append(n);
}
/*除法*/
public boolean divide(double d)
{
	if(d==0)
	{
		dispresult.setText("除数不能为0!");
		prev=true;
		repeat=true;
		firstsymbol='\0';
		secondsymbol='\0';
		return true;
	}
	return false;
}
/*监听按键*/
public void actionPerformed(ActionEvent e)
{   
		Object source=e.getSource();
		//CE：只清除当前这步输入的数字
		if(source==jbuttons[8])
		{
			dispresult.setText("0.");
			dot=true;
			prev=true;
			repeat=true;
			return;
		}
		//C：清零
		if(source==jbuttons[9])
		{
			process.setText(null);
			dispresult.setText("0.");
			firstsymbol='\0';
			secondsymbol='\0';
			prev=true;
			repeat=true;
			dot=true;
			return;
		}
		//back：退格删除
		if(source==jbuttons[10]){
			String text=process.getText();
			int i=text.length();
			if(i>0){
				text=text.substring(0,i-1);
				if(text.length()==0){
						dispresult.setText("0.");
						process.setText("");
						firstsymbol='\0';
						secondsymbol='\0';
						first=second=0;
						prev=true;
						repeat=true;
						dot=true;
				}
				else{
					dispresult.setText(text);
					process.setText(text);
				}
				
			}
		}
		//数字0
		if(source==jbuttons[25])
		{
			pressNumber("0");
			repeat=false;
			return;
		}
		//数字1
		if(source==jbuttons[20])
		{
			pressNumber("1");
			repeat=false;
			return;
		}
		//数字2
		if(source==jbuttons[21])
		{
			pressNumber("2");
			repeat=false;
			return;
		}
		//1/x：x分之一
		if(source==jbuttons[0])
	    {
				double temp=1/Double.parseDouble(dispresult.getText());
				dispresult.setText(String.valueOf(temp));
				prev=true;
				repeat=false;
				dot=true;
				return;
	    }
		//x2：平方
		if(source==jbuttons[1])
	    {
				double temp=Double.parseDouble(dispresult.getText())*Double.parseDouble(dispresult.getText());
				dispresult.setText(String.valueOf(temp));
				prev=true;
				repeat=false;
				dot=true;
				return;
	    }
		//√：平方根
		if(source==jbuttons[4])
	    {
				if(-Double.parseDouble(dispresult.getText())<0){
					double temp=Math.sqrt(Double.parseDouble(dispresult.getText()));
					dispresult.setText(String.valueOf(temp));
					prev=true;
					repeat=false;
					dot=true;
					return;
				}else{
					dispresult.setText("0");
				}
				
	    }
		//lg：常数对数
		if(source==jbuttons[3])
	    {
				
				if(-Double.parseDouble(dispresult.getText())<0){
					double temp=Math.log10(Double.parseDouble(dispresult.getText()));
					dispresult.setText(String.valueOf(temp));
					prev=true;
					repeat=false;
					dot=true;
					return;
				}else{
					dispresult.setText("0");
				}
				
	    }
		//%：百分号
		if(source==jbuttons[4])
	    {
				double temp=Double.parseDouble(dispresult.getText())/100;
				dispresult.setText(String.valueOf(temp));
				prev=true;
				repeat=false;
				dot=true;
				return;
	    }
		//sin：正弦
		 if(source==jbuttons[5])
	     {
				double temp=Math.sin(Double.parseDouble(dispresult.getText()));
				dispresult.setText(String.valueOf(temp));
				prev=true;
				repeat=false;
				dot=true;
				return;
	     }
		//cos:余弦
		if(source==jbuttons[6])
		{
			double temp=Math.cos(Double.parseDouble(dispresult.getText()));
			dispresult.setText(String.valueOf(temp));
			prev=true;
			repeat=false;
			dot=true;
			return;
		}
		//数字3
        if(source==jbuttons[22])
        {
                pressNumber("3");
                repeat=false;
                return;
        }
        //数字4
        if(source==jbuttons[16])
        {
                pressNumber("4");
                repeat=false;
                return;
        }
        //数字5
        if(source==jbuttons[17])
        {
                pressNumber("5");
                repeat=false;
                return;
        }
        //tan：正切
        if(source==jbuttons[7])
        {
			double temp=Math.tan(Double.parseDouble(dispresult.getText()));
			dispresult.setText(String.valueOf(temp));
			prev=true;
			repeat=false;
			dot=true;
			return;
        }
        //数字6
        if(source==jbuttons[18])
        {
                pressNumber("6");
                repeat=false;
                return;
        }
        //数字7
		if(source==jbuttons[12])
		{   pressNumber("7");
		    repeat=false;
		    return;
		}
		//数字8
		if(source==jbuttons[13])
		{   pressNumber("8");
		    repeat=false;
		    return;
		}
		//±：将数字乘以-1
		if(source==jbuttons[24])
		{   double temp=-Double.parseDouble(dispresult.getText());
		    dispresult.setText(String.valueOf(temp));
		    prev=true;
		    repeat=false;
		    dot=true;
		    return;
		}
		//数字9
		if(source==jbuttons[14])
		{   pressNumber("9");
		    repeat=false;
		    return;
		}
		//小数点.
		if(source==jbuttons[26])
		{   if(dot){
		    pressNumber(".");
		    dot=false;
		    repeat=false;
		  }
		   return;
		}
		//等于=
		if(source==jbuttons[27])
		{
		  second=Double.parseDouble(dispresult.getText());
		  dot=true;
		  //先计算乘法和除法
		  switch(secondsymbol)
		  {
		      case'*':
		        second*=first;
		        break;
		      case'/':
		        if(divide(second))  return;
		        second=first/second;
		   }//end of switch(secondsymbol)
		   secondsymbol='\0';
		   //再计算加减乘除
		   switch(firstsymbol)
		   {
		      case'+':
		         result+=second;
		         break;
		      case'-':
		         result-=second;
		         break;
		      case'*':
		         result*=second;
		         dispresult.setText(String.valueOf(result));
		         break;
		      case'/':
		         if(divide(second))   return;
		         result/=second;
		    }//end of switch(firstsymbol) 
		    if(firstsymbol!='\0') dispresult.setText(String.valueOf(result));
		    firstsymbol='\0';
		    prev=true;
		    repeat=false;
		    return;
		}
		//加法
		if(source==jbuttons[23])
		{
			process.setText(process.getText()+"+");
			dot=true;
			if(repeat){
				firstsymbol='+';
				return;
				
			}
			second=Double.parseDouble(dispresult.getText());
			switch(secondsymbol){
				case'*':
					second*=first;
					break;
				case'/':
					if(divide(second))return;
					second=first/second;
			}//end of switch(secondsymbol)
			secondsymbol='\0';
			switch(firstsymbol){
			case'\0':
				result=second;
				firstsymbol='+';
				break;
			case'+':
				result+=second;
				dispresult.setText(String.valueOf(result));
				break;
			case'-':
				result-=second;
				firstsymbol='+';
				dispresult.setText(String.valueOf(result));
				break;
			case'*':
				result*=second;
				firstsymbol='+';
				dispresult.setText(String.valueOf(result));
				break;
			case'/':
				if(divide(second))return;
				result/=second;
				firstsymbol='+';
				dispresult.setText(String.valueOf(result));
				
			}//end of switch(firstsymbol)
			prev=true;
			repeat=true;
			return;
		}
		//减法
		if(source==jbuttons[19])
		{
			process.setText(process.getText()+"-");
			dot=true;
			if(repeat){
				firstsymbol='-';
				return;
			}
			second=Double.parseDouble(dispresult.getText());
			switch(secondsymbol){
			case'*':
				second*=first;
				break;
			case'/':
				if(divide(second))return;
				second=first/second;
				
			}
			secondsymbol='\0';
			switch(firstsymbol){
			case'\0':
				result=second;
				firstsymbol='-';
				break;
			case'+':
				result=second;
				firstsymbol='-';
				dispresult.setText(String.valueOf(result));
				break;
			case'-':
				result-=second;
				dispresult.setText(String.valueOf(result));
				break;
			case'*':
				result*=second;
				firstsymbol='-';
				dispresult.setText(String.valueOf(result));
				break;
			case'/':
				if(divide(second))return;
				result/=second;
				firstsymbol='-';
				dispresult.setText(String.valueOf(result));
				
			}
			prev=true;
			repeat=true;
			return;
		}
		//乘法
		if(source==jbuttons[15])
		{
			process.setText(process.getText()+"*");
			dot=true;
			if(repeat){
				if(secondsymbol=='\0')firstsymbol='*';
				else secondsymbol='*';
				return;
			}
			second=Double.parseDouble(dispresult.getText());
			switch(secondsymbol){
			case'\0':
				switch(firstsymbol){
				case'\0':
					firstsymbol='*';
					result=second;
					break;
				case'+':
				case'-':
					first=second;
					secondsymbol='*';
					break;
				case'*':
					result*=second;
					dispresult.setText(String.valueOf(result));
					break;
				case'/':
					if(divide(second))return;
					result/=second;
					dispresult.setText(String.valueOf(result));
					firstsymbol='*';
				}
				break;
			case'*':
				first*=second;
				dispresult.setText(String.valueOf(first));
				break;
			case'/':
				if(divide(second))return;
				first/=second;
				secondsymbol='*';
				dispresult.setText(String.valueOf(first));
			}
			prev=true;
			repeat=true;
			return;
		}
		//除法
		if(source==jbuttons[11])
		{
			process.setText(process.getText()+"/");
			dot=true;
			if(repeat){
				if(secondsymbol=='\0')firstsymbol='/';
				else secondsymbol='/';
				return;
			}
			second=Double.parseDouble(dispresult.getText());
			switch(secondsymbol){
			case'\0':
				switch(firstsymbol){
				case'\0':
					firstsymbol='/';
					result=second;
					break;
				case'+':
				case'-':
					first=second;
					secondsymbol='/';
					break;
				case'*':
					result*=second;
					firstsymbol='/';
					dispresult.setText(String.valueOf(result));
					break;
				case'/':
					if(divide(second))return;
					result/=second;
					dispresult.setText(String.valueOf(result));
				}//end of switch(firstsymbol)
				break;
			case'*':
				first*=second;
				secondsymbol='/';
				dispresult.setText(String.valueOf(first));
				break;
			case'/':
				if(divide(second))return;
				first/=second;
				dispresult.setText(String.valueOf(first));
			}//end of switch(firstsymbol)
			prev=true;
			repeat=true;
			return;
		}
}
	public static void main(String args[])
	{
		jisuanqi jisuanqi=new jisuanqi();
	}
}      
	    
