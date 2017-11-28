package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class RotatorTests {
	private Stack<Double> stack;
	private Rotator rotate;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		rotate = new Rotator(stack);
	}

	@Test
	public void goDoIt_rotate_two_numbers() {
		// Arrange
		stack.push(5.0);
		stack.push(3.0);
		stack.push(6.0);
		stack.push(4.0);

		// Act
		rotate.goDoIt();

		// Assert
		assertThat(stack.pop()).isEqualTo(3.0);
		assertThat(stack.pop()).isEqualTo(4.0);
		assertThat(stack.pop()).isEqualTo(6.0);
		assertThat(stack.pop()).isEqualTo(5.0);
	}

	@Test
	public void redo_rotate_two_numbers() {
		// Arrange
		stack.push(5.0);
		stack.push(3.0);
		stack.push(6.0);
		stack.push(4.0);

		// Act
		rotate.goDoIt();
		rotate.undo(stack);

		// Assert
		assertThat(stack.pop()).isEqualTo(4.0);
		assertThat(stack.pop()).isEqualTo(6.0);
		assertThat(stack.pop()).isEqualTo(3.0);
		assertThat(stack.pop()).isEqualTo(5.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throwEmptyStackException() {
		// Arrange
		// Already arranged because stack is empty

		try {
			// Act
			rotate.goDoIt();
			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}

	}

	@Test
	public void null_stack_causes_throwNullPointerException_in_goDoIt() {
		// Arrange
		rotate = new Rotator(null);

		try {
			// Act
			rotate.goDoIt();
			// Assert
			fail("Did not throw a NullPointerException");
		}
		// }
		catch (NullPointerException npe) {
		}

	}

	@Test
	public void null_stack_causes_undo_to_throwEmptyStackException() {
		// Arrange
		rotate = new Rotator(null);

		try {
			// Act
			rotate.undo(stack);
			// Assert
			fail("Did not throw a EmptyStackException");
		} catch (EmptyStackException ese) {
		}

	}

	@Test
	public void empty_stack_causes_undo_to_throwEmptyStackException() {
		// Arrange
		// Already arranged because stack is empty

		try {
			// Act
			rotate.undo(stack);
			// Assert
			fail("Did not throw a EmptyStackException");
		} catch (EmptyStackException npe) {
		}

	}
}
