package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AddTwoNumbersTogether;
import com.lmig.gfc.rpn.models.DivideTwoNumbersTogether;
import com.lmig.gfc.rpn.models.MultiplyTwoNumbers;
import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.PushUndoer;
import com.lmig.gfc.rpn.models.Subtractor;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;
import com.lmig.gfc.rpn.models.Undoer;

@Controller
public class RpnController {
	private Stack<Double> stack;
	private Stack<Undoer> undoers;
	
	

	public RpnController() {
		stack = new Stack<Double>();
		undoers = new Stack<Undoer>();
	}

	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("stack", stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasUndoer", undoers.size()>0);
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOnToStack(double value) {
		stack.push(value);
		undoers.push(new PushUndoer());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/addition")
	public ModelAndView addNumber() {
		AddTwoNumbersTogether adder = new AddTwoNumbersTogether(stack);
		adder.goDoIt();
		undoers.push(adder);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/subtraction")
	public ModelAndView subtractNumber() {
		Subtractor sub = new Subtractor(stack);
		sub.goDoIt();
		undoers.push(sub);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	@PostMapping("/divide")
	public ModelAndView divideNumber() {
		DivideTwoNumbersTogether divider = new DivideTwoNumbersTogether(stack);
		divider.goDoIt();
		undoers.push(divider);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	@PostMapping("/multiply")
	public ModelAndView multiplyNumber() {
		MultiplyTwoNumbers multiplier = new MultiplyTwoNumbers(stack);
		multiplier.goDoIt();
		undoers.push(multiplier);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	@PostMapping("/abs")
	public ModelAndView abs() {

		double first = stack.pop();
		undoers.push(new OneArgumentUndoer(first));
		double result = Math.abs(first);
		stack.push(result);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	

	@PostMapping("/undo")
	public ModelAndView undoAction() {
		Undoer undoer = undoers.pop();
		undoer.undo(stack);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

}
