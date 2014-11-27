/**
 * @Package:com.example.mywidgettest
 *@Description:TODO
 *@author : Ds
 *@date:2014-11-24 上午10:04:11
 *
 *
 */
package com.example.mywidgettest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * @author Ds
 * 
 */
public class CustomProgressBar extends View
{

    private int firstColor;
    private int secondColor;
    private int speed;
    private int circleWidth;
    private Paint mPaint;
    private boolean isNext = false;
    private int mProgress;

    /**
     * @Description:TODO
     * @param context
     * 
     */
    public CustomProgressBar(Context context)
    {
	this(context, null);
    }

    /**
     * @Description:TODO
     * @param context
     * @param attrs
     * 
     */
    public CustomProgressBar(Context context, AttributeSet attrs)
    {
	this(context, attrs, 0);
    }

    /**
     * @Description:TODO
     * @param context
     * @param attrs
     * @param defStyleAttr
     * 
     */
    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
	super(context, attrs, defStyleAttr);

	TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
		R.styleable.CustomProgressView, defStyleAttr, 0);
	int count = a.getIndexCount();

	for (int i = 0; i < count; i++)
	{
	    int arr = a.getIndex(i);
	    switch (arr)
	    {
		case R.styleable.CustomProgressView_firstColor:
		    firstColor = a.getColor(arr, Color.BLUE);
		    break;
		case R.styleable.CustomProgressView_secondColor:
		    secondColor = a.getColor(arr, Color.RED);
		    break;
		case R.styleable.CustomProgressView_circleWidth:
		    circleWidth = a.getDimensionPixelSize(arr, (int) TypedValue.applyDimension(
			    TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));

		    break;
		case R.styleable.CustomProgressView_speed:
		    speed = a.getInt(arr, 20);
		    break;
		default:
		    break;
	    }
	}
	a.recycle();
	mPaint = new Paint();
	new Thread(new Runnable()
	{

	    @Override
	    public void run()
	    {

		while (true)
		{
		    mProgress++;
		    if (mProgress == 360)
		    {
			mProgress = 0;
			if (!isNext)
			{
			    isNext = true;
			}
			else
			{
			    isNext = false;
			}

		    }
		    postInvalidate();
		    try
		    {
			Thread.sleep(speed);
		    }
		    catch (InterruptedException e)
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }

		}
	    }
	}).start();

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
       int center = getWidth()/2;
       int radius = center-circleWidth/2;
       mPaint.setStyle(Paint.Style.STROKE);
       mPaint.setStrokeWidth(circleWidth);
       mPaint.setAntiAlias(true);
       RectF oval  = new RectF(center-radius,center-radius , center+radius, center+radius);
       
       if (!isNext)
    {
	mPaint.setColor(firstColor);
	canvas.drawCircle(center, center, radius, mPaint);
	mPaint.setColor(secondColor);
	canvas.drawArc(oval, -90, mProgress, false, mPaint);
    }else {
	mPaint.setColor(secondColor);
	canvas.drawCircle(center, center, radius, mPaint);
	mPaint.setColor(firstColor);
	canvas.drawArc(oval, -90, mProgress, false, mPaint);
    }
       
    }
}
