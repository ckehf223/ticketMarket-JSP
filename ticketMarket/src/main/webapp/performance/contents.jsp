<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="mainContent">
	<div class="bigBannerArea">
		<div class="bigBanner">
		<c:forEach var="list" items="${pfList}" begin="0" end="3">
			<div class="bigBannerItem">
				<a href="/ticketMarket/detailContent.do?no=${list.pf_no}">
				<img src="/ticketMarket/image/BigbannerImage/${list.pf_imageUrl}"></a>
			</div>
		</c:forEach>
		</div>
	</div>
	<div class="minBannerArea">
		<div class="minBanner">
			<div class="minBannerItem">
				<a href="#"><img src="/ticketMarket/image/MinibannerImage/chance.webp"></a>
			</div>
			<div class="minBannerItem">
				<a href="#"><img src="/ticketMarket/image/MinibannerImage/culture.webp"></a>
			</div>
			<div class="minBannerItem">
				<a href="#"><img src="/ticketMarket/image/MinibannerImage/heahwa.webp"></a>
			</div>
		</div>
	</div>
</div>
<div class="panelTitle">
	<h2> ${category} </h2>
</div>
<div class="contentsWrap">
	<div class="contentSwipe">
	<!-- 반복영역 -->
	<c:forEach items="${pfList}" var="list">
		<div class="contentSlide">
			<a href="/ticketMarket/detailContent.do?no=${list.pf_no}" class="contentSlide_a">
				<div class="contentSlideItem">
					<img src="/ticketMarket/image/BigbannerImage/${list.pf_imageUrl}">
				</div>
				<ul class="contentSlide_ul">
					<li class="contentSlide_title">${list.pf_name}</li>
					<li class="contentSlide_venue">${list.pf_venue}</li>
					<li class="contentSlide_day">${list.pf_date}</li>
					<li class="contentSlide_price">${list.pf_price}원</li>
				</ul>
			</a>
		</div>
		</c:forEach>
		<!-- 반복영역 -->
	</div>
</div>