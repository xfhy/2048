package com.game.xfhy;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class GameView extends GridLayout{

	private Card[][] cardsMap = new Card[Config.LINES][Config.LINES];  //��Ƭ����,��¼��Ƭ
	
	//Point java�Լ�����,��ʾ (x,y) ����ռ��е�λ�õĵ㣬����������ָ����
	//List List ���Ϊ�û��ṩ��һ���ɹ������ı����б������ô� list��ʹ�������û����е�������ѡ��
	//ArrayList List �ӿڵĴ�С�ɱ������ʵ�֡�
	private List<Point> emptyPoints = new ArrayList<Point>();  //�յĵ�
	/*
	 * Ĭ�Ϲ��췽��
	 * @param context
	 */
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
	}
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initGameView();
	}
	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		initGameView();
	}
	
	//���ܴ�ʲô���췽������,����ִ���������
	private void initGameView()
	{
		setOrientation(LinearLayout.VERTICAL);
		setColumnCount(Config.LINES);               //��������
		
		//������Ϸ������ɫ,ȥ��ҳ����2048�����Ԫ�ؼ���֪��ԭ����Ϸ��ɫ
		setBackgroundColor(0xffbbada0);  
		
		//���ü�����,���û�������Ļʱ,���ô������¼�����,�ж��û���Ҫ�ķ���
		//˼·:�Ȼ�ȡ����ʱ������,�ٻ�ȡ�뿪��Ļʱ������,ͨ������Ƚ�,�����ж��û���Ҫ�ķ���
		setOnTouchListener(new View.OnTouchListener(){

			//������ʼ����,ƫ����
			private float startX,startY,offsetX,offsetY;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						startX = event.getX();   //������Ļʱ������
						startY = event.getY();
						break;
					case MotionEvent.ACTION_UP:
						offsetX = event.getX()-startX;
						offsetY = event.getY()-startY;
						
						//���X�����ϵ�ƫ������Y�����ϵ�ƫ������,��˵���û���Ҫ����ˮƽ�����ϵĸı�
						if( Math.abs(offsetX)>Math.abs(offsetY) )
						{
							if( offsetX<=-5 )
							{
								System.out.println("��");
								swipeLeft();
							}
							else if(offsetX>=5)
							{
								System.out.println("��");
								swipeRight();
							}
						}
						else
						{
							if( offsetY<=-5 )
							{
								System.out.println("��");
								swipeUp();
							}
							else if(offsetY>=5)
							{
								System.out.println("��");
								swipeDown();
							}
						}
						break;
				}
				return true;      //������Ҫ����true,����ϵͳ����ִ�к����¼�
			}
			
		});
	}
	
	/**
	 * ����Ļ�����ı�ʱ,����
	 * ��ʱ��ҪȥAndroidManifest.xml  activity�����һ��
	 * android:screenOrientation="portrait"  ��ʾ��Ļ��������ֱ
	 * �����������ֻ�ᱻִ��һ��,�����ڵ�һ�δ���ʱ
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		//ÿ�ſ�Ƭ�Ŀ��
		Config.CARD_WIDTH = (Math.min(w, h)-10)/Config.LINES;
		
		//������еĿ�Ƭ����Ļ��
		addCards(Config.CARD_WIDTH,Config.CARD_WIDTH);
		
		//��ʼ��Ϸ
		startGame();
	}
	
	//��ӿ�Ƭ
	private void addCards(int cardWidth,int cardHeight)
	{
		Card c;
		LinearLayout line;
		LinearLayout.LayoutParams lineLp;
		//��Ƭ��4��,4��
		for (int y = 0; y < Config.LINES; y++) {
			line = new LinearLayout(getContext());
			lineLp = new LinearLayout.LayoutParams(-1, cardHeight);
			addView(line, lineLp);
			for (int x = 0; x < Config.LINES; x++) {
				c = new Card(getContext());
				line.addView(c, cardWidth, cardHeight);

				cardsMap[x][y] = c;
			}
		}
	}
	
	//��ʼ��Ϸ
	void startGame()
	{
		//����Ϸ��ʼʱ,��Ҫ����������
		SecondActivity aty = SecondActivity.getsecondActivity();
		aty.clearScore();                         //��������
		aty.showBestScore(aty.getBestScore());    //��ʾ��߷�
		
		
		//����Ϸ��ʼʱ,������Ϸ���¿�ʼʱ,��Ҫ���½���Ƭ��0(��ʼ״̬)
		for (int x = 0; x < Config.LINES; x++) {
			for (int y = 0; y < Config.LINES; y++) {
				cardsMap[x][y].setNum(0);
			}
		}
		
		addRandomNum();
		addRandomNum();
	}
	
	//��ӿ�Ƭ(�����)
	private void addRandomNum()
	{
		emptyPoints.clear();    //�Ƴ����б�(���еĿտ�Ƭ�ļ�������)�е�����Ԫ�ء�
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				//�����ǰ��Ƭû������,ֵΪ������Բ�������
				if( cardsMap[x][y].getNum()<=0 )   
				{
					//�����ǰ��Ƭ����������ӵ� �տ�Ƭ����
					emptyPoints.add(new Point(x,y));   
				}
			}
		}
		if (emptyPoints.size()>0) {
			//Math.random():��ʾ����һ��0~1֮��������(С��)
			//�ڿ����������ѡ��һ���� ��Щ����Ҫ�����������
			Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
			
			if(Config.type==2)
			{
				cardsMap[p.x][p.y].setNum(Math.random()>0.1? 2048:1024);
			}
			else
			{
				//�ڿ�Ƭ�������ҵ��ո��ҵ����Ǹ���,�������2����4,2:4=9:1,����2�ĸ��ʱ�4��,
				cardsMap[p.x][p.y].setNum(Math.random()>0.1? 2:4);
			}
			
			//��ӿ�Ƭʱ,��һ����̬������Ч��
			SecondActivity.getsecondActivity().getAnimLayer().createScaleTo1(cardsMap[p.x][p.y]);
		}
	}
	
	//���û����󻬶�,��Ҫ�ɵ�����
	private void swipeLeft()
	{
		//��¼��Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		boolean swipeTrue = false;
		
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				
				//��ǰ�еĵ�ǰλ�õ���һ��Ԫ�ؿ�ʼ,���ұ���
				for (int x1 = x+1; x1 < Config.LINES; x1++) {
					//�����һ����Ƭ��ֵ
					if(cardsMap[x1][y].getNum()>0)
					{
						//��ǰλ�õĿ�Ƭ<=0(Ϊ��),����һ����Ƭ�ƹ���
						if(cardsMap[x][y].getNum()<=0)
						{
							
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x1][y],cardsMap[x][y], x1, x, y, y);
							//����һ����Ƭ�ƹ���
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							//�Ѹո��ұ��ƹ����Ǹ�������Ϊ0
							cardsMap[x1][y].setNum(0);
							
							//a:0111
							//b:1110
							//��ǰλ���ǿ�ʱ,�ұ߿�Ƭ�����ƹ���,���������x--(������ѭ��һ����һ��)��û�п��ǵ������a->b�����
							x--;  
							swipeTrue = true;
						}
						//��ǰλ�����ұ�λ�õĿ�Ƭ���
						else if(cardsMap[x][y].equals(cardsMap[x1][y]))
						{
							//����Ч��
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x1][y], cardsMap[x][y],x1, x, y, y);
							
							if(Config.type==2)  //�����͵Ĳ�ͬ�Ʒ�
							{
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()/2);
								cardsMap[x1][y].setNum(0);
								
								//SecondActivity.getsecondActivity().addScore(cardsMap[x][y].getNum());
								switch(cardsMap[x][y].getNum())
								 {
									 case 1024:
										 SecondActivity.getsecondActivity().addScore(2);
								    	break;
									 case 512:
										 SecondActivity.getsecondActivity().addScore(4);
								    	break;
									 case 256:
										 SecondActivity.getsecondActivity().addScore(8);
								    	break;
									 case 128:
										 SecondActivity.getsecondActivity().addScore(16);
								    	break;
									 case 64:
										 SecondActivity.getsecondActivity().addScore(32);
								    	break;
									 case 32:
										 SecondActivity.getsecondActivity().addScore(64); 
								    	break;
									 case 16:
										 SecondActivity.getsecondActivity().addScore(128);
								    	break;
									 case 8:
										 SecondActivity.getsecondActivity().addScore(256);
								    	break;
									 case 4:
										 SecondActivity.getsecondActivity().addScore(512);
								    	break;
									 case 2:
										 SecondActivity.getsecondActivity().addScore(1024);
								    	break;
								 }
								swipeTrue = true;
							}
							else  //��ͨ�ͼƷ�
							{
								//�����ֱ�ӽ���ǰλ�õ���ֵ*2,Ȼ���ұ����ſ�Ƭ��Ϊ0
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x1][y].setNum(0);

								//���ӷ���,�ϲ����Ƕ�������Ӷ��ٷ�
								SecondActivity.getsecondActivity().addScore(cardsMap[x][y].getNum());
								swipeTrue = true;
							}
						}
						break;
					}
				}
			}
		}
		
		//�жϿ�Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		if( swipeTrue == true )
		{
		   addRandomNum();   //����һ��,���һ�������
		   checkGameOver();  //ÿ�����������,��Ҫ�ж��Ƿ�ﵽ��Ϸ������״̬
		}
	}
	
	private void swipeRight()
	{
		//��¼��Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		boolean swipeTrue = false;
		
		for (int y = 0; y < Config.LINES; y++) {   //y��û��
			for (int x=Config.LINES-1; x>=0; x--) {  //x�����
				
				for (int x1=x-1; x1>=0; x1--) {
					if( cardsMap[x1][y].getNum()>0 )
					{
						if( cardsMap[x][y].getNum()<=0 )
						{
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x1][y], cardsMap[x][y],x1, x, y, y);
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							
							x++;
							swipeTrue = true;
						}
						else if( cardsMap[x][y].equals(cardsMap[x1][y]) )
						{
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x1][y], cardsMap[x][y],x1, x, y, y);
							if(Config.type==2)
							{
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()/2);
								cardsMap[x1][y].setNum(0);
								switch(cardsMap[x][y].getNum())
								 {
									 case 1024:
										 SecondActivity.getsecondActivity().addScore(2);
								    	break;
									 case 512:
										 SecondActivity.getsecondActivity().addScore(4);
								    	break;
									 case 256:
										 SecondActivity.getsecondActivity().addScore(8);
								    	break;
									 case 128:
										 SecondActivity.getsecondActivity().addScore(16);
								    	break;
									 case 64:
										 SecondActivity.getsecondActivity().addScore(32);
								    	break;
									 case 32:
										 SecondActivity.getsecondActivity().addScore(64); 
								    	break;
									 case 16:
										 SecondActivity.getsecondActivity().addScore(128);
								    	break;
									 case 8:
										 SecondActivity.getsecondActivity().addScore(256);
								    	break;
									 case 4:
										 SecondActivity.getsecondActivity().addScore(512);
								    	break;
									 case 2:
										 SecondActivity.getsecondActivity().addScore(1024);
								    	break;
								 }
								swipeTrue = true;
							}
							else
							{
								//�����ֱ�ӽ���ǰλ�õ���ֵ*2,Ȼ���ұ����ſ�Ƭ��Ϊ0
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x1][y].setNum(0);
								
								//���ӷ���,�ϲ����Ƕ�������Ӷ��ٷ�
								SecondActivity.getsecondActivity().addScore(cardsMap[x][y].getNum());
								swipeTrue = true;
							}
							
							
							
						}
						break;
					}
				}
			}
		}
		//�жϿ�Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		if( swipeTrue == true )
		{
		   addRandomNum();   //����һ��,���һ�������
		   checkGameOver();  //ÿ�����������,��Ҫ�ж��Ƿ�ﵽ��Ϸ������״̬
		}
	}
	
	private void swipeDown()
	{
		//��¼��Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		boolean swipeTrue = false;
		
		for (int x = 0; x < Config.LINES; x++) {
			for (int y=Config.LINES-1; y>=0; y--) {
				
				//��ǰ�еĵ�ǰλ�õ���һ��Ԫ�ؿ�ʼ,���ϱ���
				for (int y1 = y-1; y1 >=0; y1--) {
					//�����һ����Ƭ��ֵ
					if(cardsMap[x][y1].getNum()>0)
					{
						//��ǰλ�õĿ�Ƭ<=0(Ϊ��),����һ����Ƭ�ƹ���
						if(cardsMap[x][y].getNum()<=0)
						{
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x][y1],cardsMap[x][y], x, x, y1, y);
							//����һ����Ƭ�ƹ���
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							//�Ѹո��ϱ��ƹ����Ǹ�������Ϊ0
							cardsMap[x][y1].setNum(0);
							
							//
							y++;  
							swipeTrue = true;
						}
						//��ǰλ�����ϱ�λ�õĿ�Ƭ���
						else if(cardsMap[x][y].equals(cardsMap[x][y1]))
						{
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x][y1],cardsMap[x][y], x, x, y1, y);
							if(Config.type==2)
							{
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()/2);
								cardsMap[x][y1].setNum(0);
								switch(cardsMap[x][y].getNum())
								 {
									 case 1024:
										 SecondActivity.getsecondActivity().addScore(2);
								    	break;
									 case 512:
										 SecondActivity.getsecondActivity().addScore(4);
								    	break;
									 case 256:
										 SecondActivity.getsecondActivity().addScore(8);
								    	break;
									 case 128:
										 SecondActivity.getsecondActivity().addScore(16);
								    	break;
									 case 64:
										 SecondActivity.getsecondActivity().addScore(32);
								    	break;
									 case 32:
										 SecondActivity.getsecondActivity().addScore(64); 
								    	break;
									 case 16:
										 SecondActivity.getsecondActivity().addScore(128);
								    	break;
									 case 8:
										 SecondActivity.getsecondActivity().addScore(256);
								    	break;
									 case 4:
										 SecondActivity.getsecondActivity().addScore(512);
								    	break;
									 case 2:
										 SecondActivity.getsecondActivity().addScore(1024);
								    	break;
								 }
								swipeTrue = true;
							}
							else
							{
								//�����ֱ�ӽ���ǰλ�õ���ֵ*2,Ȼ���ұ����ſ�Ƭ��Ϊ0
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x][y1].setNum(0);

								//���ӷ���,�ϲ����Ƕ�������Ӷ��ٷ�
								SecondActivity.getsecondActivity().addScore(cardsMap[x][y].getNum());
								swipeTrue = true;
							}
						}
						break;
					}
				}
			}
		}
		//�жϿ�Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		if( swipeTrue == true )
		{
		   addRandomNum();   //����һ��,���һ�������
		   checkGameOver();  //ÿ�����������,��Ҫ�ж��Ƿ�ﵽ��Ϸ������״̬
		}
	}
	
	private void swipeUp()
	{
		//��¼��Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		boolean swipeTrue = false;
		
		for (int x = 0; x < Config.LINES; x++) {
			for (int y = 0; y < Config.LINES; y++) {
				
				//��ǰ�еĵ�ǰλ�õ���һ��Ԫ�ؿ�ʼ,���ϱ���
				for (int y1 = y+1; y1 <Config.LINES; y1++) {
					//�����һ����Ƭ��ֵ
					if(cardsMap[x][y1].getNum()>0)
					{
						//��ǰλ�õĿ�Ƭ<=0(Ϊ��),����һ����Ƭ�ƹ���
						if(cardsMap[x][y].getNum()<=0)
						{
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x][y1],cardsMap[x][y], x, x, y1, y);
							//����һ����Ƭ�ƹ���
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							//�Ѹո��ϱ��ƹ����Ǹ�������Ϊ0
							cardsMap[x][y1].setNum(0);
							
							//
							y--;  
							swipeTrue = true;
						}
						//��ǰλ�����ϱ�λ�õĿ�Ƭ���
						else if(cardsMap[x][y].equals(cardsMap[x][y1]))
						{
							SecondActivity.getsecondActivity().getAnimLayer().createMoveAnim(cardsMap[x][y1],cardsMap[x][y], x, x, y1, y);
							if(Config.type==2)
							{
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()/2);
								cardsMap[x][y1].setNum(0);
								switch(cardsMap[x][y].getNum())
								 {
									 case 1024:
										 SecondActivity.getsecondActivity().addScore(2);
								    	break;
									 case 512:
										 SecondActivity.getsecondActivity().addScore(4);
								    	break;
									 case 256:
										 SecondActivity.getsecondActivity().addScore(8);
								    	break;
									 case 128:
										 SecondActivity.getsecondActivity().addScore(16);
								    	break;
									 case 64:
										 SecondActivity.getsecondActivity().addScore(32);
								    	break;
									 case 32:
										 SecondActivity.getsecondActivity().addScore(64); 
								    	break;
									 case 16:
										 SecondActivity.getsecondActivity().addScore(128);
								    	break;
									 case 8:
										 SecondActivity.getsecondActivity().addScore(256);
								    	break;
									 case 4:
										 SecondActivity.getsecondActivity().addScore(512);
								    	break;
									 case 2:
										 SecondActivity.getsecondActivity().addScore(1024);
								    	break;
								 }
								swipeTrue = true;
							}
							else
							{
								//�����ֱ�ӽ���ǰλ�õ���ֵ*2,Ȼ���ұ����ſ�Ƭ��Ϊ0
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x][y1].setNum(0);

								//���ӷ���,�ϲ����Ƕ�������Ӷ��ٷ�
								SecondActivity.getsecondActivity().addScore(cardsMap[x][y].getNum());
								swipeTrue = true;
							}
						}
						break;
					}
				}
			}
		}
		//�жϿ�Ƭ�Ƿ��ж���(���ȱ,���ߺϲ�),�ж���������¿�Ƭ
		if( swipeTrue == true )
		{
		   addRandomNum();   //����һ��,���һ�������
		   checkGameOver();  //ÿ�����������,��Ҫ�ж��Ƿ�ﵽ��Ϸ������״̬
		}
	}
	
	//�ж���Ϸ�Ƿ����
	public void checkGameOver()
	{
		/*
		 * a:����û����ͬ
		 * b:û�пո�
		 * ��a&&b,����Ϸ����
		 * */
		
		boolean gameOver = true;
		
		ALL:
		for(int x=0; x<Config.LINES; x++)
		{
			for(int y=0; y<Config.LINES; y++)
			{
				//�ж��Ƿ��пո�   �жϸ�λ�����������Ƿ�����ͬ�Ŀ�Ƭ
				if(cardsMap[x][y].getNum()==0 ||
						( x>0 && cardsMap[x][y].equals(cardsMap[x-1][y])) ||
						( x<3 && cardsMap[x][y].equals(cardsMap[x+1][y])) ||
						( y>0 && cardsMap[x][y].equals(cardsMap[x][y-1])) ||
						( y<3 && cardsMap[x][y].equals(cardsMap[x][y+1]))
						)
				{
					
					//����пո�,������Χ��һ������ͬ�����϶�Ϊ��Ϸû�н���
					gameOver = false;
					break ALL;
				}
			}
		}
		
		if(gameOver==true)   //����Ϸ����ʱ,��������,���û�ѡ���Ƿ����
		{
			new AlertDialog.Builder(getContext()).setTitle("���").setMessage("��Ϸ����").setPositiveButton("���¿�ʼ", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startGame();    //����û�������¿�ʼ,��ʼ����Ϸ
				}
				
			}
			).show();   //��ʾ�������
		}
	}
}
