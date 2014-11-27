/**
 * @Package:com.example.mywidgettest
 *@Description:TODO
 *@author : Ds
 *@date:2014-11-26 上午9:12:33
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
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author
 * 
 */
public class SpecialProgressBar extends View
{

    private int mRoundColor;
    private int mProgressColor;
    private float mBorderWidth;
    private int mProgressTextColor;
    private float mProgressTextSize;
    private boolean isTextDisplay;
    private int style=0;
    public static final int STYLE_STROKE = 0;
    public static final int STYLE_FILL = 1;
    private Paint mPaint;
    private int max;
    private int progress;

    public SpecialProgressBar(Context context)
    {
	this(context, null);
    }

    public SpecialProgressBar(Context context, AttributeSet attrs)
    {
	this(context, attrs, 0);
    }

    public SpecialProgressBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
	super(context, attrs, defStyleAttr);

	mPaint = new Paint();
	TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
		R.styleable.SepcialProgressBar, defStyleAttr, 0);

	mRoundColor = a.getColor(R.styleable.SepcialProgressBar_roundColor, Color.RED);
	mProgressColor = a.getColor(R.styleable.SepcialProgressBar_progressColor, Color.GREEN);
	mBorderWidth = a.getDimensionPixelSize(R.styleable.SepcialProgressBar_roundWidth, 5);
	mProgressTextColor = a.getColor(R.styleable.SepcialProgressBar_percentTextColor, Color.RED);
	mProgressTextSize = a.getDimensionPixelSize(R.styleable.SepcialProgressBar_percentTextSize, 20);
	style = a.getInt(R.styleable.SepcialProgressBar_style, 0);
	max = a.getInteger(R.styleable.SepcialProgressBar_max, 100);
	isTextDisplay = a.getBoolean(R.styleable.SepcialProgressBar_isTextDisplay, true);
	a.recycle();

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
	// TODO Auto-generated method stub
	super.onDraw(canvas);
	/**
	 * 圆环
	 */
	int center = getWidth() / 2;
	int radius = (int) (center - mBorderWidth / 2);
	mPaint.setColor(mRoundColor);
	mPaint.setStrokeWidth(mBorderWidth);
	mPaint.setStyle(Paint.Style.STROKE);
	mPaint.setAntiAlias(true);
	canvas.drawCircle(center, center, radius, mPaint);

	/**
	 * 百分比
	 */

	mPaint.setStrokeWidth(0);
	mPaint.setColor(mProgressTextColor);
	mPaint.setTextSize(mProgressTextSize);
	mPaint.setTypeface(Typeface.DEFAULT_BOLD);
	int percent = (int) ((float) progress / (float) max * 100);
	float textWidth = mPaint.measureText(percent + "%");
	if (isTextDisplay && percent != 0 && style == STYLE_STROKE)
	{
	    canvas.drawText(percent + "%", center - textWidth / 2, center + mProgressTextSize / 2,
		    mPaint);
	}

	/**
	 * 圆弧
	 */
	mPaint.setColor(mProgressColor);
	mPaint.setStrokeWidth(mBorderWidth);
	RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
	switch (style)
	{
	    case STYLE_STROKE:
		mPaint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(oval, 0, 360 * progress / max, false, mPaint);
		break;
	    case STYLE_FILL:
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		if (progress!=0)
		{
		    
		    canvas.drawArc(oval, 0, 360 * progress / max, true, mPaint);
		}
		break;

	    default:
		break;
	}

    }

    public int getmRoundColor()
    {
	return mRoundColor;
    }

    public void setmRoundColor(int mRoundColor)
    {
	this.mRoundColor = mRoundColor;
    }

    public int getmProgressColor()
    {
	return mProgressColor;
    }

    public void setmProgressColor(int mProgressColor)
    {
	this.mProgressColor = mProgressColor;
    }

    public float getmBorderWidth()
    {
	return mBorderWidth;
    }

    public void setmBorderWidth(float mBorderWidth)
    {
	this.mBorderWidth = mBorderWidth;
    }

    public int getmProgressTextColor()
    {
	return mProgressTextColor;
    }

    public void setmProgressTextColor(int mProgressTextColor)
    {
	this.mProgressTextColor = mProgressTextColor;
    }

    public float getmProgressTextSize()
    {
	return mProgressTextSize;
    }

    public void setmProgressTextSize(float mProgressTextSize)
    {
	this.mProgressTextSize = mProgressTextSize;
    }

    public boolean isTextDisplay()
    {
	return isTextDisplay;
    }

    public void setTextDisplay(boolean isTextDisplay)
    {
	this.isTextDisplay = isTextDisplay;
    }

    public int getStyle()
    {
	return style;
    }

    public void setStyle(int style)
    {
	this.style = style;
    }

    public Paint getmPaint()
    {
	return mPaint;
    }

    public void setmPaint(Paint mPaint)
    {
	this.mPaint = mPaint;
    }

    public synchronized int getMax()
    {
	return max;
    }

    public void setMax(int max)
    {
	if (max < 0)
	{
	    throw new IllegalArgumentException("max no less than 0");
	}
	this.max = max;
    }

    public synchronized int getProgress()
    {
	return progress;
    }

    public synchronized void setProgress(int progress)
    {
	if (progress < 0)
	{
	    throw new IllegalArgumentException("progress no less than 0");
	}
	if (progress > max)
	{
	    progress = max;
	}
	if (progress <= max)
	{
	    this.progress = progress;
	    postInvalidate();
	}
    }

    public static int getStyleStroke()
    {
	return STYLE_STROKE;
    }

    public static int getStyleFill()
    {
	return STYLE_FILL;
    }

}
