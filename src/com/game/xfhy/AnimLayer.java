package com.game.xfhy;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;


/**
 * ��Ƭ�ƶ�����Ч,���ɿ�Ƭ��Ч
 * @author XFHY
 */
public class AnimLayer extends FrameLayout {

	public AnimLayer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initLayer();
	}

	public AnimLayer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayer();
	}

	public AnimLayer(Context context) {
		super(context);
		initLayer();
	}
	
	private void initLayer()
	{
	}
	
	//��һ���㵽��һ����,�����������۵�Ч��   �����Ǵӵ�ǰλ���Ƶ��������ҵ�����һ��
	public void createMoveAnim(final Card from,final Card to,int fromX,int toX,int fromY,int toY)
	{
		
		final Card c = getCard(from.getNum());
		
		LayoutParams lp = new LayoutParams(Config.CARD_WIDTH, Config.CARD_WIDTH);
		lp.leftMargin = fromX*Config.CARD_WIDTH;   //��߾�
		lp.topMargin = fromY*Config.CARD_WIDTH;
		c.setLayoutParams(lp);
		
		//Ҫ������Ǹ����ǿյ�,���Ҫ������Ǹ�������Ϊ���ɼ�
		if (to.getNum()<=0) 
		{
			to.getLabel().setVisibility(View.INVISIBLE);
		}
		
		/*
		 *TranslateAnimation λ�ƶ���Ч�� 
		 *TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) 
		 *  float fromXDelta ������ʼ�ĵ��뵱ǰView X�����ϵĲ�ֵ 
			float toXDelta ���������ĵ��뵱ǰView X�����ϵĲ�ֵ 
			float fromYDelta ������ʼ�ĵ��뵱ǰView Y�����ϵĲ�ֵ 
			float toYDelta ������ʼ�ĵ��뵱ǰView Y�����ϵĲ�ֵ 
			
			��������:
			animation.setDuration(long durationMillis);//���ö�������ʱ�� 
			animation.setRepeatCount(int i);//�����ظ����� 
			animation.setRepeatMode(Animation.REVERSE);//���÷�����ִ�� 
		 * */
		TranslateAnimation ta = new TranslateAnimation(0, Config.CARD_WIDTH*(toX-fromX), 0, Config.CARD_WIDTH*(toY-fromY));
		ta.setDuration(100);   //���ö�������ʱ�� 
		ta.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//����Ƭ�ϵ��ı��ؼ�����Ϊ�ɼ�
				to.getLabel().setVisibility(View.VISIBLE); 
				
				//���ɼ�
				recycleCard(c);
			}
		});
		c.startAnimation(ta);    //��ʼ����
	}
	
	private Card getCard(int num)
	{
		Card c;
		if (cards.size()>0)    //��Ƭ����ֵ
		{
			c = cards.remove(0);   //�Ƴ�0��(�ײ�)
		}
		else                   //��Ƭ����ֵ
		{
			c = new Card(getContext());
			addView(c);        //�����ͼ
		}
		c.setVisibility(View.VISIBLE);
		c.setNum(num);
		return c;
	}
	
	//���տ�Ƭ,����Ƭ����Ϊ���ɼ�
	private void recycleCard(Card c)
	{
		c.setVisibility(View.INVISIBLE);
		c.setAnimation(null);
		cards.add(c);
	}
	private List<Card> cards = new ArrayList<Card>();
	
	//���ɿ�Ƭʱ������Ч��
	public void createScaleTo1(Card target)
	{
		/*
		 * android���ṩ��4�ж����� 
		AlphaAnimation ͸���ȶ���Ч�� 
		ScaleAnimation ���Ŷ���Ч�� 
		TranslateAnimation λ�ƶ���Ч�� 
		RotateAnimation ��ת����Ч�� 

		ScaleAnimation(float fromX, float toX, float fromY, float toY,int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) 
		float fromX ������ʼʱ X�����ϵ������ߴ� 
		float toX ��������ʱ X�����ϵ������ߴ� 
		float fromY ������ʼʱY�����ϵ������ߴ� 
		float toY ��������ʱY�����ϵ������ߴ� 
		int pivotXType ������X����������λ������ 
		float pivotXValue ��������������X����Ŀ�ʼλ�� 
		int pivotYType ������Y����������λ������ 
		float pivotYValue ��������������Y����Ŀ�ʼλ�� 
		 * */
		ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(100);        //���ö�������ʱ�� 
		target.setAnimation(null);  //����ִ������
		target.getLabel().startAnimation(sa);   //��ʼ����
		//startAnimation������ִ��һ��ָ���Ķ�������setAnimation����������
	}
	
}

