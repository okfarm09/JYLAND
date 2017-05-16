package project.jyland.board.dao;

import java.util.List;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;

public interface BoardDao {
	
	void writeBoard(JYBoard board);
	void updateBoard(JYBoard board);
	void deleteBoard(JYBoard board);
	String getCatPrep(int catid);
	String getCatName(int catid);
	JYBoard getBoard(JYBoard board);
	List<JYBoard> getBoardPageList(JYBoardParam param);
	int getBoardTotalCount(JYBoardParam param);

}