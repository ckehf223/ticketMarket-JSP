<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="/ticketMarket/top/style.css">
  <link rel="stylesheet" href="/ticketMarket/member/myPage.css">
  <link rel="stylesheet" href="/ticketMarket/member/paymentCart.css">
  <link rel="stylesheet" href="/ticketMarket/performance/contents.css">
  <link rel="stylesheet" href="/ticketMarket/performance/detailContent.css">
  <link rel="stylesheet" href="/ticketMarket/notice/detailNotice.css">
  <link rel="stylesheet" href="/ticketMarket/search/search.css">
	<script src="/ticketMarket/notice/comment.js"></script>
  <script src="/ticketMarket/performance/detailContent.js"></script>
  <script src="/ticketMarket/member/paymentCart.js"></script>
  <script src="/ticketMarket/top/script.js"></script>
  <script src="/ticketMarket/member/myPage.js"></script>
  <script src="https://kit.fontawesome.com/6c2f448137.js" crossorigin="anonymous"></script>
</head>
<body onload="call_js()">
  <div class="wrap">
    <header class="header">
      <div class="header_sub1">
      <div class="header_top">  
        <div class="header_left">
          <div class="header_logoArea">
            <div class="header_img"><a href="/ticketMarket/index.do"><img src="/ticketMarket/image/mainLogo.png" alt="메인 로고">FANCY TICKET</a></div>
          </div>
          <form class="search_form" action="#" method="get">
            <label for="search"></label>
            <input id="search" name="search" type="text" placeholder="검색">
            <button class="search_button" id="search_button"></button>
          </form>
        </div>
        <div class="header_right">
        <c:if test="${loginId !=null }">
        <%-- <c:set var="loginId" value="${loginId}" scope="request"/> --%>
          <a href="/ticketMarket/logoutPro.do">로그아웃</a>
          <a href="/ticketMarket/myPage.do">마이페이지</a>
        </c:if>
        <c:if test="${loginId == null }">
       	 	<a href="/ticketMarket/loginForm.do">로그인</a>
       	 	<a href="/ticketMarket/registerForm.do">회원가입</a>
          <a href="#" id="myPageButton">마이페이지</a>
        </c:if>
        </div>
      </div>
    </div>
      <nav class="header_nav">
        <div class="navMenu">
        <ul class="genreMenu">
          <li><a class="header_link" href="/ticketMarket/index.do">Home</a></li>
          <li><a class="header_link" href="/ticketMarket/contents.do?num=1">뮤지컬</a></li>
          <li><a class="header_link" href="/ticketMarket/contents.do?num=2">콘서트</a></li>
          <li><a class="header_link" href="/ticketMarket/contents.do?num=3">전시</a></li>
          <li><a class="header_link" href="/ticketMarket/contents.do?num=4">연극</a></li>
          <li><a class="header_link" href="/ticketMarket/contents.do?num=5">아동가족</a></li>
        </ul>
        <div class="header_menuDivider"></div>
        <ul class="categoryMenu">
          <li><a class="header_link" href="#">랭킹</a></li>
          <li><a class="header_link" href="/ticketMarket/notice.do">공지사항</a></li>
          <li><a class="header_link" href="/ticketMarket/searchPro.do">공연장</a></li>
        </ul>
          <div class="adImg"><img src="/ticketMarket/image/navimg.png" alt="광고이미지"></div>
      </div>
      </nav>
    </header>
  </div>