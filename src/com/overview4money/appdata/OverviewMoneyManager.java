/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.appdata;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zordan
 */
public class OverviewMoneyManager {
    //**** CLASS METHODS. ****
    private OverviewMoneyManager() {
	this.m_records = new ArrayList<MoneyRecord>();
	this.createDummyRecords();
    }
    
    
    public static OverviewMoneyManager getInstance() {
	return MyHolder.m_instance;
    }
    
    public List<MoneyRecord> getRecords() {
	synchronized(this.m_recordSync) {
	    return new ArrayList<MoneyRecord>(this.m_records);
	}
    }
    
    
    /**
     * Method returns the last state based on the last record within records list. If no records in list zero will be returned.
     * @return Last money state.
     */
    public double getLastState() {
	synchronized(m_recordSync) {
	    //**** In case we have records in our list last record state will be returned.
	    int size = this.m_records.size();
	    if(size > 0) {
		return this.m_records.get(size - 1).getState();
	    }
	    //**** No records no state. ****
	    return 0.0;
	}
    }
    
    /**
     * Method adds new money record into the list.
     * @param record New money record.
     */
    public void add(MoneyRecord record) {
	synchronized(this.m_recordSync) {
	    if(this.m_records.contains(record)) {
		throw new RuntimeException("OverviewMoneyManager.append() - Record already exist in list.");
	    }
	    //**** New record position. ****
	    int pos = -1;
	    //**** If list of records is empty then new record will be added to the beginning of list. ****
	    if(!this.m_records.isEmpty()) {
		int size = this.m_records.size();
		MoneyRecord lastRecord = this.m_records.get(size - 1);
		MoneyRecord firstRecord =  this.m_records.get(0);
		//**** If new record is greater then the last record then it will be added at the end of the list. ****
		if(record.compareTo(lastRecord) >= 0) {
		    pos = size;
		    this.m_records.add(record);
		}
		else if(record.compareTo(firstRecord) == -1) {
		    //**** In case new record is smaller then the first record, then it must be added at the beginning of the list. ****
		    pos = 0;
		    this.m_records.add(0, record);
		}
		else {
		    //**** In this case we have to search wher to put this new record. ****
		    for(int i = 0; i < size; i++) {
			MoneyRecord moneyRecord = this.m_records.get(i);
			if(record.compareTo(moneyRecord) == 1) {
			    pos = i;
			    break;
			}
		    }
		    this.m_records.add(pos, record);
		}
	    }
	    else {
		this.m_records.add(record);
	    }
	    //**** Initiate update of the record state and then propagate new states to all other records in the list. ****
	    if(pos >= 0) {
		//**** Get previous record and update state of the new record. ****
		MoneyRecord prevRecord = pos > 0 ? this.m_records.get(pos - 1) : null;
		record.onRecordUpdate(prevRecord == null ? 0.0 : prevRecord.getState());
		//**** Now we have to update all other records, starting from this new one. ****
		prevRecord = record;
		int size = this.m_records.size();
		for(int i = pos + 1; i < size; i++) {
		    MoneyRecord currentRecord = this.m_records.get(i);
		    currentRecord.onRecordUpdate(prevRecord.getState());
		    prevRecord = currentRecord;
		}
	    }
	    else {
		record.onRecordUpdate(0.0);
	    }
	}
    }
    
    
    /**
     * Method creates dummy money records for testing only. METHOD SHOULD BE REMOVED!!!
     */
    private void createDummyRecords() {
	this.m_records.add(new MoneyRecord(2014, 5, 1, 115000, "Stigla plata", MoneyRecord.RecordType.M_INCOME));
	this.m_records.add(new MoneyRecord(2014, 5, 1, 10000, "Hrana u DIS-u", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 1, 1000, "Auto gas", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 1, 500, "Benzin", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 3, 2250, "Popravka zuba", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 4, 1000, "Auto gas", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 5, 500, "Bus Plus", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 7, 1000, "Igranje fudbala", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 7, 200, "Parking", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 9, 300, "Pljeskavica", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 10, 550, "Pica", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 10, 400, "Pice", MoneyRecord.RecordType.M_EXPENSE));
	this.m_records.add(new MoneyRecord(2014, 5, 12, 29900, "LCD televizor", MoneyRecord.RecordType.M_EXPENSE));
	//**** Update states through all records. ****
	MoneyRecord prevRecord = null;
	for(MoneyRecord record : this.m_records) {
	    if(prevRecord == null) {
		record.onRecordUpdate(0.0);
	    }
	    else {
		record.onRecordUpdate(prevRecord.getState());
	    }
	    prevRecord = record;
	}
    }
    
    
    
    private static class MyHolder {
	private static OverviewMoneyManager m_instance = new OverviewMoneyManager();
    }
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //-------------------------------------------------------------------------------------------------------------------------------------
    /** Variable represents list of all money records. All operations such as write and read on this list must be synchronized. */
    private List<MoneyRecord> m_records;
    private final Object m_recordSync = new Object();
}
