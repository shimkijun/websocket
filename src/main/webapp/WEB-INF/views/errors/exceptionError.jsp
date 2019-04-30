<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link href="/images/favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="/css/reset.css" />
<link rel="stylesheet" href="/css/error.css" />
<title>Discord - ${title}</title>
</head>
<body>
	<div class="error_page">
		<div class="error_box">
			<h1>
				${title}
			</h1>
			<h2>
				${msg}
			</h2>
			<p>
				${ex}
			</p>
			<a href="/">Discord </a>
		</div>
		<div class="logoSection">
			<a href="/" title="메인페이지로">
				<img src="/images/logo.svg" alt="로고 이미지" />
			</a>
		</div>
	</div>
</body>
</html>