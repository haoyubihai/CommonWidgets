/**
 * @Package:com.example.mywidgettest
*@Description:TODO
 *@author : Ds
*@date:2014-11-18 下午4:56:09
*
*
 */
package com.example.mywidgettest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Ds
 *
 */
public class MyContain extends ViewGroup
{

    /**
     *@Description:TODO
     * @param context
     *
     */
    public MyContain(Context context)
    {
	super(context);
	// TODO Auto-generated constructor stub
    }

    /**
     *@Description:TODO
     * @param context
     * @param attrs
     *
     */
    public MyContain(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	// TODO Auto-generated constructor stub
    }

    /**
     *@Description:TODO
     * @param context
     * @param attrs
     * @param defStyle
     *
     */
    public MyContain(Context context, AttributeSet attrs, int defStyle)
    {
	super(context, attrs, defStyle);
	// TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
	int cCount  = getChildCount();
	int cWidth = 0;
	int cHeight  =0;
	MarginLayoutParams cParams=null;
	
	for (int i = 0; i < cCount; i++)
	{
	    View cView  = getChildAt(i);
	    cWidth = cView.getMeasuredWidth();
	    cHeight = cView.getMeasuredHeight();
	    cParams= (MarginLayoutParams) cView.getLayoutParams();
	    
	    int cl=0,ct=0,cr=0,cb=0;
	    
	    if (i==0)
	    {
		cl =cParams.leftMargin;
		ct=cParams.topMargin;
	    }
	    if (i==1)
	    {
		cl = getWidth()-cWidth-cParams.leftMargin-cParams.rightMargin;
		ct=cParams.topMargin;
	    }
	    if (i==2)
	    {
		cl=cParams.leftMargin;
		ct=getHeight()-cHeight-cParams.bottomMargin;
	    }
	    if (i==3)
	    {
		cl = getWidth()-cWidth-cParams.leftMargin-cParams.rightMargin;
		ct=getHeight()-cHeight-cParams.bottomMargin;
	    }
	    cr= cl+cWidth;
	    cb= ct+cHeight;
	    cView.layout(cl, ct, cr, cb);
	}
    }
    
    /* (non-Javadoc)
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

	/**
	 * 获取ViewGroup推荐的宽 高 及测量模式
	 * 
	 */
	int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	int heightMode  = MeasureSpec.getMode(heightMeasureSpec);
	int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
	int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
	//计算出子布局的宽高
	measureChildren(widthMeasureSpec, heightMeasureSpec);
		
	/**
	 * 如果设置wrap_content
	 */

	int width = 0;
	int height  = 0;
	
	int cCount   = getChildCount();
	int cwidth = 0;
	int cheight = 0;
	
	MarginLayoutParams params = null;
	int lheight=0;
	int rheight = 0;
	int twidth = 0;
	int bwidth = 0;
	
	
	
	for (int i = 0; i < cCount; i++)
	{
	    
	    View view = getChildAt(i);
	    
	    cwidth = view.getMeasuredWidth();
	    cheight = view .getMeasuredHeight();
	    params = (MarginLayoutParams) view.getLayoutParams();
	    
	    if (i==0||i==1)
	    {
		twidth+=cwidth+params.leftMargin+params.rightMargin;
	    }
	    
	    if (i==2||i==3)
	    {
		bwidth+=cwidth+params.leftMargin+params.rightMargin;
		
	    }
	    
	    if (i==0||i==2)
	    {
		lheight = cheight+params.topMargin+params.bottomMargin;
	    }
	    if (i==1||i==3)
	    {
		rheight = cheight+params.topMargin+params.bottomMargin;
		
	    }
	}
	width = Math.max(twidth, bwidth);
	height = Math.max(lheight, rheight);
	
	setMeasuredDimension(widthMode==MeasureSpec.EXACTLY?sizeWidth:width, heightMode ==MeasureSpec.EXACTLY?sizeHeight:height);
	
	
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
	// TODO Auto-generated method stub
	return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p)
    {
	// TODO Auto-generated method stub
	return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams()
    {
	// TODO Auto-generated method stub
	return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	
    }

    
    
}
