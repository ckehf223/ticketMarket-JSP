<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <div class="main-content">
        <div class="addPfWrap">
          <h2>공연정보 수정</h2>
          <div class="addPfArea">
            <table class="addTable">
              <tbody>
                <tr>
                  <td class="td1"><label for="productId">공연ID</label> </td>
                  <td class="td2">${pData.pf_id}<input type="hidden" id="productId" value="${pData.pf_id}"></td>
                </tr>
                <tr>
                  <td class="td1"><label for="productName">공연명</label> </td>
                  <td class="td2"><input type="text" name="productName" id="productName" value="${pData.pf_name}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productGenre">장르</label></td>
                  <td class="td2"><input type="text" name="productGenre" id="productGenre" value="${pData.pf_genre}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productDate">공연기간</label></td>
                  <td class="td2"><input type="text" name="productDate" id="productDate" value="${pData.pf_date}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productVenue">공연장소</label></td>
                  <td class="td2"><input type="text" name="productVenue" id="productVenue" value="${pData.pf_venue}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productAge">관람연령</label></td>
                  <td class="td2"><input type="text" name="productAge" id="productAge" value="${pData.pf_limitAge}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productSeats">좌석수</label></td>
                  <td class="td2"><input type="text" name="productSeats" id="productSeats" value="${pData.pf_totalSeats}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productPrice">가격</label></td>
                  <td class="td2"><input type="text" name="productPrice" id="productPrice" value="${pData.pf_price}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productAllow">판매여부</label></td>
                  <td class="td2"><input type="text" name="productAllow" id="productAllow" value="${pData.pf_allowcheck}" required>
                  </td>
                </tr>
                <tr>
                  <td class="td1"><label for="productPoster">포스터</label></td>
                  <td class="td2"><input type="file" name="productPoster" id="productPoster" value="${pData.pf_imageUrl}" required>
               	</td>
                </tr>
                <tr>
                  <td colspan="2" style="text-align: center; border: none;"><br>
                    <button class="productButton" id="updateProductButton">공연 수정</button>
                    <button class="resetProductButton" id="resetProductButton">취소</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>