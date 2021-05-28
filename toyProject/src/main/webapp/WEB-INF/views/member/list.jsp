<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- ClientInvoker 클래스의 메소드를 사용하기 위해 자바 클래스를 import한다.  -->
<%@page import="com.example.toyProject.ClientInvoker" %>
<!-- 테스트를 위한 memberDTO를 받아옴 -->
<%@page import="com.example.toyProject.model.member.dto.PageDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zxx">
<head>
	<!-- 기본적으로 사용하는 css 내용들  -->
	<%@include file="../include/baseCss.jsp" %>
	
	<!-- Toy Project CSS -->
    <link rel="stylesheet" href="/resources/assets/css/member/list.css?version=1.0">
    
</head>
<body>
  <header>
    <!-- Header Start -->
   <%@ include file="../include/header.jsp" %>
    <!-- Header End -->
  </header>
  <main>
      <!-- Hero Area Start-->
      <div class="slider-area ">
          <div class="single-slider slider-height2 d-flex align-items-center">
              <div class="container">
                  <div class="row">
                      <div class="col-xl-12">
                          <div class="hero-cap text-center">
                              <h2>Main</h2>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
      <!--================Cart Area =================-->
      <section class="cart_area section_padding">
      
	         
        <div class="container">
            <div class="table-responsive">
            <% 
            	 PageDTO pageDTO = (PageDTO)request.getAttribute("pageDTO");	
            	out.println("Client Invoker TotalList : "+ClientInvoker.getListTotal(pageDTO.getSearchOption(), pageDTO.getSearchKey()));
            	
            %>
		      <!--================ 검색 폼  ====================-->
		       
		      <div id="memberListPrint"> 
              
	          </div>   
	             
	          <form action="delete.do" method="post" id="deleteForm"> 
		           <!-- 현재 페이지 블록값을 저장 -->   
		           <input type="hidden" class="curBlock" name="curBlock" value="${pageDTO.curBlock}"/>
		           <input type="hidden" class="searchOption" name="searchOption" value="${pageDTO.searchOption}"/>
		           <input type="hidden" class="searchKey" name="searchKey" value="${pageDTO.searchKey}"/>
		           
		             
		             <div class="btn_Container">
		             
		                 <button type="button" class="btn_1" id="regist">등록</button>
		                 <button type="button" class="btn_1" id="delete">삭제</button>
		         
		             </div>
              </form>
          </div>
          </div>    
      </section>
      <!--================End Cart Area =================-->
  </main>
   <!--? Search model Begin -->
  <div class="search-model-box">
      <div class="h-100 d-flex align-items-center justify-content-center">
          <div class="search-close-btn">+</div>
          <form class="search-model-form">
              <input type="text" id="search-input" placeholder="Searching key.....">
          </form>
      </div>
  </div>
  <!-- Search model end -->
  

  <!-- JS here -->
  
	<!-- 기본적으로 사용하는 JS 내용들  -->
	<%@include file="../include/baseJS.jsp" %>
	
	<!-- Toy Project JS -->

	<script src="/resources/assets/js/toyProject/member/list.js?ver1.1"></script>
	
	<!-- 날짜 포맷을 위한 라이브러리 jstl의 format date 와 유사  -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>



	
	


</body>
</html>