package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AbsoluterOfOneNumber;
import com.lmig.gfc.rpn.models.AddTwoNumbersTogether;
import com.lmig.gfc.rpn.models.DivideTwoNumbersTogether;
import com.lmig.gfc.rpn.models.ExpoCalculaion;
import com.lmig.gfc.rpn.models.GoDoer;
import com.lmig.gfc.rpn.models.MultiplyTwoNumbers;
import com.lmig.gfc.rpn.models.Pusher;
import com.lmig.gfc.rpn.models.Rotator;
import com.lmig.gfc.rpn.models.StackClearer;
import com.lmig.gfc.rpn.models.Subtractor;
import com.lmig.gfc.rpn.models.Swapper;

@Controller
public class RpnController {
	private Stack<Double> stack;
	private Stack<GoDoer> undoers;
	private Stack<GoDoer> redoers;

	public RpnController() {
		stack = new Stack<Double>();
		undoers = new Stack<GoDoer>();
		redoers = new Stack<GoDoer>();
	}

	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("stack", stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasUndoer", undoers.size() > 0);
		mv.addObject("hasRedoer", redoers.size() > 0);
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOnToStack(double value) {
		Pusher pusher = new Pusher(stack, value);
		undoers.push(pusher);
		return doOperations(pusher);
	}

	@PostMapping("/addition")
	public ModelAndView addNumber() {
		AddTwoNumbersTogether adder = new AddTwoNumbersTogether(stack);
		return doOperations(adder);
	}

	@PostMapping("/subtraction")
	public ModelAndView subtractNumber() {
		Subtractor sub = new Subtractor(stack);
		return doOperations(sub);
	}

	@PostMapping("/divide")
	public ModelAndView divideNumber() {
		DivideTwoNumbersTogether divider = new DivideTwoNumbersTogether(stack);
		return doOperations(divider);
	}

	@PostMapping("/multiply")
	public ModelAndView multiplyNumber() {
		MultiplyTwoNumbers multiplier = new MultiplyTwoNumbers(stack);
		return doOperations(multiplier);
	}

	@PostMapping("/pow")
	public ModelAndView powNumber() {
		ExpoCalculaion expo = new ExpoCalculaion(stack);
		return doOperations(expo);
	}

	@PostMapping("/abs")
	public ModelAndView abs() {

		AbsoluterOfOneNumber absoluter = new AbsoluterOfOneNumber(stack);
		absoluter.goDoIt();
		return doOperations(absoluter);
	}

	@PostMapping("/clear")
	public ModelAndView clearTheStack() {
		StackClearer clearer = new StackClearer(stack);
		return doOperations(clearer);
	}

	@PostMapping("/swap")
	public ModelAndView swapMeet() {
		Swapper swapper = new Swapper(stack);
		return doOperations(swapper);
	}

	@PostMapping("/rotate")
	public ModelAndView rotatorCuff() {
		Rotator rotator = new Rotator(stack);
		return doOperations(rotator);
	}

	@PostMapping("/undo")
	public ModelAndView undoAction() {
		GoDoer undoer = undoers.pop();
		undoer.undo(stack);
		redoers.push(undoer);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/redo")
	public ModelAndView redoAction() {
		GoDoer redoer = redoers.pop();
		redoer.goDoIt();
		undoers.push(redoer);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	private ModelAndView doOperations(GoDoer caly) {
		caly.goDoIt();
		undoers.push(caly);
		redoers.clear();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
}
