<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/news-feed.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/home.js" />" ></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/user-nav-bar.jsp"></jsp:include>

<div class="container-fluid">
<div class="col-md-3"></div>
<div class="col-md-9">
	<h3>Add New Faculty</h3>
	<hr />
	<div class="row" id="add-faculty-container" style="border:1px solid red; background-color: grey;">
		<div class="col-md-8" style="background-color:white">
			<h4>Update Personal Details</h4>
			<hr />
			<div class="col-md-8">
				<dl class="form-group">
					<dt><label for="name">Name</label></dt>
					<dd>
						<input id="name" class="form-control" required />
					</dd>
				</dl>
				<dl class="form-group">
					<dt><label for="number">Mobile Number</label></dt>
					<dd>
						<input id="number" class="form-control" required />
					</dd>
				</dl>
				<dl class="form-group">
					<dt><label for="email">Date of Birth</label></dt>
					<dd>
						<input id="dob" type="date" class="form-control" required />
					</dd>
				</dl>
				<dl class="form-group">
					<dt><label for="email">Email</label></dt>
					<dd>
						<input id="email" class="form-control" required />
					</dd>
				</dl>
				<dl class="form-group">
					<div class="message-options-description">
						<select class="form-control">
						  <option value="volvo">Select Branch</option>
						  <option value="Computer Science">Computer Science</option>
						  <option value="Electronics and Communication">Electronics and Communication</option>
						</select>
					</div>
				</dl>
				
			</div>
	</div>
</div>

</div>
</div>
</body>
</html>