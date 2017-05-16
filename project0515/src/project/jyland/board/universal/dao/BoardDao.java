package project.jyland.board.universal.dao;

import java.util.List;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;

public interface BoardDao {
	
	void updateBoard(JYBoard board);
	void deleteBoard(JYBoard board);
	String getCatPrep(int catid);
	JYBoard getBoard(JYBoard board);
	List<JYBoard> getBoardPageList(JYBoardParam param);
	int getBoardTotalCount(JYBoardParam param);

}
