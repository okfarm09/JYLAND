package project.jyland.book.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.book.model.JYBook;
import project.jyland.book.model.JYBookParam;
import project.jyland.book.model.JYBookRatecount;
import project.jyland.book.model.JYBookimg;
import project.jyland.book.model.JYBooktitle;
import project.jyland.book.model.JYBooktitleParam;

@Service
public class JYBookServiceImpl implements JYBookService {

	@Autowired
	private JYBookDao jYBookDao;
	
	@Override
	@Transactional
	public void createNewBook(JYBooktitle booktitle) {
		jYBookDao.createNewBook(booktitle);
	}

	@Override
	@Transactional
	public void writeNewBook(JYBook book, JYBookimg[] imgs) {
		jYBookDao.writeBook(book);
		for(JYBookimg img:imgs) {
			jYBookDao.uploadImg(img);
		}
	}

	@Override
	@Transactional
	public void setRate(JYBookRatecount ratecount) {
		jYBookDao.setRate(ratecount);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYBooktitle> getBookTitleList(JYBooktitleParam param) {
		return jYBookDao.getBookTitleList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYBook> getBookDetailList(JYBookParam param) {
		return jYBookDao.getBookDetailList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYBookimg> getImgList(JYBook book) {
		return jYBookDao.getImgList(book);
	}

	@Override
	@Transactional(readOnly=true)
	public JYBooktitle getBookTitle(JYBooktitle booktitle) {
		return jYBookDao.getBookTitle(booktitle);
	}

	@Override
	@Transactional(readOnly=true)
	public JYBook getBookDetail(JYBook book) {
		return jYBookDao.getBookDetail(book);
	}

	@Override
	@Transactional(readOnly=true)
	public int getBookTitleTotalCount() {
		return jYBookDao.getBookTitleTotalCount();
	}

	@Override
	@Transactional(readOnly=true)
	public int getBookDetailTotalCount() {
		return jYBookDao.getBookDetailTotalCount();
	}

	@Override
	@Transactional(readOnly=true)
	public JYBook getRate(JYBook book) {
		return jYBookDao.getRate(book);
	}

	@Override
	@Transactional(readOnly=true)
	public int checkRate(JYBookRatecount ratecount) {
		return jYBookDao.checkRate(ratecount);
	}

	@Override
	@Transactional
	public void updateReadcount(JYBook book) {
		jYBookDao.updateReadcount(book);
	}

	@Override
	@Transactional
	public void updateRate(JYBook book) {
		jYBookDao.updateRate(book);
	}

	@Override
	@Transactional
	public void updateUdate(JYBook book) {
		jYBookDao.updateUdate(book);
	}

}
