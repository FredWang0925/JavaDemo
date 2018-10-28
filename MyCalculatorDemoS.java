import java.awt.*;
import java.awt.event.*;

public class MyCalculatorDemoS
{
	private static Frame frm = new Frame("�ۻs�p��L");
	private static Label lab = new Label("0", Label.RIGHT);
	private static Panel pnl = new Panel();
	private static Button btn_backspace = new Button("\u2192");//���V���誺�b�Y
	private static Button btn_ce = new Button("CE");
	private static Button btn_c = new Button("C");
	private static Button btn_plus_or_minus = new Button("\u00b1");//���t���лx
	private static Button btn_squartroot = new Button("\u221a");//����ڸ�
	private static Button btn_7 = new Button("7");
	private static Button btn_8 = new Button("8");
	private static Button btn_9 = new Button("9");
	private static Button btn_divide = new Button("\u00f7");//��g����
	private static Button btn_percent = new Button("%");
	private static Button btn_4 = new Button("4");
	private static Button btn_5 = new Button("5");
	private static Button btn_6 = new Button("6");
	private static Button btn_multiply = new Button("\u00d7");//��g����
	private static Button btn_reciprocal = new Button("1/x");
	private static Button btn_1 = new Button("1");
	private static Button btn_2 = new Button("2");
	private static Button btn_3 = new Button("3");
	private static Button btn_minus = new Button("-");
	private static Button btn_equal = new Button("=");
	private static Button btn_0 = new Button("0");
	private static Button btn_point = new Button(".");
	private static Button btn_plus = new Button("+");
	//�H�W�O�����~�[����������
	private static boolean reinput = true;//�ˬd�ثe�O���O�ݭn���s��J�Ʀr
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
	//�H�W�O�p��֤ߪ�����

	public static void main(String args[])
	{
		frm.setBounds(144, 81, 480, 655);//�o�O�p����϶��һݭn�Ϊ������j�p�A�����j�p�٬O1440 x 810
		frm.setBackground(Color.CYAN);
		frm.setResizable(false); //�o�@�檺���L�|�v�T�����\�񪺦�m
		frm.setVisible(true);
		frm.setLayout(null);

		lab.setBounds(20, 60, 440, 100); //���D�C���פj���O 50 pixels
		lab.setFont( new Font("Serief", Font.PLAIN, 76) );//�]�w��76���r���j�p�A�N�i�H�b���󱡪p�����|���X��ܰ�
		lab.setBackground( new Color(100, 150, 255) );
		lab.setForeground(Color.BLACK);

		pnl.setBounds(20, 170, 440, 465);

		addButtonsToPanel(pnl);

		btn_ce.setName("button0"); //�]������W�����Ҹ����A�ҥH���R�W�n�H�U�T�ӯS�w���s�A��K��switch�j��B�z
		btn_c.setName("button1");
		btn_backspace.setName("button2");

		frm.add(lab);
		frm.add(pnl);//�o�@����G�n�b�̫�[�W�h�A�����~���|�ñ��C

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
		);//�����������s�һݭn���{���X
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

	private static void showResult(String str)//��ܥX�B�⵲�G�A�˥h���ƪ��p�ƭȡA�B�z����
	{
		try
		{
			if ( str.contains(".") )//�p�⵲�G�]�t�p�ƪ����p
			{
				if ( str.contains("-") )//�p�ƥB�]�t�t�������p�A�̦h�u�����11���
				{
					if ( str.contains("E")  && str.length() > 11 )//�����ƪ����p�A���̫e�����E��Ʀr�[�W�̫᭱�����Ƴ���
						lab.setText( str.substring(0, 9) + str.substring( str.length()-2, str.length() ) );
					else if ( str.length() > 11 )//���p���I�A���t�ơA�S������
						lab.setText( str.substring(0, 11) );
					else
						lab.setText(str);
				}
				else//���p���I���O�S���t�������p
				{
					if ( str.contains("E")  && str.length() > 10 )//�����ƨS���t�������p�A���̫e�����K��Ʀr�[�W�̫᭱�����Ƴ���
						lab.setText( str.substring(0, 8) + str.substring( str.length()-2, str.length() ) );
					else if ( str.length() > 10 )//�S�t���A�S���ơA�u���p���I
						lab.setText( str.substring(0, 10) );
					else//�����쪺���p
						lab.setText(str);
				}
			}
			else//�p�⵲�G����ƪ����p
			{
				if ( ( str.contains("-") && str.length() > 10 ) || ( !str.contains("-") && str.length() > 9 ) )//���t�������p�A�W�X�Q��ƺⷸ��F�L�t�������p�A�W�X�E��ƺⷸ��
					lab.setText( "Overflow" );
				else//�����쪺���p
					lab.setText(str);
			}

			str = lab.getText();

			while ( str.contains(".") && str.endsWith("0") )//���h�p���I�᭱��0
			{
				StringBuffer sb = new StringBuffer( str );//�]�������n��delete();
				sb.deleteCharAt( sb.length()-1 );
				str = sb.toString();
				lab.setText(str);
			}
			if ( str.endsWith(".") )//�p�G�p���I�᭱�w�g�S���Ʀr�A���h�p���I
			{
				StringBuffer sb = new StringBuffer( str );//�]�������n��delete();
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
			StringBuffer strbuf = new StringBuffer( lab.getText() );//�]�������ϥΨ�append()
			operater_is_just_pressed = false;//�קK�s����U�⦸�B��l�|�X�{�p�⵲�G

			if (
					( lab.getText().length() < 9 )//�@�몺���p�A��J�̦h�u�౵���E�Ӧr��
					||
					( lab.getText().length() < 10 ) && ( lab.getText().contains(".") )//���p���I�����p�A��J�̦h����10�Ӧr��(�E��Ʀr�[�W�@��p���I)
					||
					(reinput == true)//��Ĥ@�B�⤸�w�g�ϥΩҦ�����ƮɡA�i�H��J�ĤG�B�⤸
				)//��J�S�����쪺���p
			{
				if (reinput == true)//��J�ݭn�k�s�����p(��p�w�g���U�B��l)�A�B�z��J���Ĥ@�ӼƦr
				{
					if ( btn.getLabel() == "." )//�Ĥ@�ӫ���O�p���I�����p
						lab.setText("0.");
					else//�p�G�Ĥ@�ӫ���O0~9����@�ӼƦr�A�����л\��ܰϤ��e
						lab.setText( btn.getLabel() );
					reinput = false;//���U�B��l����A�u�n���U���N�Ʀr��J����Areset�X�дN����
				}
				else
				{
					if ( lab.getText().contains(".") && (btn.getLabel() == ".") )//�����J�Ʀr���i�H����Ӥp���I(��J�Ʀr�t�p���I���A"."���s������)
						;
					else if ( (lab.getText() == "0") && ( btn.getLabel() != "." ) )//(����J�Ʀr�Ϊ̿�J0�����p)�A���F�p���I�H�~��10�ӼƦr�A���Ψ��N�\���g����
						lab.setText( btn.getLabel() );
					else//�Ĥ@��Ʀr���O�s�����p
						lab.setText( strbuf.append( btn.getLabel() ).toString() );//�ثe�Ʀr����0�A�]�t�p���I�b�����Ҧ��Ʀr�A���Ϊ��[�\���g����
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
			case '0' ://CE��
				lab.setText("0");
				break;
			case '1' ://C��
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
			case '2' ://�˰h��
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
			//�p������T�ӨB�J: 1.���� 2.�p�� 3.��X���G

			Button btn = (Button)e.getSource();
			String op_str = lab.getText();
			reinput = true;

			if (op_str  == "NaN")//�p�⪺��J���O�Ʀr�A�����p��
				lab.setText("NaN");
			else//��J�ϬO�Ʀr�A�~���p��
			{
				if ( op_str.contains(".") || ( oprand_double_1 != 0 ) )//��ܰϪ��Ʀr�O�p�ƪ����p
				{
					//1.���Ȱ϶�
					try
					{
						oprand_double_temp = Double.parseDouble(op_str);
					}
					catch (NumberFormatException ex)
					{
						op_str = "NaN";
					}

					//2.�p��϶�
					switch ( btn.getLabel().charAt(0) )
					{
					case '\u00b1'://���t���B�z
						oprand_double_temp = -oprand_double_temp;
						op_str = Double.toString(oprand_double_temp);
						break;
					case '\u221a'://����ڳB�z
						if (op_str == "NaN")//�]���٬O���i�঳�DNaN���D�k�r��i�J���϶��A�]�������A���@���ˬd
							;
						else
							op_str = Double.toString( Math.sqrt(oprand_double_temp) );
						break;
					case '%'://�ʤ���B�z
						if (op_str == "NaN")
							;
						else if ( op == '\u00d7' )//���e���B��O���k�����p�~���ʤ���B�z
						{
							op_str = Double.toString(oprand_double_1 *= ( oprand_double_temp / 100.0) );
							op = ' ';
							oprand_1_is_set = false;
							operater_is_just_pressed = true;
						}
						else
							op_str ="0";
						break;
					case '1'://�˼ƳB�z
						if (op_str == "NaN")
							;
						else
							op_str = Double.toString( 1 / oprand_double_temp );
					}
				}
				else //���O�p�ƪ����p
				{
					//1.���Ȱ϶�
					oprand_int_temp = Integer.parseInt( op_str );

					//2.�p��϶�
					switch ( btn.getLabel().charAt(0) )
					{
					case '\u00b1'://���t���B�z
						oprand_int_temp = -oprand_int_temp;
						op_str = Integer.toString(oprand_int_temp);
						break;
					case '\u221a'://����ڳB�z
						op_str = Double.toString( Math.sqrt( (double)oprand_int_temp) );
						break;
					case '%'://�ʤ���B�z
						if ( op == '\u00d7' )//���e���B��O���k�����p�~���ʤ���B�z
						{
							op_str = Double.toString(oprand_int_1 *= ( oprand_int_temp / (double)100.0) );
							op = ' ';
							oprand_1_is_set = false;
							operater_is_just_pressed = true;
						}
						else
							op_str ="0";
						break;
					case '1'://�˼ƳB�z
						op_str = Double.toString( 1 / (double)oprand_int_temp );
					}
				}
				//3.��X���G
				showResult(op_str);
			}
		}// end of actionPerformed(ActionEvent e)
	}// end of class CUnaryOperation

	static class CBinaryOperation implements ActionListener
	{
		//�p������T�ӨB�J: 1.���� 2.�p�� 3.��X���G

		public void actionPerformed(ActionEvent e)
		{
			Button btn = (Button)e.getSource();
			String op_str = lab.getText();
			reinput = true;

			if ( op_str.contains(".") )
				float_mode = true;

			//1.���Ȱ϶�
			if (float_mode == true)//��ӹB�⤸���@�t���p�ƪ����p
			{
				if (oprand_1_is_set == false)//�p�G�٨S�꺡��ӹB�⤸�A�u�O���Ĥ@�B�⤸�A�����B��
				{
					oprand_double_1 = Double.parseDouble(op_str);
					oprand_1_is_set = true;
				}
				else if (operater_is_just_pressed == false)//�u�n�S����J�ĤG�ӹB�⤸�A�����B��
				{
					oprand_double_2 = Double.parseDouble(op_str);

					//2.�B��϶�
					switch (op)//���׫��U����@�ӤG���B��l�A���p��X�W�@�ӹB��l�����G
					{
					case '+':
						op_str = Double.toString( oprand_double_1 += oprand_double_2 );
						break;
					case '-':
						op_str = Double.toString( oprand_double_1 -= oprand_double_2);
						break;
					case '\u00d7'://����
						op_str = Double.toString( oprand_double_1 *= oprand_double_2 );
						break;
					case '\u00f7'://�����A�����H0���ҥ~�B�z�A���O���G�S����
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
			else//���t�p�ƪ����p
			{
				//1.���Ȱ϶�
				if (oprand_1_is_set == false)//�p�G�٨S�꺡��ӹB�⤸�A�u�O���Ĥ@�B�⤸�A�����B��
				{
					oprand_int_1 = Integer.parseInt(op_str);
					oprand_1_is_set = true;
				}
				else if (operater_is_just_pressed == false)//�u�n�S����J�ĤG�ӹB�⤸�A�����B��
				{
					oprand_int_2 = Integer.parseInt(op_str);

					//2.�B��϶�
					switch (op)//���׫��U����@�ӤG���B��l�A���p��X�W�@�ӹB��l�����G
					{
					case '+':
						op_str = Integer.toString( oprand_int_1 += oprand_int_2 );
						break;
					case '-':
						op_str = Integer.toString( oprand_int_1 -= oprand_int_2 );
						break;
					case '\u00d7': //����
						op_str = Integer.toString( oprand_int_1 *= oprand_int_2 );
						break;
					case '\u00f7': //����
						try
						{
							if ( (oprand_int_1 % oprand_int_2) != 0 )//����㰣�����p�A�������p�ƹB��Ҧ�
							{
								float_mode = true;
								oprand_double_1 = oprand_int_1 / (double)oprand_int_2;
								op_str = Double.toString(oprand_double_1);
							}
							else
								op_str = Integer.toString(oprand_int_1 /= oprand_int_2);
						}
						catch (ArithmeticException ex)//�����H0���ҥ~�B�z
						{
							op_str = "Error";
						}
					}
				}
				oprand_double_1 = oprand_int_1;//�C��������ƪ��B��A���G�|�ƻs�@����p�ƪ����Ĥ@�B�⤸
			}

			if (btn.getLabel().charAt(0) != '=')
				op = btn.getLabel().charAt(0);
			operater_is_just_pressed = true;//�p�G�ϥΪ̫����G���B��l(�]�t�����b��)�A�[�W����i�H�ץ�

			//3.��X���G�϶�
			showResult(op_str);
		}// end of actionPerformed(ActionEvent e)
	}// end of class CBinaryOperation
}// end of class MyCalculatorDemo