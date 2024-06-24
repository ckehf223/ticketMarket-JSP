<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 	<main id="myPageContent">
      <section class="profile_section">
        <h2>내 프로필</h2>
        <div class="profile_info">
          <img class="profile_pic" src="/ticketMarket/image/기본프로필.png">
          <div class="profile_details">
            <p><b>이름:</b> ${member.ct_name}</p>
            <p><b>이메일:</b> ${member.ct_email}</p>
            <p><b>전화번호:</b> ${member.ct_phone}</p>
            <p><b>주소:</b> ${member.ct_address}</p>
            <p><b>등급:</b> ${member.ct_grade}</p>
            <p><b>포인트:</b> ${member.ct_mileage}점</p>
            <button class="edit_profile_button" id="modifyButton">회원정보 수정</button>
            <button class="edit_profile_button" id="deleteButton">회원탈퇴</button>
          </div>
        </div>
      </section>
      <section class="tickets_section">
        <h2>예약 내역</h2>
        <ul class="tickets_list">
          <!-- 예약내역 for문 -->
          <c:forEach items="${cartMap}" var="citem">
          <li>
            <div class="ticket_item">
              <img src="/ticketMarket/image/BigbannerImage/${citem.get('pf_imageurl')}" >
              <div class="ticket_details">
                <span><b>장르</b>: ${citem.get('pf_genre')}</span>
                <span><b>공연</b>: ${citem.get('pf_name')}</span>
                <span><b>날짜</b>: ${citem.get('pf_date')}</span>
                <span><b>장소</b>: ${citem.get('pf_venue')}</span>
                <span><b>좌석정보</b>: ${citem.get('seat_location')}</span>
                <span><b>총 가격</b>: ${citem.get('cart_totalprice')}</span>
              </div>
              <button class="cancel_ticket_button" onclick="cancelTicket(${citem.get('cart_no')})">예약 취소</button>
            </div>
          </li>
          </c:forEach>
        </ul>
      </section>
      <c:if test="${count > 0}">
      <div class="selectButtonArea">
      	<button class="selectButton" id="paymentButton">결제하기</button>
      	<button class="selectButton" id="cencelAllButton">전체 취소</button>
      </div>      
      </c:if>
      <section class="payments_section">
        <h2>결제내역</h2>
        <ul class="payments_list">
        <c:forEach items="${payMap}" var="pitem" varStatus="j">
          <li>
            <div class="payments_item">
              <img src="/ticketMarket/image/BigbannerImage/${pitem.get('pf_imageurl')}">
              <div class="payment_details">
                <span><b>장르</b>: ${pitem.get('pf_genre')}</span>
                <span><b>공연</b>: ${pitem.get('pf_name')}</span>
                <span><b>날짜</b>: ${pitem.get('pf_date')}</span>
                <span><b>장소</b>: ${pitem.get('pf_venue')}</span>
                <span><b>좌석정보</b>: ${pitem.get('seat_location')}</span>
                <span><b>총 가격</b>: ${pitem.get('cart_totalprice')}</span>
              </div>
              <div class="payment_details">
                <span><b>배송자명</b>: ${pitem.get('delivery_address')}</span>
                <span><b>배송주소</b>: ${pitem.get('delivery_name')}</span>
                <span><b>배송메세지</b>: ${pitem.get('delivery_message')}</span>
                <span><b>배송일</b>: ${pitem.get('delivery_date')}</span>
              </div>
            </div>
          </li>
          </c:forEach>
        </ul>
      </section>
    </main>