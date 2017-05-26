package project.jyland.board.dao;

import java.util.List;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardLHCount;
import project.jyland.board.model.JYBoardParam;

public interface BoardService {

	void writeBoard(JYBoard board);
	void updateBoard(JYBoard board);
	void deleteBoard(JYBoard board);
	String getCatName(int catid);
	JYBoard getBoard(JYBoard board);
	List<JYBoard> getBoardPageList(JYBoardParam param);
	int getBoardTotalCount(JYBoardParam param);
	List<JYBoard> getRecentList();
	void updateReadcount(JYBoard board);
	void updateLikecount(JYBoard board);
	void updateHatecount(JYBoard board);
	void setLikeHate(JYBoardLHCount board);
	JYBoard getLHCount(JYBoard board);
	int checkLikeHate(JYBoardLHCount board);

	List<JYBoard> getGlobalNoticeList();
	List<JYBoard> getLocalNoticeList(JYBoardParam param);
	void goNotice(JYBoard board);
	List<JYBoard> getBestList();
	List<JYBoard> getDateBoardList();
}
