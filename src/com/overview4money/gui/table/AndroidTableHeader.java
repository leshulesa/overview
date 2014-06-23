/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.gui.table;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * @author Zordan
 */
public class AndroidTableHeader extends View {
    //**** CLASS METHODS. ****
    public AndroidTableHeader(Context context) {
	super(context);
    }
    
    public AndroidTableHeader(Context context, AttributeSet attrs) {
	super(context, attrs);
    }
    
    public AndroidTableHeader(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
    }
    
    
    public TableModel getTableModel() {
	return this.m_tableModel;
    }
    
    
    /**
     * Android table will use this method to specify new table model for this header. It will not be used outside, it will only be
     * used by the android table object. New table model will be specified when header is added to the table or when new model is specified for table.
     * @param tableModel Table mode.
     */
    protected void setTableModel(TableModel tableModel) {
	this.m_tableModel = tableModel;
    }
    
    
    /**
     * This is very important method which must be always implemented. The onMeasure() method determines the size requirements for a widget.
     * It takes two parameters: the width and height measure specifications. Customized widgets should calculate the width and height based
     * on the contents inside the widget and then call setMeasuredDimension() method with these values (this is important to call).
     * @param widthMeasureSpec Width measure specification.
     * @param heightMeasureSpec Height measure specification.
     */
    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }
    
    /**
     * Calculates the width of the table. It uses MeasureSpec class methods to return the specification mode and specification size given by the parent.
     * In case of table we will always return size specified by the measure specification.
     * @param measureSpec
     * @return 
     */
    private int measureWidth(int measureSpec) {
	return MeasureSpec.getSize(measureSpec);
    }
    
    /** Same as mesureWidth() method. */
    private int measureHeight(int measureSpec) {
//	return MeasureSpec.getSize(measureSpec);
	return 100;
    }
    
    
    /**
     * The onDraw()method allows customized drawing on the widget. Drawing is handled by walking down the tree and rendering view by view.
     * All parents are drawn before the children get drawn.
     * @param canvas For drawings, something like Graphics in real Java.
     */
    @Override protected void onDraw(Canvas canvas) {
	canvas.drawText("Dijanica", 10, 10, new Paint());
	canvas.drawRect(0f, 0f, this.getWidth(), this.getHeight(), new Paint());
	if(this.m_tableModel == null) return;
	Paint textPaint = new Paint();
	textPaint.setColor(0xFFFF0000);
	textPaint.setTextSize(30);
	int columnsCount = this.m_tableModel.getColumnsCount();
	String columnNames[] = this.m_tableModel.getColumnNames();
	for(int i = 0; i < columnsCount; i++) {
	    canvas.drawText(columnNames[i], 10 + 100*i, 30, textPaint);
	}
//	//**** Create rotation matrix around the button center. ****
//	this.mMatrix.setRotate(mRotate, this.getMeasuredWidth()/2, this.getMeasuredHeight()/2);
//	this.mShader.setLocalMatrix(mMatrix);
//	//**** For next step increment mRotate parameter for 3 degrees. On this way we will have animation of the circle. ****
//	this.mRotate += 3;
//	if(this.mRotate >= 360) {
//	    mRotate = 0;
//	}
//
//	//**** Set the dimension of the button's rectangle ****
//	this.mButtonRect.set(this.getWidth() - this.mTextPaint.measureText(mText), (this.getHeight() - mTextPaint.getTextSize())/2, 
//		mTextPaint.measureText(mText), this.getHeight() - (this.getHeight() - this.mTextPaint.getTextSize())/2);
//	
//	this.drawArcs(canvas, this.mButtonRect, false, mPaint);
//	
//	this.mSweep += SWEEP_INC;
//	if(mSweep > 360) {
//	    mSweep -= 360;
//	    mStart += START_INC;
//	    if(mStart >= 360) {
//		mStart -= 360;
//	    }
//	}
//	if (mSweep > 180) {
//	    canvas.drawText(mText, getPaddingLeft(), getPaddingTop() - mAscent, mTextPaint);
//	}
//	
//	invalidate();
    }
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //------------------------------------------------------------------------------------------------------------------------------------------------
    /** Table model holding the data and information about columns. It will be set when header is added to some table or when new model is
     set for some android table. */
    private TableModel m_tableModel;
}
