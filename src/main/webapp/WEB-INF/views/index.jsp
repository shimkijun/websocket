<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/resources/commons/header.jsp" />
<c:import url="/resources/commons/sideNav.jsp" />

<div class="mainWrap">
	<div class="mainContainer">
		<div class="mainMenu">
			<div class="chatSearch">
				<div>
					<input type="search" name="chatSearch" id="chatSearch" placeholder="대화를 츶거나 시작해 보세요" />
				</div>
			</div>
			<div class="menuContent">
				<div class="mainContentMenu">
					<div class="mcmBox">
						<ul>
							<li class="active">
								<span class="mcmIcon">★</span>
								<span class="mcmTxt">내 활동</span>
							</li>
							<li>
								<span class="mcmIcon">★</span>
								<span class="mcmTxt">친구</span>
							</li>
						</ul>
					</div>
				</div>
				<div class="friendsMessageList">
					<h4>개인 메시지</h4>
					<div class="msgList">
						<ul>
					<c:choose>
						<c:when test="${empty chatList}">
					       <li class="listEmpty">
								<img src="/images/listEmpty.svg" alt="개인 메시지가 없을경우 이미지"/>
						   </li>
					    </c:when>
						<c:otherwise>
							<c:forEach var="chatlist" items="${chatList}" varStatus="status">
							<li>
								<div>
									<input type="hidden" name="clName" id="clName" value="${chatlist.uemail}" />
									<p class="profileIcon">
										<img src="/images/${chatlist.uprofilename}" />
										<c:choose>
											<c:when test="${chatlist.ustatus == 0 || chatlist.umystatus == 4}">
										        <span class="profile off"></span>
										    </c:when>
										    <c:when test="${chatlist.ustatus == 1}">
										    	<c:choose>
											    	<c:when test="${chatlist.umystatus == 1}">
											        	<span class="profile on"></span>
											        </c:when>
											        <c:when test="${chatlist.umystatus == 2}">
											        	<span class="profile away"></span>
											        </c:when>
											        <c:when test="${chatlist.umystatus == 3}">
											        	<span class="profile other"></span>
											        </c:when>
										       </c:choose>
										    </c:when>
										</c:choose>
									</p>
									<p class="profileName">${chatlist.uname}</p>
								</div> 
								<button type="button" id="close_msg"><i class="fas fa-times"></i></button>
							</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
						</ul>
					</div>
				</div>
			</div>
			<div class="myInfo">
				<div>
					<p class="profileIcon"><img src="/images/${userInfo.uprofilename}" />
					<c:choose>
						<c:when test="${userInfo.ustatus == 0 || userInfo.umystatus == 4}">
					        <span class="profile off"></span>
					    </c:when>
					    <c:when test="${userInfo.ustatus == 1}">
					    	<c:choose>
						    	<c:when test="${userInfo.umystatus == 1}">
						        	<span class="profile on"></span>
						        </c:when>
						        <c:when test="${userInfo.umystatus == 2}">
						        	<span class="profile away"></span>
						        </c:when>
						        <c:when test="${userInfo.umystatus == 3}">
						        	<span class="profile other"></span>
						        </c:when>
					       </c:choose>
					    </c:when>
					</c:choose>
					</p>
					<p class="profileName">
						<span class="myInfoName">${userInfo.uname}</span>
						<span class="myInfoCode">#${userInfo.ucode}<span>
					</p>
				</div>
				<div class="infoBtn">
					<button type="button"><i class="fas fa-cog"></i></button>
				</div>
				<ul class="condition-box">
					<li class="condition-line">
						<span class="condition-btn on"></span>
						<span class="condition-txt">온라인</span>
					</li>
					<li class="condition-line">
						<span class="condition-btn away"></span>
						<span class="condition-txt">자리비움</span>
					</li>
					<li class="condition-line">
						<span class="condition-btn other"></span>
						<span class="condition-txt">다른 용무 중</span>
					</li>
					<li class="condition-line">
						<span class="condition-btn off"></span>
						<span class="condition-txt">오프라인 표시</span>
					</li>	
				</ul>
			</div>
		</div>
		<div class="mainContent">
			<div class="mainFriendMe">
				<div class="headLine">
					
				</div>
			</div>
			
		</div>
	</div>
</div>
<c:import url="/resources/commons/footer.jsp" />
