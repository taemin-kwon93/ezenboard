package org.ezenboard.service;

import java.util.List;

import org.ezenboard.domain.BoardVO;
import org.ezenboard.domain.Criteria;

public interface BoardService {
	
	/* public List<BoardVO> getList(); */

	public List<BoardVO> getListWithPaging(Criteria cri);

	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean remove(Long bno);
	
	public boolean modify(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
}
