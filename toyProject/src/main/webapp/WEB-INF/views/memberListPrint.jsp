<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<!-- 검색 폼 -->
	<form action="#" method="post">
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
	            <button id="searchButton" type="button" onclick="searchButtonClick()" class="btn-secondary">검색</button>
	       </section>
       </form>
<!-- 회원 목록 리스트와 페이저 구현을 여기서 진행한다.  -->

 
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
          	<c:forEach var="row" items="${list}">
           	<tr>
           		<td><input type="checkbox" name="checkbox" value="${row.userid}" /></td>
           		<td>${row.membernum}</td>
           		<td><a href="./modify?userid=${row.userid}&curBlock=${pageDTO.curBlock}&searchOption=${pageDTO.searchOption}&searchKey=${pageDTO.searchKey}">${row.userid}</a></td>
           		<td>${row.name}</td>
           		<td>
           			<c:set var="phoneNum" value="${fn:replace(row.phonenum,'-','')}"/>
           			<c:set var="firstNum" value="${fn:substring(phoneNum,0,3)}"/>
           			<c:set var="middleNum" value="${fn:substring(phoneNum,3,fn:length(phoneNum)-4)}"/>
           			<c:set var="lastNum" value="${fn:substring(phoneNum,fn:length(phoneNum)-4,fn:length(phoneNum))}"/>
           			<c:out value="${firstNum}-${middleNum}-${lastNum}"/>
           		</td>
           		<td><fmt:formatDate value="${row.joindate}" pattern="yyyy-MM-dd HH:mm"/></td>
           	</tr>
          	</c:forEach>
           
          </tbody>
        </table>
        	<c:set var="startBlock" value="${pageDTO.curBlock-(pageDTO.curBlock-1) % 10}"/>
        	<c:set var="lastBlock" value="${fn:substringBefore(Math.ceil(totalPage/10),'.')}"/>
       
        <nav id="pager">
     		<c:if test="${startBlock > 1}">
     			<button type="button" id="prevButton" onclick="prevButtonClick()"><i class='fas fa-angle-left' title='이전'></i></button>
     		</c:if>
     		
     		<c:forEach var="i" begin="${startBlock}" end="${(startBlock + 9 > lastBlock) ? lastBlock : startBlock + 9}">
     			<c:choose>
     				<c:when test="${i == pageDTO.curBlock}"><span style="color: red;">${i}</span></c:when>
      			
      			<c:otherwise><button type="button" class="pageButton" onclick="pageButtonClick()" value="${i}">${i}</button></c:otherwise>
     			</c:choose>
     		</c:forEach>
     		
     		<c:if test="${startBlock + 10 <= lastBlock}">
     			<button type="button" id="nextButton" onclick="nextButtonClick()"><i class='fas fa-angle-right' title='다음'></i></button>
     		</c:if>
     		
        </nav>
	            


</body>
</html>