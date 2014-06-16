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
public interface TableModel {
    /**
     * Method returns array of all columns names.
     * @return Columns names.
     */
    public String[] getColumnNames();
	    
    /**
     * Method returns number of rows in table.
     * @return Number of rows.
     */
    public int getRowCount();
    
    /**
     * Method returns number of columns in table.
     * @return Number of columns.
     */
    public int getColumnsCount();
    
    /**
     * Method returns value at the specified row and column.
     * @param row Row index.
     * @param column Column index.
     * @return Value at the specified row and column.
     */
    public Object getValueAt(int row, int column);
    
    /**
     * Method returns type of all values at the specified column. These values will be returned by the getValueAt() method for the
     * specified column.
     * @param column Desired column index.
     * @return Class type of entire column.
     */
    public Class getColumnType(int column);
}
