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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link href="<c:url value="/resources/css/user-message.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/user-message.js" />" ></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/user-nav-bar.jsp"></jsp:include>

<div class="container-fluid">
	<div id="message-title">Messages</div>
	
	<div id="user-message-container">
		<div class="col-md-3" style="height:82vh;overflow:auto;">
			<hr />
			
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			<div id="" class="row message-block-container">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="container-fluid">
				
				<div class="row">
					<div class="col-md-8">
						<label>Who you want to send the message to?</label>
						<div class="row">
							<div class="col-md-3">
								<dl class="form-group">
									<div class="radio form-control">
										<label><input type="radio" id="student" class="message-options" name="message-options" value="SO" />Student</label>
									</div>
								</dl>
							</div>
							<div class="col-md-3">
								<dl class="message-options form-group">
									<div class="radio form-control">
										<label><input type="radio" id="faculty" class="message-options" name="message-options" value="SO" />Faculty</label>
									</div>
								</dl>
							</div>
						</div>
					</div>
				</div>
				
				
				<div id="student-options" class="form-group message-options-container hidden">
					<div class="row">
						<div class="col-md-8">
							<label>Please select the appropriate values!</label>
							<div class="row">
								<div class="col-md-4 message-options-description">
									<select class="form-control">
									  <option value="volvo">Select Year</option>
									  <option value="saab">Saab</option>
									  <option value="mercedes">Mercedes</option>
									  <option value="audi">Audi</option>
									</select>
								</div>
								
								<div class="col-md-4 message-options-description">
									<select class="form-control">
									  <option value="volvo">Select Branch</option>
									  <option value="saab">Saab</option>
									  <option value="mercedes">Mercedes</option>
									  <option value="audi">Audi</option>
									</select>
								</div>
								
								<div class="col-md-4 message-options-description">
									<select class="form-control">
									  <option value="volvo">Select Name</option>
									  <option value="saab">Saab</option>
									  <option value="mercedes">Mercedes</option>
									  <option value="audi">Audi</option>
									</select>
								</div>								
							</div>
						</div>
					</div>
				</div>
				
				<div id="faculty-options" class="form-group message-options-container hidden">
					<div class="row">
						<div class="col-md-8">
							<label>Please select the appropriate values!</label>
							<div class="row">
								<div class="col-md-4 message-options-description">
									<select class="form-control">
									  <option value="volvo">Select Branch</option>
									  <option value="saab">Saab</option>
									  <option value="mercedes">Mercedes</option>
									  <option value="audi">Audi</option>
									</select>
								</div>
								
								<div class="col-md-4 message-options-description">
									<select class="form-control">
									  <option value="volvo">Select Name</option>
									  <option value="saab">Saab</option>
									  <option value="mercedes">Mercedes</option>
									  <option value="audi">Audi</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div id="message-content-container" class="row form-group">
					<div class="col-md-12">
						<label for="message-content">Enter Your Message</label>
						<div class="row">
							<div class="col-md-12">
								<textarea rows=5 id="message-content" class="form-control"></textarea>
							</div>
						</div>
					</div>
				</div>
				
				<div id="message-submit-container" class="row form-group">
					<div class="col-md-4">
						<button id="submit-message" class="btn btn-default">Send Message!</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>