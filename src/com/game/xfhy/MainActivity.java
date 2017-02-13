package com.game.xfhy;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private long mExitTime;                   //�˳�ʱ��
	private Button original = null;           //�����
	private Button lovers = null;             //���°�
	private Button reverse = null;            //�����
	private Button bereavement = null;        //ɥ����
	private Button dynasty = null;            //������
	private Button themeetingpoint = null;    //���Ұ�
	private Button about_author = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //��ȡ��Ӧ�Ŀؼ�
        original = (Button)findViewById(R.id.original);
        lovers = (Button)findViewById(R.id.lovers);
        reverse = (Button)findViewById(R.id.reverse);
        bereavement = (Button)findViewById(R.id.bereavement);
        dynasty = (Button)findViewById(R.id.dynasty);
        themeetingpoint = (Button)findViewById(R.id.themeetingpoint);
        about_author = (Button)findViewById(R.id.about_author);
        
        original.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println("�����");
				Config.LINES=4;
				Config.type=0;
				//����һ��intent����
	    		Intent intent = new Intent();
	    		//ǰһ������һ��
    		    intent.setClass(MainActivity.this, SecondActivity.class);
	    		MainActivity.this.startActivity(intent);
			}
        }
        );
        
        lovers.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println("���°�");
				Config.LINES=4;
				Config.type=1;
				//����һ��intent����
	    		Intent intent = new Intent();
	    		//ǰһ������һ��
    		    intent.setClass(MainActivity.this, SecondActivity.class);
	    		MainActivity.this.startActivity(intent);
			}
        }
        );
        
        reverse.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println("�����");
				Config.LINES=4;
				Config.type=2;
				//����һ��intent����
	    		Intent intent = new Intent();
	    		//ǰһ������һ��
    		    intent.setClass(MainActivity.this, SecondActivity.class);
	    		MainActivity.this.startActivity(intent);
			}
        }
        );
        
        bereavement.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println("ɥ����");
				Config.LINES=8;
				Config.type=3;
				//����һ��intent����
	    		Intent intent = new Intent();
	    		//ǰһ������һ��
    		    intent.setClass(MainActivity.this, SecondActivity.class);
	    		MainActivity.this.startActivity(intent);
			}
        }
        );
        
        dynasty.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println("������");
				Config.LINES=4;
				Config.type=4;
				//����һ��intent����
	    		Intent intent = new Intent();
	    		//ǰһ������һ��
    		    intent.setClass(MainActivity.this, SecondActivity.class);
	    		MainActivity.this.startActivity(intent);
			}
        }
        );
        
        themeetingpoint.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println("���Ұ�");
				Config.LINES=4;
				Config.type=5;
				//����һ��intent����
	    		Intent intent = new Intent();
	    		//ǰһ������һ��
    		    intent.setClass(MainActivity.this, SecondActivity.class);
	    		MainActivity.this.startActivity(intent);
			}
        }
        );
        
        about_author.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println("��������");
				//����һ��intent����
	    		Intent intent = new Intent();
	    		//ǰһ������һ��
    		    intent.setClass(MainActivity.this, About.class);
	    		MainActivity.this.startActivity(intent);
			}
        }
        );
    }
    
    //��2�η��ؼ��˳�
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
    	//����2�ΰ��·��ؼ�֮���ʱ����,���<2000ms���˳�
        if (keyCode == KeyEvent.KEYCODE_BACK) 
        {
                if ((System.currentTimeMillis() - mExitTime) > 2000) 
                {
                        Object mHelperUtils;
                        Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
                        mExitTime = System.currentTimeMillis();

                } 
                else 
                {
                        finish();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
     }
}
