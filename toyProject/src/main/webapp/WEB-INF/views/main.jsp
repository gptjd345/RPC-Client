<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="zxx">
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Watch shop | eCommers</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="shortcut icon" type="image/x-icon" href="/resources/assets/img/favicon.ico">

     <!-- 기본적으로 사용하는 css 내용들  -->
	<%@include file="./include/baseCss.jsp" %>
</head>

<body>
  <header>
    <!-- Header Start -->
    <%@ include file="include/header.jsp" %>
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
          <div class="cart_inner">
            <div class="table-responsive">
              <table class="table">
                <tbody>
                  <tr class="bottom_button">
                    <td>
                      <a class="btn_1" href="./member/list">회원관리</a>
                    </td>
                    <td>
                        <a class="btn_1" href="#">게시판</a>
                    </td>
                    <td>
                      <div class="cupon_text float-right">
                        <a class="btn_1" href="#">상품관리</a>
                      </div>
                      
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
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
	<%@include file="./include/baseJS.jsp" %>

</body>
</html>