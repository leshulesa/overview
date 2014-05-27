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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.overview4money.R;
import com.overview4money.appdata.MoneyRecord;
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
	
	//**** Get radio buttons. No need for listeners. By default expense radio button will be selected. ****
	this.m_expenseRadioButton = (RadioButton)dialogView.findViewById(R.id.expenseRadioButton);
	this.m_incomeRadioButton = (RadioButton)dialogView.findViewById(R.id.incomeRadioButton);
	this.m_expenseRadioButton.setChecked(true);
	
	//**** Get date picker. ****
	this.m_datePicker = (DatePicker)dialogView.findViewById(R.id.inputDatePicker);
	this.m_valueEditText = (EditText)dialogView.findViewById(R.id.valueEditText);
	this.m_descriptionAutoTextView = (AutoCompleteTextView)dialogView.findViewById(R.id.descAutoCompleteTextView);
	
	TextView enterType = (TextView)dialogView.findViewById(R.id.enterTypeTV);
	enterType.setText(enterType.getText() + ":");
	TextView enterDate = (TextView)dialogView.findViewById(R.id.dateTV);
	enterDate.setText(enterDate.getText() + ":");
	TextView enterValue = (TextView)dialogView.findViewById(R.id.valueTextView);
	enterValue.setText(enterValue.getText() + ":");
	TextView descTextView = (TextView)dialogView.findViewById(R.id.descTextView);
	descTextView.setText(descTextView.getText() + ":");
	
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
	    double value = Double.parseDouble(this.m_valueEditText.getEditableText().toString());
	    String description = this.m_descriptionAutoTextView.getEditableText().toString();
	    MoneyRecord.RecordType type = this.m_incomeRadioButton.isSelected() ? MoneyRecord.RecordType.M_INCOME : MoneyRecord.RecordType.M_EXPENSE;
	    MoneyRecord record = new MoneyRecord(this.m_datePicker.getYear(), this.m_datePicker.getMonth() + 1, this.m_datePicker.getDayOfMonth(), value, description, type);
	    record.init();
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
    /** Expense and income radio buttons. Both are added to the radio group. */
    private RadioButton m_incomeRadioButton, m_expenseRadioButton;
    /** Date picker, value text field and description text field necessary for money record creation. */
    private DatePicker m_datePicker;
    private EditText m_valueEditText;
    private AutoCompleteTextView m_descriptionAutoTextView;
}
