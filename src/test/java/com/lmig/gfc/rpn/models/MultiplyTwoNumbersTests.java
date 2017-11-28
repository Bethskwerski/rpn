package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class MultiplyTwoNumbersTests {
	private Stack<Double> stack;
	private MultiplyTwoNumbers multiplier;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		multiplier = new MultiplyTwoNumbers(stack);
	}

	@Test
	public void goDoIt_multiply_two_positive_numbers() {
		// Arrange
		stack.push(5.0);
		stack.push(3.0);

		// Act
		multiplier.goDoIt();

		// Assert
		assertThat(stack.peek()).isEqualTo(15.0);
	}

	@Test
	public void goDoIt_multiply_one_positive_and_one_neg_number_together() {
		// Arrange
		stack.push(5.0);
		stack.push(-3.0);

		// Act
		multiplier.goDoIt();

		// Assert
		assertThat(stack.peek()).isEqualTo(-15.0);
	}

	@Test
	public void goDoIt_multiply_two_neg_numbers_together() {
		// Arrange
		stack.push(-5.0);
		stack.push(-3.0);

		// Act
		multiplier.goDoIt();

		// Assert
		assertThat(stack.peek()).isEqualTo(15.0);
	}

	@Test
	public void goDoIt_multiply_one_positive_and_zero_together() {
		// Arrange
		stack.push(5.0);
		stack.push(0.0);

		// Act
		multiplier.goDoIt();

		// Assert
		assertThat(stack.peek()).isEqualTo(0.0);
	}

	@Test
	public void undo_multiplication_of_one_positive_and_one_neg_number_together() {
		// Arrange
		stack.push(5.0);
		stack.push(-3.0);
		multiplier.goDoIt();

		// Act
		multiplier.undo(stack);

		// Assert
		assertThat(stack.pop()).isEqualTo(-3.0);
		assertThat(stack.pop()).isEqualTo(5.0);
	}

	@Test
	public void undo_multiplication_of_two_neg_numbers_together() {
		// Arrange
		stack.push(-5.0);
		stack.push(-3.0);
		multiplier.goDoIt();

		// Act
		multiplier.undo(stack);

		// Assert
		assertThat(stack.pop()).isEqualTo(-3.0);
		assertThat(stack.pop()).isEqualTo(-5.0);
	}

	@Test
	public void undo_multiplication_owith_zero() {
		// Arrange
		stack.push(5.0);
		stack.push(0.0);
		multiplier.goDoIt();

		// Act
		multiplier.undo(stack);

		// Assert
		assertThat(stack.pop()).isEqualTo(0.0);
		assertThat(stack.pop()).isEqualTo(5.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throwEmptyStackException() {
		// Arrange
		// Already arranged because stack is empty

		try {
			// Act
			multiplier.goDoIt();
			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}

	}

	@Test
	public void null_stack_causes_throwNullPointerException_in_goDoIt() {
		// Arrange
		multiplier = new MultiplyTwoNumbers(null);

		try {
			// Act
			multiplier.goDoIt();
			// Assert
			fail("Did not throw a NullPointerException");
		}
		// }
		catch (NullPointerException npe) {
		}

	}

	@Test
	public void null_stack_causes_undo_to_throwNullPointerException() {
		// Arrange
		multiplier = new MultiplyTwoNumbers(null);

		try {
			// Act
			multiplier.undo(stack);
			// Assert
			fail("Did not throw a NullPointerException");
		} catch (NullPointerException npe) {
		}

	}

	@Test
	public void empty_stack_causes_undo_to_throwNullPointerException() {
		// Arrange
		// Already arranged because stack is empty

		try {
			// Act
			multiplier.undo(stack);
			// Assert
			fail("Did not throw a NullPointerException");
		} catch (NullPointerException npe) {
		}

	}
}
