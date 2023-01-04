package com.gura.boot07;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		
		List<String> noticeList = new ArrayList<>();
		noticeList.add("청목");
		noticeList.add("뢰벤");
		noticeList.add("오목");
		
		request.setAttribute("noticeList", noticeList);
		
		return "home";
	}
}
