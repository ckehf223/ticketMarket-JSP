<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>fancyManagement</title>
  <link rel="stylesheet" href="/ticketMarket/admin/admin.css">
  <script src="/ticketMarket/admin/adminLogin.js"></script>
  <script src="/ticketMarket/admin/admin.js"></script>
  <script src="/ticketMarket/admin/adminProduct.js"></script>
  <script src="/ticketMarket/admin/adminProductRegister.js"></script>
  <script src="/ticketMarket/admin/adminNotice.js"></script>
  <script src="/ticketMarket/admin/adminNoticeWrite.js"></script>
  <script src="/ticketMarket/admin/adminNoticeModify.js"></script>
  <script src="https://kit.fontawesome.com/6c2f448137.js" crossorigin="anonymous"></script>
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
</head>
<body>
  <div class="adminWrap">
    <div class="sidebarWrap">
      <div class="sidebar">
        <div class="sidebar_header">
          <h1><em>FancyManager</em></h1>
        </div>
        <c:if test="${adminLoginId != null }">
        <div class="sideMenu">
          <ul class="menu_ul">
            <li>
              <div class="sidebar_menu">
                <div class="imgIcon" id="dashIcon"><i class="fa-solid fa-table"></i></div><a href="/ticketMarket/mg/dashBoard.do">대시보드</a>
              </div>
            </li>
            <li>
              <div class="sidebar_menu" id="userMenu">
                <div class="imgIcon" id="userIcon"><i class="fa-solid fa-user"></i></div>
                <span>회원관리</span>
                <button class="openButton" id="openUserButton">+</button>
              </div>
              <div class="dropdown_menu" id="dropdown_user">
                <div><a href="/ticketMarket/mg/userList.do">회원조회</a></div>
                <div><a href="/ticketMarket/mg/userDelete.do">회원삭제</a></div>
              </div>
            </li>
            <li>
              <div class="sidebar_menu" id="performanceMenu">
                <div class="imgIcon" id="pfIcon"><i class="fa-solid fa-bag-shopping"></i></div>
                <span>공연관리</span>
                <button class="openButton" id="openPerformaceButton">+</button>
              </div>
              <div class="dropdown_menu" id="dropdown_pf">
                <div><a href="/ticketMarket/mg/productList.do">공연조회</a></div>
                <div><a href="/ticketMarket/mg/productRegister.do">공연추가</a></div>
              </div>
            </li>
            <li>
              <div class="sidebar_menu" id="noticeMenu">
                <div class="imgIcon" id="noticeIcon"><i class="fa-solid fa-note-sticky"></i></div>
                <span>공지사항</span>
                <button class="openButton" id="openNoticeButton">+</button>
              </div>
              <div class="dropdown_menu" id="dropdown_notice">
                <div><a href="/ticketMarket/mg/noticeList.do">공지조회</a></div>
                <div><a href="/ticketMarket/mg/noticeWrite.do">공지추가</a></div>
              </div>
            </li>
          </ul>
        </div>
        </c:if>
      </div>
    </div>
    <div class="mainArea">
      <div class="main_headerArea">
        <div class="main_header">
          <img src="/ticketMarket/image/mainLogo.png" >
          <span class="mainText">FancyTicket</span>
        </div>
        <div class="mainButtonArea">
          <button class="fancyMoveButton" id="fancyMoveButton" >고객사이트 이동</button>
          <c:if test="${adminLoginId != null }">
          <button class="logoutButton" id="logoutButton">로그아웃</button>
          </c:if>
        </div>
      </div>