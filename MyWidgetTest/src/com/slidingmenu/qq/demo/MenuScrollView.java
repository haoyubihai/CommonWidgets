/**
 * @Package:com.slidingmenu.qq.demo
 *@Description:TODO
 *@author : Ds
 *@date:2014-11-27 上午9:35:06
 *
 *
 */
package com.slidingmenu.qq.demo;

import com.example.mywidgettest.R;
import com.nineoldandroids.view.ViewHelper;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * @author Ds
 * 
 */
public class MenuScrollView extends HorizontalScrollView
{

    private LinearLayout mWapper;
    private ViewGroup mLeftMenu;
    private ViewGroup mContent;
    private int mScreenWidth;
    private int mMarginRight = 80;
    private int mMenuWidth = 0;
    private boolean once;
    private boolean isopen;

    public MenuScrollView(Context context)
    {
	this(context, null);
	// TODO Auto-generated constructor stub
    }

    /**
     * @Description:TODO
     * @param context
     * @param attrs
     * 
     */
    public MenuScrollView(Context context, AttributeSet attrs)
    {
	this(context, attrs, 0);
	// TODO Auto-generated constructor stub
    }

    /**
     * @Description:TODO
     * @param context
     * @param attrs
     * @param defStyle
     * 
     */
    public MenuScrollView(Context context, AttributeSet attrs, int defStyle)
    {
	super(context, attrs, defStyle);

	TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuScrollView, defStyle,
		0);
	int n = a.getIndexCount();
	for (int i = 0; i < n; i++)
	{
	    int arr = a.getIndex(i);
	    switch (arr)
	    {
		case R.styleable.MenuScrollView_MarginRightSize:
		    mMarginRight = a.getDimensionPixelSize(arr, (int) TypedValue.applyDimension(
			    TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()));
		    break;

	    }
	}
	// 获取屏幕的宽度
	WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	DisplayMetrics outMetrics = new DisplayMetrics();
	manager.getDefaultDisplay().getMetrics(outMetrics);
	mScreenWidth = outMetrics.widthPixels;


	
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.HorizontalScrollView#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
	// TODO Auto-generated method stub
	if (!once)
	{
	    mWapper = (LinearLayout) getChildAt(0);
	    mLeftMenu = (ViewGroup) mWapper.getChildAt(0);
	    mContent = (ViewGroup) mWapper.getChildAt(1);
	    mMenuWidth = mLeftMenu.getLayoutParams().width = mScreenWidth - mMarginRight;
	    mContent.getLayoutParams().width = mScreenWidth;

	    once = true;
	}
	super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.HorizontalScrollView#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
	// TODO Auto-generated method stub
	super.onLayout(changed, l, t, r, b);
	if (changed)
	{
	    this.scrollTo(mMenuWidth, 0);
	}
	
    }

    /* (non-Javadoc)
     * @see android.view.View#onScrollChanged(int, int, int, int)
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        // TODO Auto-generated method stub
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = 1.0f*l/mMenuWidth;//1-0
    
        //mContent 1-0.7  0.7+0.3*scale
        float rightScale = 0.7f+0.3f*scale;
        float scaleContent = 0.8f+0.2f*scale;

        float leftscale = 1.0f-scale*0.3f;
        float leftalpha = 0.6f+0.4f*(1-scale);
        ViewHelper.setTranslationX(mLeftMenu, mMenuWidth*scale*0.7f);

        ViewHelper.setScaleX(mLeftMenu, leftscale);
        ViewHelper.setScaleY(mLeftMenu, leftscale);
        ViewHelper.setAlpha(mLeftMenu, leftalpha);
        ViewHelper.setScaleX(mContent, scaleContent);
        ViewHelper.setScaleY(mContent, scaleContent);
        
    }
    /*
     * (non-Javadoc)
     * 
     * @see android.widget.HorizontalScrollView#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
	switch (ev.getAction())
	{
	    case MotionEvent.ACTION_UP:
		int scrollx = getScrollX();
		if (scrollx > mMenuWidth / 2)
		{
		    this.scrollTo(mMenuWidth, 0);
		    return true;
		}
		else
		{
		    this.scrollTo(0, 0);
		}

		break;

	}
	return super.onTouchEvent(ev);
    }

    public void openMenu()
    {

	if (isopen)
	{
	    return;
	}
	isopen = true;
	this.smoothScrollTo(0, 0);

    }

    public void coloseMenu()
    {

	if (!isopen)
	{
	    return;
	}
	isopen = false;
	this.smoothScrollTo(mMenuWidth, 0);

    }

    public void toggle()
    {
	if (isopen)
	{
	    coloseMenu();

	}
	else
	{
	    openMenu();

	}
    }
}
