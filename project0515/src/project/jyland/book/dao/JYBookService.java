package project.jyland.book.dao;

import java.util.List;

import project.jyland.book.model.JYBook;
import project.jyland.book.model.JYBookParam;
import project.jyland.book.model.JYBookRatecount;
import project.jyland.book.model.JYBookimg;
import project.jyland.book.model.JYBooktitle;
import project.jyland.book.model.JYBooktitleParam;

public interface JYBookService {
	
	void createNewBook(JYBooktitle booktitle);
	void writeNewBook(JYBook book, JYBookimg[] imgs);
	void setRate(JYBookRatecount ratecount);
	List<JYBooktitle> getBookTitleList(JYBooktitleParam param);
	List<JYBook> getBookDetailList(JYBookParam param);
	List<JYBookimg> getImgList(JYBook book);
	JYBooktitle getBookTitle(JYBooktitle booktitle);
	JYBook getBookDetail(JYBook book);
	int getBookTitleTotalCount();
	int getBookDetailTotalCount();
	JYBook getRate(JYBook book);
	int checkRate(JYBookRatecount ratecount);
	void updateReadcount(JYBook book);
	void updateRate(JYBook book);
	void updateUdate(JYBook book);

}
