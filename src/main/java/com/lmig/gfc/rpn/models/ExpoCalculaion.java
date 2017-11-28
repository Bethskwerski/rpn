package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class ExpoCalculaion extends TwoNumberCalculation{
	public ExpoCalculaion(Stack<Double> stack) {
		super(stack);
	}

	@Override
	protected double doMath(double first, double second) {
		return Math.pow(second, first);
	}
}

