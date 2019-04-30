<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/resources/commons/header.jsp" />
<div class="registerWrap">
	<div id="logo">
		<h1 class="logo">
			<img src="/images/logo.svg" alt="로고 이미지" />
		</h1>
	</div>
	<div class="registerFormWrap">
		<div class="registerForm">
			<form method="POST" action="/user/register" name="registerAction">
				<div class="form-title">
					<h2>
						계정 만들기
					</h2>
				</div>
				<div class="form-group">
					<h5>
						이메일 <span class="errorMessage"></span>
					</h5>
					<div class="form-group-input">
						<label for="uemail">이메일</label>
						<input type="email" id="uemail" name="uemail" maxlength="150" required >
					</div>
				</div>
				<div class="form-group">
					<h5>
						사용자명 <span class="errorMessage"></span>
					</h5>
					<div class="form-group-input">
						<label for="uname">사용자명</label>
						<input type="uname" id="uname" name="uname" maxlength="128" required>
					</div>
				</div>
				<div class="form-group">
					<h5>
						비밀번호<span class="errorMessage"></span>
					</h5>
					<div class="form-group-input">
						<label for="upassword">비밀번호</label>
						<input type="password" id="upassword" name="upassword" maxlength="150" required>
					</div>
				</div>
				<div class="form-group-btn">
					<div class="registerBtn">
						<button type="button" id="registerBtn">계속</button>
					</div>
				</div>
				<div class="form-group-link">
					<p>
						<a href="/user/login">이미 계정이 있으신가요?</a>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>
<c:import url="/resources/commons/footer.jsp" />
<script>
	registerForm();
</script>
