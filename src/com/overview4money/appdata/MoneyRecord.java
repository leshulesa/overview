/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.appdata;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 
 * @author Zordan
 */
public class MoneyRecord {
    //**** CLASS METHODS. ****
    public MoneyRecord(int year, int month, int day, double value, String description, RecordType type) {
	//**** Get the current UTC time. ****
	Calendar cal = Calendar.getInstance();
	//**** Create new record time from the specified year, month and days. ****
	Calendar recordCalendar = new GregorianCalendar();
	recordCalendar.setTimeInMillis(cal.getTimeInMillis());
	recordCalendar.set(Calendar.YEAR, year);
	recordCalendar.set(Calendar.MONTH, month);
	recordCalendar.set(Calendar.DAY_OF_MONTH, day);
	this.m_dateTime = recordCalendar.getTime();
	
	//**** Save description and record type. ****
	this.m_description = description;
	this.m_recordType = type;
	//**** Save current value. ****
	this.m_currentValue = value;
	//**** Read previous state based on the position of this new money record from the date time. ****
	this.m_state = 30000.0;
	//**** Calculate the state of this money record.
	if(this.m_recordType == RecordType.M_EXPENSE) {
	    this.m_state -= this.m_currentValue;
	}
	else {
	    this.m_state += this.m_currentValue;
	}
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
