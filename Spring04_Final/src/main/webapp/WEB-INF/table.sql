-- 게시들의 번호를 얻어낼 시퀀스
CREATE SEQUENCE board_cafe_seq;

-- 댓글을 지정할 테이블
CREATE TABLE board_cafe_comment(
	num NUMBER PRIMARY KEY, -- 댓글의 글 번호
	writer VARCHAR2(100), -- 댓글의 작성자 아이디
	content VARCHAR2(500), -- 댓글 내용
	target_id VARCHAR2(100), -- 댓들의 대상자 아이디
	ref_group NUMBER,
	comment_group NUMBER,
	deleted CHAR(3) DEFAULT 'no',
	regdate DATE
);
-- 댓글의 글번호를 얻어낼 시퀀스
CREATE SEQUENCE board_cafe_comment_seq;
