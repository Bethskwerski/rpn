package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Pusher implements Undoer, GoDoer{
	
	private Stack<Double> stack;
	private double value;
	
	public Pusher(Stack<Double> stack, double value) {
		this.stack= stack;
		this.value=value;
	}

	@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		
	}

	@Override
	public void goDoIt() {
		stack.push(value);
		
	}
	

}
