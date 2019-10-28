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

	public FifteenSv(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		aGrid = new Grid(600,200, 4);
		sqrs = aGrid.getSquares();
	}

	public void onDraw(Canvas canvas){

		for(int i = 0;i<4;i++) {
			for (int j = 0; j < 4; j++)
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

						left = sqrs[i][j-1].getValue();

						top = sqrs[i-1][j].getValue();

						right = sqrs[i][j+1].getValue();

						bottom = sqrs[i+1][j].getValue();
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

		invalidate();
		return true;
	}
}
