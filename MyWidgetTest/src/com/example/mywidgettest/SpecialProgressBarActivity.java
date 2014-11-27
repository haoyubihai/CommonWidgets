/**
 * @Package:com.example.mywidgettest
 *@Description:TODO
 *@author : Ds
 *@date:2014-11-26 上午10:11:00
 *
 *
 */
package com.example.mywidgettest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Ds
 * 
 */
public class SpecialProgressBarActivity extends Activity
{

    SpecialProgressBar sp1, sp2, sp3, sp4, sp5;
    int progress = 0;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.special_progress_bar);
	sp1 = (SpecialProgressBar) findViewById(R.id.p1);
	sp2 = (SpecialProgressBar) findViewById(R.id.p2);
	sp3 = (SpecialProgressBar) findViewById(R.id.p3);
	sp4 = (SpecialProgressBar) findViewById(R.id.p4);
	sp5 = (SpecialProgressBar) findViewById(R.id.p5);
	sp5.setStyle(1);

	btn = (Button) findViewById(R.id.btn);

	btn.setOnClickListener(new OnClickListener()
	{

	    @Override
	    public void onClick(View v)
	    {
		new Thread(new Runnable()
		{

		    @Override
		    public void run()
		    {
			while (progress <= 100)
			{
			    progress += 3;
			    sp1.setProgress(progress);
			    sp2.setProgress(progress);
			    sp3.setProgress(progress);
			    sp4.setProgress(progress);
			    sp5.setProgress(progress);

			    try
			    {
				Thread.sleep(1000);
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
	});
    }

}
