package com.gura.spring02.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.todo.dto.TodoDto;
import com.gura.spring02.todo.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService service;
	
	@RequestMapping("/todo/insertform")
	public String insertform() {
		return "todo/insertform";
	}
	
	@RequestMapping("/todo/insert")
	public String insert(TodoDto dto) {
		service.addTodo(dto);
		return "todo/insert";
	}
	
	@RequestMapping("/todo/updateform")
	public ModelAndView updateform(int num, ModelAndView mView) {
		service.getTodo(num, mView);
		mView.setViewName("todo/updateform");
		
		return mView;
	}
	
	@RequestMapping("/todo/update")
	public String update(TodoDto dto) {
		service.updateTodo(dto);
		return "todo/update";
	}
	
	@RequestMapping("/todo/delete")
	public String delete(int num) {
		service.deleteTodo(num);
		return "redirect:/todo/list";
	}
	
	@RequestMapping("/todo/list")
	public ModelAndView getList(ModelAndView mView) {
		service.getListTodo(mView);
		mView.setViewName("todo/list");
		return mView;
	}
}
