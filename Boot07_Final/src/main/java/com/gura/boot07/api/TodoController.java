package com.gura.boot07.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  spring 5.0 부터 추가된 RestController 어노테이션을 붙이면
 *  json 문자열을 응답할 때 @ResponseBody를 생략할 수 있다.
 */

@RestController
public class TodoController {
	
	@Autowired
	private TodoDao dao;
	
	@GetMapping("/api/todo/list")
	public List<TodoDto> list(){
		return dao.getList();
	}
	
	@PostMapping("/api/todo/insert")
	public Map<String, Object> insert(String content){
		TodoDto dto = new TodoDto();
		dto.setContent(content);
		dao.insert(dto);
		
		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	
	@RequestMapping("/api/todo/delete")
	public Map<String, Object> delete(int num){
		dao.delete(num);

		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
}
