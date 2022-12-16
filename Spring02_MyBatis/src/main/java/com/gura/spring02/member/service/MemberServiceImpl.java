package com.gura.spring02.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;

//component scan을 통해서 스프링이 관리하는 bean이 되게 하기위해 필요한 어노테이션
@Service
public class MemberServiceImpl implements MemberService{

	//핵심 의존 객체 주입 받기
	@Autowired
	private MemberDao dao;
	
	@Override
	public void addMember(MemberDto dto) {
		dao.insert(dto);
	}

	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
	}

	@Override
	public void deleteMember(int num) {
		dao.delete(num);
	}

	@Override
	public void getMember(int num, ModelAndView mView) {
		//회원 한 명의 정보를 얻어오기
		MemberDto dto = dao.getData(num);
		//Controller에서 전달한 ModelAndView 객체에 담기 (request scope에 담기 위해서)
		mView.addObject("dto", dto);
	}

	@Override
	public void getListMember(ModelAndView mView) {
		//회원 목록을 얻어오기
		List<MemberDto> list = dao.getList();
		//Controller에서 전달한 ModelAndView객체에 담기 (request scope에 담기 위해서)
		mView.addObject("list", list);
	}

}
