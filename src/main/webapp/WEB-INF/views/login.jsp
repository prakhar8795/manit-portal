<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<link href="<c:url value="/resources/css/first.css" />" rel="stylesheet">
</head>

<body>
	<div class="container">
		<div class="row center-align">
			<img src="<c:url value="/resources/images/MANIT-Logo.png"/>" style="height:160px"/>
		</div>
		<div class="row center-align top-gap" style="font-size:180%">
			<b>Maulana Azad National Institute of Technology</b>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="login-box">
					<h2>
						Sign in <strong></strong>
					</h2>
					
					<form action="login" method="post">
						<div class="email-div">
							<label for="Email"><strong class="email-label">User ID</strong></label>
							<input spellcheck="false" name="userID" value=""
								type="text">
						</div>
						<div class="passwd-div">
							<label for="Passwd"><strong class="passwd-label">Password</strong></label>
							<input name="password" type="password">
						</div>
						<div class="text-danger">
							${loginFailedText }
						</div>				
						<input class="button button-submit" name="signIn" id="signIn" value="Sign in" type="submit">
					</form>
					<div class="row bottom-gap">
						<div class="col-md-6 bottom-gap" style="padding:0px">
							<a id="forgot_password" href="/portal/forgotPasswd"> Forgot Password?</a>
						</div>
						<div class="col-md-6 bottom-gap">
							<a href="/portal/signup" style="float:right"> Sign Up</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>