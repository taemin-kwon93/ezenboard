package org.ezenboard.mapper;

import java.util.List;

import org.ezenboard.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Long bno);
	
}
