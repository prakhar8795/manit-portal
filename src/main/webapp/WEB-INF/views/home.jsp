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


<script type="text/javascript">

</script>

<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/news-feed.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/home.js" />" ></script>
</head>
<body style="background:#f5f1f1;">

<jsp:include page="/WEB-INF/views/user-nav-bar.jsp"></jsp:include>
<script src="<c:url value="/resources/js/sidenav-chatbot.js" />" ></script>
	
<div id="main">	
	<div class="row">
		<!--
		<div id="sidenav" class="sidenav1" style="width:250px;">
				<a href="home">Home</a>
				<a href="post">Post New Update</a>
				<a href="message">Send Message</a>
				<a href="#">View Profile</a>
				<a href="result">Result Analysis</a>
				<a id="myBot" href="#">Talk to MANIT Bot !</a>
		</div>
		 
		<div id="user-profile-tab" class="col-md-1">
			<div id="user-min-details">
				<div id="user-profile-image" class="user-details"><img src="<c:url value="${profile.imageURL }"/>"/></div>
				<h4>
				<span id="user-name-text" class="user-details"><strong>${profile.userName }</strong></span>
				<br/>
				<span id="user-handle-text" class="user-details small">${profile.userHandle }</span>
				</h4>
				
				<div id="user-about-text" class="user-details">${profile.userAbout}</div>
			</div>
			<hr />
			<div id="user-academic-details">
				<div class="text-primary">${profile.branch}</div>
				<div class="text-primary">${profile.batch}</div>
				<div class="text-primary">${profile.pointer}</div>
				<div class="text-primary">${profile.type}</div>
			</div>
			<hr />
			<div id="user-personal-details">
				<div class="text-primary">${profile.contact}</div>
				<div class="text-primary">${profile.email}</div>
				<div class="text-primary">${profile.dob}</div>
			</div>
			
			<div>
				<form method="POST" action="message">
					<input type="text" name="userName" value="${profile.userName }" />
					<input type="text" name="userID" value="${profile.userID }" />
					<button class="btn btn-default btn-sm">Send Message</button>
				</form>
			</div>
			<hr />
		</div>
		 -->
		<div class="col-md-3">
		</div>
		<div id="middle-bar" class="col-md-6 bar-style" >
				<div>
				    <!-- /Page header -->
				    <h4><label>Hi Prakhar, your recent updates are!</label></h4>  
				    <!-- Timeline -->
				    <div class="timeline">
				        <!-- Panel -->
				        <c:forEach items="${feedList }" var="feed">
				        <article class="panel panel-primary">
				            <!-- Heading -->
					            <div class="panel-heading">
					                <div class="panel-title"><div>${feed.userName }</div><div class="font13">${feed.userID }</div></div>
					                <div class="pull-right"><div>${feed.date }</div><div class="font13">${feed.time }</div></div>								
					            </div>
				            <!-- /Heading -->
				    		
				            <!-- Body -->
				            <div class="panel-body">
				            <div >
								${feed.content }
				            </div>
						    <c:if test = "${feed.imageURL != 'NA'}">
				            <div class="panel-image hide-panel-body center-align">
						    	<img src="<c:url value="${feed.imageURL }"/>" class="panel-image-preview"/>
						    </div>
						    </c:if>
						    </div>
				            <!-- /Body -->
				    
				            <!-- Footer -->
				            <div class="panel-footer">
				       		<c:if test = "${feed.fileURL != 'NA'}">
				            <div>
						    	Attached File - 
						    	<a href="<c:url value="${feed.fileURL }"/>" target="_blank">${feed.fileName }</a>
						    </div>
						    </c:if>
						    <c:if test = "${feed.videoURL != 'NA'}">
				            <div>
						    	Video Link -
						    	<a href="<c:url value="${feed.videoURL }"/>" target="_blank">Video</a>
						    </div>
						    </c:if>
				            </div>
				            <!-- /Footer -->
				        </article>
				        </c:forEach>
				    </div>
				    <!-- /Timeline -->
				
				</div>
			</div>
	</div>
	
	<!--  
	<div id="button-center" style="position: fixed; bottom:40vh ; right:0px; text-align:left">
		<button id="button-bot" class="btn btn-primary">
			Hi <br /> I am <br /> MANIT <br /> BOT!!
		</button>
	</div>
	-->
</div>
</body>
</html>