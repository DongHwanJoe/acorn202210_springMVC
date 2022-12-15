package com.gura.spring01;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		//응답에 필요한 데이터(Model) 이라고 가정
		List<String> noticeList = new ArrayList<String>();
		noticeList.add("날씨가 많이 추워졌네요");
		noticeList.add("어쩌구");
		noticeList.add("저쩌구");
		
		//Model을 request scope에 특정 key값으로 담는다.
		request.setAttribute("noticeList",  noticeList);
		
		// /WEB-INF/views/home.jsp 페이지로 응답을 위임(forward 이동)
		return "home";
	}
	
}
