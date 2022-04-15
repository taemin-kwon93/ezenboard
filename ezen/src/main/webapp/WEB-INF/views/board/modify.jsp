<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<!-- 이미지 파일들 테스트 할때 해당 link를 보도록 하자. -->
<link rel="stylesheet" href="/resources/css/boardstyle.css"></link>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Modify</h1>
  </div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">Board Modify</div>
			<!-- /.panel-heading -->
				<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
					<div class="form-group">
						<label>Bno</label> <input class="form-control" name='bno'
							value='<c:out value="${board.bno }"/>' readonly="readonly">
					</div>

					<!-- 수정 가능 내용 2개, Title & Text -->
					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title'
							value='<c:out value="${board.title }"/>'>
					</div>
					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" name='content'><c:out
								value="${board.content}" /></textarea>
					</div>

					<div class="form-group">
						<label>Writer</label> <input class="form-control" name='writer'
							value='<c:out value="${board.writer}"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>RegDate</label> <input class="form-control" name='regDate'
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate}" />'
							readonly="readonly">
					</div>

					<div class="form-group">
						<label>Update Date</label> <input class="form-control"
							name='updateDate'
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'
							readonly="readonly">
					</div>

					<%-- <div class="form-group">
						<label>Writer</label> <input class="form-control" name='writer'
							value='<sec:authentication property="principal.username"/>'
							readonly="readonly">
					</div> --%>
					
					<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
					<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
					<button data-oper='list' class="btn btn-info">List</button>
				</form>
			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">

$(document).ready(function() {
	
	var formObj = $("form");
	$('button').on("click", function(e) {

	e.preventDefault();
	
	var operation = $(this).data("oper");
	
	console.log(operation);
	
	if (operation === 'remove') {
		formObj.attr("action", "/board/remove");
	
	} else if (operation === 'list') {
		//move to list
		formObj.attr("action", "/board/list")
				.attr("method", "get");
	
		var pageNumTag = $("input[name='pageNum']").clone();
		var amountTag = $("input[name='amount']").clone();
		var keywordTag = $("input[name='keyword']").clone();
		var typeTag = $("input[name='type']").clone();

			formObj.empty();
		
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);

		} else if (operation === 'modify') {
		
			console.log("submit clicked");
		
			var str = "";
		
			$(".uploadResult ul li").each(function(i, obj) {

				var jobj = $(obj);
				console.dir(jobj);

				str += "<input type='hidden' name='attachList["
						+ i
						+ "].fileName' value='"
						+ jobj.data("filename")
						+ "'>";
				str += "<input type='hidden' name='attachList["
						+ i
						+ "].uuid' value='"
						+ jobj.data("uuid")
						+ "'>";
				str += "<input type='hidden' name='attachList["
						+ i 
						+ "].uploadPath' value='"
						+ jobj.data("path")
						+ "'>";
				str += "<input type='hidden' name='attachList["
						+ i
						+ "].filetype' value='"
						+ jobj.data("type")
						+ "'>";
				});
	
			formObj.append(str).submit();
		}

		formObj.submit();
	});
});
</script>
<%@include file="../includes/footer.jsp"%>
