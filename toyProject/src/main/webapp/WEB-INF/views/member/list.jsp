<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
            
		      <!--================ 검색 폼  ====================-->
		      <form action="#">
		      	<input type="hidden" name="curBlock" value="${pageDTO.curBlock}"/>
			      <section id="search-section">
			      <!-- =========== 검색 정보 유지================= -->
			      <c:choose>
			      	<c:when test="${pageDTO.searchOption == 'none'}">	
			            <select name="searchOption">
			                <option value="none" selected>=== 선택 ===</option>
			                <option value="all">멤버번호+아이디+이름</option>
			                <option value="membernum">멤버번호</option>
			                <option value="userid">아이디</option>
			                <option value="name">이름</option>
			            </select>
			         </c:when>  
			         <c:when test="${pageDTO.searchOption == 'all'}">	
			            <select name="searchOption">
			                <option value="none">=== 선택 ===</option>
			                <option value="all" selected>멤버번호+아이디+이름</option>
			                <option value="membernum">멤버번호</option>
			                <option value="userid">아이디</option>
			                <option value="name">이름</option>
			            </select>
			         </c:when> 
			         <c:when test="${pageDTO.searchOption == 'membernum'}">	
			            <select name="searchOption">
			                <option value="none">=== 선택 ===</option>
			                <option value="all">멤버번호+아이디+이름</option>
			                <option value="membernum" selected>멤버번호</option>
			                <option value="userid">아이디</option>
			                <option value="name">이름</option>
			            </select>
			         </c:when> 
			         <c:when test="${pageDTO.searchOption == 'userid'}">	
			            <select name="searchOption">
			                <option value="none">=== 선택 ===</option>
			                <option value="all">멤버번호+아이디+이름</option>
			                <option value="membernum">멤버번호</option>
			                <option value="userid" selected>아이디</option>
			                <option value="name">이름</option>
			            </select>
			         </c:when>  
			         <c:when test="${pageDTO.searchOption == 'name'}">	
			            <select name="searchOption">
			                <option value="none">=== 선택 ===</option>
			                <option value="all">멤버번호+아이디+이름</option>
			                <option value="membernum">멤버번호</option>
			                <option value="userid">아이디</option>
			                <option value="name" selected>이름</option>
			            </select>
			         </c:when> 
			      </c:choose>      
			            <div class="col-lg-2">
			                <input type="text" class="form-control" name="searchKey" value="${pageDTO.searchKey}"/>
			            </div>
			            <button id="searchButton" type="button" class="btn-secondary">검색</button>
			       </section>
		       </form>
		       
		       
              <table class="table">
                <thead>
                	<th>선택</th>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>가입일자</th>
                </thead>
                <tbody>
	                    
                </tbody>
              </table>
	              <nav>
	          		
	              </nav>
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

	<script src="/resources/assets/js/toyProject/member/list.js?ver1.8"></script>
	
	<!-- 날짜 포맷을 위한 라이브러리 jstl의 format date 와 유사  -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>



	
	


</body>
</html>