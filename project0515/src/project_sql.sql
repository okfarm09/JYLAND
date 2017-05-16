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
INSERT INTO JYCAT VALUES(1, '자유게시판');

SELECT seq, id, title, content, wdate FROM
(SELECT ROW_NUMBER() over(ORDER BY seq DESC) AS rnum,
seq, id, title, content, wdate FROM JYBOARD WHERE catid=1
) a WHERE rnum BETWEEN 1 AND 20;

alter table JYCOMMENT rename column seq_reply to seqreply;

SELECT SEQ, ID, TITLE, CONTENT, LOCATION, READCOUNT, LIKECOUNT, 
HATECOUNT, WDATE, UPLOAD, CATID, DEL, IP, REGIONID, COMMENTCOUNT 
FROM (
SELECT ROW_NUMBER() OVER(ORDER BY SEQ DESC) AS RNUM,
SEQ, ID, TITLE, CONTENT, LOCATION, READCOUNT, LIKECOUNT, 
HATECOUNT, WDATE, UPLOAD, CATID, DEL, IP, REGIONID, NVL(COMMENTCOUNT, 0)COMMENTCOUNT
FROM JYBOARD 
LEFT JOIN (SELECT BOARDSEQ, COUNT(JYCOMMENT.SEQ) AS COMMENTCOUNT 
FROM JYCOMMENT GROUP BY BOARDSEQ) 
ON SEQ=BOARDSEQ 
WHERE ID='namnam' AND DEL=0
) A WHERE RNUM BETWEEN 1 AND 10

SELECT SEQ, ID, CONTENT, BOARDSEQ, WDATE, LIKECOUNT, HATECOUNT, IP, SEQREPLY
FROM JYCOMMENT WHERE ID='jy'
ORDER BY REF ASC, SEQ ASC

SELECT NVL(count(*), 0) AS cnt FROM JYBOARD WHERE ID='jy'

alter table JYCOMMENT add (delflag number default 0)
UPDATE JYCOMMENT SET DELFLAG = 1 WHERE SEQ=103