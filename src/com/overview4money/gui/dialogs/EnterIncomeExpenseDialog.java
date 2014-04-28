/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.gui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.overview4money.R;
import static com.overview4money.gui.Overview4Money.m_tag;

/**
 * Dialog allows user to enter income or expense data on a simple way.
 * @author Zordan
 */
public class EnterIncomeExpenseDialog extends DialogFragment {
    //**** CLASS METHODS. ****
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
	Log.d(m_tag, "EnterIncomeExpenseDialog.onCreateDialog()");
	AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
	//**** Get the layout inflater. ****
	LayoutInflater inflater = this.getActivity().getLayoutInflater();

	//**** Inflate and set the layout for the dialog. ****
	//**** Pass null as the parent view because its going in the dialog layout. ****
	View dialogView = inflater.inflate(R.layout.layout_enterincomeexpensedialog, null);
	builder.setView(dialogView).setTitle(R.string.enterincexp);
	
	//**** Get buttons. ****
	this.m_okButton = (Button)dialogView.findViewById(R.id.m_incExpDlgOkB);
	this.m_backButton = (Button)dialogView.findViewById(R.id.m_incExpDlgBackB);
	//**** Add listeners on buttons. ****
	View.OnClickListener onClickListener = new View.OnClickListener() {
	    public void onClick(View v) {
		onButtonsClicked(v);
	    }
	};
	this.m_backButton.setOnClickListener(onClickListener);
	this.m_okButton.setOnClickListener(onClickListener);
	
	return builder.create();
    }    
    
    
    /**
     * Method will be called whenever user clicks on some buttons.
     * @param v Clicked button.
     */
    protected void onButtonsClicked(View v) {
	if(this.m_okButton == v) {
	    //**** Ok button is pressed so we should create an record. ****
	    Log.d(m_tag, "EnterIncomeExpenseDialog.onButtonsClicked()");
	    this.getDialog().cancel();
	}
	else if(this.m_backButton == v) {
	    //**** Back button is pressed so we should dismiss this dialog. ****
	    this.getDialog().cancel();
	}
    }
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //-------------------------------------------------------------------------------------------------------------------------------------
    /** Dialog buttons. Ok button will be disabled until everything is entered. */
    private Button m_okButton, m_backButton;
}
