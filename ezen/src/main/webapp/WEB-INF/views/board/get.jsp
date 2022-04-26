<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<!-- 이미지 파일들 테스트 할때 해당 link를 보도록 하자. -->
<link rel="stylesheet" href="/resources/css/boardstyle.css"></link>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Read</h1>
  </div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">Board Register</div>
			<!-- /.panel-heading -->
				<div class="panel-body">
	
					<div class="form-group">
						<label>Bno</label> <input class="form-control" name='bno'
							value='<c:out value="${board.bno }"/>' readonly="readonly">
					</div>
	
					<%-- <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> --%>
					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title'
							value='<c:out value="${board.title }"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" name='content'
						readonly="readonly"><c:out value="${board.content}" /></textarea>
					</div>
					
					<div class="form-group">
					  <label>Writer</label> <input class="form-control" name='writer'
					  	value='<c:out value="${board.writer }"/>' readonly="readonly">
					</div>	
					
					<%-- <div class="form-group">
						<label>Writer</label> <input class="form-control" name='writer'
							value='<sec:authentication property="principal.username"/>'
							readonly="readonly">
					</div> --%>
					<button data-oper='modify' class="btn btn-default">Modify</button>
					<button data-oper='list' class="btn btn-info">List</button>
					
					<!-- 조회 페이지에서 form 처리 -->
					<form id='operForm' action="/boad/modify" method="get">
						<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
						<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
						<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
						<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
						<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>  
					</form>
			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	console.log("board.bno : " + ${board.bno});
	console.log("cri.pageNum : " + ${cri.pageNum});
	console.log(replyService);
	
	<!-- 조회 페이지에서 form 처리 -->
	var operForm = $("#operForm");

	$("button[data-oper='modify']").on("click", function(e) {
		operForm.attr("action", "/board/modify").submit();
	});

	$("button[data-oper='list']").on("click", function(e) {
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list")
		operForm.submit();

	});
});
</script>

<script type="text/javascript">
	console.log("==========");		
	console.log("JS TEST2");	
	
	
	var bnoValue ='<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");

	//function getList(param, callback, error)
	replyService.getList({bno:bnoValue, page: 1}, //param
		function(list){ //callback
			for (var i = 0, len = list.length||0; i < len; i++) { 
				console.log(list[i]);
		} 
	});
	
	
	//R 댓글 가져오기
	showList(1);
	
	function showList(page) {
		console.log("show list page : " + page);
		
		replyService.getList({bno:bnoValue, page: page || 1}, 
			function(replyCnt, list){
			
			console.log("replyCnt: " + replyCnt);
			console.log("list: " + list);
			
			if(page == -1) {
				pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
			}
			
			var str = "";
			
			if(list == null || list.length == 0) { 
				return;
			}
			
			for (var i = 0,  len = list.length || 0; i < len; i++ ){
				str +="<li class='left clearfix' data-rno='" + list[i].rno + "'>";
				str +="  <div><div class='header'><strong class='primary-font'>["
			    	   +list[i].rno+"] "+list[i].replyer+"</strong>"; 
				str +="		<small class='pull-right text-muted'>"
					+replyService.displayTime(list[i].replyDate)+"</small></div>";
				str +="		<p>"+list[i].reply+"</p></div></li>";
		  	}

		     replyUL.html(str);
		     
		     showReplyPage(replyCnt);
			
		});//end replyService.getList 
	}//end showList(page) 

	
	
	
	/*	
	댓글 CRUD Test
	//C 댓글 추가 확인
	replyService.add(
	{reply:"JS TEST4", replyer:"tester4", bno:bnoValue}
	,
		function(result) {
			alert("Result: " + result);	
		})
	
	//R 댓글 읽기 확인
	replyService.get(53, function(data) { 
		console.log("replyService.get_data값 확인 : " + data);
	}); 
	
	//U 댓글 수정 확인
	replyService.update({
		rno : 54,
		bno : bnoValue,
		reply : "Modified...2",
		replyer : "Tom"
		}, 
		function(result){
			alert("수정완료");		
		});	
	
	//D 댓글 삭제 확인 
	replyService.remove(55, function(count) {
		console.log("get.jsp_replyService.remove() : " + count);
		//삭제가 되면 ResponseEntity<String>로 받아온 값 "success"가 count에 담긴다.
		//new ResponseEntity<>("success", HttpStatus.OK)
		
		console.log("Removed...");
		
		if (count === "success") {
			alert("Removed");
		}
			
		}, function(err) {
			alert("Error");
		});
	*/

</script>


<%@include file="../includes/footer.jsp"%>
