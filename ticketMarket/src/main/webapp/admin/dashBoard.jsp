<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="main-content">
        <div class="rankHeader">
          오늘의 랭킹
        </div>
        <div class="contentsWrap">
          <div class="contentSwipe">
            <div class="contentSlide">
              <div class="rankNum">1</div>
              <a href="#" class="contentSlide_a">
                <div class="contentSlideItem">
                  <img src="/ticketMarket/image/BigbannerImage/${map.get(0).get('pf_imageurl') }" />
                </div>
                <ul class="contentSlide_ul">
                  <li class="contentSlide_title">${map.get(0).get('pf_name')}</li>
                </ul>
              </a>
            </div>
            <div class="contentSlide">
              <div class="rankNum">2</div>
              <a href="#" class="contentSlide_a">
                <div class="contentSlideItem">
                  <img src="/ticketMarket/image/BigbannerImage/${map.get(1).get('pf_imageurl') }" alt="" />
                </div>
                <ul class="contentSlide_ul">
                  <li class="contentSlide_title">${map.get(1).get('pf_name')}</li>
                </ul>
              </a>
            </div>
            <div class="contentSlide">
              <div class="rankNum">3</div>
              <a href="#" class="contentSlide_a">
                <div class="contentSlideItem">
                  <img src="/ticketMarket/image/BigbannerImage/${map.get(2).get('pf_imageurl') }"/>
                </div>
                <ul class="contentSlide_ul">
                  <li class="contentSlide_title">${map.get(2).get('pf_name')}</li>
                </ul>
              </a>
            </div>
          </div>
        </div>
      </div>