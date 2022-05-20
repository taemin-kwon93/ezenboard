package org.ezenboard.mapper;

import org.ezenboard.domain.MemberVO;

public interface MemberMapper {

	public MemberVO read(String userid);
}
