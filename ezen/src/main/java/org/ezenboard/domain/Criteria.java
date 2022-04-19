package org.ezenboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;//페이지 넘버
	private int amount;//페이징 시, 한 페이지에 보여줄 글목록 개수
	private String type;//검색시 카테고리 조건을 걸어줍니다.
	private String keyword;//검색어를 받기 위한 변수.
	
	public Criteria() {
		this(1, 10);//기본값 1페이지 10개씩 목록띄우기.
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
		/*
		 * BoardMapper.xml, <sql id="criteria">에서
		 * <foreach> 안에 collection="typeArr"로 활용된다. 
		 * getTypeArr -> get/set 메서드와 같이 사용.
		 */
	}

}
