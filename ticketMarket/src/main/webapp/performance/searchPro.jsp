<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="searchMain">
	<div class="searchWrap">
		<c:choose>
			<c:when test="${count == 0 }">
				<div class="searchNone">
					<span><b>" ${text} "</b>에 대한 판매중/예정 공연이 없습니다.</span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="searchContentBox">
					<div class="contentsWrap">
						<h2>${text} 에 대한 검색 내용 입니다.</h2>
						<hr style="margin-bottom: 15px">
						<div class="contentSwipe">
							<c:forEach var="list" items="${pfList}">
								<div class="contentSlide">
									<a href="/ticketMarket/detailContent.do?no=${list.pf_no}"
										class="contentSlide_a">
										<div class="contentSlideItem">
											<img
												src="/ticketMarket/image/BigbannerImage/${list.pf_imageUrl}">
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
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>