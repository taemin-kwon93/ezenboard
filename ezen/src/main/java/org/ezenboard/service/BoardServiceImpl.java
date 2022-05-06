package org.ezenboard.service;

import java.util.List;

import org.ezenboard.domain.BoardVO;
import org.ezenboard.domain.Criteria;
import org.ezenboard.mapper.BoardMapper;
import org.ezenboard.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	//root-context.xml 설정 'context'체크 이후 component-scan에 service pack 등록
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean remove(Long bno) {
		
		return mapper.delete(bno) == 1;
	}

	@Override
	public boolean modify(BoardVO board) {
		
		return mapper.update(board) == 1;
	}

}
