<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Jsp</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<!-- common_lib.jsp -->

</head>

<body>


	<!-- header.jsp -->
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				left include
				<!-- left.jsp -->
			</div>

			<div class="container">
				<table class="table">
					<tr>
						<th>사용자아이디</th>
						<th>사용자이름</th>
						<th>사용자별명</th>
						<th>등록일시</th>
					</tr>
					<c:forEach items="${userList }" var="user">
					<tr>
						<td>${user.userid }</td>
						<td>${user.usernm }</td>
						<td>${user.alias }</td>
				<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></td>
					</tr>
					</c:forEach>
				</table>
			<a class="btn btn-default pull-right">사용자 등록</a>
			</div>

			<div class="text-center">
				<ul class="pagination">
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
