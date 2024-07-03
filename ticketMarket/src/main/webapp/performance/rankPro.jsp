<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="rankWrap">
	<div class="rankHeader">
		<h2>공연 랭킹</h2>
	</div>
	<div class="rankMiddle">
		<p>${today} 기준</p>
	</div>
	<div class="rankMain">
		<div class="rankMain_top">
			<div class="rankMain_top3">
				<!-- 반복-->
				<c:forEach var="list" items="${list}" begin="0" end="2" varStatus="i">
				<div class="rank_topitem">
					<div class="rank_topIcon">
						<div class="rank_topText">${i.index+1}</div>
						<div class="rank_topImg"></div>
					</div>
					<div class="rank_topContent">

						<a class="rank_topContent_top" href="/ticketMarket/detailContent.do?no=${list.get('pf_no')}"> <img
							src="/ticketMarket/image/BigbannerImage/${list.get('pf_imageurl')}">
						</a>
						<ul class="rank_topContent_section">
							<li class="topContent_section_title">${list.get('pf_name')}</li>
							<li class="topContent_section_venue">${list.get('pf_venue') }</li>
							<li class="topContent_section_day">${list.get('pf_date')}</li>
						</ul>
					</div>
				</div>
				<!-- 반복-->
				</c:forEach>
			</div>
		</div>
		<div class="rankSection_wrap">
		<c:forEach var="list" items="${list }" begin="3" end="9" varStatus="i">
			<!-- 반복 -->
			<div class="rankSection">
				<div class="rankSection_num">
					<p>${i.index+1}</p>
				</div>
				<div class="rankSection_item">
					<a class="rankSection_img" href="/ticketMarket/detailContent.do?no=${list.get('pf_no')}"> <img
						src="/ticketMarket/image/BigbannerImage/${list.get('pf_imageurl')}">
					</a>
					<div class="rankSection_itemContent">
						<div class="rankSection_itemTitle">${list.get('pf_name')}</div>
						<div class="rankSection_itemVenue">${list.get('pf_venue') }</div>
						<div class="rankSection_itemDate">${list.get('pf_date')}</div>
					</div>
				</div>
			</div>
			<!-- 반복 -->
			</c:forEach>
		</div>
	</div>
</div>