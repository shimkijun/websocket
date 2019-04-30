<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mainFriendMe">
	<div class="headLine">
		<ul>
			<li class="primary on">
				친구 추가
			</li>
			<li>
				모두표시
			</li>
			<li>
				온라인
			</li>
			<li>
				승인대기
			</li>
		</ul>
	</div>
	<div class="fmTable">
		<div class="addFriendContent addTop">
			<h2>친구 추가</h2>
			<p>DiscordTag를 사용하여 친구를 추가 할 수 있습니다.</p>
			<div class="addFriendInput">
				<input type="text" name="discordTag" id="discordTag" placeholder="DiscordTag#0000입력" maxlength="128"/>
				<button type="button" id="addFriendBtn">친구 요청 보내기</button>
			</div>
		</div>
		<div class="addFriendContent">
			<div class=" addBottom">
				<h2>친구 제안</h2>
			</div>
			<div class="fmTbableBody addFriend">
				<ul>

				</ul>
			</div>
		</div>
	</div>
</div>