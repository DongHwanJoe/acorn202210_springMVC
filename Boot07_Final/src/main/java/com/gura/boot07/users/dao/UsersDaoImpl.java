package com.gura.boot07.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.boot07.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{

	@Autowired
	private SqlSession session;
	
	//해당 아이디가 존재하는지 여부를 리턴하는 메소드
	@Override
	public boolean isExist(String inputId) {
		//id를 이용해서 select 하여 null인지 여부를 확인
		UsersDto dto = session.selectOne("users.getData", inputId);
	
		boolean isExist = dto == null ? false : true;
		
		return isExist;
	}

	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

	@Override
	public UsersDto getData(String id) {
		UsersDto dto = session.selectOne("users.getData", id);
		return dto;
	}

	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd", dto);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}

}
