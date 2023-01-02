package com.gura.boot07.gallery.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gura.boot07.gallery.dto.GalleryDto;

public interface GalleryService {
	public void getList(HttpServletRequest request);
	public void getDetail(HttpServletRequest request);
	public void saveImage(GalleryDto dto);
	public void updateImage(GalleryDto dto, HttpServletRequest request);
	public void deleteImage(int num, HttpServletRequest request);
	public void getData(HttpServletRequest request);
	public Map<String, Object> saveImagePath(HttpServletRequest request, MultipartFile mFile);
}
