package com.overview4money;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Overview4Money extends Activity {
    //**** CLASS METHODS. ****
    @Override protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	//**** Load layout xml file. ****
	setContentView(R.layout.layout_overview4money);
	//**** Create all necessary listeners. ****
	this.createListeners();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.overview4_money, menu);
	return true;
    }
    
    
    /**
     * Method create necessary listeners for all GUI component for this activity.
     */
    private void createListeners() {
	//**** Get buttons from ID. ****
	this.m_enterButton = (Button)this.findViewById(R.id.enterButton);
	this.m_moneyStateButton = (Button)this.findViewById(R.id.stateButton);
	this.m_moneyOverviewButton = (Button)this.findViewById(R.id.overviewButton);
	
	//**** Add specific listeners. ****
	View.OnClickListener onClickListener = new View.OnClickListener() {
	    public void onClick(View v) {
		onButtonsClicked(v);
	    }
	};
	this.m_enterButton.setOnClickListener(onClickListener);
	this.m_moneyStateButton.setOnClickListener(onClickListener);
	this.m_moneyOverviewButton.setOnClickListener(onClickListener);
    }
    
    /**
     * Called whenever some button is clicked.
     * @param v Desired button.
     */
    private void onButtonsClicked(View v) {
	if(this.m_enterButton == v) {
	    Log.i(m_tag, "onButtonsClicked() - Enter button");
	}
	else if(this.m_moneyStateButton == v) {
	    Log.i(m_tag, "onButtonsClicked() - Money state button");
	}
	else if(this.m_moneyOverviewButton == v) {
	    Log.i(m_tag, "onButtonsClicked() - Money overview button");
	}
    }

    
    
    //**** CLASS ATTRIBUTES. ****
    //--------------------------------------------------------------------------------------------------------------------
    /** Tag used for Log method calls shown in LogCat window. */
    private static final String m_tag = "Overview4Money";
    
    /** Main menu buttons. Enter button allows us to enter income and expense. Money state will allow us to view the state of the money and
     money overview button will allow as to see all incomes and expense. */
    private Button m_enterButton, m_moneyStateButton, m_moneyOverviewButton;
}
