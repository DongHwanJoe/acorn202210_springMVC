package com.gura.spring02.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;

@Controller
public class MemberController {
	
	//필요한 객체 주입
	@Autowired
	private MemberDao dao;
	
	@RequestMapping("/member/delete")
	public String delete(int num) {// get 방식 전송 파라미터도 추출 가능
		dao.delete(num);
		return "redirect:/member/list";
	}
	
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) {//폼 전송되는 name, addr이 자동으로 추출되어서 MemberDto에 담겨서 전달된다.
		dao.insert(dto);
		return "member/insert";
	}
	
	@RequestMapping("/member/insertform")
	public String insertform() {
		// /WEB-INF/views/member/insertform.jsp로 forward 이동
		return "member/insertform";
	}
	
	@RequestMapping("/member/list")
	public ModelAndView getList(ModelAndView mView) {
		//주입받은 MemberDao 객체를 이용해서 회원목록 생성
		List<MemberDto> list = dao.getList();
		
		//view page에 전달할 Model을 담는다
		mView.addObject("list", list);
		//view page 정보 담기
		mView.setViewName("member/list");
		
		//리턴
		return mView;
	}
}