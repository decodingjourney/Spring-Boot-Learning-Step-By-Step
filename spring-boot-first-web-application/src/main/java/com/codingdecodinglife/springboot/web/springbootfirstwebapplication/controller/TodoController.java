package com.codingdecodinglife.springboot.web.springbootfirstwebapplication.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@GetMapping("/list-todos")
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(name));
	
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		return (String)model.get("name");
	}
	
	@GetMapping("/add-todo")
	public String showTodosPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "", new Date(), false));
		
		return "todo";
	}
	
	@PostMapping("/add-todo")
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}
	
	@GetMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@GetMapping("/update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}
	
	@PostMapping("/update-todo")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		todo.setUser(getLoggedInUserName(model));
		service.updateTodo(todo);
		/*Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";*/
		return "redirect:/list-todos";
	}
	

}
