<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<!DOCTYPE>
<html>
<head>
	<title>로그인</title>
  	
  	<jsp:include page="/includee/init.jsp"/>
  	
  	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
  	
  	<style> 
    .center-form > div {
    	margin: 10px;
    	color: #777;
    }  
    input {
    	width: 240px;
    	margin-top: 20px;
    	margin-bottom: 20px;
    } 
    input:hover {
    	border-color: #FF4543;
    }   
  	</style>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid" style="background-color: #f7f7f7;">
		<form action="${ctxPath}/login.do" method="post">
			<div class="form-group-lg center-form" align="center">
				<a id="custom-login-btn" href="javascript:loginWithKakao()">
					<img src="${ctxPath}/img/kakao.png" width="240"/>
				</a>
				<div style="margin-top: 20px;">또는</div>
				<c:if test="${errors.idOrPwNotMatch }"><div style="color: #FF3533">아이디나 비밀번호가 틀립니다</div></c:if>
				<input type="text" name="id" class="form-control" autofocus="autofocus" placeholder="아이디를 입력해주세요" required="required"/>
				<input type="password" name="password" class="form-control" placeholder="비밀번호를 입력해주세요" required="required"/>
				<input type="submit" class="btn okay form-control"  value="로그인" />
				<br /><br />
				<div>아직 계정이 없으신가요? <a href="${ctxPath}/join.do">회원가입</a></div>
			</div>	
		</form>
	</div>
	
	<jsp:include page="/includee/footer.jsp"/>
	
	<!-- KaKao Login -->
	<script type='text/javascript'>
    	Kakao.init('caae0f5968fa56895a0cd511236ac0e1');
    	function loginWithKakao() {
      		Kakao.Auth.login({
        		success: function(authObj) {
        			Kakao.API.request({
        		    	url: '/v1/user/me',
        		        success: function(res) {
        		        	var info = {
        		        			id: res.id,
        		        			nickname: res.properties.nickname,
        		        			email: res.kaccount_email
        		        	}
        		        	post_to_url("kakao.do", info, "post");
        		        },
        		        fail: function(error) {
        		        	alert(JSON.stringify(error));
        		        }
        		  	});
        		},
        		fail: function(err) {
          			alert(JSON.stringify(err));
        		}
      		});
    	};
    	
    	function post_to_url(path, params, method) {
    	    method = method || "post"; // 전송 방식 기본값을 POST
    	    var form = document.createElement("form");
    	    form.setAttribute("method", method);
    	    form.setAttribute("action", path);
    	 
    	    //히든으로 값을 주입시킨다.
    	    for(var key in params) {
    	        var hiddenField = document.createElement("input");
    	        hiddenField.setAttribute("type", "hidden");
    	        hiddenField.setAttribute("name", key);
    	        hiddenField.setAttribute("value", params[key]);
    	        form.appendChild(hiddenField);
    	    }
    	 
    	    document.body.appendChild(form);
    	    form.submit();
    	}
	</script>
</body>
</html>