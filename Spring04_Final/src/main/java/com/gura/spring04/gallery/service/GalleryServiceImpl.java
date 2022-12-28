package com.gura.spring04.gallery.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring04.exception.NotDeleteException;
import com.gura.spring04.file.dto.FileDto;
import com.gura.spring04.gallery.dao.GalleryDao;
import com.gura.spring04.gallery.dto.GalleryDto;

@Service
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	@Override
	public void getList(HttpServletRequest request) {
		final int PAGE_ROW_COUNT = 5;
		final int PAGE_DISPLAY_COUNT = 5;

		int pageNum = 1;

		String strPageNum = request.getParameter("pageNum");
		if (strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}

		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		if (keyword == null) {
			keyword = "";
			condition = "";
		}

		String encodedK = URLEncoder.encode(keyword);

		GalleryDto dto = new GalleryDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		if (!keyword.equals("")) {
			if (condition.equals("caption")) {// 설명 검색인 경우
				dto.setCaption(keyword);
			} else if (condition.equals("writer")) { // 작성자 검색인 경우
				dto.setWriter(keyword);
			}
		}

		List<GalleryDto> list = galleryDao.getList(dto);
		int totalRow = galleryDao.getCount(dto);
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		if (endPageNum > totalPageCount) {
			endPageNum = totalPageCount;
		}

		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("condition", condition);
	}

	@Override
	public void getDetail(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));

		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		if (keyword == null) {
			keyword = "";
			condition = "";
		}

		GalleryDto dto = new GalleryDto();
		dto.setNum(num);
		if (!keyword.equals("")) {
			if (condition.equals("caption")) {// 설명 검색인 경우
				dto.setCaption(keyword);
			} else if (condition.equals("writer")) { // 작성자 검색인 경우
				dto.setWriter(keyword);
			}
		}

		GalleryDto resultDto = galleryDao.getData(dto);

		String encodedK = URLEncoder.encode(keyword);

		
		request.setAttribute("dto", resultDto);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
		request.setAttribute("condition", condition);
	}

	@Override
	public void saveImage(GalleryDto dto) {
		galleryDao.insert(dto);
	}

	@Override
	public void updateImage(GalleryDto dto, HttpServletRequest request) {
		String caption = (String)request.getParameter("caption");
		String imagePath = (String)request.getParameter("imagePath");
		dto.setCaption(caption);
		dto.setImagePath(imagePath);
		galleryDao.update(dto);
	}

	@Override
	public void deleteImage(int num, HttpServletRequest request) {
		
		GalleryDto dto = galleryDao.getData(num);
		
		//글 작성자와 로그인된 아이디의 일치여부에 따라 삭제 및 예외 발생시키기
		String id = (String)request.getSession().getAttribute("id");
		if(!dto.getWriter().equals(id)) {
			//예외를 발생시키면 해당예외를 처리하는 곳으로 실행의 흐름이 넘어간다.
			throw new NotDeleteException("남의 파일 지우기 없기");
		}
		
		String[] filePath = dto.getImagePath().split("/");
		String fileName = filePath[3];
		String path = request.getServletContext().getRealPath("/resources/upload")+
				File.separator+fileName;
		new File(path).delete();	
		galleryDao.delete(num);
	}

	@Override
	public void getData(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		GalleryDto dto = galleryDao.getData(num);
		request.setAttribute("dto", dto);
	}

	@Override
	public Map<String, Object> saveImagePath(HttpServletRequest request, MultipartFile mFile) {
		// 업로드된 파일에 대한 정보를 MultipartFile 객체를 이용해서 얻어낼수 있다.

		// 원본 파일명
		String orgFileName = mFile.getOriginalFilename();
		// upload 폴더에 저장할 파일명을 직접구성한다.
		// 1234123424343xxx.jpg
		String saveFileName = System.currentTimeMillis() + orgFileName;

		// webapp/upload 폴더까지의 실제 경로 얻어내기
		String realPath = request.getServletContext().getRealPath("/resources/upload");
		// upload 폴더가 존재하지 않을경우 만들기 위한 File 객체 생성
		File upload = new File(realPath);
		if (!upload.exists()) {// 만일 존재 하지 않으면
			upload.mkdir(); // 만들어준다.
		}
		try {
			// 파일을 저장할 전체 경로를 구성한다.
			String savePath = realPath + File.separator + saveFileName;
			// 임시폴더에 업로드된 파일을 원하는 파일을 저장할 경로에 전송한다.
			mFile.transferTo(new File(savePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// json 문자열을 출력하기 위한 Map 객체 생성하고 정보 담기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imagePath", "/resources/upload/" + saveFileName);
		
		return map;
	}
}
