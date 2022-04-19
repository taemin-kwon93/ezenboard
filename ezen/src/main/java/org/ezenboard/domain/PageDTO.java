package org.ezenboard.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage;
	private int endPage;
	//페이지의 수가 일정 이상이 되면, 이전화면 다음화면 키를 등록시킨다. 해당 키를 위한 변수는 boolean타입으로 한다.
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) { 
		this.cri = cri;
		this.total = total;
		
		/*
		 * 글목록으로 띄울 개수(Amount)로 전체글의 개수를 나눈다. 예를들어 120개의 글이 있고 목록을 10개씩 띄운다면 12라는 값이 endPage에 저장된다.
		 * ceil은 올림값을 뜻한다. 예를들어 Math.ceil(4.4)는 5라는 값을 리턴한다.
	 	 */
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount())); 
		
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
