<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="main-content">
        <div class="addPfWrap">
          <h2>공연 추가하기</h2>
          <div class="addPfArea">
            <table class="addTable">
              <tbody>
                <tr>
                  <td class="td1"><label for="productName">공연명</label> </td>
                  <td class="td2"><input type="text" name="productName" id="productName" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productGenre">장르</label></td>
                  <td class="td2"><input type="text" name="productGenre" id="productGenre" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productDate">공연기간</label></td>
                  <td class="td2"><input type="text" name="productDate" id="productDate" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productVenue">공연장소</label></td>
                  <td class="td2"><input type="text" name="productVenue" id="productVenue" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productAge">관람연령</label></td>
                  <td class="td2"><input type="text" name="productAge" id="productAge" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productSeats">좌석수</label></td>
                  <td class="td2"><input type="text" name="productSeats" id="productSeats" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productPrice">가격</label></td>
                  <td class="td2"><input type="text" name="productPrice" id="productPrice" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productPoster">포스터</label></td>
                  <td class="td2"><input type="file" name="productPoster" id="productPoster" required>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="text-align: center; border: none;"><br>
                    <button class="productButton" id="addProductButton">공연등록</button>
                    <button class="resetProductButton" id="resetProductButton">취소</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>