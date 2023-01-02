package com.gura.boot07.gallery.dao;

import java.util.List;

import com.gura.boot07.gallery.dto.GalleryDto;

public interface GalleryDao {
	public List<GalleryDto> getList(GalleryDto dto);
	public int getCount(GalleryDto dto);
	public GalleryDto getData(int num);
	public GalleryDto getData(GalleryDto dto);
	public void insert(GalleryDto dto);
	public void update(GalleryDto dto);
	public void delete(int num);
}
