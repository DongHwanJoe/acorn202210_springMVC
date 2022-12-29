package com.gura.boot05;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *  [ jsp 페이지를 사용하기 위한 설정 ]
 *  1. pom.xml에 tomcat-embed-jasper와 jstl dependency를 추가한다.
 *  2. /webapp/WEB-INF/views/ 구조로 폴더를 src/main 하위에 만든다.
 *  3. view page의 prefix와 suffix 설정을 추가한다.
 *     application.properties에 설정한다면,
 *     
 *     spring.mvc.view.prefix=/WEB-INF/views/
 *     spring.mvc.view.suffix=.jsp
 *     
 *     위 내용을 추가한다.
 */
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		
		List<String> noticeList = new ArrayList<>();
		noticeList.add("Spring Boot Start");
		noticeList.add("asd");
		noticeList.add("zxc");
		
		request.setAttribute("noticeList", noticeList);
		
		return "home";
	}
}
