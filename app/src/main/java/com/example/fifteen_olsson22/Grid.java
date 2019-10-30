package com.example.fifteen_olsson22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grid {


	private int x;
	private int y;
	private int size;
	private Square[][] squares;



	public Grid(int x, int y, int size){
		this.x=x;
		this.y=y;
		this.size=size;
		int side = 120;
		int counter = 0;
		squares = new Square[size][size];
		for(int i = 0; i<size;i++){
			for(int j = 0; j<size;j++){
				if(i==size-1 && j==size-1){
					Square sqr = new Square(x + (j) * side, y + (i) * side, side, 0);
					squares[i][j] = sqr;
				}
				else
				{
					counter++;
					Square sqr = new Square(x + (j) * side, y + (i) * side, side, counter);
					squares[i][j] = sqr;
				}

			}
		}
		shuffle();
	}

	public Square[][] getSquares(){
		return squares;
	}
	public int getSize(){
		return size;
	}


	public void shuffle(){

		int n = getSize()-1;
		int y =getSize()-1;
		Random rand = new Random();
		for(int i = 0; i<getSize();i++){
			for(int j =0;j<getSize();j++){

			int r = rand.nextInt(n);
			int c = rand.nextInt(y);
			int tmp = squares[r][c].getValue();
			squares[r][c].setValue(squares[i][j].getValue());
			squares[i][j].setValue(tmp);
			}
		}
	}


}
