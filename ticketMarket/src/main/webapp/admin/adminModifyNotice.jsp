<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-content">
	<div class="sectionNotice">
		<div class="noticeContentWrap">
			<h2 class="wrap_h2">공지사항 수정</h2>
			<div class="noticeArea">
				<div class="noticeHeader">
					<h3>제목</h3>
					<input type="text" class="adminNoticeTitle" id="adminNoticeTitle" value="${notice.title}">
					<div class="noticeContentArea">
						<div class="noticeContent">
							<h3>내용 작성</h3>
							<textarea name="noticeContentText" id="noticeContentText"
								placeholder="내용작성">${notice.content}</textarea>
						</div>
						<input type="hidden" id="noticeNum" value="${num}">
					</div>
				</div>
				<div class="noticeButtonBox">
					<button class="registerButton" id="noticeModifyButton">수정</button>
					<button class="cencelButton" id="cencelButton">취소</button>
				</div>
			</div>
		</div>
	</div>
</div>