package project.jyland.data.dao;

import java.util.List;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;
import project.jyland.data.model.Region;

public interface DataService {
	
	List<Region> getRegionList();	
	List<JYBoard> getSearchList(JYBoardParam param);
	int getSearchTotalCount(JYBoardParam param);
}
