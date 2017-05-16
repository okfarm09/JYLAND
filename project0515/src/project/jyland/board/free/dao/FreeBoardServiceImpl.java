package project.jyland.board.free.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	private FreeBoardDao freeBoardDao;

	@Override
	@Transactional
	public void writeFreeBoard(JYBoard board) {
		freeBoardDao.writeFreeBoard(board);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYBoard> getFreeList() {
		return freeBoardDao.getFreeList();
	}

	@Override
	@Transactional(readOnly=true)
	public JYBoard getFree(JYBoard board) {
		return freeBoardDao.getFree(board);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYBoard> freePageList(JYBoardParam param) {
		return freeBoardDao.freePageList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getFreeTotalCount(JYBoardParam param) {
		return freeBoardDao.getFreeTotalCount(param);
	}

}
