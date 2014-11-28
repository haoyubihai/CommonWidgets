/**
 * @Package:com.drawerlayout.demo
*@Description:TODO
 *@author : Ds
*@date:2014-11-28 上午9:45:16
*
*
 */
package com.drawerlayout.demo;

import com.example.mywidgettest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Ds
 *
 */
public class RightMenuFragment extends Fragment
{

    /**
     *@Description:TODO
     *
     */
    public RightMenuFragment()
    {
	// TODO Auto-generated constructor stub
    }
    
    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.righ_menu_view, null, false);
    }

}
