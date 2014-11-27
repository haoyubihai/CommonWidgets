/**
 * @Package:com.slidingmenu.qq.demo
*@Description:TODO
 *@author : Ds
*@date:2014-11-27 上午9:23:58
*
*
 */
package com.slidingmenu.qq.demo;

import com.example.mywidgettest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * @author Ds
 *
 */
public class QQmianActivity extends Activity
{

    Button toggleBtn;
    MenuScrollView menuScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.qq_main_view);
        toggleBtn = (Button) findViewById(R.id.btn);
        menuScrollView = (MenuScrollView) findViewById(R.id.menuScroll);
        toggleBtn.setOnClickListener(new OnClickListener()
	{
	    
	    @Override
	    public void onClick(View v)
	    {
		System.out.println("lalala");
		menuScrollView.toggle();
		
	    }
	});
    }
}
