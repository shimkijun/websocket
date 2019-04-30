<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div class="addContainer">
	<div class="DarkBack"></div>
	<div class="addchannel">
		<div class="addContent">
			<ul>
				<li class="addContent1">
					<h3>앗, 다른 서버를 원하시는 건가요?</h3>
					<div class="createServer">
						<h4>만들기</h4>
						<p class="createTxt">
							서버를 만들어 친구를 초대</br>
							해보세요! 무료입니다!
						</p>
						<p class="createImg">
							<img src="/images/addChannelIcon.png" alt="채널 만들기 아이콘 이미지">
						</p>
						<p class="createBtn">
							<button type="button">Create a server</button>
						</p>
					</div>
				</li>
				<li class="addContent2">
					<h3>서버를 만들어보세요</h3>
					<h4>
						서버를 만들면 친구와 음성 및 텍스트 채팅을 <strong>무료로</strong></br> 즐길수 있습니다.
					</h4>
					<form action="/channels/add" method="POST" name="addChannelForm" enctype="multipart/form-data" autocomplete="off" >
					<div class="addChannelForm">
						<div class="serverNaming">
							<div class="form-group">
								<label>서버 이름 <span class="errorMessage"></span></label>
								<input type="text" name="cname" id="cname" placeholder="서버 이름을 입력하세요" maxlength="30" />
							</div>
						</div>
						<div class="channelIcon">
							<div class="form-group">
								<div class="iconChangeBox">
									<div class="iconBack"></div>
									<span class="iconTxt">아이콘</br>변경</span>
									<input type="file" name="file" id="cicon" />
								</div>
								<span class="iconFileSize">최소 크기 : <strong>128x128</strong></span>
							</div>
						</div>
					</div>
					<div class="addChannelBtn">
						<div class="addChannelBack">
							<i class="fas fa-long-arrow-alt-left"></i>
							뒤로
						</div>
						<div class="addBtn">
							<button type="submit">만들기</button>
						</div>
					</div>
					</form>
				</li>
			</ul>
		</div>
	</div>
</div>
