<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-content">
        <div class="pfListWrap">
          <h2>공연 리스트</h2>
          <ul class="tickets_list">
            <!-- 예약내역 for문 -->
            <li>
              <div class="ticket_item">
                <img src="./image/BigbannerImage/chicago.webp" alt="티켓 이미지">
                <div class="ticket_details">
                  <span><b>ID</b>: p123123123123</span>
                  <span><b>공연</b>: 뮤지컬 '캣츠'</span>
                  <span><b>장르</b>: 뮤지컬</span>
                </div>
                <div class="ticket_details">
                  <span><b>장소</b>: 서울 예술의 전당</span>
                  <span><b>날짜</b>: 2024년 7월 15일</span>
                </div>
                <div class="ticket_details">
                  <span><b>가격</b>: 서울 예술의 전당</span>
                  <span><b>누적판매량</b>: 서울 예술의 전당</span>
                </div>
                <button class="update_productButton">수정</button>
                <button class="delete_productButton">삭제</button>
              </div>
            </li>
            
          </ul>
        </div>
      </div>