<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>  	

</head>

<body>
<div class='bigPictureWrapper'>
	<div class='bigPicture'>
	</div>
</div>

<div class='uploadDiv'>
	<input type='file' name='uploadFile' multiple>
</div>
<button id='uploadBtn'>Upload</button>

<div class='uploadResult'>
	<ul>
		<!-- 사진이 업로드되면 해당 부분에 썸네일을 띄운다. -->
	</ul>
</div>

</body>

<!-- jQuery 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

	function showImage(fileCallPath) {
		$(".bigPictureWrapper").css("display", "flex").show();
		
			$(".bigPicture")
			.html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>")
			.animate({width:'25%', height:'25%'}, 0);
	}
	
	$(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture").animate({width:'0%', height:'0%'}, 1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
			}, 1000);
	});

$(document).ready(function(){
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 52428800;//50MB
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일의 크기가 너무 큽니다.");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	var cloneObj = $(".uploadDiv").clone();
	
	$("#uploadBtn").on("click", function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		console.log(files);
		
		for(var i=0; i<files.length; i++){
			if(!checkExtension(files[i].name, files[i].size) ){
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
	var uploadResult = $(".uploadResult ul");
		
	function showUploadedFile(uploadResultArr) {
		var str = "";
		
		$(uploadResultArr).each(function(i, obj) {
			if(!obj.image){
				var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
				var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
				
				str += "<li><div><a href='/download?fileName=" + fileCallPath + "'>"
				+ "<img src='/resources/img/attach.png'>" + obj.fileName + "</a>" 
				+ "<span data-file=\'" + fileCallPath + "\' data-type='file'> x </span>"
				+ "</div></li>"
			} else{
		          var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
		          var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
		          originPath = originPath.replace(new RegExp(/\\/g), "/");
		          
		          str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\">"+
	              "<img src='display?fileName="+fileCallPath+"'></a>"+
	              "<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"+
	              "<li>";
			}
		});
		
		uploadResult.append(str);
	}
		
	$.ajax({
		url: '/uploadAjaxAction',
		processData: false,
		contentType: false,
		data: formData,
		type: 'POST',
		dataType: 'json', 
		success: function(result){
			console.log("uploadAjaxAction의 결과 값");
			console.log(result);
			showUploadedFile(result);
			$(".uploadDiv").html(cloneObj.html());
		}
	});
	
	});
	
	$(".uploadResult").on("click", "span", function(e){
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		
		$.ajax({
			url: '/deleteFile',
			data: {fileName:targetFile, type:type},
			type: 'POST',
			dataType: 'json', 
			success: function(result){
				alert(result);
			}
		});
		
	});
	
});

</script>		
</html>