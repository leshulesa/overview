/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.appdata;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Zordan
 */
public class MoneyRecord implements Comparable<MoneyRecord> {
    //**** CLASS METHODS. ****
    public MoneyRecord(int year, int month, int day, double value, String description, RecordType type) {
	//**** Get the current UTC time. ****
	Calendar cal = Calendar.getInstance();
	//**** Create new record time from the specified year, month and days. ****
	Calendar recordCalendar = new GregorianCalendar();
	recordCalendar.setTimeInMillis(cal.getTimeInMillis());
	recordCalendar.set(Calendar.YEAR, year);
	recordCalendar.set(Calendar.MONTH, month - 1);
	recordCalendar.set(Calendar.DAY_OF_MONTH, day);
	this.m_dateTime = recordCalendar.getTime();
	
	//**** Save description and record type. ****
	this.m_description = description;
	this.m_recordType = type;
	//**** Save current value. ****
	this.m_currentValue = value;
	
	//**** State will be update after record is added to the global list.
	this.m_state = Double.NaN;
    }
    
    
    public Date getDateTime() {
	return new Date(this.m_dateTime.getTime());
    }
    
    public double getCurrentValue() {
	return this.m_currentValue;
    }
    
    public String getDescription() {
	return this.m_description;
    }
    
    public RecordType getRecordType() {
	return this.m_recordType;
    }
    
    public double getState() {
	return this.m_state;
    }
    
    
    /**
     * Method initializes money record object properly by adding it in global list.
     * @return Reference on this money record object.
     */
    public MoneyRecord init() {
	OverviewMoneyManager.getInstance().add(this);
	return this;
    }

    
    @Override public int compareTo(MoneyRecord another) {
	if(this.m_dateTime.getTime() < another.m_dateTime.getTime()) {
	    return -1;
	}
	else if(this.m_dateTime.getTime() > another.m_dateTime.getTime()) {
	    return 1;
	}
	return 0;
    }
    
    /**
     * Called by the manager when new record is added to the list. Method should update new record state value and to return new value.
     * @param previousState State value from some previous record.
     * @return The new calculated state value.
     */
    public double onRecordUpdate(double previousState) {
	this.m_state = previousState;
	if(this.m_recordType == RecordType.M_EXPENSE) {
	    this.m_state -= this.m_currentValue;
	}
	else {
	    this.m_state += this.m_currentValue;
	}
	return this.m_state;
    }
    
    
    
    public enum RecordType {
	M_INCOME, M_EXPENSE
    }
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //------------------------------------------------------------------------------------------------------------------------------
    /** Date time of this record. */
    private Date m_dateTime;
    /** Current record value. */
    private double m_currentValue;
    /** Simple record description. */
    private String m_description;
    /** Record type which can be income or expense. */
    private RecordType m_recordType;
    
    /** Current state at the time this record being created. */
    private double m_state;
}
