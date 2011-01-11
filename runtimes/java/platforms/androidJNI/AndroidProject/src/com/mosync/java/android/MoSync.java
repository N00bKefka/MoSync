/* Copyright (C) 2010 MoSync AB

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU General Public License, version 2, as published by
the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License
along with this program; see the file COPYING.  If not, write to the Free
Software Foundation, 59 Temple Place - Suite 330, Boston, MA
02111-1307, USA.
*/

package com.mosync.java.android;

import static com.mosync.internal.android.MoSyncHelpers.SYSLOG;

import static com.mosync.internal.generated.MAAPI_consts.EVENT_TYPE_FOCUS_GAINED;
import static com.mosync.internal.generated.MAAPI_consts.EVENT_TYPE_FOCUS_LOST;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import com.mosync.internal.android.MoSyncThread;
import com.mosync.internal.android.MoSyncView;

/**
* Main MoSync activity
*
* As long as this activity is running the application is running.
*/
public class MoSync extends Activity
{
	static public MoSyncThread mMoSyncThread;
	MoSyncView mMoSyncView;
	Intent mMoSyncServiceIntent;
	
	/**
	 * Sets screen and window properties.
	 * Creates and initializes the MoSync thread
	 * Calls the createMoSyncView method and then shows the created view.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) 
	{
		Log.i("MoSync", "onCreate");
		
		super.onCreate(savedInstanceState);
		
		//if (theMoSyncThreadIsDead()) { return ; }
		
		// Initialize.
		mMoSyncView = null;
		
		// MoSync Android apps do not have a system title bar.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Default screen orientation is landscape mode.
		// There is a name clash between the MoSync and the Android 
		// SCREEN_ORIENTATION_PORTRAIT constant!
		// Preprecessing of .jpp files fuck things up!
		// We therefore use the numeric value of this Android constant.
		setRequestedOrientation(1);
		
		try
		{
			// Create the thread.
			mMoSyncThread = new MoSyncThread(this, new Handler());
		}
		catch (Exception ex)
		{
			MoSyncThread.logError(
				"MoSync - Unable to create thread! " +
				"Application is closed!", 
				ex);
			//super.onDestroy();
			finish();
			return;
		}
		
		// Create the view.
		createMoSyncView();
		mMoSyncThread.setMoSyncView(mMoSyncView);
		setContentView(mMoSyncView);
    }
	
	/**
	 * Handles the new configurations when the screen rotates.
	 * @param newConfig Object that holds configuration info.
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		Log.i("MoSync", "onConfigurationChanged");

		super.onConfigurationChanged(newConfig);
	}
	
	@Override
    protected void onPause()
	{
		Log.i("MoSync", "onPause");
		
		super.onPause();
		
		if (theMoSyncThreadIsDead()) { return ; }
		
		SYSLOG("Posting EVENT_TYPE_FOCUS_LOST to MoSync");
		int[] event = new int[1];
		event[0] = EVENT_TYPE_FOCUS_LOST;
		mMoSyncThread.postEvent(event);
    }
	
    @Override
    protected void onStop()
	{
		Log.i("MoSync", "onStop");
		
		super.onStop();
		
		if (theMoSyncThreadIsDead()) { return ; }
		
		// The view is destroyed, inform the thread about this.
		mMoSyncThread.setMoSyncView(null);
	}

	@Override
    protected void onRestart()
	{
		Log.i("MoSync", "onRestart");
		
		super.onRestart();
		
		if (theMoSyncThreadIsDead()) { return ; }
	}
	
	@Override
    protected void onStart()
	{
		Log.i("MoSync", "onStart");
		
		super.onStart();
	}
	
	@Override
    protected void onResume()
	{
		Log.i("MoSync", "onResume");
		
		super.onResume();
		
		if (theMoSyncThreadIsDead()) { return ; }
		
		mMoSyncThread.setMoSyncView(mMoSyncView);
		
		SYSLOG("Posting EVENT_TYPE_FOCUS_GAINED to MoSync");
		int[] event = new int[1];
		event[0] = EVENT_TYPE_FOCUS_GAINED;
		mMoSyncThread.postEvent(event);
    }
	
	@Override
    protected void onDestroy()
	{
    	Log.i("MoSync", "onDestroy");
		
		super.onDestroy();
    }
	
	/**
	 * Creates the MoSyncView. If it fails the Activity is destroyed.
	 */
	private void createMoSyncView()
	{
		Log.i("MoSync", "createMoSyncView");
		
		try 
		{
			mMoSyncView = new MoSyncView(this, mMoSyncThread);
        } 
		catch (Exception ex) 
		{
			MoSyncThread.logError(
				"MoSync - The MoSyncView could not be created, " +
				"the application could not start!", 
				ex);
			finish();
		}
	}

	/**
	 * In case of maPanic the MoSync thread exits
	 * and is dead. In this case we should finish
	 * the application.
	 */
	private boolean theMoSyncThreadIsDead()
	{
		if (null != mMoSyncThread && mMoSyncThread.isDead())
		{
			MoSyncService.stopService();
			finish();
			return true;
		}
		else
		{
			return false;
		}
	}
}