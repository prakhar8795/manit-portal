<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link href="<c:url value="/resources/css/user-profile-edit.css" />" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-filestyle.min.js"/> "> </script>


<script src="<c:url value="/resources/js/user-profile-edit.js" />" ></script>
<style>
body{
        padding-top:60px !important;
        background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkYxpdBvLbBdEnM2iQ2erYxVhqQc2sP2aabJlDWJlh-8de7liRLA") !important;
        background-repeat: repeat !important ;
        background-size: contain !important;
    }
</style>

</head>
<body>

<jsp:include page="/WEB-INF/views/user-nav-bar.jsp"></jsp:include>
<script src="<c:url value="/resources/js/sidenav-chatbot.js" />" ></script>


<div class="page-content container clearfix">
<div class="row">
	<div class="col-md-3">
	</div>
	<div id="user-profile-tab" class="col-md-9" style="background-color:white;">
		<h3>Basic Details</h3>
		<hr />
		<form id="profileForm" method="POST" action="profile" enctype="multipart/form-data">
			<div class="row">
				<div id="updateProfile" class="col-md-8">
					<dl class="form-group">
						<dt><label for="userName">Name</label></dt>
						<dd>
						<input id="userName" name="userName" class="form-control" value="${profile.userName }" disabled />
						</dd>
					</dl>
					<dl class="form-group">
						<dt><label for="userHandle">Handle</label></dt>
						<dd>
							<div class="col-md-8" style="padding-left:0px">
								<input type="text" name="userHandle" id="userHandle" class="form-control" value="${profile.userHandle }" />
							</div>
							
							<button type="button" class="btn btn-default" onClick="checkUserHandle()" id="checkButton">Check for availability</button>
							<span id="userHandleResultIcon"></span>
							<small id="userHandleResultText"></small>
							
						</dd>
					</dl>
					<dl class="form-group">
						<dt><label for="userAbout">Bio</label></dt>
						<dd>
						<textarea name="userAbout" id="userAbout" class="form-control">${profile.userAbout }</textarea>
						</dd>
					</dl>
				</div>
				<div class="col-md-4" style="z-index:0;">
					<div id="profileImage" class="user-details"><img src="<c:url value="${profile.imageURL }"/>"/></div>
					<input type="file" class="filestyle" data-buttonText="Upload" id="uploadedImage" name="uploadedImage">
					
				</div>
			</div>
			<div class="row">
			<div class="col-md-2">
			<input type="submit" class="btn btn-primary" name="update" id="update" value="Update" onClick="return isFormValid()">
			</div>
			<div class="col-md-4">
			<small id="updateResultText" class="text-success"> ${successMessage }</small>
			</div>
			</div>
		</form>
		<h3>Personal Details</h3>
		<hr />
		<div id="personalDetails">
			<div class="row">
				<div class="col-md-8">
					<dl class="form-group">
						<dt><label for="contact">Mobile Number</label></dt>
						<dd>
						<input id="contact" name="contact" class="form-control" value="${profile.contact }" />
						</dd>
					</dl>
					<dl class="form-group">
						<dt><label for="email">Email</label></dt>
						<dd>
						<input type="text" name="email" id="email" class="form-control" value="${profile.email }" disabled />
						</dd>
					</dl>
					<dl class="form-group">
						<dt><label for="dob">Date of Birth</label></dt>
						<dd>
						<input type="date" name="dob" id="dob" class="form-control" value="${profile.dob }"></input>
						</dd>
					</dl>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
				<button type="button" class="btn btn-primary" id="updatePersonalData" onClick="updatePersonalDetails()" >Update</button>
				</div>
				<div class="col-md-4">
				<small id="updatePersonalDetailsResultText"></small>
				</div>
			</div>
			<hr />
			
		</div>
	</div>
</div>
</div>

</body>
</html>