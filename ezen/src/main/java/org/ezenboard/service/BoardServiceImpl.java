package org.ezenboard.service;

import java.util.List;

import org.ezenboard.domain.BoardAttachVO;
import org.ezenboard.domain.BoardVO;
import org.ezenboard.domain.Criteria;
import org.ezenboard.mapper.BoardAttachMapper;
import org.ezenboard.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Setter(onMethod_ = {@Autowired})
	private BoardAttachMapper attachMapper;
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Transactional
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);

		if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}

		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean remove(Long bno) {
		attachMapper.deleteAll(bno);
		return mapper.delete(bno) == 1;
	}

	@Transactional
	@Override
	public boolean modify(BoardVO board) {

		log.info("modify......" + board);

		attachMapper.deleteAll(board.getBno());

		boolean modifyResult = mapper.update(board) == 1;
		
		if (modifyResult && board.getAttachList() != null) {

			board.getAttachList().forEach(attach -> {

				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}

		return modifyResult;
	}
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {

		return attachMapper.findByBno(bno);
	}

}
