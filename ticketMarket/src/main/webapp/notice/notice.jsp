<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/ticketMarket/notice/notice.css">
<div class="noticeMain">
    <div class="noticeWrap">
      <div class="noticeHeader">
        <h2>공 지 사 항</h2>
      </div>
      <div class="noticeContentsArea">
        <div class="noticeContentTop">
          <h3>공지</h3>
          <div>
            <span class="noticeSpan">HOT | MEGA | TiKi 선예매</span>
          </div>
          <div class="noticeSortBox">
            <button class="sortButton">등록순</button>
            <button class="sortButton" id="sortCount">조회순</button>
            <button class="sortButton">날짜순</button>
          </div>
          <div class="noticeSearch">
            <input type="text" >
            <button class="noticeSearchButton">검색</button>
          </div>
        </div>
        <table class="noticeTable">
          <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>등록일</th>
            <th>조회수</th>
          </tr>
        </thead>
        <!-- 반복시작-->
        <c:forEach items="${noList}" var="list">
          <tr>
            <td>${number}</td>
            <c:set var="number" value="${number - 1}" scope="request" />
            <td class="noticeTitle">
            <a href="/ticketMarket/detailNotice.do?num=${list.no}&pageNum=${pageNum}">${list.title}</a>
            </td>
            <td>${list.regdate}</td>
            <td>${list.hits}</td>
          </tr>
          </c:forEach>
          <!-- 반복 끝-->
        </table>
        <div class="noticeButtonArea">
        <c:if test="${startPage > pageBlock}">
        	<button class="noticeButton" onclick="location.href='/ticketMarket/notice.do?pageNum=${startPage - pageBlock}'">&lt;</button>
        </c:if>
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
          <button class="noticeButton" onclick="location.href='/ticketMarket/notice.do?pageNum=${i}'">${i}</button>    
        </c:forEach>
        <c:if test="${endPage < pageCount}">
        	<button class="noticeButton" onclick="location.href='/ticketMarket/notice.do?pageNum=${startPage + pageBlock}'">&gt;</button>
        </c:if>
        </div>
      </div>
    </div>
  </div>