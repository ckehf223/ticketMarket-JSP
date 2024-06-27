<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <!--  댓글 반복 시작 -->
            <input type="hidden" id="notice_num" value="${num}">
            <div id="comments">
            <c:forEach items="${commentList}" var="list">
            <div class="comments_Area" id="comment-${list.no}">
              <div class="titleArea">
                <span class="comment_writerId">${list.id}</span>
              </div>
              <div>
                <span class="comment_content">${list.content}</span>
                <br>
                <span class="comment_writeDate">${list.regdate}</span>
                <c:if test="${loginId != null && list.id eq loginId}">
                <button class="commentAreaButton" onclick="deleteComment(${list.no})">삭제</button>
                </c:if>
              </div>
            </div>
            </c:forEach>
         		</div>
            <!-- 댓글 반복문 끝-->
            <div class="commentWrite">
              <div class="commentWriteBox">
              <c:if test="${loginId != null }">
                <span class="comment_writerId">${loginId}</span>
                <input type="hidden" id="commentWriter" value="${loginId}">
                <br>
                <input type="text" id="commentWriteContent" placeholder="댓글을 남겨보세요">
                <button class="commentInButton" id="commentAddButton">등록</button>
                </c:if>
              </div>
            </div>
            <div class="backAwayArea">
              <button class="backAwayButton" id="backAwayButton" onclick="location.href='/ticketMarket/notice.do?pagNum${pageNum}'">목록</button>
            	</div>
          </div>
        </div>
      </div>
    </div>