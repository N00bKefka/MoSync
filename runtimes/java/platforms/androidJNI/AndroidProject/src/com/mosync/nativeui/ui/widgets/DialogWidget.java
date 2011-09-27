/* Copyright (C) 2011 MoSync AB

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License,
version 2, as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
MA 02110-1301, USA.
*/

package com.mosync.nativeui.ui.widgets;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.ViewGroup;

import com.mosync.internal.android.EventQueue;
import com.mosync.internal.generated.IX_WIDGET;
import com.mosync.nativeui.util.properties.InvalidPropertyValueException;
import com.mosync.nativeui.util.properties.PropertyConversionException;

/**
 * A dialog widget that represents the root of a widget tree.
 * It acts like a layout: contains widgets, and can be dismissed
 * by using one of the two buttons: Negative/Positive.
 *
 *
 * @author emma
 */
public class DialogWidget extends Layout
{
	/**
	 * The alert dialog instance.
	 */
	private AlertDialog m_dialog;
	private AlertDialog.Builder m_dialogBuilder;

	/**
	 * The widget handle of this view.
	 */
	private int m_handle;

	/**
	 * The container layout.
	 */
	private ViewGroup m_container;

	/**
	 * The title of this dialog.
	 */
	private String m_title = "";

	/**
	 * Constructor
	 *
	 * @param handle handle Integer handle corresponding to this instance.
	 * @param view A screen wrapped by this widget.
	 */
	public DialogWidget(int handle,AlertDialog.Builder builder, ViewGroup view)
	{
		super( handle, view );
		m_dialogBuilder = builder;
		m_dialog = m_dialogBuilder.create();
		m_container = view;
		m_handle = handle;
	}

	public void show()
	{
		m_dialog.show();
	}

	public void hide()
	{
		if ( m_dialog.isShowing() )
			m_dialog.hide();
	}

	/**
	 * Returns true if the given widget is in fact a dialog.
	 *
	 * @return
	 */
	public boolean isDialog()
	{
		return true;
	}

	/**
	 * Adds a child to this layout at the given position. By
	 * default the view will be added to the wrapped ViewGroup.
	 *
	 * @param child The child to be added.
	 * @param index The index where to add the child. The child will have
	 *        this index after it has been added, the index starts at 0. If
	 *        the index is -1 it will be added at the end.
	 */
	@Override
	public void addChildAt(Widget child, int index)
	{
		int listIndex = index;
		if( index == -1 )
		{
			listIndex = m_children.size( );
		}

		child.setParent( this );
		m_children.add( listIndex, child );

		updateLayoutParamsForChild( child );

		// Add child to layout
		m_container.addView(child.getRootView(), listIndex);
		m_dialogBuilder.setView(m_container);
	}

	/**
	 * Removes a child form this layout, by default
	 * the view of the child is removed from the wrapped
	 * ViewGroup.
	 * The child's parent will be set to null.
	 *
	 * @param child
	 */
	@Override
	public void removeChild(Widget child)
	{
		child.setParent( null );
		m_children.remove( child );
		m_container.removeView( child.getRootView( ) );
	}

	/**
	 * @see Widget.setProperty.
	 */
	@Override
	public boolean setProperty(String property, String value)
			throws PropertyConversionException, InvalidPropertyValueException
	{
		if( property.equals( IX_WIDGET.MAW_DIALOG_TITLE ) )
		{
			m_title = value;
			m_dialog.setTitle(value);
		}
		else if ( property.equals( IX_WIDGET.MAW_DIALOG_LEFT_BUTTON_TITLE ) )
		{
			if ( value.length() > 0 )
			{
				m_dialog.setCancelable(true);

				m_dialog.setButton(Dialog.BUTTON_NEGATIVE, value, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						EventQueue.getDefault().postDialogButtonClicked(m_handle, 1);

					}
				});
			}
			else
			{
				m_dialog.setCancelable(false);
			}
		}
		else if ( property.equals( IX_WIDGET.MAW_DIALOG_RIGHT_BUTTON_TITLE ) )
		{
			m_dialog.setButton(Dialog.BUTTON_POSITIVE, value, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int position) {
					EventQueue.getDefault().postDialogButtonClicked(m_handle, 0);

				}
			});
		}
		// Do not get any other properties besides those ones.
		else
		{
			return false;
		}

		return true;
	}

	/**
	 * @see Widget.getProperty.
	 */
	@Override
	public String getProperty(String property)
	{
		// Get only those properties.
		if( property.equals( IX_WIDGET.MAW_DIALOG_TITLE ) )
		{
			return m_title;
		}
		else if ( property.equals( IX_WIDGET.MAW_WIDGET_VISIBLE ) )
		{
			return Boolean.toString( m_dialog.isShowing() );
		}
		else
		{
			return "";
		}
	}

	/**
	 * @see Layout.updateLayoutParams.
	 */
	@Override
	public ViewGroup.LayoutParams createNativeLayoutParams(LayoutParams mosyncLayoutParams)
	{
		return new android.widget.FrameLayout.LayoutParams( mosyncLayoutParams.getWidth( ) , mosyncLayoutParams.getHeight( ) );
	}

	/**
	 * Returns the title of this dialog.
	 *
	 * @return the title of this dialog.
	 */
	public String getTitle()
	{
		return m_title;
	}

}