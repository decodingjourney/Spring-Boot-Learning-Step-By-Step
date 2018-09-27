package com.codingdecodinglife.springboot.web.springbootfirstwebapplication.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.codingdecodinglife.springboot.web.springbootfirstwebapplication.model.Todo;
import com.codingdecodinglife.springboot.web.springbootfirstwebapplication.service.LoginService;
import com.codingdecodinglife.springboot.web.springbootfirstwebapplication.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@GetMapping("/list-todos")
	public String showTodos(ModelMap model) {
		String name = (String)model.get("name");
		model.put("todos", service.retrieveTodos(name));
	
		return "list-todos";
	}
	
	@GetMapping("/add-todo")
	public String showTodosPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String)model.get("name"), "", new Date(), false));
		
		return "todo";
	}
	
	@PostMapping("/add-todo")
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		service.addTodo((String)model.get("name"), todo.getDesc(), new Date(), false);
		return "redirect:/list-todos";
	}
	
	@GetMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@GetMapping("/update-todo")
	public String updateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}
	

}
