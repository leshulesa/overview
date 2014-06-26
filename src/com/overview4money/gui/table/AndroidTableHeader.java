/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.gui.table;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Zordan
 */
public class AndroidTableHeader extends View {
    //**** CLASS METHODS. ****
    public AndroidTableHeader(Context context) {
	super(context);
	this.initTableHeader();
    }
    
    public AndroidTableHeader(Context context, AttributeSet attrs) {
	super(context, attrs);
	this.initTableHeader();
    }
    
    public AndroidTableHeader(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	this.initTableHeader();
    }
    
    
    /**
     * Method returns table model used for header drawings.
     * @return Table model object.
     */
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
	this.createHeaders();
    }
    
    
    /**
     * Header initialization method.
     */
    private void initTableHeader() {
	//**** Create unique paint for all headers in table. ****
	this.m_headerPaint = new Paint();
	this.m_headerPaint.setARGB(255, 0, 0, 0);
	this.m_headerPaint.setStrokeWidth(6.0f);
	this.m_headerPaint.setStyle(Paint.Style.STROKE);
	//**** Create unique paint for all header's text. ****
	this.m_headerTextPaint = new TextPaint();
	this.m_headerTextPaint.setARGB(255, 0, 0, 0);
	this.m_headerTextPaint.setTextSize(30);
	
	
	//**** List of headers will be currently empty. ****
	this.m_headers = new LinkedList<Header>();
    }
    
    /**
     * Method will be called whenever new table model is set or new layout is detected. It is used to recreate header objects.
     */
    private void createHeaders() {
	if(m_tableModel == null || this.getWidth() == 0) {
	    return;
	}
	//**** No need for previous headers. ****
	this.m_headers.clear();
	//**** Now we can create new headers. ****
	float columnWidths[] = this.m_tableModel.getColumnWidths();
	String columnNames[] = this.m_tableModel.getColumnNames();
	final float DISP = 5.0f;
	int width = this.getWidth();
	int height = this.getHeight();
	float widthSum = 0.0f;
	float top = 0.0f + DISP;
	float bottom = height - DISP;
	for(int i = 0; i < columnWidths.length; i++) {
	    //**** Left is zero at the beginning or previous width sum. ****
	    float left = i == 0 ? 0.0f + DISP : widthSum + DISP;
	    widthSum += columnWidths[i]*width;
	    //**** Right is the current width sum. ****
	    float right = widthSum - DISP;
	    //**** Add header to the list. ****
	    this.m_headers.add(new Header(columnNames[i], i, left, right, top, bottom));
	}
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
     * Layout changed for this component. 
     * @param changed True if layout is changed.
     */
    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
	super.onLayout(changed, left, top, right, bottom);
	if(changed) {
	    //**** We have a new size so we can initialize headers. ****
	    this.createHeaders();
	}
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
	//**** Draw headers. ****
	this.drawHeaders(canvas);
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
    
    /**
     * Method draws headers on the android table header component.
     * @param canvas Canvas used for drawings.
     */
    private void drawHeaders(Canvas canvas) {
	for(Header header : this.m_headers) {
	    header.onDraw(canvas, this.m_headerPaint, this.m_headerTextPaint);
	}
    }
    
    
    
    /** Represents one header for simplicity. */
    public class Header {
	//**** CLASS METHODS. ****
	public Header(String headerValue, int index, float left, float right, float top, float bottom) {
	    this.m_headerValue = headerValue;
	    this.m_index = index;
	    this.m_left = left;
	    this.m_right = right;
	    this.m_top = top;
	    this.m_bottom = bottom;
	}
	
	
	/**
	 * Method draws this header object on the android table header.
	 * @param canvas Canvas used for drawings.
	 * @param headerPaint Special header paint used for drawings.
	 * @param headerTextPaint Paint for header text.
	 */
	public void onDraw(Canvas canvas, Paint headerPaint, Paint headerTextPaint) {
	    canvas.drawRect(this.m_left, this.m_top, this.m_right, this.m_bottom, headerPaint);
	    canvas.drawText(this.m_headerValue, this.m_left + 10.0f, this.m_bottom - 10.0f, headerTextPaint);
	}
	
	
	
	//**** CLASS ATTRIBUTES. ****
	//--------------------------------------------------------------------------------------------------------------------------------------------
	/** Header index or position. */
	private int m_index;
	/** Header string value which must be drawn. */
	private String m_headerValue;
	/** Used for rectangle drawings. */
	private float m_left, m_right;
	private float m_top, m_bottom;
    }
    
    
    
    //**** CLASS ATTRIBUTES. ****
    //------------------------------------------------------------------------------------------------------------------------------------------------
    /** Table model holding the data and information about columns. It will be set when header is added to some table or when new model is
     set for some android table. */
    private TableModel m_tableModel;
    
    //------------------------------------------------------------------------------------------------------------------------------------------------
    /** Paint for header lines. */
    private Paint m_headerPaint;
    /** Paint used for header text. */
    private TextPaint m_headerTextPaint;
    /** List of headers. Empty at the beginning, but when new model is specified, headers will be created. */
    private List<Header> m_headers;
}
