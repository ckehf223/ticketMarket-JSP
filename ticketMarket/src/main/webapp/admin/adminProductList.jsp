<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-content">
        <div class="pfListWrap">
          <h2>공연 리스트</h2>
          <ul class="tickets_list">
            <c:forEach var="list" items="${list}">
            <li>
              <div class="ticket_item">
                <img src="/ticketMarket/image/BigbannerImage/${list.pf_imageUrl}">
                <div class="ticket_details">
                  <span><b>ID</b>: ${list.pf_id}</span>
                  <span><b>공연</b>: ${list.pf_name}</span>
                  <span><b>장르</b>: ${list.pf_genre}</span>
                </div>
                <div class="ticket_details">
                  <span><b>장소</b>: ${list.pf_venue}</span>
                  <span><b>날짜</b>: ${list.pf_date}</span>
                  <span><b>관람연령</b>: ${list.pf_limitAge}</span>
                </div>
                <div class="ticket_details">
                  <span><b>가격</b>: ${list.pf_price}</span>
                  <span><b>누적판매량</b>: ${list.pf_quantity}</span>
                  <c:choose>
                  <c:when test="${list.pf_allowcheck == 1 }">
                  <span><b>판매여부</b>: O</span>
                  </c:when>
                  <c:otherwise>
                   <span><b>판매여부</b>: X</span>
                  </c:otherwise>
                  </c:choose>
                </div>
                <button class="update_productButton" onclick="onUpdateProduct(${list.pf_no})">수정</button>
                <button class="delete_productButton" onclick="onDeleteProduct(${list.pf_no})">삭제</button>
              </div>
            </li>
            </c:forEach>
          </ul>
        </div>
      </div>