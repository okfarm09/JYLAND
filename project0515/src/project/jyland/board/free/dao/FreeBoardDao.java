package project.jyland.board.free.dao;

import java.util.List;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;

public interface FreeBoardDao {

	void writeFreeBoard(JYBoard board);
	List<JYBoard> getFreeList();
	JYBoard getFree(JYBoard board);
	List<JYBoard> freePageList(JYBoardParam param);
	int getFreeTotalCount(JYBoardParam param);

}
