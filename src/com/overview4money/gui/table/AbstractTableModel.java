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
    }
    
    protected AbstractTableModel(String columnNames[]) {
	this.setColumnNames(columnNames);
    }
    
    
    public final String[] getColumnNames() {
	String copyArray[] = new String[this.m_columnNames.length];
	System.arraycopy(this.m_columnNames, 0, copyArray, 0, this.m_columnNames.length);
	return copyArray;
    }
    
    public final Class[] getColumnTypes() {
	Class copyArray[] = new Class[this.m_columnTypes.length];
	System.arraycopy(this.m_columnTypes, 0, copyArray, 0, this.m_columnTypes.length);
	return copyArray;
    }
    
    public final void setColumnNames(String columnNames[]) {
	this.m_columnNames = new String[columnNames.length];
	System.arraycopy(columnNames, 0, this.m_columnNames, 0, columnNames.length);
    }
    
    public final void setColumnTypes(Class columnTypes[]) {
	this.m_columnTypes = new Class[columnTypes.length];
	System.arraycopy(columnTypes, 0, this.m_columnTypes, 0, columnTypes.length);
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
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //----------------------------------------------------------------------------------------------------------------------------------
    /** Variable represents names of table's columns, also gives a number of columns. */
    private String m_columnNames[];
    /** Variable represents type of all columns. Array length must match length of the column names array. */
    private Class m_columnTypes[];
}
