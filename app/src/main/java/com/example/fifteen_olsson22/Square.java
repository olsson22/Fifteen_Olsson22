package com.example.fifteen_olsson22;

/**
 *
 * @Author Fredrik Olsson
 * This class represents a Square containing a value
 * used for building the grid of Fifteen
 *
 * Date: 30 October 2019
 */


public class Square
{

	private int x;
	private int y;
	private int value;
	private int side;

	/**
	 * Creates a Square object with a certain x and y coordinate, a side and a value.
	 * @param x x coordinate of the square
	 * @param y y coordinate of the square
	 * @param side length of the side of the square
	 * @param value the value contained within the square
	 */
	public Square(int x, int y,int side, int value){
		this.x=x;
		this.y=y;
		this.side=side;
		this.value=value;
	}

	/**
	 * returns the length of the side of the square
	 * @return the length of the side of the square
	 */
	public int getSide(){
		return side;
	}

	/**
	 * returns the x coordinate of the square
	 * @return the x coordinate of the square
	 */
	public int getX(){
		return x;
	}

	/**
	 * returns the y coordinate of the square
	 * @return the y coordinate of the square
	 */
	public int getY() {
		return y;
	}

	/**
	 * returns the value contained within the square
	 * @return the value contained within the square
	 */
	public int getValue() {
		return value;
	}

	/**
	 * assigns a new value to be contained within the square
	 * @param newValue the new value to set within the square
	 */
	public void setValue(int newValue){
		value = newValue;
	}
}
