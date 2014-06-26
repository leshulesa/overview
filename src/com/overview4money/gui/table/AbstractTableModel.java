/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.gui.table;


/**
 *
 * @author Zordan
 */
public abstract class AbstractTableModel implements TableModel {
    //**** CLASS METHODS. ****
    protected AbstractTableModel() {
	//**** Create empty arrays. ****
	this.m_columnNames = new String[0];
	this.m_columnTypes = new Class[0];
	this.m_columnWidths = new float[0];
    }
    
    protected AbstractTableModel(String columnNames[]) {
	this.setColumnNames(columnNames);
    }
    
    
    /**
     * Method returns array of column names.
     * @return New string array.
     */
    public final String[] getColumnNames() {
	String copyArray[] = new String[this.m_columnNames.length];
	System.arraycopy(this.m_columnNames, 0, copyArray, 0, this.m_columnNames.length);
	return copyArray;
    }
    
    /**
     * Method returns type for all columns.
     * @return New class array.
     */
    public final Class[] getColumnTypes() {
	Class copyArray[] = new Class[this.m_columnTypes.length];
	System.arraycopy(this.m_columnTypes, 0, copyArray, 0, this.m_columnTypes.length);
	return copyArray;
    }
    
    /**
     * Method returns column widths.
     * @return New double array.
     */
    public final float[] getColumnWidths() {
	float copyArray[] = new float[this.m_columnWidths.length];
	System.arraycopy(this.m_columnWidths, 0, copyArray, 0, this.m_columnWidths.length);
	return copyArray;
    }
    
    
    /**
     * Method sets new column names for this table model. New column widths will be calculated and all columns will be of Object type.
     * @param columnNames New column names.
     */
    public final void setColumnNames(String columnNames[]) {
	this.m_columnNames = new String[columnNames.length];
	System.arraycopy(columnNames, 0, this.m_columnNames, 0, columnNames.length);
	this.makeAllColumnsWidthsEqual();
	this.makeAllColumnsOfObjectType();
    }
    
    public final void setColumnTypes(Class columnTypes[]) {
	this.m_columnTypes = new Class[columnTypes.length];
	System.arraycopy(columnTypes, 0, this.m_columnTypes, 0, columnTypes.length);
    }
    
    public final void setColumnWidths(double columnWidths[]) {
	this.m_columnWidths = new float[columnWidths.length];
	System.arraycopy(columnWidths, 0, this.m_columnWidths, 0, columnWidths.length);
    }
    

    /**
     * Method returns number of columns in table.
     * @return Number of columns.
     */
    public int getColumnsCount() {
	return this.m_columnNames.length;
    }

    /**
     * Method returns type of all values at the specified column. These values will be returned by the getValueAt() method for the
     * specified column.
     * @param column Desired column index.
     * @return Class type of entire column.
     */
    public Class getColumnType(int column) {
	return this.m_columnTypes[column];
    }
    
    
    /**
     * Method recalculates columns widths based on the number of columns. All widths will be equal. Method is called when new column names are set.
     */
    protected void makeAllColumnsWidthsEqual() {
	this.m_columnWidths = new float[this.m_columnNames.length];
	float width = 1.0f/this.m_columnNames.length;
	for(int i = 0; i < this.m_columnWidths.length; i++) {
	    this.m_columnWidths[i] = width;
	}
    }
    
    /**
     * Method recreates column types array based on the number of columns. All columns will be of Object type. Method is called when
     * new column names are set.
     */
    protected void makeAllColumnsOfObjectType() {
	this.m_columnTypes = new Class[this.m_columnNames.length];
	for(int i = 0; i < this.m_columnTypes.length; i++) {
	    this.m_columnTypes[i] = Object.class;
	}
    }
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //----------------------------------------------------------------------------------------------------------------------------------
    /** Variable represents names of table's columns, also gives a number of columns. */
    private String m_columnNames[];
    /** Variable represents type of all columns. Array length must match length of the column names array. */
    private Class m_columnTypes[];
    /** These are normalized columns widths. Normalized means that values are between 0 and 1, and sum of all normalized columns widths are equal to 1. */
    private float m_columnWidths[];
}
