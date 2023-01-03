package com.gura.boot07.users.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.users.dto.UsersDto;
import com.gura.boot07.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	//프로필 이미지 요청에 대한 응답을 할 메소드를 따로 만들어야 한다.
	//이미지 데이터가 응답되어야 한다.
	//웹브라우저에게 이미지 데이터를 응답한다고 알려야 한다.
	//응답할 미이지의 이름은 떄에 따라 다르다.
	
	/*
	 *  이 컨트롤러 메소드에서 응답한 byte[] 배열을 클라이언트에게 응답하는 방법
	 *  1. @ResponseBody
	 *  2. byte[] 배열 리턴
	 *  
	 *  응답된 byte[] 배열에 있는 데이터를 이미지 데이터로 클라이언트 웹브라우저가 인식하게 하는 방법
	 *  produces = MediaType.IMAGE_JPEG_VALUE 
	 */
	@GetMapping(
			value = "/users/profile/{imageName}",
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE}
	)
	@ResponseBody
	public byte[] usersProfile(@PathVariable("imageName") String imageName) throws IOException {
		
		String absolutePath = fileLocation + File.separator + imageName;
		//파일에서 읽어들일 InputStream
		InputStream is = new FileInputStream(absolutePath);
		//이미지데이터(byte)를 읽어서 배열에 담아 클라이언트에게 응답한다.
		return IOUtils.toByteArray(is);
	}
	
	/*
	 * GET 방식 /users/signup_form 요청을 처리할 메소드
	 * - 요청방식이 다르면 실행되지 않는다.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	//회원가입 요청처리
	@RequestMapping(method = RequestMethod.POST, value = "/users/signup")
	public ModelAndView signup(ModelAndView mView, UsersDto dto) {
		service.addUser(dto);
		mView.setViewName("users/signup");
		return mView;
	}
	
	//로그인 폼 요청 처리
	@RequestMapping(method = RequestMethod.GET, value = "/users/loginform")
	public String loginForm() {
		return "users/loginform";
	}
	
	//로그인 요청 처리
	@RequestMapping("/users/login")
	public ModelAndView login(ModelAndView mView, UsersDto dto, String url, HttpSession session) {
		/*
		 * 서비스에서 비즈니스 로직을 처리할 때 필요로 하는 객체를 컨트롤러에서 직접 전달해줘야 한다.
		 * 주로 HttpServletRequest, HttpServletResponse, HttpSession, ModelAndView
		 * 등등의 객체이다.
		 */
		service.loginProcess(dto, session);
		
		//로그인 후에 가야할 목적지 정보를 인코딩 하지않는 것과 인코딩 한 것을 모두 ModelAndView 객체에 담기
		String encodedUrl = URLEncoder.encode(url);
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		//view page로 forward 이동해서 응답
		mView.setViewName("users/login");
		return mView;
	}
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "users/logout";
	}
	
	@RequestMapping("/users/info")
	public ModelAndView info(HttpSession session, ModelAndView mView) {
		service.getInfo(session, mView);
		
		mView.setViewName("users/info");
		return mView;
	}
	
	@RequestMapping("/users/pwd_updateform")
	public String pwdUpdateForm() {
		return "users/pwd_updateform";
	}
	
	@RequestMapping("/users/pwd_update")
	public ModelAndView pwdUpdate(UsersDto dto, ModelAndView mView, HttpSession session) {
		//서비스에 필요한 객체의 참조값을 전달해서 비밀번호 수정 로직을 처리한다.
		service.updateUserPwd(session, dto, mView);
		//view page로 forward 이동 후 결과 응답
		mView.setViewName("users/pwd_update");
		return mView;
	}
	
	@RequestMapping("/users/delete")
	public ModelAndView delete(HttpSession session, ModelAndView mView) {
		service.deleteUser(session, mView);
		
		mView.setViewName("users/delete");
		return mView;
	}
	
	@RequestMapping("/users/updateform")
	public ModelAndView updateform(HttpSession session, ModelAndView mView) {
		service.getInfo(session, mView);
		mView.setViewName("users/updateform");
		return mView;
	}
	
	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public ModelAndView update(UsersDto dto, HttpSession session, ModelAndView mView,
			HttpServletRequest request) {
		//서비스를 이용해서 개인정보를 수정
		service.updateUser(dto, session);
		mView.setViewName("redirect:/users/info");
		return mView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/users/profile_upload", method = RequestMethod.POST)
	public Map<String, Object> profileUpload(MultipartFile image, HttpServletRequest request) {
		
		//서비스를 이용해서 이미지를 upload폴더에 저장하고 리턴되는 Map을 리턴해서 json문자열 응답하기
		return service.saveProfileImage(request, image);
	}
}
