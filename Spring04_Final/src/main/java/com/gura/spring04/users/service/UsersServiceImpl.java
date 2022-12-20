package com.gura.spring04.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.users.dao.UsersDao;
import com.gura.spring04.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao dao;
	
	@Override
	public Map<String, Object> isExistId(String inputId) {
		return null;
	}

	@Override
	public void addUser(UsersDto dto) {
		//가입 시 입력한 비밀번호를 읽어오기
		String pwd = dto.getPwd();
		//암호화 후
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(pwd);
		//dto에 재입력
		dto.setPwd(encodedPwd);
		
		//암호화된 비밀번호가 들어있는 dto를 dao에 전달해서 새로운 회원정보를 추가한다.
		dao.insert(dto);
	}

	@Override
	public void loginProcess(UsersDto dto, HttpSession session) {
		//입력한 정보가 맞는지 여부
		boolean isValid = false;
		//아이디를 이용해서 회원 정보를 얻어온다.
		UsersDto resultDto = dao.getData(dto.getId());
		if(resultDto != null) {
			isValid = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());
		}
		if(isValid) {
			//Bycrpt 클래스의 static 메소드를 이용해서 입력한 비밀번호와 암호화된 비밀번호 일치 여부를 확인
			session.setAttribute("id", resultDto.getId());
		}
	}

	@Override
	public void getInfo(HttpSession session, ModelAndView mView) {
		//로그인 된 아이디를 읽어오기
		String id = (String)session.getAttribute("id");
		//DB에서 회원정보 얻어오기
		UsersDto dto = dao.getData(id);
		//ModelAndView 객체에 담기
		mView.addObject("dto", dto);
	}

	@Override
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(UsersDto dto, HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(HttpSession session, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

}
