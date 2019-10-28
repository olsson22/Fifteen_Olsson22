package com.example.fifteen_olsson22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FifteenSv theSv = findViewById(R.id.fifteenSv);

		theSv.setOnTouchListener(theSv);







	}

}
