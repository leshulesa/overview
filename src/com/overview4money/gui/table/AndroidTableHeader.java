/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.overview4money.gui.table;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
	float scaledDensity = this.getResources().getDisplayMetrics().scaledDensity;
	
	//**** Create unique paint for all headers in table. ****
	this.m_headerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	this.m_headerPaint.setARGB(255, 0, 0, 0);
	this.m_headerPaint.setStrokeWidth(3.0f*scaledDensity);
	this.m_headerPaint.setStyle(Paint.Style.STROKE);
	//**** Create unique paint for all header's text. ****
	this.m_headerTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
	this.m_headerTextPaint.setARGB(255, 0, 0, 0);
	this.m_headerTextPaint.setTextSize(20f*scaledDensity);
	
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
	int width = this.getWidth();
	int height = this.getHeight();
	float widthSum = 0.0f;
	float top = 0.0f;
	float bottom = height;
	for(int i = 0; i < columnWidths.length; i++) {
	    //**** Left is zero at the beginning or previous width sum. ****
	    float left = i == 0 ? 0.0f : widthSum;
	    widthSum += columnWidths[i]*width;
	    //**** Right is the current width sum. ****
	    float right = widthSum;
	    //**** Add header to the list. ****
	    this.m_headers.add(new Header(columnNames[i], i, left, right, top, bottom).init());
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
	return (int)(60*this.getResources().getDisplayMetrics().scaledDensity);
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
	    header.onDraw(canvas);
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
	    
	    this.m_headerValueForDraw = this.m_headerValue;
	    this.m_textXPos = this.m_left;
	    this.m_textYPos = this.m_top;
	}
	
	
	/**
	 * Header initialization function.
	 * @return Returns reference on this.
	 */
	public Header init() {
	    this.updateHeaderView();
	    return this;
	}
	
	
	/**
	 * Method draws this header object on the android table header.
	 * @param canvas Canvas used for drawings.
	 */
	public void onDraw(Canvas canvas) {
	    float disp = 5.0f;
	    canvas.drawRect(this.m_left + disp, this.m_top + disp, this.m_right - disp, this.m_bottom - disp, m_headerPaint);
	    canvas.drawText(this.m_headerValueForDraw, this.m_textXPos, this.m_textYPos, m_headerTextPaint);
	}
	
	/**
	 * Method updates header's view such as text position and size of the text.
	 */
	protected void updateHeaderView() {
	    //**** This is the current width and height of this header. ****
	    float width = this.m_right - this.m_left;
	    float height = this.m_bottom - this.m_top;
	    //**** Based on the hader text and header width calculates the text width and number of characters that can be put within the header's width. ****
	    float newTextWidth[] = new float[1];
	    int numOfChars = m_headerTextPaint.breakText(this.m_headerValue, true, width, newTextWidth);
	    //**** If number of available characters are less then header text length we will add three dots. ****
	    if(numOfChars < this.m_headerValue.length()) {
		this.m_headerValueForDraw = this.m_headerValue.substring(0, numOfChars - 3).concat("...");
	    }
	    else this.m_headerValueForDraw = this.m_headerValue;
	    
	    //**** Based on the header's left, width and height value and text bounds value we calculates text X and Y positions for this header. ****
	    Rect bounds = new Rect();
	    m_headerTextPaint.getTextBounds(this.m_headerValueForDraw, 0, this.m_headerValueForDraw.length(), bounds);
	    this.m_textXPos = this.m_left + width*0.5f - bounds.exactCenterX();
	    this.m_textYPos = 0.5f*height - bounds.exactCenterY();
	}
	
	
	
	//**** CLASS ATTRIBUTES. ****
	//--------------------------------------------------------------------------------------------------------------------------------------------
	/** Header index or position. */
	private int m_index;
	/** Used for rectangle drawings. */
	private float m_left, m_right;
	private float m_top, m_bottom;
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	/** Header string value which must be drawn. */
	private String m_headerValue;
	/** Header value which will be used for drawing. For example if header string is to long "Income Money" header value for draw will be "Income Mo...". */
	private String m_headerValueForDraw;
	/** Calculated text X and Y position. Header text will be positioned at the header's center. */
	private float m_textXPos, m_textYPos;
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
