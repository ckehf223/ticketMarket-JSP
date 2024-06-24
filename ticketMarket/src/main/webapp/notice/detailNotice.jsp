<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/ticketMarket/notice/detailNotice.css">
<script src="/ticketMarket/notice/comment.js"></script>
 <div class="sectionNotice">
      <div class="noticeContentWrap">
        <h2 class="wrap_h2">공지사항</h2>
        <div class="articleArea">
          <div class="articleHeader">
            <h3>${notice.title}</h3>
            <div class="writerInfo">
              <span class="writeId">fancyTicket 관리자</span>
              <br>
              <span class="writeDate">${notice.regdate }</span>
              <span class="writeHits">${notice.hits}</span>
            </div>
            <div class="articleContentArea">
              <div class="articleContent">
                <span>${notice.content}</span>
              </div>
            </div>
          </div>
          <div class="commentBox">
            <h3>댓글</h3>
            <c:forEach items="${commentList}" var="list">
            <div class="comments_Area">
              <div class="titleArea">
                <span class="comment_writerId">${list.id}</span>
              </div>
              <div>
                <span class="comment_content">${list.content}</span>
                <br>
                <span class="comment_writeDate">${list.regdate}</span>
                <c:if test="${list.id eq loginId}">
                <button class="commentAreaButton" id="commentAreaButton">삭제</button>
                <input type="hidden" id="comment_num" value="${list.no}">
                </c:if>
              </div>
            </div>
            </c:forEach>
            <!-- 댓글 반복문 끝-->
            <div class="commentWrite">
              <div class="commentWriteBox">
              <c:if test="${loginId != null }">
                <span class="comment_writerId">${loginId}</span>
                <input type="hidden" id="commentWriter" value="${loginId}">
                <br>
                </c:if>
                <input type="text" id="commentWriteContent" placeholder="댓글을 남겨보세요">
                <button class="commentInButton">등록</button>
              </div>
            </div>
            <div class="backAwayArea">
              <button class="backAwayButton">목록</button>
            </div>
          </div>
        </div>
      </div>
    </div>