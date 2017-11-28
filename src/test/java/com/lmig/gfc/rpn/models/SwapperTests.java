package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class SwapperTests {
	private Stack<Double> stack;
	private Swapper swap;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		swap = new Swapper(stack);
	}

	@Test
	public void goDoIt_swap_two_numbers() {
		// Arrange
		stack.push(5.0);
		stack.push(3.0);
		stack.push(6.0);

		// Act
		swap.goDoIt();

		// Assert
		assertThat(stack.pop()).isEqualTo(3.0);
		assertThat(stack.pop()).isEqualTo(6.0);
		assertThat(stack.pop()).isEqualTo(5.0);
	}

	@Test
	public void redo_swap_two_numbers() {
		// Arrange
		stack.push(5.0);
		stack.push(3.0);
		stack.push(6.0);

		// Act
		swap.goDoIt();
		swap.undo(stack);

		// Assert
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
			swap.goDoIt();
			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}

	}

	@Test
	public void null_stack_causes_throwNullPointerException_in_goDoIt() {
		// Arrange
		swap = new Swapper(null);

		try {
			// Act
			swap.goDoIt();
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
		swap = new Swapper(null);

		try {
			// Act
			swap.undo(stack);
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
			swap.undo(stack);
			// Assert
			fail("Did not throw a EmptyStackException");
		} catch (EmptyStackException npe) {
		}

	}
}
