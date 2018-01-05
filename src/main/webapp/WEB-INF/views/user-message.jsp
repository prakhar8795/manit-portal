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

<style>

#sidenav {
	display:none; 
}

</style>

<div class="container-fluid">
	<div class="row">
		<div id="message-title" class="col-md-3" style="text-align:center; border-bottom:1px solid black;">Messages</div>
		<div id="message-chat" class="col-md-9" style="text-align:center;border-bottom:1px solid black;"></div>
	</div>
	<div id="user-message-container" class="row">
		<div class="col-md-3" style="height:82vh;overflow:auto;border-right: 1px solid black;">
			
			<div id="new-message" class="row message-block-container-new" style="padding:4px;margin-top:10px;">
				<div class="col-md-3 message-icon-container">
					<img class="message-icon" src="<c:url value="/resources/images/user-profile-blank-image.png"/>"/>
				</div>
				<div class="col-md-9 message-name">
					<label>New Message</label>
				</div>
			</div>
			
			<c:forEach items="${messageData }" var="userConversation">
			
				<div id="${userConversation.conversationID }" class="row message-block-container">
					<div class="col-md-3 message-icon-container">
						<img class="message-icon" src="<c:url value="${userConversation.userProfileImage }"/>"/>
					</div>
					<div class="col-md-9 message-name">
						<label>${userConversation.userName }</label>
					</div>
					<input class="hidden" value="${userConversation.userID }" />
				</div>
			
			</c:forEach>
		</div>
		
		<div id="new-message-container" class="col-md-9 hidden" style="background:#fbfbee;height:100vh">
			<div class="container-fluid" style="margin-top:10px">
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
						<div class="col-md-12">
							<label>Please select the appropriate values!</label>
							<div class="row">
								<div class="col-md-3 message-options-description">
									<select id="year" class="form-control">
									  <option value="" >Select Year</option>
									  <option value="2013">Batch of 2013</option>
									  <option value="2014">Batch of 2014</option>
									  <option value="2015">Batch of 2015</option>
									  <option value="2016">Batch of 2016</option>
									  <option value="2017">Batch of 2017</option>
									</select>
								</div>
								
								<div class="col-md-5 message-options-description">
									<select id="branch" class="form-control">
									  <option value="" >Select Branch</option>
									  <option value="Computer Science">Computer Science</option>
									  <option value="Electronics and Communication">Electronics and Communication</option>
									</select>
								</div>
								
								<div class="col-md-4 message-options-description">
									<select id="names" class="form-control">
									  <option value="volvo">Select Name</option>
									  
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
									<select id="branchF" class="form-control">
									  <option value="volvo">Select Branch</option>
									  <option value="Computer Science">Computer Science</option>
									  <option value="Electronics and Communication">Electronics and Communication</option>
									</select>
								</div>
								
								<div class="col-md-4 message-options-description">
									<select id="namesF" class="form-control">
									  <option value="volvo">Select Name</option>
									  
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
		
		<div id="conversation-container" class="col-md-9 hidden" style="background:#fbfbee; height:100vh">
			<div id="conversation-data" class="container-fluid box" style="height:70vh;overflow:auto;margin-top:10px;">
				
			</div>
			<!--  
			<div id="new-message-box" class="container-fluid" style="margin-top:20px; margin-bottom:20px">
				<div class="col-md-10">
				<input class="form-control" />
				</div>
				<div class="col-md-2">
					<button type="input" class="btn btn-primary">Send Message!</button>
				</div>
			</div>
			-->
		</div>
	</div>
</div>
<form class="hidden" id="submit-message-form" method="POST" action="new-message">
	<input id="form-senderID" name="senderID" value="${senderData.userID }" />
	<input id="form-senderName" name="senderName" value="${senderData.userName }" />
	<input id="form-senderProfileImage" name="senderProfileImage" value="${senderData.imageURL }" />
	
	<input id="form-userID" name="recieverID" />
	<input id="form-userName" name="recieverName" />
	<input id="form-userProfileImage" name="recieverProfileImage" />
	
	<input id="form-conversationID" name="conversationID" />
	<input id="form-message" name="message" />
</form>
</body>
</html>