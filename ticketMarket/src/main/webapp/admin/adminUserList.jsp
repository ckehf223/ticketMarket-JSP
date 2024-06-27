<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="main-content">
        <div class="userListWrap">
          <div class="userListArea">
            <div>
              <h1>회원 리스트</h1>
            </div>
            <table class="userList">
              <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>이메일</th>
                <th>등급</th>
                <th>마일리지</th>
                <th>누적금액</th>
              </tr>
              <tbody>
                <c:forEach var="list" items="${list}">
                <tr>
                  <td>${list.ct_id}</td>
                  <td>${list.ct_name}</td>
                  <td>${list.ct_email}</td>
                  <td>${list.ct_grade}</td>
                  <td>${list.ct_mileage}점</td>
                  <td>${list.ct_totalamount}원</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>