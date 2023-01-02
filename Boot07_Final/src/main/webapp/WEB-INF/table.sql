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


-- 상품테이블
CREATE TABLE shop(
   num NUMBER PRIMARY KEY, --상품번호
   name VARCHAR2(30), --상품이름
   price NUMBER, --상품가격
   remainCount NUMBER CHECK(remainCount >= 0) --재고갯수 
);

-- 고객 계좌 테이블
CREATE TABLE client_account(
   id VARCHAR2(30) PRIMARY KEY, -- 고객의 아이디
   money NUMBER CHECK(money >= 0), -- 고객의 잔고 
   point NUMBER
);

-- 주문 테이블
CREATE TABLE client_order(
   num NUMBER PRIMARY KEY, -- 주문번호
   id VARCHAR2(30), -- 주문 고객의 아이디
   code NUMBER, -- 주문한 상품의 번호 
   addr VARCHAR2(50) -- 배송 주소
);

-- 주문 테이블에 사용할 시퀀스 
CREATE SEQUENCE client_order_seq;


-- sample 데이터
INSERT INTO shop (num,name,price,remainCount)
VALUES(1, '사과', 1000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(2, '바나나', 2000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(3, '귤', 3000, 5);

-- 이미지 갤러리를 만들기 위한 테이블
CREATE TABLE board_gallery(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100),
	caption VARCHAR2(100), -- 이미지에 대한 설명
	imagePath VARCHAR2(100), -- 업로드된 이미지의 경로 ex(/resources/upload/xxx.jpg)
	regdate DATE -- 이미지 업로드 날짜
);

CREATE SEQUENCE board_gallery_seq;

