/**
 * @Package:com.example.mywidgettest
 *@Description:TODO
 *@author : Ds
 *@date:2014-11-19 上午10:51:04
 *
 *
 */
package com.example.mywidgettest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

/**
 * @author Ds
 * 
 */
public class Flowlayout extends ViewGroup
{

    /**
     * @Description:TODO
     * @param context
     * 
     */
    public Flowlayout(Context context)
    {
	super(context);
	// TODO Auto-generated constructor stub
    }

    /**
     * @Description:TODO
     * @param context
     * @param attrs
     * 
     */
    public Flowlayout(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	// TODO Auto-generated constructor stub
    }

    /**
     * @Description:TODO
     * @param context
     * @param attrs
     * @param defStyle
     * 
     */
    public Flowlayout(Context context, AttributeSet attrs, int defStyle)
    {
	super(context, attrs, defStyle);
	// TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
	// TODO Auto-generated method stub

	int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	int withSize = MeasureSpec.getSize(widthMeasureSpec);
	int heightSize = MeasureSpec.getSize(heightMeasureSpec);

	// content
	int width = 0;
	int height = 0;

	int lineWidth = 0;
	int lineHeight = 0;

	MarginLayoutParams cParams = null;

	int cCount = getChildCount();

	for (int i = 0; i < cCount; i++)
	{
	    View child = getChildAt(i);
	    measureChild(child, widthMeasureSpec, heightMeasureSpec);
	    cParams = (MarginLayoutParams) child.getLayoutParams();

	    int cWidthSpace = child.getMeasuredWidth() + cParams.leftMargin + cParams.rightMargin;
	    int cHeightSpace = child.getMeasuredHeight() + cParams.topMargin + cParams.bottomMargin;
	    // 加入child宽度大于最大宽度
	    if (lineWidth + cWidthSpace > withSize)
	    {
		width = Math.max(lineWidth, cWidthSpace);
		lineWidth = cWidthSpace;
		height += lineHeight;
		lineHeight = cHeightSpace;

	    }
	    else
	    {
		lineWidth += cWidthSpace;

		lineHeight = Math.max(lineHeight, cHeightSpace);

	    }
	    // 最后一个
	    if (i == cCount - 1)
	    {
		width = Math.max(lineWidth, cWidthSpace);
		height += cHeightSpace;
	    }

	}

	setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? withSize : width,
		heightMode == MeasureSpec.EXACTLY ? heightSize : height);
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

    // 所有view 集合
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {

	mAllViews.clear();
	mLineHeight.clear();
	int width = getWidth();
	int lineWidth = 0;
	int lineHeight = 0;
	// 存储 每一行的 view
	List<View> lineViews = new ArrayList<View>();
	int cCount = getChildCount();

	for (int i = 0; i < cCount; i++)
	{
	    View child = getChildAt(i);
	    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

	    int childWidth = child.getMeasuredWidth();
	    int childHeight = child.getMeasuredHeight();

	    if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width)
	    {
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);
		lineWidth = 0;
		lineViews = new ArrayList<View>();
	    }
	    lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
	    lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
	    lineViews.add(child);

	}

	mLineHeight.add(lineHeight);
	mAllViews.add(lineViews);

	int left = 0;
	int top = 0;
	int lineNums = mAllViews.size();
	for (int i = 0; i < lineNums; i++)
	{
	    lineViews = mAllViews.get(i);
	    lineHeight = mLineHeight.get(i);

	    for (int j = 0; j < lineViews.size(); j++)
	    {
		View child = lineViews.get(j);
		if (child.getVisibility() == View.GONE)
		{
		    continue;
		}
		MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
		int lc = left + lp.leftMargin;
		int tc = top + lp.topMargin;
		int rc = lc + child.getMeasuredWidth();
		int bc = tc + child.getMeasuredHeight();

		child.layout(lc, tc, rc, bc);
		left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;

	    }
	    left = 0;
	    top += lineHeight;

	}

    }

}
