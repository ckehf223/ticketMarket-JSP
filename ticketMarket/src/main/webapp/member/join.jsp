<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="/ticketMarket/member/join.css">
  <script src="/ticketMarket/member/join.js"></script> 
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
  <div id="joinMembership-wrap">
    <div class="joinMembership_header">
    <div class="logoArea"><a href="/ticketMarket/index.do"><img src="/ticketMarket/image/mainLogo.png">fancyticket</a></div>
    <h1 id="joinMembership-h1">회원가입</h1>
  </div>
    <br>
    <div id="joinMembership-div">
      <span class="starIcon">⁕</span><span id="onlinespan">온라인 회원가입작성</span>
      <p id="joinMembership-top-p">＊고객님의 정보는 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 개인정보보호정책에 의해 철저하게 보호됩니다.</p>
      <br>
    </div>
    <form class="joinMembership-form" name="joinMembership-form" action="#" method="post">
      <table class="joinMembership-table">
        <tbody>
          <!-- 사용자 ID 입력  -->
          <tr>
            <td class="td1"><label for="userId">사용자ID</label> <span class="starIcon">⁕</span> </td>
            <td class="td2"><input type="text" name="userId" id="userId" onkeyup="userIdCheck()" required> 
              <input type="button" value="ID중복확인"  id="chekcIdButton"><br>
              <span class="checkMessage" id="userIdMessage"></span>
            </td>
          </tr>
          <!-- 사용자 비밀번호  -->
          <tr>
            <td class="td1"><label for="userPw">비밀번호</label> <span class="starIcon">⁕</span></td>
            <td class="td2"><input type="password" name="userPw" id="userPw" onkeyup="userPwCheck()" required> <span> ·
                영문,숫자,특수문자(!, @, $, %, ^, &, *) 8자리 이상</span><br>
              <span class="checkMessage" id="userPwMessage"></span>
            </td>
          </tr>
          <!-- 비밀번호 확인  -->
          <tr>
            <td class="td1"><label for="userPwok">비밀번호 확인</label><span class="starIcon">⁕</span></td>
            <td class="td2"><input type="password" name="userPwok" id="userPwok" onkeyup="userPwokCheck()"  required><br>
              <span class="checkMessage" id="userPwokMessage"></span>
            </td>
          </tr>
          <!-- 사용자 이름  -->
          <tr>
            <td class="td1"><label for="userName">성명</label> <span class="starIcon">⁕</span></td>
            <td class="td2"><input type="text" name="userName" id="userName" onkeyup="userNameCheck()"  required autocomplete="name">
              <span> · 띄어쓰기 없이 입력, 반드시 실명이어야 합니다!</span> <br>
              <span class="checkMessage" id="nameMessage"></span></td>
          </tr>
          <!-- 사용자 생년월일 -->
          <tr>
            <td class="td1"><label for="userYear">생년월일</label> <span class="starIcon">⁕</span></td>
            <td class="td2"><input  type="text" id="userYear" size="9" required >
              <select class="userBirth-list" name="userYear" id="year-list" onclick="userBirthCheck()"><option value="" selected disabled>년도</option></select>
  
              <input  type="text" id="userMonth" size="4" required >
              <select class="userBirth-list" name="userMonth" id="month-list" onclick="userBirthCheck()"><option value="" selected disabled>월</option></select>
  
              <input  type="text" id="userDay" size="4" required >
              <select class="userBirth-list"  name="userDay" id="day-list" onclick="userBirthCheck()"><option value=""  selected disabled>일</option></select>
              <span class="checkMessage" id="userBirthMessage"></span></td>
          </tr>
          <!--사용자 email  -->
          <tr>
            <td class="td1"><label for="userEmail">E-mail</label> <span class="starIcon">⁕</span></td>
            <td class="td2"><input type="text" name="userEmail" id="userEmail" size="15" onkeyup="userEmailCheck()" required> 
            
              <span> <small>@</small> </span>
              <!-- email select   -->
              <input type="text" id="domain" size="15" required  onkeyup="userEmailCheck()" >
              <select id="domainChange" name="userDomain" onclick="userEmailCheck()">
                <option value="direct" >직접입력</option>
                <option value="google.com">gmail.com</option>
                <option value="naver.com">naver.com</option>
                <option value="nate.com">nate.com</option>
               <option value="hanmail.net">hanmail.net</option>
              </select>
  
              <span id="mailbold"> 메일 수신여부</span> <input type="checkbox" name="emailCheck" id="emailCheck"> <br>
              <span class="checkMessage" id="domainMessage"></span>
              <p class="emailP"> · <span id="boldspan">할인 이벤트정보</span> 및 할인쿠폰, 관심분야 등 꼭 필요한 정보를 빠르게 받아보실 수 있습니다</p>
              <p class="emailP"> · 비밀번호 분실시 E-mail로 확인해 드리니, <span id="skybluespan">정확한 E-mail주소를 기입</span>해 주세요.</p>
            </td>
          </tr>
          <!-- 사용자 우편번호  -->
          <tr>
            <td class="td1"><label for="userPostal">우편번호</label> <span class="starIcon">⁕</span></td>
            <td class="td2"><input class="postal" id="userPostal" type="text" name="userPostal" onkeyup="userPostalCheck()" required readonly> 
                <button class="joinMembership-button" type="button" onclick="sample6_execDaumPostcode()">우편번호 검색</button> <br>
                <span class="checkMessage" id="postalMessage"></span>
              </td>
          </tr>
          <!-- 사용자 주소  -->
          <tr>
            <td class="td1"><label for="userAddress">주소</label> <span class="starIcon">⁕</span></td>
            <td class="td2"><input class="address" name="userAddress1" id="userAddress" type="text" size="40" placeholder="주소" required onkeyup="userAddressCheck()"><br>
              <input class="address" id="userDetailAddress"  name="userAddress2" type="text" size="40" value=" " placeholder="상세주소"> <br>
              <span class="checkMessage" id="addressMessage"></span>
          </td>
          </tr>
          <!-- 사용자 전화번호 입력 숫자만   -->
          <tr>
            <td class="td1"><label for="userPhone">휴대폰번호</label> <span class="starIcon">⁕</span></td>
            <td class="td2"><input class="phone" name="userPhone" id="userPhone" type="tel" size="20" onkeyup="userPhoneCheck()" required> <br>
              <span class="checkMessage" id="PhoneMessage"></span>
            </td>
          </tr>
          <!-- 사용자 정보 확인 후 전송  -->
          <tr>
            <td colspan="2" style="text-align: center; border: none;"><br>
              <button class="joinMembership-button" id="submit-button" type="button" onclick="allCheck()" >회원가입</button> 
              <button class="joinMembership-button" id="reset-button" type="button" >취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</body>
</html>