import java.awt.*;
import java.awt.event.*;

public class MyCalculatorDemoS
{
	private static Frame frm = new Frame("自製小算盤");
	private static Label lab = new Label("0", Label.RIGHT);
	private static Panel pnl = new Panel();
	private static Button btn_backspace = new Button("\u2192");//指向左方的箭頭
	private static Button btn_ce = new Button("CE");
	private static Button btn_c = new Button("C");
	private static Button btn_plus_or_minus = new Button("\u00b1");//正負號標誌
	private static Button btn_squartroot = new Button("\u221a");//平方根號
	private static Button btn_7 = new Button("7");
	private static Button btn_8 = new Button("8");
	private static Button btn_9 = new Button("9");
	private static Button btn_divide = new Button("\u00f7");//手寫除號
	private static Button btn_percent = new Button("%");
	private static Button btn_4 = new Button("4");
	private static Button btn_5 = new Button("5");
	private static Button btn_6 = new Button("6");
	private static Button btn_multiply = new Button("\u00d7");//手寫乘號
	private static Button btn_reciprocal = new Button("1/x");
	private static Button btn_1 = new Button("1");
	private static Button btn_2 = new Button("2");
	private static Button btn_3 = new Button("3");
	private static Button btn_minus = new Button("-");
	private static Button btn_equal = new Button("=");
	private static Button btn_0 = new Button("0");
	private static Button btn_point = new Button(".");
	private static Button btn_plus = new Button("+");
	//以上是視窗外觀部分的元件
	private static boolean reinput = true;//檢查目前是不是需要重新輸入數字
	private static boolean float_mode = false;
	private static boolean oprand_1_is_set = false;
	private static boolean operater_is_just_pressed = false;
	private static char op =' ';
	private static double oprand_double_temp = 0;
	private static double oprand_double_1 = 0;
	private static double oprand_double_2 = 0;
	private static int oprand_int_temp = 0;
	private static int oprand_int_1 = 0;
	private static int oprand_int_2 = 0;
	//以上是計算核心的元件

	public static void main(String args[])
	{
		frm.setBounds(144, 81, 480, 655);//這是計算機區塊所需要用的版面大小，全版大小還是1440 x 810
		frm.setBackground(Color.CYAN);
		frm.setResizable(false); //這一行的有無會影響元件擺放的位置
		frm.setVisible(true);
		frm.setLayout(null);

		lab.setBounds(20, 60, 440, 100); //標題列高度大約是 50 pixels
		lab.setFont( new Font("Serief", Font.PLAIN, 76) );//設定為76的字型大小，就可以在任何情況都不會滿出顯示區
		lab.setBackground( new Color(100, 150, 255) );
		lab.setForeground(Color.BLACK);

		pnl.setBounds(20, 170, 440, 465);

		addButtonsToPanel(pnl);

		btn_ce.setName("button0"); //因為按鍵上的標籤較長，所以先命名好以下三個特定按鈕，方便做switch迴圈處理
		btn_c.setName("button1");
		btn_backspace.setName("button2");

		frm.add(lab);
		frm.add(pnl);//這一行似乎要在最後加上去，版面才不會亂掉。

		CNumInput num_input = new CNumInput();

		btn_0.addActionListener(num_input);
		btn_1.addActionListener(num_input);
		btn_2.addActionListener(num_input);
		btn_3.addActionListener(num_input);
		btn_4.addActionListener(num_input);
		btn_5.addActionListener(num_input);
		btn_6.addActionListener(num_input);
		btn_7.addActionListener(num_input);
		btn_8.addActionListener(num_input);
		btn_9.addActionListener(num_input);
		btn_point.addActionListener(num_input);

		CNumCorrection num_correct = new CNumCorrection();

		btn_backspace.addActionListener(num_correct);
		btn_ce.addActionListener(num_correct);
		btn_c.addActionListener(num_correct);

		CUnaryOperation unary_op = new CUnaryOperation();

		btn_plus_or_minus.addActionListener(unary_op);
		btn_squartroot.addActionListener(unary_op);
		btn_percent.addActionListener(unary_op);
		btn_reciprocal.addActionListener(unary_op);

		CBinaryOperation binary_op = new CBinaryOperation();

		btn_plus.addActionListener(binary_op);
		btn_minus.addActionListener(binary_op);
		btn_multiply.addActionListener(binary_op);
		btn_divide.addActionListener(binary_op);
		btn_equal.addActionListener(binary_op);

		frm.addWindowListener
		( new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}
		);//關閉視窗按鈕所需要的程式碼
	}// end of main(String args[])

	private static void addButtonsToPanel(Panel tmp_pnl)
	{
		tmp_pnl.setLayout( new GridBagLayout() );
		GridBagConstraints gbc = new GridBagConstraints();

		btn_backspace.setFont( new Font("Serief", Font.BOLD, 25) );
        btn_backspace.setBackground( Color.DARK_GRAY );
        btn_backspace.setForeground( Color.WHITE );
        gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 0;
		gbc.ipady = 0;
        tmp_pnl.add(btn_backspace, gbc);

        btn_ce.setFont( new Font("Serief", Font.BOLD, 20) );
        btn_ce.setBackground( Color.DARK_GRAY );
        btn_ce.setForeground( Color.WHITE );
        gbc.gridx = 1;
        tmp_pnl.add(btn_ce, gbc);

        btn_c.setFont( new Font("Serief", Font.BOLD, 30) );
        btn_c.setBackground( Color.DARK_GRAY );
        btn_c.setForeground( Color.WHITE );
        gbc.gridx = 2;
        tmp_pnl.add(btn_c, gbc);

        btn_plus_or_minus.setFont( new Font("Serief", Font.BOLD, 30) );
        btn_plus_or_minus.setBackground( Color.LIGHT_GRAY );
        gbc.gridx = 3;
        tmp_pnl.add(btn_plus_or_minus, gbc);

        btn_squartroot.setFont( new Font("Serief", Font.BOLD, 30) );
        btn_squartroot.setBackground( Color.LIGHT_GRAY );
        gbc.gridx = 4;
        tmp_pnl.add(btn_squartroot, gbc);

        btn_7.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_7.setBackground( Color.WHITE );
        gbc.gridx = 0;
        gbc.gridy = 1;
        tmp_pnl.add(btn_7, gbc);

        btn_8.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_8.setBackground( Color.WHITE );
        gbc.gridx = 1;
        tmp_pnl.add(btn_8, gbc);

        btn_9.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_9.setBackground( Color.WHITE );
        gbc.gridx = 2;
        tmp_pnl.add(btn_9, gbc);

        btn_divide.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_divide.setBackground( Color.ORANGE );
        gbc.gridx = 3;
        tmp_pnl.add(btn_divide, gbc);

        btn_percent.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_percent.setBackground( Color.LIGHT_GRAY );
        gbc.gridx = 4;
        tmp_pnl.add(btn_percent, gbc);

        btn_4.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_4.setBackground( Color.WHITE );
        gbc.gridx = 0;
        gbc.gridy = 2;
        tmp_pnl.add(btn_4, gbc);

        btn_5.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_5.setBackground( Color.WHITE );
        gbc.gridx = 1;
        tmp_pnl.add(btn_5, gbc);

        btn_6.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_6.setBackground( Color.WHITE );
        gbc.gridx = 2;
        tmp_pnl.add(btn_6, gbc);

        btn_multiply.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_multiply.setBackground( Color.ORANGE );
        gbc.gridx = 3;
        tmp_pnl.add(btn_multiply, gbc);

        btn_reciprocal.setFont( new Font("Serief", Font.BOLD, 20) );
        btn_reciprocal.setBackground( Color.LIGHT_GRAY );
        gbc.gridx = 4;
        tmp_pnl.add(btn_reciprocal, gbc);

        btn_1.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_1.setBackground( Color.WHITE );
        gbc.gridx = 0;
        gbc.gridy = 3;
        tmp_pnl.add(btn_1, gbc);

        btn_2.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_2.setBackground( Color.WHITE );
        gbc.gridx = 1;
        tmp_pnl.add(btn_2, gbc);

        btn_3.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_3.setBackground( Color.WHITE );
        gbc.gridx = 2;
        tmp_pnl.add(btn_3, gbc);

        btn_minus.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_minus.setBackground( Color.ORANGE );
        gbc.gridx = 3;
        tmp_pnl.add(btn_minus, gbc);

        btn_equal.setFont( new Font("Serief", Font.BOLD, 50) );
        btn_equal.setBackground( new Color(100, 150, 255) );
        btn_equal.setForeground( Color.BLACK);
        gbc.gridx = 4;
        gbc.gridheight = 2;
        tmp_pnl.add(btn_equal, gbc);

        btn_0.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_0.setBackground( Color.WHITE );
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        tmp_pnl.add(btn_0, gbc);

        btn_point.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_point.setBackground( Color.WHITE );
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        tmp_pnl.add(btn_point, gbc);

        btn_plus.setFont( new Font("Serief", Font.BOLD, 35) );
        btn_plus.setBackground( Color.ORANGE );
        gbc.gridx = 3;
        tmp_pnl.add(btn_plus, gbc);
	}// end of addButtonsToPanel(Panel tmp_pnl)

	private static void showResult(String str)//顯示出運算結果，捨去尾數的小數值，處理溢位
	{
		try
		{
			if ( str.contains(".") )//計算結果包含小數的情況
			{
				if ( str.contains("-") )//小數且包含負號的情況，最多只能顯示11位數
				{
					if ( str.contains("E")  && str.length() > 11 )//有指數的情況，取最前面的九位數字加上最後面的指數部分
						lab.setText( str.substring(0, 9) + str.substring( str.length()-2, str.length() ) );
					else if ( str.length() > 11 )//有小數點，有負數，沒有指數
						lab.setText( str.substring(0, 11) );
					else
						lab.setText(str);
				}
				else//有小數點但是沒有負號的情況
				{
					if ( str.contains("E")  && str.length() > 10 )//有指數沒正負號的情況，取最前面的八位數字加上最後面的指數部分
						lab.setText( str.substring(0, 8) + str.substring( str.length()-2, str.length() ) );
					else if ( str.length() > 10 )//沒負號，沒指數，只有小數點
						lab.setText( str.substring(0, 10) );
					else//未溢位的情況
						lab.setText(str);
				}
			}
			else//計算結果為整數的情況
			{
				if ( ( str.contains("-") && str.length() > 10 ) || ( !str.contains("-") && str.length() > 9 ) )//有負號的情況，超出十位數算溢位；無負號的情況，超出九位數算溢位
					lab.setText( "Overflow" );
				else//未溢位的情況
					lab.setText(str);
			}

			str = lab.getText();

			while ( str.contains(".") && str.endsWith("0") )//除去小數點後面的0
			{
				StringBuffer sb = new StringBuffer( str );//因為必須要用delete();
				sb.deleteCharAt( sb.length()-1 );
				str = sb.toString();
				lab.setText(str);
			}
			if ( str.endsWith(".") )//如果小數點後面已經沒有數字，除去小數點
			{
				StringBuffer sb = new StringBuffer( str );//因為必須要用delete();
				sb.deleteCharAt( sb.length()-1 );
				str = sb.toString();
				lab.setText(str);
			}
		}
		catch (StringIndexOutOfBoundsException ex)
		{
			lab.setText("Overflow");
		}
	}// end of showResult(String str)

	static class CNumInput implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Button btn = (Button)e.getSource();
			StringBuffer strbuf = new StringBuffer( lab.getText() );//因為必須使用到append()
			operater_is_just_pressed = false;//避免連續按下兩次運算子會出現計算結果

			if (
					( lab.getText().length() < 9 )//一般的情況，輸入最多只能接受九個字元
					||
					( lab.getText().length() < 10 ) && ( lab.getText().contains(".") )//有小數點的情況，輸入最多接受10個字元(九位數字加上一位小數點)
					||
					(reinput == true)//當第一運算元已經使用所有的位數時，可以輸入第二運算元
				)//輸入沒有溢位的情況
			{
				if (reinput == true)//輸入需要歸零的情況(比如已經按下運算子)，處理輸入的第一個數字
				{
					if ( btn.getLabel() == "." )//第一個按鍵是小數點的情況
						lab.setText("0.");
					else//如果第一個按鍵是0~9任何一個數字，直接覆蓋顯示區內容
						lab.setText( btn.getLabel() );
					reinput = false;//按下運算子之後，只要按下任意數字輸入按鍵，reset旗標就關閉
				}
				else
				{
					if ( lab.getText().contains(".") && (btn.getLabel() == ".") )//限制輸入數字不可以有兩個小數點(輸入數字含小數點的，"."按鈕不做事)
						;
					else if ( (lab.getText() == "0") && ( btn.getLabel() != "." ) )//(未輸入數字或者輸入0的情況)，除了小數點以外的10個數字，都用取代功能改寫標籤
						lab.setText( btn.getLabel() );
					else//第一位數字不是零的情況
						lab.setText( strbuf.append( btn.getLabel() ).toString() );//目前數字不為0，包含小數點在內的所有數字，都用附加功能改寫標籤
				}
			}
		}// end of actionPerformed(ActionEvent e)
	}// end of class CNumInput

	static class CNumCorrection implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Button btn = (Button)e.getSource();
			StringBuffer strbuf = new StringBuffer( lab.getText() );

			switch ( btn.getName().charAt(6) )
			{
			case '0' ://CE鍵
				lab.setText("0");
				break;
			case '1' ://C鍵
				lab.setText("0");
				reinput = true;
				float_mode = false;
				oprand_1_is_set = false;
				operater_is_just_pressed = true;
				op =' ';
				oprand_double_temp = 0;
				oprand_double_1 = 0;
				oprand_double_2 = 0;
				oprand_int_temp = 0;
				oprand_int_1 = 0;
				oprand_int_2 = 0;
				break;
			case '2' ://倒退鍵
				if ( lab.getText().length() == 1)
				{
					lab.setText("0");
					break;
				}
				strbuf.deleteCharAt( strbuf.length()-1 );
				lab.setText( strbuf.toString() );
			}
		}// end of actionPerformed(ActionEvent e)
	}// end of class CNumCorrection

	static class CUnaryOperation implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//計算分成三個步驟: 1.取值 2.計算 3.輸出結果

			Button btn = (Button)e.getSource();
			String op_str = lab.getText();
			reinput = true;

			if (op_str  == "NaN")//計算的輸入不是數字，不做計算
				lab.setText("NaN");
			else//輸入區是數字，才做計算
			{
				if ( op_str.contains(".") || ( oprand_double_1 != 0 ) )//顯示區的數字是小數的情況
				{
					//1.取值區塊
					try
					{
						oprand_double_temp = Double.parseDouble(op_str);
					}
					catch (NumberFormatException ex)
					{
						op_str = "NaN";
					}

					//2.計算區塊
					switch ( btn.getLabel().charAt(0) )
					{
					case '\u00b1'://正負號處理
						oprand_double_temp = -oprand_double_temp;
						op_str = Double.toString(oprand_double_temp);
						break;
					case '\u221a'://平方根處理
						if (op_str == "NaN")//因為還是有可能有非NaN的非法字串進入此區塊，因此必須再做一次檢查
							;
						else
							op_str = Double.toString( Math.sqrt(oprand_double_temp) );
						break;
					case '%'://百分比處理
						if (op_str == "NaN")
							;
						else if ( op == '\u00d7' )//之前的運算是乘法的情況才做百分比處理
						{
							op_str = Double.toString(oprand_double_1 *= ( oprand_double_temp / 100.0) );
							op = ' ';
							oprand_1_is_set = false;
							operater_is_just_pressed = true;
						}
						else
							op_str ="0";
						break;
					case '1'://倒數處理
						if (op_str == "NaN")
							;
						else
							op_str = Double.toString( 1 / oprand_double_temp );
					}
				}
				else //不是小數的情況
				{
					//1.取值區塊
					oprand_int_temp = Integer.parseInt( op_str );

					//2.計算區塊
					switch ( btn.getLabel().charAt(0) )
					{
					case '\u00b1'://正負號處理
						oprand_int_temp = -oprand_int_temp;
						op_str = Integer.toString(oprand_int_temp);
						break;
					case '\u221a'://平方根處理
						op_str = Double.toString( Math.sqrt( (double)oprand_int_temp) );
						break;
					case '%'://百分比處理
						if ( op == '\u00d7' )//之前的運算是乘法的情況才做百分比處理
						{
							op_str = Double.toString(oprand_int_1 *= ( oprand_int_temp / (double)100.0) );
							op = ' ';
							oprand_1_is_set = false;
							operater_is_just_pressed = true;
						}
						else
							op_str ="0";
						break;
					case '1'://倒數處理
						op_str = Double.toString( 1 / (double)oprand_int_temp );
					}
				}
				//3.輸出結果
				showResult(op_str);
			}
		}// end of actionPerformed(ActionEvent e)
	}// end of class CUnaryOperation

	static class CBinaryOperation implements ActionListener
	{
		//計算分成三個步驟: 1.取值 2.計算 3.輸出結果

		public void actionPerformed(ActionEvent e)
		{
			Button btn = (Button)e.getSource();
			String op_str = lab.getText();
			reinput = true;

			if ( op_str.contains(".") )
				float_mode = true;

			//1.取值區塊
			if (float_mode == true)//兩個運算元之一含有小數的情況
			{
				if (oprand_1_is_set == false)//如果還沒湊滿兩個運算元，只記錄第一運算元，不做運算
				{
					oprand_double_1 = Double.parseDouble(op_str);
					oprand_1_is_set = true;
				}
				else if (operater_is_just_pressed == false)//只要沒有輸入第二個運算元，不做運算
				{
					oprand_double_2 = Double.parseDouble(op_str);

					//2.運算區塊
					switch (op)//不論按下任何一個二元運算子，先計算出上一個運算子的結果
					{
					case '+':
						op_str = Double.toString( oprand_double_1 += oprand_double_2 );
						break;
					case '-':
						op_str = Double.toString( oprand_double_1 -= oprand_double_2);
						break;
					case '\u00d7'://乘號
						op_str = Double.toString( oprand_double_1 *= oprand_double_2 );
						break;
					case '\u00f7'://除號，做除以0的例外處理，但是似乎沒有用
						try
						{
							op_str = Double.toString( oprand_double_1 /= oprand_double_2 );
						}
						catch (ArithmeticException ex)
						{
							lab.setText("Error");
						}
					}
				}
			}
			else//不含小數的情況
			{
				//1.取值區塊
				if (oprand_1_is_set == false)//如果還沒湊滿兩個運算元，只記錄第一運算元，不做運算
				{
					oprand_int_1 = Integer.parseInt(op_str);
					oprand_1_is_set = true;
				}
				else if (operater_is_just_pressed == false)//只要沒有輸入第二個運算元，不做運算
				{
					oprand_int_2 = Integer.parseInt(op_str);

					//2.運算區塊
					switch (op)//不論按下任何一個二元運算子，先計算出上一個運算子的結果
					{
					case '+':
						op_str = Integer.toString( oprand_int_1 += oprand_int_2 );
						break;
					case '-':
						op_str = Integer.toString( oprand_int_1 -= oprand_int_2 );
						break;
					case '\u00d7': //乘號
						op_str = Integer.toString( oprand_int_1 *= oprand_int_2 );
						break;
					case '\u00f7': //除號
						try
						{
							if ( (oprand_int_1 % oprand_int_2) != 0 )//不能整除的情況，切換為小數運算模式
							{
								float_mode = true;
								oprand_double_1 = oprand_int_1 / (double)oprand_int_2;
								op_str = Double.toString(oprand_double_1);
							}
							else
								op_str = Integer.toString(oprand_int_1 /= oprand_int_2);
						}
						catch (ArithmeticException ex)//做除以0的例外處理
						{
							op_str = "Error";
						}
					}
				}
				oprand_double_1 = oprand_int_1;//每次做完整數的運算，結果會複製一份到小數版的第一運算元
			}

			if (btn.getLabel().charAt(0) != '=')
				op = btn.getLabel().charAt(0);
			operater_is_just_pressed = true;//如果使用者按錯二元運算子(包含等號在內)，加上此行可以修正

			//3.輸出結果區塊
			showResult(op_str);
		}// end of actionPerformed(ActionEvent e)
	}// end of class CBinaryOperation
}// end of class MyCalculatorDemo