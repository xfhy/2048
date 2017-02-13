package com.game.xfhy;

import com.game.xfhy.AnimLayer;
import com.game.xfhy.GameView;
import com.game.xfhy.MainActivity;
import com.game.xfhy.R;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends ActionBarActivity {

	private TextView tvScore;                          //��¼����
	private static SecondActivity mainActivity = null;   //�����õ����Activity
	private int score = 0;                             //����
	private TextView tvBestScore;                      //��߷�
	private LinearLayout root = null;                  //��ײ�Ĳ����ļ�(LinearLayout)
	private Button btnNewGame;                         //��ʼ����Ϸ�İ�ť
	private GameView gameView;                         //��ȡ�Զ�����Ϸ�߼����ֿؼ�
	private AnimLayer animLayer = null;                //��ȡ�ƶ���Ч�Ĳ��ֿؼ�
	public static final String SP_KEY_BEST_SCORE = "bestScore";
	
	//�������Է��ʵ�MainActivityʵ����
		public SecondActivity() {      //���캯��
			mainActivity = this;    //���ɷ���
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
        tvScore = (TextView)findViewById(R.id.tvScore);   //�ҵ�id����ķ���TextView�ؼ�
        
        root = (LinearLayout) findViewById(R.id.container);
		root.setBackgroundColor(0xfffaf8ef);

		tvBestScore = (TextView) findViewById(R.id.tvBestScore);

		gameView = (GameView) findViewById(R.id.gameView);

		btnNewGame = (Button) findViewById(R.id.btnNewGame);
		btnNewGame.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) 
			{
			   gameView.startGame();
		    }
		});
		
		animLayer = (AnimLayer)findViewById(R.id.animLayer);
	}
	
	//��ȡ��ǰActivity
    public static SecondActivity getsecondActivity() {
		return mainActivity;
	}
    
    //�ѷ�������
    public void clearScore()
    {
    	score=0;
    	showScore();   //���������,����Ӧ������ʾ
    }
    
    //���ַ���
    public void showScore()
    {
    	tvScore.setText(score+"");
    }
    
    //�����ۼ�
    public void addScore(int s)
    {
    	score += s;  //�����ۼ�
    	showScore(); //��ʱ��ʾ��ǰ����
    	
    	int maxScore = Math.max(score, getBestScore());
		saveBestScore(maxScore);
		showBestScore(maxScore);
    }
    
    //������߷�
    public void saveBestScore(int s){
    	//getPreferences:������߷�Ҫ�õ�    �����Ǳ��浽�ļ���ȥ��
    	//Shared Preferences:
        //��Ҫ���ڴ洢key-value�Ը�ʽ�����ݣ����������Ĵ洢���ƣ��ᵽֻ�ܴ洢������������
		Editor e = getPreferences(MODE_PRIVATE).edit();
		e.putInt(SP_KEY_BEST_SCORE, s);
		e.commit();
	}

    //�õ���߷�
	public int getBestScore(){
		return getPreferences(MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE, 0);
	}

	//��ʾ��߷�
	public void showBestScore(int s){
		tvBestScore.setText(s+"");
	}
	
	//�õ������ƶ��������Ǹ���
	public AnimLayer getAnimLayer() {
		return animLayer;
	}
}
