package com.lmig.gfc.rpn.models;

import java.util.Stack;



public class Subtractor extends TwoNumberCalculation implements Undoer{
	public Subtractor(Stack<Double> stack){
		super(stack);
	}

@Override	
protected double doMath(double first, double second) {
		return second - first;	
	}

}
