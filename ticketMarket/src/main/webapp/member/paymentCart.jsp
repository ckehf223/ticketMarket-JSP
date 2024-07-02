<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <div id="paymentContent">
    <form class="paymentWrap" id="paymentCartForm" action="/ticketMarket/paymentCartPro.do" onsubmit="return payment()" method="post">
      <div class="paymentTitleArea">
        <h3>주문 / 결제</h3>
      </div>
      <div class="customerInfoArea">
        <h2>구매자정보</h2>
        <table class="customerTable">
          <tr>
            <td class="customer_col1">이름</td>
            <td class="customer_col2">${member.ct_name}</td>
          </tr>
          <tr>
            <td class="customer_col1">이메일</td>
            <td class="customer_col2">${member.ct_email}</td>
          </tr>
          <tr>
            <td class="customer_col1">휴대폰번호</td>
            <td class="customer_col2">
            <div class="customerPhone">${member.ct_phone}</div>
            <span class="phone_desc">* 티켓정보는 구매한 분의 번호로 전송됩니다.</span>
            </td>
          </tr>
        </table>
      </div>
      <div class="deliveryAddress">
        <h2>받는사람정보</h2>
        <table class="deliveryTable">
          <tr>
            <td class="delivery_col1">이름</td>
            <td class="delivery_col2">
            <input type="text" id="deliveryName" name="name" value="${member.ct_name}"></td>
          </tr>
          <tr>
            <td class="delivery_col1">배송주소</td>
            <td class="delivery_col2">
            <input type="text"  id="paymentAddress1" name="addr1" readonly value="${member.ct_address}">
            <input type="text" placeholder="상세주소" id="paymentAddress2" name="addr2">
            <button type="button" class="changeAddressButton" onclick="sample7_execDaumPostcode()" >배송지 변경</button>
            </td>
          </tr>
          <tr>
            <td class="delivery_col1">배송 요청사항</td>
            <td class="delivery_col2"><input type="text" id="requestMessage" name="message" value="  "></td>
          </tr>
        </table>
      </div>
      <div class="paymentItemArea">
        <div class="paymentItemTitle">공연 항목 ${count}건</div>
        <div class="item_groupBox">
          <div class="deliveryDateBox">
            <span class="deliveryDate1">${date}</span>
            <span class="deliveryDate2">도착예정</span>
            <input type="hidden" id="deliveryDate" name="date" value="${date}">
          </div>
          <div class="itemListArea">
            <!-- 반복-->
            <c:forEach var="cart" items="${cartMap}">
            <div class="itemBox">
                <img class="itemBoxImg" src="/ticketMarket/image/BigbannerImage/${cart.get('pf_imageurl')}" >
                <div class="itemDatailBox">
                  <span><b>장르</b>: ${cart.get('pf_genre')}</span>
                  <span><b>공연</b>: ${cart.get('pf_name')}</span>
                  <span><b>장소</b>: ${cart.get('pf_venue')}</span>
                  <span><b>공연기간</b>: ${cart.get('pf_date')}</span>
                </div>
                <div class="itemDatailBox">
                  <span><b>수량</b>: ${cart.get('cart_quantity')}</span>
                  <span><b>좌석정보</b>: ${cart.get('seat_location')}</span>
                  <span><b>총 가격</b>: ${cart.get('cart_totalprice')}원</span>
                </div>
              </div>
              </c:forEach>
              <!-- 반복 끝-->
            </div>
          </div>
        </div>
        <div class="paymentBox">
          <h2>결제정보</h2>
            <table class="paymentBoxTable">
              <tr>
                <td class="payment_col1">총상품가격</td>
                <td class="payment_col2">${totalPrice}원
                 <input type="hidden" name="totalPrice" value="${totalPrice}"></td>
                
              </tr>
              <tr>
                <td class="payment_col1">할인금액</td>
                <td class="payment_col2">${sale}원
                <input type="hidden" name="salesPrice" value="${sale}"></td>
              </tr>
              <tr>
                <td class="payment_col1">적립예상포인트</td>
                <td class="payment_col2">${point}점
                <input type="hidden" name="savePoint" value="${point}"></td>
              </tr>
              <c:choose>
              	<c:when test="${member.ct_mileage >= 5000 }">
              <tr>
                <td class="payment_col1">포인트</td>
                <td class="payment_col2">
                  <div><span id="usePoint">0</span>점 &nbsp;&nbsp;&nbsp;&nbsp;보유: ${member.ct_mileage}점</div>
                  <input type="text" placeholder="5000원 이상부터 사용가능" id="pointInput" name="usePoint" value="0">
                  <button type="button" class="pointButton" id="pointButton" onclick="onClickPoint()">적용</button></td>
             		</tr>              	
              	</c:when>
              	<c:otherwise>
	              	<tr>
		                <td class="payment_col1">포인트</td>
		                <td class="payment_col2">
		                  <div><span id="usePoint">0</span>점 &nbsp;&nbsp;&nbsp;&nbsp;보유: ${member.ct_mileage}점</div>
		                  <input type="text" placeholder="5000원 이상부터 사용가능" id="pointInput" name="usePoint" value="0" disabled></td>
	              	</tr>
              	</c:otherwise>
              </c:choose>
              <tr>
                <td class="payment_col1">총결제금액</td>
                <td class="payment_col2"><span id="realPrice">${realPrice}</span>원
                <input type="hidden" value="${realPrice}" id="changePrice" name="changePrice">
                <input type="hidden" value="${realPrice}" id="finalPrice">
                </td>
              </tr>
            </table>
          </div>
          <div class="paymentButtonArea">
            <input type="submit" class="paymentsButton" id="paymentsButton" value="결제하기">
          </div>
        </form>
        </div>