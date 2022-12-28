package com.gura.spring04.gallery.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.gallery.dto.GalleryDto;
import com.gura.spring04.gallery.service.GalleryService;

@Controller
public class GalleryController {

	@Autowired
	private GalleryService service;
	
	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		return "gallery/list";
	}
	
	@RequestMapping("/gallery/uploadform")
	public String uploadform() {
		return "gallery/uploadform";
	}
	
	@RequestMapping("/gallery/upload")
	public String upload(GalleryDto dto, HttpSession session, HttpServletRequest request) {
		String writer = (String)session.getAttribute("id");
		String imagePath = (String)request.getParameter("imagePath");
		dto.setWriter(writer);
		dto.setImagePath(imagePath);
		service.saveImage(dto);
		return "gallery/upload";
	}
	
	@ResponseBody
	@RequestMapping(value = "/gallery/image_upload", method = RequestMethod.POST)
	public Map<String, Object> imageUpload(MultipartFile image, HttpServletRequest request) {

		return service.saveImagePath(request, image);
	}
	
	@RequestMapping("/gallery/detail")
	public String detail(HttpServletRequest request) {
		service.getDetail(request);
		return "gallery/detail";
	}
	
	@RequestMapping("/gallery/updateform")
	public String updateform(HttpServletRequest request) {
		service.getData(request);
		return "gallery/updateform";
	}
	
	@RequestMapping("/gallery/update")
	public String update(GalleryDto dto, HttpServletRequest request) {
		service.updateImage(dto, request);
		return "gallery/update";
	}
	
	@RequestMapping("/gallery/delete")
	public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
		service.deleteImage(num, request);
		mView.setViewName("redirect:/gallery/list");
		return mView;
	}
}
