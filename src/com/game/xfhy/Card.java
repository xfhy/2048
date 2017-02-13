package com.game.xfhy;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


/*
 * ��label��ʾ��Ӧ�÷������background����Ӧ����ͼƬ��
�������ڹ��캯����ʼ�����������������Ӧ���֣������С���Լ���Ӧ�Ķ��뷽ʽ�ˣ�
                 ��Щ���������ݳ�ʼ�������Ķ����ˡ�
��������ݲ�ͬ��ֵ��Ƭ������ʾ��ͬ��ɫ�Ŀ�Ƭ�����������setnumber��ʵ�ֵġ�
��������Ӧ�Ŀ�Ƭ����,��һ�ֱ�Ȼ�����Ǿ������clone�������������Ӧֵ���ݡ�
������������������������ԭ�򣬾Ͱ�һ����Ƭ����ģ������ˡ�

     config�࣬��Ҫ��һЩ������Ϣ�����Ǽ�¼��ÿ�еĳ��ȣ��Ϳ�Ƭ�Ŀ�ȡ�
 * */
public class Card extends FrameLayout 
{

	private int num = 0;      //��Ƭ��ֵ
	private TextView label;   //��Ƭ��ֵ��ʾ
	private View background;  //��Ƭ
	
	public Card(Context context)
	{
		super(context);
		LayoutParams lp = null;    //һ�ſ�Ƭ����һ��LayoutParams

		background = new View(getContext());
		lp = new LayoutParams(-1, -1);
		lp.setMargins(10, 10, 0, 0);
		background.setBackgroundColor(0x33ffffff);
		addView(background, lp);              //����LayoutParams����ӱ���

		label = new TextView(getContext());   //��ʼ���ı��ؼ�
		label.setTextSize(20);                //�ı��ؼ���С:28
		label.setGravity(Gravity.CENTER);     //�ı������м�
		//TextPaint tp = label.getPaint(); 
		//tp.setFakeBoldText(true); 


		lp = new LayoutParams(-1, -1);        //�����������������
		lp.setMargins(10, 10, 0, 0);          //����,����ı߾�
		addView(label, lp);                   //����LayoutParams����ı��ؼ�

		setNum(0);                            //���ʱ,��Ƭ�ϵ���ֵ��0,����ʾ�ı�
	}
    
	//��ȡ��Card����ֵ
	public int getNum() 
	{
		return num;
	}
	
	//���ø�Card����ֵ
	public void setNum(int num) 
	{
		this.num = num;
		if( num<=0 )
		{
			label.setText("");
		}
		else
		{
			//������������ǵ�һ��int����,�������""��,�ͱ���ַ�����,�Ϳ�����
			//label.setText(num+""); 
			
			if(Config.type==0)    //  �����2048
			{
				label.setText(num+""); 
			}
			else if(Config.type==1)    //  ���°�2048
			{
			    switch(num)
			    {
				    case 0:
				    	label.setText(""); 
				    	break;
				    case 2:
				    	label.setText("��ڨ"); 
				    	break;
				    case 4:
				    	label.setText("����"); 
				    	break;
				    case 8:
				    	label.setText("Լ��"); 
				    	break;
				    case 16:
				    	label.setText("���"); 
				    	break;
				    case 32:
				    	label.setText("����"); 
				    	break;
				    case 64:
				    	label.setText("ǣ��"); 
				    	break;
				    case 128:
				    	label.setText("ӵ��"); 
				    	break;
				    case 256:
				    	label.setText("����"); 
				    	break;
				    case 512:
				    	label.setText("XXOO"); 
				    	break;
				    case 1024:
				    	label.setText("���"); 
				    	break;
				    case 2048:
				    	label.setText("С����"); 
				    	break;
				    case 4096:
				    	label.setText("�����ĭ"); 
				    	break;
			    }
			}
			else if(Config.type==2) //�����
			{
				 switch(num)
				 {
					 case 0:
				    	label.setText(""); 
				    	break;
					 case 2048:
				    	label.setText("2048"); 
				    	break;
					 case 1024:
				    	label.setText("1024"); 
				    	break;
					 case 512:
				    	label.setText("512"); 
				    	break;
					 case 256:
				    	label.setText("256"); 
				    	break;
					 case 128:
				    	label.setText("128"); 
				    	break;
					 case 64:
				    	label.setText("64"); 
				    	break;
					 case 32:
				    	label.setText("32"); 
				    	break;
					 case 16:
				    	label.setText("16"); 
				    	break;
					 case 8:
				    	label.setText("8"); 
				    	break;
					 case 4:
				    	label.setText("4"); 
				    	break;
					 case 2:
				    	label.setText("2"); 
				    	break;
				 }
			}
			else if(Config.type==3)   //ɥ����
			{
				label.setText(num+""); 
			}
			else if(Config.type==4)  //������
			{
				switch(num)
			    {
				    case 0:
				    	label.setText(""); 
				    	break;
				    case 2:
				    	label.setText("��"); 
				    	break;
				    case 4:
				    	label.setText("��"); 
				    	break;
				    case 8:
				    	label.setText("��"); 
				    	break;
				    case 16:
				    	label.setText("��"); 
				    	break;
				    case 32:
				    	label.setText("��"); 
				    	break;
				    case 64:
				    	label.setText("κ"); 
				    	break;
				    case 128:
				    	label.setText("��"); 
				    	break;
				    case 256:
				    	label.setText("�ϱ�"); 
				    	break;
				    case 512:
				    	label.setText("��"); 
				    	break;
				    case 1024:
				    	label.setText("��"); 
				    	break;
				    case 2048:
				    	label.setText("��"); 
				    	break;
				    case 4096:
				    	label.setText("Ԫ"); 
				    	break;
			    }
		    }
			else if(Config.type==5)
			{
				/*
				 * ���ұ����켺�����ɹ��ӳ���î������δ�����纥Ϊ��ɵ�֧
				 * */
				switch(num)
			    {
				    case 0:
				    	label.setText(""); 
				    	break;
				    case 2:
				    	label.setText("��"); 
				    	break;
				    case 4:
				    	label.setText("��"); 
				    	break;
				    case 8:
				    	label.setText("��"); 
				    	break;
				    case 16:
				    	label.setText("��"); 
				    	break;
				    case 32:
				    	label.setText("��"); 
				    	break;
				    case 64:
				    	label.setText("��"); 
				    	break;
				    case 128:
				    	label.setText("��"); 
				    	break;
				    case 256:
				    	label.setText("��"); 
				    	break;
				    case 512:
				    	label.setText("��"); 
				    	break;
				    case 1024:
				    	label.setText("��"); 
				    	break;
				    case 2048:
				    	label.setText("��"); 
				    	break;
				    case 4096:
				    	label.setText("��"); 
				    	break;
			    }
			}
		
	   }
		
		
		//���ò�ͬ������ɫ
		switch (num) 
		{
			case 0:
				//label.setBackgroundColor(0x33ffffff);
				label.setBackgroundColor(0x00000000);
				break;
			case 2:
				label.setBackgroundColor(0xffeee4da);
				break;
			case 4:
				label.setBackgroundColor(0xffede0c8);
				break;
			case 8:
				label.setBackgroundColor(0xfff2b179);
				break;
			case 16:
				label.setBackgroundColor(0xfff59563);
				break;
			case 32:
				label.setBackgroundColor(0xfff67c5f);
				break;
			case 64:
				label.setBackgroundColor(0xfff65e3b);
				break;
			case 128:
				label.setBackgroundColor(0xffedcf72);
				break;
			case 256:
				label.setBackgroundColor(0xffedcc61);
				break;
			case 512:
				label.setBackgroundColor(0xffedc850);
				break;
			case 1024:
				label.setBackgroundColor(0xffedc53f);
				break;
			case 2048:
				label.setBackgroundColor(0xffedc22e);
				break;
			case 4096:
				label.setBackgroundColor(0xff3c3a32);
				break;
			default:
				label.setBackgroundColor(0xff3c3a32);
				break;
		}
		
	}
	
	//�ж�2�ſ�Ƭ�Ƿ���ͬ
	public boolean equals(Card o) {
		// TODO Auto-generated method stub
		return getNum()==o.num;
	}
	
	//��Ƭ��¡(����)
	protected Card clone(){
		Card c= new Card(getContext());
		c.setNum(getNum());
		return c;
	}

	//��ȡ��Ƭ�ϵ�TextView�ؼ�
	public TextView getLabel() {
		return label;
	}
}
