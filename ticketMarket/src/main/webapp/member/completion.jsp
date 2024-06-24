<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="/ticketMarket/member/completion.js"></script> -->
<link rel="stylesheet" href="/ticketMarket/member/completion.css">
<div class="completion">
    <div class="completionWrap">
      <div class="completionHeader">
        <h2>결제완료</h2>
      </div>
      <div class="completionMessage">
        <span><strong>결제 완료</strong>되었습니다. 감사합니다!</span>
      </div>
      <div class="completionDeliveryArea">
        <div class="deliveryInfo">
          <h3>티켓배송 정보</h3>
        </div>
        <div class="deliveryContentArea">
          <div class="deliveryContentHeader">
            <div class="contentHeader">
            <span class="contentHeader_span1">${date} 도착예정 (상품 ${count} 개)</span>
            <span class="contentHeader_span2">판매자: fancyTicket</span>
            <div id="openButton"></div>
            </div>
            <!-- 반복문 시작-->
            <c:forEach var="item" items="${cartMap}">
            <div class="contentItemArea">
              <div class="contentItemBox">
                <a href="/ticketMarket/detailContent.do?no=${item.get('pf_no')}">
                <img src="./image/BigbannerImage/${item.get('pf_imageurl')}" ></a>
                <div class="contentInfo">
                <span><b>장르</b>: ${item.get('pf_genre')}</span>
                <span><b>공연</b>: ${item.get('pf_name')}</span>
                <span><b>장소</b>: ${item.get('pf_venue')}</span>
                <span><b>좌석정보</b>: ${item.get('seat_location')}</span>
                <span><b>총 가격</b>: ${item.get('cart_totalprice')}</span>
                </div>
              </div>
            </div>
            </c:forEach>
              <!-- 반복 끝-->
          </div>
        </div>
        <table class="deliveryContentTable">
          <tr>
            <td class="deliveryContent_col1">
              <div class="deliveryInfo">
                <h3>받는사람 정보</h3>
                <table class="deliveryInfoTable">
                  <tr>
                    <td class="deliveryInfo_col1">받는사람</td>
                    <td class="deliveryInfo_col2">${name}</td>
                  </tr>
                  <tr>
                    <td class="deliveryInfo_col1">받는주소</td>
                    <td class="deliveryInfo_col2">${address}</td>
                  </tr>
                  <tr>
                    <td class="deliveryInfo_col1">베송요청사항</td>
                    <td class="deliveryInfo_col2">${message}</td>
                  </tr>
                </table>
              </div>
            </td>
            <td class="deliveryCotent_col2">
              <div class="paymentInfo">
                <h3>결제 정보</h3>
                <table class="paymentInfoTable">
                  <tr>
                    <td class="paymentInfo_col1">주문금액</td>
                    <td class="paymentInfo_col2">${totalPrice} 원</td>
                  </tr>
                  <tr>
                    <td class="paymentInfo_col1">할인금액</td>
                    <td class="paymentInfo_col2">-${salesPrice} 원</td>
                  </tr>
                  <tr>
                    <td class="paymentInfo_col1">적립포인트</td>
                    <td class="paymentInfo_col2">${savePoint} 점</td>
                  </tr>
                  <tr>
                    <td class="totalPrice_col1">총 결제금액</td>
                    <td class="totalPrice_col2">${finalPrice}원</td>
                  </tr>
                </table>
              </div>
            </td>
          </tr>
        </table>
        <div class="completionButtonArea">
          <button class="completionButton" id="completionButton">쇼핑 계속하기</button>
        </div>
      </div>
    </div>
  </div>