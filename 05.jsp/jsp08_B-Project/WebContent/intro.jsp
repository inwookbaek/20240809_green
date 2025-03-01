<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

${ctxPath = pageContext.request.contextPath; ''}

<!DOCTYPE>
<html>
<head>
	<title></title>
	<jsp:include page="/includee/init.jsp"/>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	<div class="container" style="margin: 60px auto;">
		<br /><br />
		<h2 style="font-family: 'Audiowide'; font-size: 50px; color:#2a4f92;">Become a Business Booster!</h2>
		<br /><br />
		<img src="${ctxPath}/img/intro.jpg" alt="Image" />
		<br /><br /><br /><br />

		<div class="row" style="font-family: Audiowide; color:#2a4f92;">
			<div class="col-sm-4">			
				<p class="text-center" style="font-size: 30px;"><strong>BECOME</strong></p><br>
				<div class="text-center" style="color:#595959;">
					<p> B-PROJECT는 <p>
					<p> 창작자들의 신선한 창작 아이디어를 모아 </p>
					<p> 공유하고 알림으로 창작자와 후원자를 연결하는 </p>
					<p> 프로젝트 서포터즈 입니다.</p>
					<p> 창의적이고 사회적 가치를 실현 할 수 있는 아이디어들이 </p>
					<p> 보다 많이 실현 될 수 있도록 도울 수 있는 </p>
					<p> 플랫폼을 제공하고자 합니다. </p>
				</div>	
			</div>
	
			<div class="col-sm-4">			
				<p class="text-center" style="font-size: 30px;"><strong>BUSINESS</strong></p><br>
				<div class="text-center" style="color:#595959;">
					<p> B-PROJECT에서는 
					<p> 누구나 창작자가 될 수 있습니다.</p>
					<p> 자신의 아이디어를 공유해 빛을 볼 수 있게 해주세요. </p>
					<p> 차별화 된 플랫폼의 기능을 통해 창작자들이 </p>
					<p> 그들의 아이디어와 꿈을 실현 시킬 수 있도록 돕습니다. </p>				
				</div>
			</div>
	
			<div class="col-sm-4">				
				<p class="text-center" style="font-size: 30px;"><strong>BOOSTER</strong></p><br>
				<div class="text-center" style="color:#595959;">
					<p> B-PROJECT는 <p>
					<p> 후원자들에게 좋은 아이디어를 </p>
					<p> 공유할 것을 약속드립니다. </p>
					<p> 누구나 후원자가 될 수 있고 손 쉽게 후원 할 수 있습니다. </p>
					<p> 직접 창작자들을 후원하고 프로젝트를 통해 만들어지는 </p>
					<p> 창작물을 받아보세요. </p>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>