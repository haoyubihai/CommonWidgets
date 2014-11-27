/**
 * @Package:com.example.mywidgettest
*@Description:TODO
 *@author : Ds
*@date:2014-11-19 下午2:31:16
*
*
 */
package com.example.mywidgettest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Ds
 *
 */
public class CustomTextView extends View
{

  private String mTitleText;
  private int mTitleColor;
  private int mTitleSize;
  
  private Rect mBound;
  private Paint mPaint;
    public CustomTextView(Context context)
    {
	this(context ,null);
    }
    public CustomTextView(Context context, AttributeSet attrs)
    {
	this(context,attrs,0);
    }
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
	super(context, attrs, defStyleAttr);
	//获取自定义属性
	
	TypedArray a   =  context.getTheme().obtainStyledAttributes(attrs, R.styleable.customTextView, defStyleAttr, 0);
	int n = a.getIndexCount();
	for (int i = 0; i < n; i++)
	{
	    int attr = a.getIndex(i);
	    switch (attr)
	    {
		case R.styleable.customTextView_mtitle:
		    mTitleText = a.getString(attr);
		    break;
		case R.styleable.customTextView_mtitle_color:
		    mTitleColor = a.getColor(attr, Color.BLACK);
		    break;
		case R.styleable.customTextView_mtitle_size:
		    mTitleSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
		    
		    break;

		default:
		    break;
	    }
	}
	a.recycle();
	
	mPaint = new Paint();
	mPaint.setTextSize(mTitleSize);
	mBound = new Rect();
	mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
	mPaint.setColor(Color.GREEN);
	canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
	mPaint.setColor(mTitleColor);
	canvas.drawText(mTitleText, getWidth()/2-mBound.width()/2, getHeight()/2+mBound.height()/2, mPaint);
	
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

	int widthMode =  MeasureSpec.getMode(widthMeasureSpec);
	int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	
	int width=0;
	int height = 0;
	
	if (widthMode==MeasureSpec.EXACTLY)
	{
	    width = widthSize;
	}else{
	    
	    mPaint.setTextSize(mTitleSize);
	    mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
	    float textWidth = mBound.width();
	    int desired = (int) (getPaddingLeft()+textWidth+getPaddingRight());
	    width = desired;
	    
	}
	
	if (heightMode ==MeasureSpec.EXACTLY)
	{
	    height = heightSize;
	}else{
	    mPaint.setTextSize(mTitleSize);
	    mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
	    float textHeight = mBound.height();
	    int desired = (int) (getPaddingTop()+textHeight+getPaddingBottom());
	    height = desired;
	    
	}

	setMeasuredDimension(width, height);
    }

 

}
