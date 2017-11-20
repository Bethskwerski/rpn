package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;

@Controller
public class RpnController {
	private Stack<Double> stack;
	private OneArgumentUndoer undoer;
	

	public RpnController() {
		stack = new Stack<Double>();
	}

	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("stack", stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasUndoer", undoer != null);
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOnToStack(double value) {
		stack.push(value);
		undoer = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/addition")
	public ModelAndView addNumber() {

		double first = stack.pop();
		double second = stack.pop();
		double result = second + first;
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/subtraction")
	public ModelAndView subtractNumber() {

		double first = stack.pop();
		double second = stack.pop();
		double result = second - first;
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	@PostMapping("/abs")
	public ModelAndView abs() {

		double first = stack.pop();
		undoer = new OneArgumentUndoer(first);
		double result = Math.abs(first);
		stack.push(result);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	

	@PostMapping("/undo")
	public ModelAndView undoAction() {
		undoer.undo(stack);
		undoer = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

}
