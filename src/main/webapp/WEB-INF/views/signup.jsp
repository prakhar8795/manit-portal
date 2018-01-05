<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

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
<script src="<c:url value="/resources/js/forgot-password.js" />" ></script>

<link href="<c:url value="/resources/css/first.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>

</head>
<body>
<div class="container">
		
		<div class="row center-align">
			<img src="<c:url value="/resources/images/MANIT-Logo.png"/>" style="height:150px;"/>			
		</div>
		<div class="row center-align top-gap" style="font-size:180%">
			<b>Maulana Azad National Institute of Technology</b>
		</div>
		
		<div class="row top-gap">
			<div class="col-md-12">
				<div class="login-box">
					<h2>
						${isSignUp} <strong></strong>
					</h2>
					<form>
						<div>
							<input type="text" id="isSignUp" name="isSignUp" value="${isSignUp}" style="display:none">
						</div>
						<div class="email-div">						
							<label for="Email"><strong class="email-label">User ID or Handle</strong></label>
						</div>
						<div>
							
							<input type="text" name="userInput" id="userInput" value="" />
						</div>
						
							<button type="button" style="padding-left:0px" class="button button-submit" name="passwordRecovery" id="passwordRecovery" onClick="isMailSent()" >Submit</button>
						
						<div>
								<span id="isMailSentIcon"></span>
								<small id="isMailSentText"></small>
						</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>