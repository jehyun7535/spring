<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

	<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
	<%-- common_lib.jsp의 내용을 지금 기술되는 부분에 코드를 복사해서 붙여 넣기 --%>
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	
    <link href="${cp}/css/signin.css" rel="stylesheet">
<!--     <link href="/css/signin.css" rel="stylesheet"> -->
	<script>		
	
	
	$(document).ready(function(){
		
	});
		function getCookieValue(cookieStr, cookieName){
			
			//String[] cookies = cookieStr.split("; ");
			var cookies = cookieStr.split("; ");
			
			//for(String cookieString : cookies) {
			for(var i in cookies) {
				
				//String[] cookie = cookieString.split("=");
				var cookie = cookies[i].split("=");
				
				//if(cookie[0].equals(cookieName)) {
				if(cookie[0]==cookieName) {
					return cookie[1];
				}
			}
		}
			
			//cookieName : 추가하고자 하는 쿠키이름
			//cookieValue : 쿠키의 값
			//expires : 유효기간(일자)
		function addCookie(cookieName, cookieValue, expires){
			var dt = new Date();	// 지금현재 날짜 ==> expires 만큼 미래날짜로 변경
			dt.setDate(dt.getDate() + parseInt(expires));
			console.log(dt);
			
			document.cookie = cookieName + "=" + cookieValue + "; " + 
								"path=/; expires=" + dt.toGMTString();
		}
		
		function deleteCookie(cookieName){
			addCookie(cookieName, "", -1);	
		}
		
		//html 문서 로딩이 완료 되고 나서 실행 되는 코드
			$(function(){
				<c:if test="${msg != null}">
					alert("${msg}" + "ra");
				</c:if>
				
			//userid, rememberme 쿠키를 확인하여 존재할 경우 값설정, 체크
			//var userid = getCookies(document)
			if(Cookies.get("rememberme")=="Y"){
			$("#userid").val(Cookies.get("userid"));
			$("#rememberme").prop("checked",true);
			}
			
			//singin 아이디를 select
		$("#signin").on("click", function(){
			//rememberme 체크박스가 체크 되어있는지 확인
			
			// 체크되어있을 경우
			if($("#rememberme").is(":checked")==true){
				
			// userid input 에 있는 값을 userid쿠키로 저장
				Cookies.set("userid", $("#userid").val());
			
			// rememberme 쿠키로 Y 값을 저장
				Cookies.set("rememberme", "Y");
			}
			//체크 해체되어 있을 경우 : 더이상 사용하지 않는다는 의미 이므로 userid, rememberme 쿠키 삭제
			else{
				Cookies.remove("userid");
				Cookies.remove("rememberme");				
			}
			
			// form태그를 이용하여 signin 요청
			$("#frm").submit();			
		});
	});
		
	</script>
  </head>

  <body>

    <div class="container">
    
      <form class="form-signin" id="frm"
      action="${cp}/login/process" method="Post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userid" class="sr-only">user id:</label>
        <input type="text" id="userid" name="userid" class="form-control">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="pass" value="brownpass" class="form-control">
        <div class="checkbox">
          <label>
            <input type="checkbox" id="rememberme" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="signin">Sign in</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
