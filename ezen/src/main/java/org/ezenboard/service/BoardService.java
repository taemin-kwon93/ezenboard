package org.ezenboard.service;

import java.util.List;

import org.ezenboard.domain.BoardVO;

public interface BoardService {
	
	public List<BoardVO> getList();

	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean remove(Long bno);
	
	public boolean modify(BoardVO board);
}
