package org.ezenboard.domain;

import lombok.Data;

@Data
public class AttachFileDTO {
	
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
	
}
/*
 * Client -> Server 이미지 업로드
 * View에서 Ajax로 /uploadAjaxAction 요청을 전송.
 * 전송된 정보 안에는 특정 파일이 있음.
 * 해당 파일에 대한 검증을 Js로 먼저 처리하고 Controller에서 다시 한번 처리함.
 * @PostMapping("/uploadAjaxAction")에 따라 실행된, uploadAjaxPost()는 
 * 
 */