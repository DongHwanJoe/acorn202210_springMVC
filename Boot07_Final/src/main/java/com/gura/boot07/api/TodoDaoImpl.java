package com.gura.boot07.api;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDaoImpl implements TodoDao{

	@Autowired SqlSession session;
	
	@Override
	public List<TodoDto> getList() {
		return session.selectList("todo.getList");
	}

	@Override
	public void insert(TodoDto dto) {
		session.insert("todo.insert", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("todo.delete", num);
	}

}
