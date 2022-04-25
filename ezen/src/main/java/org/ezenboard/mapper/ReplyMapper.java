package org.ezenboard.mapper;

import java.util.List;

import org.ezenboard.domain.ReplyVO;

public interface ReplyMapper {

	public List<ReplyVO> getReplyList();
	
	public int insert(ReplyVO vo);
}
