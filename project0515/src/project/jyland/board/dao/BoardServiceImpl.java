package project.jyland.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	@Transactional
	public void updateBoard(JYBoard board) {
		boardDao.updateBoard(board);
	}

	@Override
	@Transactional
	public void deleteBoard(JYBoard board) {
		boardDao.deleteBoard(board);
	}

	@Override
	@Transactional(readOnly=true)
	public String getCatPrep(int catid) {
		return boardDao.getCatPrep(catid);
	}

	@Override
	@Transactional(readOnly=true)
	public JYBoard getBoard(JYBoard board) {
		return boardDao.getBoard(board);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYBoard> getBoardPageList(JYBoardParam param) {
		return boardDao.getBoardPageList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getBoardTotalCount(JYBoardParam param) {
		return boardDao.getBoardTotalCount(param);
	}

	@Override
	public String getCatName(int catid) {
		return boardDao.getCatName(catid);
	}

	@Override
	public void writeBoard(JYBoard board) {
		boardDao.writeBoard(board);
	}

}