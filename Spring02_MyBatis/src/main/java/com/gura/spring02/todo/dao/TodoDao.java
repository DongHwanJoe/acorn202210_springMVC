package com.gura.spring02.todo.dao;

import java.util.List;

import com.gura.spring02.todo.dto.TodoDto;

public interface TodoDao {
	public void insert(TodoDto dto);
	public void update(TodoDto dto);
	public void delete(int num);
	public TodoDto getData(int num);
	public List<TodoDto> getList();
}
