package com.gura.boot07.api;

import java.util.List;

public interface TodoDao {
	public List<TodoDto> getList();
	public void insert(TodoDto dto);
	public void delete(int num);
}
