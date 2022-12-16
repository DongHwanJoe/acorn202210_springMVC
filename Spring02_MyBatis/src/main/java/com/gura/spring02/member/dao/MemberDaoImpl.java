package com.gura.spring02.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring02.member.dto.MemberDto;

//component scan 을 통해서 spring이 관리하는 bean이 될 수 있도록 어노테이션 붙여놓기
@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSession session; // select, insert, update, delete 작업을 하기위한 핵심 의존 객체
	
	@Override
	public void insert(MemberDto dto) {
		/*
		 * mapper's namespace => member
		 * sql's id => insert
		 */
		session.insert("member.insert", dto);
	}

	@Override
	public void update(MemberDto dto) {
		/*
		 * mapper's namespace => member
		 * sql's id => update
		 */
		session.update("member.update", dto);
	}

	@Override
	public void delete(int num) {
		/*
		 *  member's namespace => member
		 *  sql's id => delete
		 *  parameterType => int
		 */
		session.delete("member.delete", num);
	}

	@Override
	public MemberDto getData(int num) {
		/*
		 * mapper's namespace => member
		 * sql's id => getData
		 * parameterType => int
		 * 
		 * - selectOne() 메소드를 호출하면 mapper xml의 resultType이 바로 메소드의 return type이 된다.
		 * 
		 * resultType => MemberDto
		 * return type => MemberDto
		 */
		MemberDto dto = session.selectOne("member.getData", num);
		
		return dto;
	}

	@Override
	public List<MemberDto> getList() {
		/*
		 * mapper's namespace => member
		 * sql's id => getList
		 * 
		 * - selectList() 메소드를 호춯하면 List가 return type이고 mapper xml의 resultType이
		 *   List의 generic type이 된다.
		 *   
		 * resultType => MemberDto
		 * return type => List
		 */
		List<MemberDto> list = session.selectList("member.getList");
		
		return list;
	}

}
