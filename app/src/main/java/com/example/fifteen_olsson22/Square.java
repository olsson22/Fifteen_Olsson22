package com.example.fifteen_olsson22;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Square
{

	private int x;
	private int y;
	private int value;
	private int side;
	private Paint paint = new Paint();

	public Square(int x, int y,int side, int value){
		this.x=x;
		this.y=y;
		this.side=side;
		this.value=value;
	}


	public int getSide(){
		return side;
	}

	public int getX(){
		return x;
	}


	public int getY()
	{
		return y;
	}


	public int getValue()
	{
		return value;
	}

	public void setValue(int newValue){
		value = newValue;
	}
}
