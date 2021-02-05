<%@page import="org.apache.catalina.User"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${userList }" var="user">
	<tr class="user" data-userid="${user.userid }">
		<td>${user.userid }</td>
		<td>${user.usernm }</td>
		<td>${user.alias }</td>
		<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd" />
		</td>
	</tr>
</c:forEach>

####################

<li class="prev">
<%-- <a href="${cp}/user/pagingUser?page=1&pageSize=${pageVo.pageSize}">«</a> --%>
	<a href="javascript:pagingUserAjax(1, ${pageVo.pageSize});">«</a>
</li>

<c:forEach begin="1" end="${pagination}" var="i">
	<c:choose>
		<c:when test="${pageVo.page == i}">
			<li class="active"><span>${i}</span></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="javascript:pagingUserAjax(${i }, ${pageVo.pageSize});">${i}</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>

<li class="next"><a
	href="javascript:pagingUserAjax(${pagination}, ${pageVo.pageSize});">»</a>
</li>
