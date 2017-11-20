package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer {
	protected double first;
	
public  OneArgumentUndoer(double first){
	 this.first=first;
	 
}

public void undo(Stack<Double> stack) {
	stack.pop();
	parentUndo(stack);
}
protected void parentUndo(Stack<Double> stack) {
	stack.push(first);
}
}

