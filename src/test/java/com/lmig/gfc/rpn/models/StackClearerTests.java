package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class StackClearerTests {
	private Stack<Double> stack;
	private StackClearer clear;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		clear = new StackClearer(stack);
	}

	@Test
	public void goDoIt_clear_numbers() {
		// Arrange
		stack.push(5.0);
		stack.push(3.0);
		stack.push(6.0);
		stack.push(4.0);

		// Act
		clear.goDoIt();

		// Assert
		assertThat(stack.empty());

	}

	@Test
	public void undo_clear_numbers() {
		// Arrange
		stack.push(5.0);
		stack.push(3.0);
		stack.push(6.0);
		stack.push(4.0);

		// Act
		clear.goDoIt();
		clear.undo(stack);

		// Assert
		assertThat(stack.pop()).isEqualTo(4.0);
		assertThat(stack.pop()).isEqualTo(6.0);
		assertThat(stack.pop()).isEqualTo(3.0);
		assertThat(stack.pop()).isEqualTo(5.0);
	}

}
