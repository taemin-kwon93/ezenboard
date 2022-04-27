console.log("Reply Module........");

var replyService = (function() {

	function add(reply, callback, error) {
		console.log("add reply...............");

		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			}, //success
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			} //error
		}) //ajax
	} //function add
	
	function getList(param, callback, error) {
	
	var bno = param.bno;
	var page = param.page || 1;
		
		/*getJSON url을 통해 ReplyController(REST)가 실행되고
		Mapping에 맞춰 메소드를 실행.
		실행된 메소드를 통해 받아온 JSON데이터 들이 function(data)에 data로 담긴다.*/
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data) { 
			
			if (callback) {
					callback(data); // 댓글 목록만 가져오는 경우 
					//callback(data.replyCnt, data.list); //댓글 숫자와 목록을 가져오는 경우 
				}
				
			}).fail(function(xhr, status, err) {
				
				if (error) {
					error();
				}
			
		});
	}
/*
	function remove(rno, replyer, callback, error) {
		  
	console.log("reply.js_remove()---------------------------------");  
	console.log(JSON.stringify({rno:rno, replyer:replyer}));  
	    
	$.ajax({
		type : 'delete',
		url : '/replies/' + rno,
		data:  JSON.stringify({rno:rno, replyer:replyer}),
		contentType: "application/json; charset=utf-8",
		
		success : function(deleteResult, status, xhr) {
			if (callback) {
				callback(deleteResult);
			}
		},
		
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});
	}
*/
	function remove(rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/'+rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});//$.ajax
		}//function remove
		
	function update(reply, callback, error) {

		console.log("RNO: " + reply.rno);

		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	function get(rno, callback, error) {

		$.get("/replies/" + rno + ".json", function(result) {

			if (callback) {
				callback(result);
			}

		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}

	function displayTime(timeValue) {

		var today = new Date();

		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		if (gap < (1000 * 60 * 60 * 24)) {
		/*  
			 의도한바에 비추어 잘못된 계산임. 
			글의 최초등록시간을 기준으로 24시간이 지났을 때
			시간표시가 아닌 날짜 표시로 전환함.
			즉, 4월 26일 오후 7시에 등록된 글이 
			4월 27일 오후 4시에 '19:59:49' 이런식으로 보이게 됨.
		*/
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}//2022/4/25 -> 22/04/25 이런식으로 형식을 맞춰서 표시해줌.
	};

	return {
		add : add,
		get : get,
		getList : getList,
		remove : remove,
		update : update,
		displayTime : displayTime
	};

})();
