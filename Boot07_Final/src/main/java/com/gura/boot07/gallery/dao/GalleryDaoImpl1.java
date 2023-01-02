package com.gura.boot07.gallery.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.boot07.gallery.dto.GalleryDto1;



@Repository
public class GalleryDaoImpl1 implements GalleryDao1 {

	@Autowired
	private SqlSession session;
	
	/*
	 * Mapper's namespace : gallery
	 * sql's id : getList
	 * parameterType : GalleryDto
	 * resultType : GalleryDto
	 */
	//gallery 의 모든 리스트 가져오기
	@Override
	public List<GalleryDto1> getList(GalleryDto1 dto) {
		
		return session.selectList("gallery1.getList", dto);
	}
	
	/*
	 * Mapper's namespace : gallery
	 * sql's id : getCount
	 * resultType : int
	 */
	//row 의 총 개수 구하기
	@Override
	public int getCount() {
		return session.selectOne("gallery1.getCount");
	}
	
	/*
	 * Mapper's namespace : gallery
	 * sql's id : insert
	 * parameterType : GalleryDto
	 */
	@Override
	public void insert(GalleryDto1 dto) {
		session.insert("gallery1.insert", dto);
	}
	
	/*
	 * Mapper's namespace : gallery
	 * sql's id : getData
	 * parameterType : int
	 * resultType : GalleryDto
	 */
	@Override
	public GalleryDto1 getData(int num) {
		return session.selectOne("gallery1.getData", num);
	}
	
}
