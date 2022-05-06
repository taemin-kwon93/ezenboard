package org.ezenboard.service;

import java.util.List;

import org.ezenboard.domain.Criteria;
import org.ezenboard.domain.ReplyPageDTO;
import org.ezenboard.domain.ReplyVO;
import org.ezenboard.mapper.BoardMapper;
import org.ezenboard.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("register(ReplyVO vo) : " + vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get(Long rno) : " + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify(ReplyVO vo) : " + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove(Long rno) : " + rno);
		ReplyVO vo = mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("getList(Criteria cri, Long bno) : " + bno);
		return mapper.getReplyList(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		//cri를 통해서 list생성, bno를 통해서 replyCnt생성.
		
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getReplyList(cri, bno));
	}

}
