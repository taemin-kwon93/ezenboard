package org.ezenboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ezenboard.domain.Criteria;
import org.ezenboard.domain.ReplyVO;

public interface ReplyMapper {

	public List<ReplyVO> getReplyList(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long rno);
	//public ReplyVO read(Long bno);
	
	public int delete(Long rno);
	
	public int update(ReplyVO vo); 
	
	
}
