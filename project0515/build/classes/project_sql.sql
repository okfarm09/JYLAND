SELECT * FROM JYUSER;
ALTER TABLE JYUSER ADD(email VARCHAR2(50));

INSERT INTO JYUSER(id, upwd, NICKNAME, email)
VALUES('admin', 'projectadmin', '관리자', 'okfarm09@gmail.com');
UPDATE JYUSER SET auth=1 WHERE ID='admin';

SELECT id, upwd, nickname, email, auth
FROM JYUSER WHERE id='admin' AND upwd='projectadmin';

SELECT * FROM JYBOARD;
SELECT * FROM JYREGION;
SELECT * FROM JYCOMMENT;
SELECT * FROM JYCAT;

CREATE TABLE JYBOARDLHCOUNT(
	SEQ			NUMBER			PRIMARY KEY,
	BOARDSEQ	NUMBER			NOT NULL,
	USERID		VARCHAR2(50)	NOT NULL
);

CREATE SEQUENCE jy_lh_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE JYBOARDLHCOUNT ADD CONSTRAINT JYBOARDLH_BOARDSEQ_FK 
FOREIGN KEY(BOARDSEQ) REFERENCES JYBOARD(SEQ);

ALTER TABLE JYBOARDLHCOUNT ADD CONSTRAINT JYBOARDLH_USERID_FK 
FOREIGN KEY(USERID) REFERENCES JYUSER(ID);

ALTER TABLE JYUSER DROP CONSTRAINT SYS_C007386 CASCADE;
SELECT * FROM ALL_CONSTRAINTS
WHERE TABLE_NAME = 'JYUSER';

ALTER TABLE JYCAT DROP(CATPREP);

SELECT CATPREP FROM JYCAT
WHERE CATID=1;

ALTER TABLE JYCAT ADD(catprep VARCHAR2(10));
ALTER TABLE JYCAT MODIFY(catprep VARCHAR2(10) NOT NULL);
UPDATE JYCAT SET catprep='free' WHERE CATID=1;

INSERT INTO JYCAT VALUES(1, '자유게시판');
INSERT INTO JYCAT VALUES(2, '토론게시판');
INSERT INTO JYCAT VALUES(3, '사진게시판');
INSERT INTO JYCAT VALUES(4, '보드게시판');
INSERT INTO JYCAT VALUES(5, '신고게시판');
INSERT INTO JYCAT VALUES(6, '만화게시판');
INSERT INTO JYCAT VALUES(7, '영화게시판');
INSERT INTO JYCAT VALUES(8, '뻘글게시판');
INSERT INTO JYCAT VALUES(9, '운동게시판');
INSERT INTO JYCAT VALUES(10, '인방게시판');
INSERT INTO JYCAT VALUES(11, '게임게시판');
INSERT INTO JYCAT VALUES(12, '노래게시판');
INSERT INTO JYCAT VALUES(13, '소설게시판');
INSERT INTO JYCAT VALUES(14, '동물게시판');


SELECT seq, id, title, content, wdate FROM
(SELECT ROW_NUMBER() over(ORDER BY seq DESC) AS rnum,
seq, id, title, content, wdate FROM JYBOARD WHERE catid=1
) a WHERE rnum BETWEEN 1 AND 20;

ALTER TABLE JYBOARD MODIFY(ip VARCHAR2(16) DEFAULT '000.000.000.000');

INSERT INTO JYREGION VALUES(0, '선택 없음');
UPDATE JYBOARD SET regionid=0 WHERE seq=4;

SELECT * FROM JYMESSAGE;

SELECT SEQ, SID, RID, CONTENT, WDATE, READCHK 
FROM (
SELECT ROW_NUMBER() OVER(ORDER BY SEQ DESC) AS RNUM, 
SEQ, SID, RID,  SUBSTR(CONTENT,1,20) AS CONTENT, 
WDATE, READCHK 
FROM JYMESSAGE WHERE RID='namnam'
) A WHERE RNUM BETWEEN 1 AND 30

SELECT RNUM, ID, UPWD, NICKNAME, EMAIL, AUTH
FROM (
SELECT ROW_NUMBER() OVER(ORDER BY ID ASC) AS RNUM,
ID, UPWD, NICKNAME, EMAIL, AUTH 
FROM JYUSER 
) WHERE RNUM BETWEEN 1 AND 30
SELECT NVL(count(*), 0) AS cnt FROM JYUSER

UPDATE JYUSER SET UPWD='123', NICKNAME='탈퇴 회원', EMAIL ='#'
WHERE ID='22'

SELECT constraint_name, search_condition FROM user_constraints where table_name = 'jyuser';
ALTER TABLE JYUSER DROP CONSTRAINT PROJECT.SYS_C007386 

alter table JYBOARD add (notice number default 0)
insert into JYCAT values(9999, '공지')
SELECT CATID, CATNAME FROM JYCAT WHERE CATID!=0;

INSERT INTO JYCAT(CATID, CATNAME) 
VALUES((SELECT NVL(MAX(CATID),0)+1 FROM JYCAT), '야구게시판');

UPDATE JYCAT SET CATID = 0 WHERE CATID = 9999
UPDATE JYBOARD SET CATID = 0 WHERE SEQ=67 

SELECT SEQ, ID, TITLE, CONTENT, LOCATION, READCOUNT, LIKECOUNT, 
HATECOUNT, WDATE, UPLOAD, CATID, DEL, IP, REGIONID, NOTICE
FROM JYBOARD
WHERE CATID = 0

SELECT A.SEQ, A.TITLE, C.CATNAME,
A.WDATE, A.CATID, A.DEL, A.COMMENTCOUNT 
FROM (
SELECT ROW_NUMBER() OVER(ORDER BY SEQ DESC) AS RNUM,
SEQ, TITLE, WDATE, CATID, DEL, NVL(COMMENTCOUNT, 0)COMMENTCOUNT
FROM JYBOARD 
LEFT JOIN (SELECT BOARDSEQ, COUNT(JYCOMMENT.SEQ) AS COMMENTCOUNT 
FROM JYCOMMENT GROUP BY BOARDSEQ) 
ON SEQ=BOARDSEQ 
WHERE DEL=0 AND NOT CATID=0
) A JOIN JYCAT C ON A.CATID=C.CATID
WHERE RNUM BETWEEN 1 AND 20
ORDER BY WDATE DESC

select * from JYBOARDLHCOUNT

SELECT A.SEQ, A.TITLE, A.LIKECOUNT, C.CATNAME,
A.WDATE, A.CATID, A.DEL, A.COMMENTCOUNT 
FROM (
SELECT ROW_NUMBER() OVER(ORDER BY SEQ DESC) AS RNUM,
SEQ, TITLE, LIKECOUNT, WDATE, CATID, DEL, NVL(COMMENTCOUNT, 0)COMMENTCOUNT
FROM JYBOARD 
LEFT JOIN (SELECT BOARDSEQ, COUNT(JYCOMMENT.SEQ) AS COMMENTCOUNT 
FROM JYCOMMENT GROUP BY BOARDSEQ) 
ON SEQ=BOARDSEQ 
WHERE DEL=0 AND NOT CATID=0
) A JOIN JYCAT C ON A.CATID=C.CATID
WHERE RNUM BETWEEN 1 AND 20
ORDER BY LIKECOUNT DESC
