<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/resources/commons/header.jsp" />
<div class="loginWrap">
	<div id="logo">
		<h1 class="logo">
			<img src="/images/logo.svg" alt="로고 이미지" />
		</h1>
	</div>
	<div class="loginFormWrap">
		<div class="loginForm">
			<form action="login" method="post">
				<div class="form-title">
					<h2>
						돌아오신 것을 환영합니다!
					</h2>
					<p>
						We're so excited to see you again!
					</p>
				</div>
				<div class="form-group">
					<h5>
						이메일 <span class="errorMessage"></span>
					</h5>
					<div class="form-group-input">
						<label for="uemail">이메일</label>
						<input type="email" id="uemail" name="uemail" maxlength="150">
					</div>
				</div>
				<div class="form-group">
					<h5>
						비밀번호 <span class="errorMessage"></span>
					</h5>
					<div class="form-group-input">
						<label for="upassword">비밀번호</label>
						<input type="password" id="upassword" name="upassword" maxlength="15">
					</div>
				</div>
				<div class="form-group-link">
					<p>
						<a href="#none">Forgot your password?</a>
					</p>
				</div>
				<div class="form-group-btn">
					<div class="loginBtn">
						<button type="button" id="loginBtn">로그인</button>
					</div>
				</div>
				<div class="form-group-link">
					<p>
						계정이 없으신가요? <a href="/user/register">가입하기</a>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>
<c:import url="/resources/commons/footer.jsp" />
<script>
	 loginForm();
</script>
