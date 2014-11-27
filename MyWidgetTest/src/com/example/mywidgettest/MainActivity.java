
package com.example.mywidgettest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.slidingmenu.qq.demo.QQmianActivity;


public class MainActivity extends Activity
{
    private TextView mFirstGroup;
    private TextView mFlowLay;
    private TextView mCustomTextView;
    private TextView mCustomImageView;
    private TextView mCustomProgressBar;
    private TextView mSpecialProgressBar;
    private TextView mQQleftMenu;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main_view);
	
	initView();

    }

    /**
     *@Description:TODO
     *@author: Ds
     *@date: 2014-11-19 下午2:02:12
     *@return: void
     */
    private void initView()
    {
	mFirstGroup  = (TextView) findViewById(R.id.viewGroupFirst);
	mFlowLay = (TextView) findViewById(R.id.flowlay);
	mCustomTextView = (TextView) findViewById(R.id.customTextView);
	mCustomImageView = (TextView) findViewById(R.id.customImgView);
	mCustomProgressBar = (TextView) findViewById(R.id.customProgressBar);
	mSpecialProgressBar = (TextView) findViewById(R.id.specialProgressBar);
	mQQleftMenu = (TextView) findViewById(R.id.qqLeftMenu);
	
	
	mFirstGroup.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		Intent  intent = new Intent(MainActivity.this, FirstLay.class);
		startActivity(intent);
		
	    }
	});
	
	mFlowLay.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		Intent  intent = new Intent(MainActivity.this, FlowLayActivity.class);
		startActivity(intent);
		
	    }
	});
	
	mCustomTextView.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		Intent  intent = new Intent(MainActivity.this, CustomTextViewActivity.class);
		startActivity(intent);
		
		
	    }
	});
	mCustomImageView.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		Intent  intent = new Intent(MainActivity.this, CustomImageViewActivity.class);
		startActivity(intent);
		
	    }
	});
	mCustomProgressBar.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		Intent  intent = new Intent(MainActivity.this, CustomProgressBarActivity.class);
		startActivity(intent);
		
	    }
	});
	mSpecialProgressBar.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		Intent  intent = new Intent(MainActivity.this, SpecialProgressBarActivity.class);
		startActivity(intent);
		
	    }
	});
	mQQleftMenu.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		Intent  intent = new Intent(MainActivity.this, QQmianActivity.class);
		startActivity(intent);
		
	    }
	});
	
    }

}
