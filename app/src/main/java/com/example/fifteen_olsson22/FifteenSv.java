package com.example.fifteen_olsson22;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

public class FifteenSv extends SurfaceView implements View.OnTouchListener
{


	private Paint paint = new Paint();
	protected Square[][] sqrs;
	private Grid aGrid;
	private Paint backGround = new Paint();
	private int gridSize;

	public FifteenSv(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		setGridSize(6);
		aGrid = new Grid(600,10, gridSize);
		sqrs = aGrid.getSquares();
	}

	public void setGridSize(int newSize){
		gridSize = newSize;
	}

	public void onDraw(Canvas canvas){
		sqrs = aGrid.getSquares();
		backGround.setColor(Color.GREEN);
		backGround.setTextSize(60);
		if(checkIfFinished(sqrs,aGrid.getSize())==true){
			canvas.drawText("YOU DID IT", 200,200,backGround);
		}

		for(int i = 0;i<aGrid.getSize();i++) {
			for (int j = 0; j < aGrid.getSize(); j++)
			{
				paint.setColor(Color.RED);
				canvas.drawRect(sqrs[i][j].getX(), sqrs[i][j].getY(), sqrs[i][j].getX() + sqrs[i][j].getSide(), sqrs[i][j].getY() + sqrs[i][j].getSide(), paint);
				paint.setColor(Color.BLACK);
				canvas.drawRect(sqrs[i][j].getX() + 1, sqrs[i][j].getY() + 1, sqrs[i][j].getX() + sqrs[i][j].getSide() - 1, sqrs[i][j].getY() + sqrs[i][j].getSide() - 1, paint);
				paint.setColor(Color.RED);
				paint.setTextSize(30);
				int value = sqrs[i][j].getValue();
				if (value > 0)
				{
					canvas.drawText("" + sqrs[i][j].getValue(), sqrs[i][j].getX() + sqrs[i][j].getSide() / 2, sqrs[i][j].getY() + sqrs[i][j].getSide() / 2, paint);
				} else
				{
					canvas.drawText("", sqrs[i][j].getX() + sqrs[i][j].getSide() / 2, sqrs[i][j].getY() + sqrs[i][j].getSide() / 2, paint);
				}
			}
		}
	}


	public boolean checkIfFinished(Square[][] theGrid, int size) {
		ArrayList<Integer> tempList = new ArrayList<>();

		for(int i = 0;i<size;i++){
			for(int j = 0; j<size;j++){
				int value = theGrid[i][j].getValue();
				Log.d("value", "the value is: " + value);

				int goal = (j%size)+1 +i*size;
				Log.d("goal", "the goal is: "+ goal);
				if(value==goal)
				{
					tempList.add(1);
				}
			}
	}
			if(tempList.size()==size*size-1){
				return true;

			}
			tempList.clear();
			return false;
	}

	private int[] getCoordinates(int i, int j)
	{
		int left;
		int top;
		int right;
		int bottom;
		int[] coordList = new int[4];

		if (i - 1 < 0 && j - 1 < 0)
		{
			left = -1;
			top = -1;
			right = sqrs[i][j + 1].getValue();
			bottom = sqrs[i + 1][j].getValue();
		} else if (i - 1 < 0 && j + 1 == aGrid.getSize())
		{
			left = sqrs[i][j - 1].getValue();
			top = -1;
			right = -1;
			bottom = sqrs[i + 1][j].getValue();
		} else if (i + 1 == aGrid.getSize() && j + 1 == aGrid.getSize())
		{
			left = sqrs[i][j - 1].getValue();
			top = sqrs[i - 1][j].getValue();
			right = -1;
			bottom = -1;
		} else if (i + 1 == aGrid.getSize() && j - 1 < 0)
		{
			left = -1;
			top = sqrs[i - 1][j].getValue();
			right = sqrs[i][j + 1].getValue();
			bottom = -1;
		} else if (i - 1 < 0)
		{
			left = sqrs[i][j - 1].getValue();
			top = -1;
			right = sqrs[i][j + 1].getValue();
			bottom = sqrs[i + 1][j].getValue();
		} else if (j - 1 < 0)
		{
			left = -1;
			top = sqrs[i - 1][j].getValue();
			right = sqrs[i][j + 1].getValue();
			bottom = sqrs[i + 1][j].getValue();
		} else if (i + 1 == aGrid.getSize())
		{
			left = sqrs[i][j - 1].getValue();
			top = sqrs[i - 1][j].getValue();
			right = sqrs[i][j + 1].getValue();
			bottom = -1;
		} else if (j + 1 == aGrid.getSize())
		{
			left = sqrs[i][j - 1].getValue();
			top = sqrs[i - 1][j].getValue();
			right = -1;
			bottom = sqrs[i + 1][j].getValue();
		} else
		{
			left = sqrs[i][j - 1].getValue();
			top = sqrs[i - 1][j].getValue();
			right = sqrs[i][j + 1].getValue();
			bottom = sqrs[i + 1][j].getValue();
		}
		coordList[0]=left;
		coordList[1]=top;
		coordList[2]=right;
		coordList[3]=bottom;
		return coordList;

	}
	@Override
	public boolean onTouch(View view, MotionEvent event) {

		int left=-1;
		int right=-1;
		int top=-1;
		int bottom=-1;


		if(event.getActionMasked()==MotionEvent.ACTION_DOWN){
			float xPressed = event.getX();
			float yPressed = event.getY();
			Log.d("pressed","it was pressed at x: "+ xPressed + " y: " + yPressed);

			for(int i=0;i<aGrid.getSize();i++){
				for(int j = 0; j<aGrid.getSize();j++){
					int xMin= sqrs[i][j].getX();
					int xMax = sqrs[i][j].getX()+sqrs[i][j].getSide();
					int yMin = sqrs[i][j].getY();
					int yMax = sqrs[i][j].getY()+sqrs[i][j].getSide();
					if(xPressed> xMin && xPressed<xMax && yPressed>yMin && yPressed<yMax ){


						int[] coordinates= getCoordinates(i,j);
						left=coordinates[0];
						top = coordinates[1];
						right = coordinates[2];
						bottom = coordinates[3];


						if(left==0){
							int temp = sqrs[i][j].getValue();
							sqrs[i][j].setValue(0);
							sqrs[i][j-1].setValue(temp);
						}
						if(top==0){
							int temp = sqrs[i][j].getValue();
							sqrs[i][j].setValue(0);
							sqrs[i-1][j].setValue(temp);
						}
						if(right==0){
							int temp = sqrs[i][j].getValue();
							sqrs[i][j].setValue(0);
							sqrs[i][j+1].setValue(temp);
						}
						if(bottom==0){
							int temp = sqrs[i][j].getValue();
							sqrs[i][j].setValue(0);
							sqrs[i+1][j].setValue(temp);
						}

					}
				}
			}

		}
		checkIfFinished(sqrs,aGrid.getSize());
		for(int i = 0; i<aGrid.getSize();i++){
			for(int j = 0;j<aGrid.getSize();j++){
				Log.d("the grid", "position " + i +", "+j+"has value"+ sqrs[i][j].getValue());
			}
		}
		invalidate();
		return true;
	}
}
