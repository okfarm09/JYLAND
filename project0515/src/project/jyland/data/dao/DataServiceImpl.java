package project.jyland.data.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;
import project.jyland.data.model.Region;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private DataDao regionDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Region> getRegionList() {
		return regionDao.getRegionList();
	}

	@Override
	public List<JYBoard> getSearchList(JYBoardParam param) {
		return regionDao.getSearchList(param);
	}

	@Override
	public int getSearchTotalCount(JYBoardParam param) {
		return regionDao.getSearchTotalCount(param);
	}

}
