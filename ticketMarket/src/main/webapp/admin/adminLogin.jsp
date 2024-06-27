<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
      <div class="main-content">
        <div class="loginWrap">
          <div class="loginLogo"><a href=""><img src="/ticketMarket/image/mainLogo.png"><span>관리자</span></a></div>
          <form id="loginForm" action="#">
            <div class="loginInner">
              <div class="loginForm">
                <div class="inputBox">
                  <div class="inputId">
                    <label for="adminId"></label>
                    <input type="text" name="adminId" placeholder="아이디" id="adminId">
                  </div>
                  <div class="inputPw">
                    <label for="adminPw"></label>
                    <input type="password" name="adminPw" placeholder="비밀번호" id="adminPw">
                  </div>
                </div>
                <div class="errorMessage">
                  <div class="message" id="message"></div>
                </div>
              </div>
              <div class="loginButtonBox">
                <button type="button" class="loginButton" id="loginButton"><span>로그인</span></button>
              </div>
            </div>
          </form>
        </div>
      </div>