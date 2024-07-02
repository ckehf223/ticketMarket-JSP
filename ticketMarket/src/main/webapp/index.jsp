<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${type == 0 }">
	<jsp:include page="/admin/adminTop.jsp" />
	<c:if test="${adminLoginId == null}">
				<jsp:include page="/admin/adminLogin.jsp" />
	</c:if>
	<c:if test="${adminLoginId != null}">
				<jsp:include page="${cont}" />
	</c:if>
	<jsp:include page="/admin/adminBottom.jsp" />
</c:if>


<c:if test="${type == null || type == 1 }">
	<c:choose>
		<c:when
			test="${(cont != null && cont eq '/member/login.jsp') || (cont != null && cont eq '/member/join.jsp') }">
			<jsp:include page="${cont}" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/top/top.jsp" />
			<c:if test="${cont == null}">
				<jsp:include page="/main/main.jsp" />
			</c:if>
			<c:if test="${cont != null}">
				<jsp:include page="${cont}" />
			</c:if>
			<jsp:include page="/bottom/bottom.jsp" />
		</c:otherwise>
	</c:choose>
</c:if>

