package com.gura.spring04.gallery.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.gallery.dto.GalleryDto1;



public interface GalleryService1 {
	//갤러리의 list 가져오기
	public void getList(HttpServletRequest request);
	//갤러리에 사진 upload & DB 저장하기
	public void saveImage(GalleryDto1 dto, HttpServletRequest request);
	//갤러리에 사진 저장하기 - ajax
	public Map<String, Object> uploadAjaxImage(GalleryDto1 dto, HttpServletRequest request);
	//갤러리에 사진 저장하기 - db에만 저장(upload 작업은 이미 완료)
	public void insert(GalleryDto1 dto, HttpServletRequest request);
	//갤러리 detail 페이지에 필요한 data를 ModelAndView 에 저장
	public void getDetail(ModelAndView mView, int num);
}