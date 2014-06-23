/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.gui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.overview4money.R;
import com.overview4money.gui.table.AbstractTableModel;
import com.overview4money.gui.table.AndroidTable;
import com.overview4money.gui.table.AndroidTableHeader;

/**
 *
 * @author Zordan
 */
public class DailyIncomeExpenseActivity extends FragmentActivity {
    //**** CLASS METHODS. ****
    @Override protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	//**** Load layout xml file. ****
	setContentView(R.layout.layout_dailyincomeexpense);
	
	AndroidTable table = (AndroidTable)this.findViewById(R.id.dailyincometable);
	AndroidTableHeader header = (AndroidTableHeader)this.findViewById(R.id.dailyincomeheader);
	table.setTableHeader(header);
	
	//**** Create table model special for this type of activity. ****
	String columns[] = this.getResources().getStringArray(R.array.dailyIncomeExpenseColumns);
	AbstractTableModel tableModel = new AbstractTableModel(columns) {
	    
	    public int getRowCount() {
		return 0;
	    }
	    
	    public Object getValueAt(int row, int column) {
		return 0;
	    }
	};
	table.setTableModel(tableModel);
    }
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //------------------------------------------------------------------------------------------------------------------------
}
