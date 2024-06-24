<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="detailContentWrap">
    <div class="detailContent">
    <div class="content_header">
      <h1 class="content_title">${pd.pf_genre} &lt;${pd.pf_name}&gt;</h1>
    </div>
    <div class="content_mainArea">
      <img src="/ticketMarket/image/BigbannerImage/${pd.pf_imageUrl}" class="content_poster">
      <div class="chicago-details">
        <div class="details_info"><b>장소:</b> ${pd.pf_venue}</div>
        <div class="details_info"><b>공연기간:</b> ${pd.pf_date}</div>
        <div class="details_info"><b>관람연령:</b> ${pd.pf_limitAge}세 이상 관람가</div>
        <div class="details_info"><b>가격:</b> ${pd.pf_price}원</div>
        <div class="details_info"><b>혜택:</b> 무이자할부</div>
        <input type="hidden" id="limitAge" value="${pd.pf_limitAge}">
        <input type="hidden" id="userAge" value="${age}">
      </div>
      <div class="contentSeatsArea">
        <div class="contentStage">
          <div class="stage">S T A G E</div>
        </div>
        <div></div>
        <form class="seatWrap" method="post" id="seatForm" action="#">
        	<input type="hidden" value="${pd.pf_no}" name="pf_no" id="pf_no">
        	<input type="hidden" value="${pd.pf_price}" name="pf_price" id="pf_price">
        	<input type="hidden" value="${pd.pf_id}" name="pf_id" id="pf_id">
          <label for="seatCheckNum" class="seatCheckNum"></label>
          <label for="seatCheckNum" class="seatCheckNum">1</label>
          <label for="seatCheckNum" class="seatCheckNum">2</label>
          <label for="seatCheckNum" class="seatCheckNum">3</label>
          <label for="seatCheckNum" class="seatCheckNum">4</label>
          <label for="seatCheckNum" class="seatCheckNum">5</label>
          <label for="seatCheckNum" class="seatCheckNum">6</label>
          <label for="seatCheckNum" class="seatCheckNum">7</label>
          <label for="seatCheckNum" class="seatCheckNum">8</label>
          <label for="seatCheckNum" class="seatCheckNum">9</label>
          <label for="seatCheckNum" class="seatCheckNum">10</label>
          
          <!-- 반복문 label, input-->
          <c:forEach var="row" items="${rowList}" varStatus="rowStatus">
          <label for="seatCheckNum" class="seatCheckNum">${row}</label>
          	<c:forEach var="col" begin="1" end="10" varStatus="colStatus">
          		<c:set var="seatId" value="${row}${col}"/>
          			<c:if test="${not empty list}">
          			<c:set var="flag" value="false"/>
          				<c:forEach var="list" items="${list}">
          					<c:if test="${seatId eq list && flag != true}">
        							 <label for="seatCheckBox${colStatus.index+(10*rowStatus.index)}" class="seatCheckBox_res">${seatId}</label>
        							 <c:set var="flag" value="true"/>
        							 </c:if>
          				</c:forEach>
          			</c:if>
          			<c:if test="${!flag}">
				          <label for="seatCheckBox${colStatus.index+(10*rowStatus.index)}" class="seatCheckBox_lable">${seatId}</label>
        				  <input type="checkbox" class="seatCheckBox" name="seatCheckBox" id="seatCheckBox${colStatus.index+(10*rowStatus.index)}" value="${seatId}">
          			</c:if>
          	</c:forEach>
          </c:forEach>
          <c:if test="${loginId != null }">
      		 <button class="seatBoxButton" id="seatBoxButton">예매하기</button>
          </c:if>
        </form>
      </div>
    </div>
  </div>
  </div>