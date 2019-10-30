package com.example.fifteen_olsson22;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * @Author Fredrik Olsson
 * Date: 30 October 2019
 *
 * I chose to implement the grow shrink feature
 * using a seekbar to set the size of the grid.
 * This feature is implemented using the setGridSize()-method
 * and the onProgressChanged()-method in the FifteenSV-class
 *
 */

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Creates the FifteenSv object
		FifteenSv theSv = findViewById(R.id.fifteenSv);

		//Sets on the listener for the FifteenSv
		theSv.setOnTouchListener(theSv);

		//Creates the reset Button object
		Button reset = findViewById(R.id.resetButton);

		//sets on the listener for the button
		reset.setOnClickListener(theSv);

		//Creates the seekbar object used for setting the dimensions of the board
		SeekBar dimensionBar = findViewById(R.id.dimBar);

		//Sets on the listener for the seekbar
		dimensionBar.setOnSeekBarChangeListener(theSv);


	}


}
