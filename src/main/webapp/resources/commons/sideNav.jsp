<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="viewAdd"></div>
<div class="sideNav">
	<div class="sideScroll">
		<div class="homeIcon">
			<div>
				<div class="homeiconImg active">
					<a href="#none"><img src="/images/icon1.png" alt="아이콘"></a>
				</div>
				<div class="homeIconTxt">
					<span>${cnt}명 접속중</span>
				</div>
			</div>
		</div>
		<style>
		</style>
		<span class="sideLine"></span>
		<div class="serverIcon">
			<ul>
				<c:forEach var="channel" items="${myChannel}" varStatus="status">     
					<li>
						<input type="hidden" value="${channel.ccode}"/>
						<div class="serverIconTxt">
							<span>${channel.cname}</span>
							<span class="serverTitle">${channel.cname}</span>
							<c:choose>
								<c:when test="${channel.clevel == 1}">
							        <i class="fas fa-times-circle"></i>
							    </c:when>
							</c:choose>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="addServer">
			<div class="serverIconTxt">
				<a href="#none" title="서버 추가하기"><span>+</span></a>
				<span class="serverTitle">서버 추가하기</span>
			</div>
		</div>
		<span class="sideLine"></span>
	</div>
</div>