package com.gura.spring03.users.dao;

import com.gura.spring03.users.dto.UsersDto;

public interface UsersDao {
	public void insert(UsersDto dto);
	public void update(UsersDto dto);
	public void updatePwd(UsersDto dto);
	public void delete(int num);
	public UsersDto getData(int num);
}
