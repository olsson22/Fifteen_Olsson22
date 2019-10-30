package com.example.fifteen_olsson22;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import java.util.ArrayList;

/**
 * @author Fredrik Olsson
 *
 * This class represents a game of Fifteen on a specific
 * Context and AttributeSet
 *
 * Date: 30 October 2019
 *
 *
 */

public class FifteenSv extends SurfaceView implements View.OnTouchListener,
		View.OnClickListener, SeekBar.OnSeekBarChangeListener {


	private Paint paint = new Paint();
	protected Square[][] squares;
	private Grid aGrid;
	private Paint backGround = new Paint();
	private int gridSize=4;


	/**
	 * When a user creates a FifteenSv the user must specify what Context and
	 * what AttributeSet the FifteenSv should be created within.
	 * @param context
	 * @param attrs
	 */
	public FifteenSv(Context context, AttributeSet attrs) {
		super(context, attrs);

		setGridSize(gridSize);
		//aGrid = new Grid(600,10, gridSize);
		//squares = aGrid.getSquares();
		setWillNotDraw(false);
	}

	/**
	 * Sets the gridsize of the board.
	 * @param newSize the new size which the board will be set to
	 */
	public void setGridSize(int newSize){
		gridSize = newSize;
		aGrid = new Grid(600,10, gridSize);
		squares = aGrid.getSquares();
	}

	/**
	 *Paints the grid on the given canvas
	 * @param canvas the canvas on which the grid will be drawn upon
	 */
	public void onDraw(Canvas canvas){
		squares = aGrid.getSquares();
		backGround.setColor(Color.GREEN);
		backGround.setTextSize(60);
		if(checkIfFinished(squares,aGrid.getSize())==true){
			canvas.drawText("YOU DID IT", 200,200,backGround);
		}

		for(int i = 0;i<aGrid.getSize();i++) {
			for (int j = 0; j < aGrid.getSize(); j++) {
				paint.setColor(Color.RED);

				int left = squares[i][j].getX();
				int top= squares[i][j].getY();
				int right = squares[i][j].getX() + squares[i][j].getSide();
				int bottom = squares[i][j].getY() + squares[i][j].getSide();

				canvas.drawRect(left, top,right , bottom, paint);
				paint.setColor(Color.BLACK);

				left = squares[i][j].getX()+1;
				top= squares[i][j].getY()+1;
				right = squares[i][j].getX() + squares[i][j].getSide()-1;
				bottom = squares[i][j].getY() + squares[i][j].getSide()-1;

				canvas.drawRect(left, top, right, bottom, paint);

				paint.setColor(Color.RED);
				paint.setTextSize(30);

				int value = squares[i][j].getValue();
				int x = squares[i][j].getX() + squares[i][j].getSide() / 2;
				int y = squares[i][j].getY() + squares[i][j].getSide() / 2;
				if (value > 0) {
					canvas.drawText("" + value,x ,y , paint);
				}
				else {
					canvas.drawText("", x, y, paint);

				}
			}
		}
	}

	/**
	 * checks to see if the numbers are in order on the board
	 * i.e. the player has finished the game.
	 * @param theGrid the grid for the game
	 * @param size the size of the grid
	 * @return true if the game is finished
	 */
	public boolean checkIfFinished(Square[][] theGrid, int size) {
		ArrayList<Integer> tempList = new ArrayList<>();

		for(int i = 0;i<size;i++){
			for(int j = 0; j<size;j++){
				int value = theGrid[i][j].getValue();
				int goal = (j%size)+1 +i*size;
				if(value==goal) {
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


	/**
	 * helper method for finding the values to the left, top, right and bottom of
	 * the square that was clicked by the user.
	 * @param row the row of the clicked square
	 * @param col the column of the clicked square
	 * @return an array of the left, top, right and bottom values.
	 */
	private int[] getCoordinates(int row, int col) {
		int left;
		int top;
		int right;
		int bottom;
		int[] coordList = new int[4];

		if (row - 1 < 0 && col - 1 < 0) {
			left = -1;
			top = -1;
			right = squares[row][col + 1].getValue();
			bottom = squares[row + 1][col].getValue();

		} else if (row - 1 < 0 && col + 1 == aGrid.getSize()) {
			left = squares[row][col - 1].getValue();
			top = -1;
			right = -1;
			bottom = squares[row + 1][col].getValue();

		} else if (row + 1 == aGrid.getSize() && col + 1 == aGrid.getSize()) {
			left = squares[row][col - 1].getValue();
			top = squares[row - 1][col].getValue();
			right = -1;
			bottom = -1;

		} else if (row + 1 == aGrid.getSize() && col - 1 < 0) {
			left = -1;
			top = squares[row - 1][col].getValue();
			right = squares[row][col + 1].getValue();
			bottom = -1;

		} else if (row - 1 < 0) {
			left = squares[row][col - 1].getValue();
			top = -1;
			right = squares[row][col + 1].getValue();
			bottom = squares[row + 1][col].getValue();

		} else if (col - 1 < 0) {
			left = -1;
			top = squares[row - 1][col].getValue();
			right = squares[row][col + 1].getValue();
			bottom = squares[row + 1][col].getValue();

		} else if (row + 1 == aGrid.getSize()) {
			left = squares[row][col - 1].getValue();
			top = squares[row - 1][col].getValue();
			right = squares[row][col + 1].getValue();
			bottom = -1;

		} else if (col + 1 == aGrid.getSize()) {
			left = squares[row][col - 1].getValue();
			top = squares[row - 1][col].getValue();
			right = -1;
			bottom = squares[row + 1][col].getValue();

		} else {
			left = squares[row][col - 1].getValue();
			top = squares[row - 1][col].getValue();
			right = squares[row][col + 1].getValue();
			bottom = squares[row + 1][col].getValue();

		}
		coordList[0]=left;
		coordList[1]=top;
		coordList[2]=right;
		coordList[3]=bottom;
		return coordList;

	}

	/**
	 External Citation

	 Date: 29 October 2019

	 Problem: did not know what listener to use for taps on a surfaceview

	 Resource: https://stackoverflow.com/questions/11690504/how-to-use-view-ontouchlistener-instead-of-onclick

	 Solution: I used the examples from this page
	 * */

	/**
	 * listens to clicks that occur on the board. If a square to the left, top, right or bottom
	 * of the one clicked, the value of the clicked square is moved to this square
	 * @param view not used
	 * @param event the clickevent that occured on the surfaceview
	 * @return true if the surfaceview was clicked
	 */
	@Override
	public boolean onTouch(View view, MotionEvent event) {

		int left;
		int right;
		int top;
		int bottom;


		if(event.getActionMasked()==MotionEvent.ACTION_DOWN){
			float xPressed = event.getX();
			float yPressed = event.getY();
			Log.d("pressed","it was pressed at x: "+ xPressed + " y: " + yPressed);

			for(int i=0;i<aGrid.getSize();i++){
				for(int j = 0; j<aGrid.getSize();j++){
					int xMin= squares[i][j].getX();
					int xMax = squares[i][j].getX()+squares[i][j].getSide();
					int yMin = squares[i][j].getY();
					int yMax = squares[i][j].getY()+squares[i][j].getSide();
					if(xPressed> xMin && xPressed<xMax && yPressed>yMin && yPressed<yMax ){


						int[] coordinates= getCoordinates(i,j);
						left=coordinates[0];
						top = coordinates[1];
						right = coordinates[2];
						bottom = coordinates[3];


						if(left==0){
							int temp = squares[i][j].getValue();
							squares[i][j].setValue(0);
							squares[i][j-1].setValue(temp);
						}
						if(top==0){
							int temp = squares[i][j].getValue();
							squares[i][j].setValue(0);
							squares[i-1][j].setValue(temp);
						}
						if(right==0){
							int temp = squares[i][j].getValue();
							squares[i][j].setValue(0);
							squares[i][j+1].setValue(temp);
						}
						if(bottom==0){
							int temp = squares[i][j].getValue();
							squares[i][j].setValue(0);
							squares[i+1][j].setValue(temp);
						}

					}
				}
			}

		}
		checkIfFinished(squares,aGrid.getSize());
		invalidate();
		return true;
	}

	/**
	 * listens to see if the reset button is clicked. if so, shuffles the board.
	 * @param view not used
	 */
	@Override
	public void onClick(View view) {
			aGrid.shuffle();
			invalidate();

	}

	/**
	 * Changes the dimensions of the board according to the progress of the seekbar.
	 * The smallest value is 4 and the largest is 8.
	 * @param seekBar the seekbar that has been changed.
	 * @param progress the progress of the seekbar that was changed.
	 * @param b not used.
	 */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
			gridSize = 4 + progress;
			setGridSize(gridSize);
			invalidate();
	}

	/**
	 * Unimplemented method from SeekBar.OnSeekBarChangeListener interface.
	 * @param seekBar
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{

	}

	/**
	 *
	 * Unimplemented method from SeekBar.OnSeekBarChangeListener interface.
	 * @param seekBar
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{

	}
}
