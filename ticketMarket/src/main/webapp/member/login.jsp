<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="/ticketMarket/member/login.js"></script>
  <link rel="stylesheet" href="/ticketMarket/member/login.css">
</head>
<body>
  <div class="loginWrap">
    <div class="loginLogo"><a href="/ticketMarket/index.do"><img src="/ticketMarket/image/mainLogo.png" alt=""><span>fancyticket</span></a></div>
    <form id="loginForm" action="#">
      <div class="loginInner">
        <div class="loginForm">
          <div class="inputBox">
            <div class="inputId">
              <label for="userId"></label>
              <input type="text" name="userId" placeholder="아이디" id="userId">
            </div>
            <div class="inputPw">
              <label for="userPw"></label>
              <input type="password" name="userPw" placeholder="비밀번호" id="userPw">
            </div>
          </div>
          <div class="errorMessage">
            <div class="message" id="message"></div>
          </div>
        </div>
        <div class="loginButtonBox">
          <button type="button" id="loginButton" class="loginButton"><span>로그인</span></button>
        </div>
        <div class="findList">
          <ul>
            <li><a id="findId" href="#">아이디 찾기</a></li>
            <li><a id="findPw" href="#">비밀번호 찾기</a></li>
            <li><a id="join" href="/ticketMarket/registerForm.do">회원가입</a></li>
          </ul>
        </div>
      </div>
    </form>
  </div>
</body>
</html>