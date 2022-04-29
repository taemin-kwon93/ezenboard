package org.ezenboard.service;

import java.util.List;

import org.ezenboard.domain.Criteria;
import org.ezenboard.domain.ReplyPageDTO;
import org.ezenboard.domain.ReplyVO;
//import org.ezenboard.domain.ReplyPageDTO;

public interface ReplyService {

	public int register(ReplyVO vo);

	public ReplyVO get(Long rno);

	public int modify(ReplyVO vo);

	public int remove(Long rno);

	public List<ReplyVO> getList(Criteria cri, Long bno);

	public ReplyPageDTO getListPage(Criteria cri, Long bno);

}
