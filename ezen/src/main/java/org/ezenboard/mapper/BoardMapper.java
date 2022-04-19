package org.ezenboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.ezenboard.domain.BoardVO;
import org.ezenboard.domain.Criteria;

public interface BoardMapper {
	
	/*
	 * @Select("select * from tbl_board where bno > 0") 
	 * public List<BoardVO> getList();
	 */

	public List<BoardVO> getListWithPaging(Criteria cri);

	public void insert(BoardVO board);

	public Integer insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
}
