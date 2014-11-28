/**
 * @Package:com.drawerlayout.demo
 *@Description:TODO
 *@author : Ds
 *@date:2014-11-28 上午9:24:41
 *
 *
 */
package com.drawerlayout.demo;

import com.example.mywidgettest.R;
import com.nineoldandroids.view.ViewHelper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class DrawMainActivity extends FragmentActivity
{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle arg0)
    {
	// TODO Auto-generated method stub
	super.onCreate(arg0);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.draw_main_view);
	initView();
	initEvents();
	findViewById(R.id.btn_right).setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		// TODO Auto-generated method stub
		drawerLayout.openDrawer(Gravity.RIGHT);
		
//		drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
	    }
	});

    }

    /**
     * @Description:TODO
     * @author: Ds
     * @date: 2014-11-28 上午9:58:36
     * @return: void
     */
    private void initEvents()
    {
	drawerLayout.setDrawerListener(new DrawerListener()
	{

	    @Override
	    public void onDrawerStateChanged(int arg0)
	    {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void onDrawerSlide(View drawerView, float slideOffset)
	    {
		View mContent = drawerLayout.getChildAt(0);
		View mMenu = drawerView;
		float scale = 1 - slideOffset;// 1-0
		float rightScale = 0.8f + scale * 0.2f;// 1-0.8
		if (drawerView.getTag().equals("LEFT"))
		{
		    float leftScale = 1 - 0.3f * scale;
		    ViewHelper.setScaleX(mMenu, leftScale);
		    ViewHelper.setScaleY(mMenu, leftScale);
		    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1f - scale));
		    ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));
		    ViewHelper.setPivotX(mContent, 0);
		    ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
		    mContent.invalidate();
		    ViewHelper.setScaleX(mContent, rightScale);
		    ViewHelper.setScaleY(mContent, rightScale);

		}
		else
		{
		    ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * slideOffset);
		    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
		    ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
		    mContent.invalidate();
		    ViewHelper.setScaleX(mContent, rightScale);
		    ViewHelper.setScaleY(mContent, rightScale);
		}

	    }

	    @Override
	    public void onDrawerOpened(View arg0)
	    {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void onDrawerClosed(View arg0)
	    {
//		drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);

	    }
	});

    }

    /**
     * @Description:TODO
     * @author: Ds
     * @date: 2014-11-28 上午9:51:02
     * @return: void
     */
    private void initView()
    {
	drawerLayout = (DrawerLayout) findViewById(R.id.draw_lay);
	//设置只能以编码方式打开 就是说滑动屏幕右边缘无效
//	drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);

    }

}
