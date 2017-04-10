<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/resources/css/user-nav-bar.css" />" rel="stylesheet">
<div class="container-fluid">
 
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Maulana Azad National Institute of Technology</a>
    </div>
    
    <ul class="nav navbar-nav navbar-right">
       <li class="active"><a href="home">Home</a></li>
       <li><a href="profile"><span class="glyphicon glyphicon-user"></span> ${profile.userName}</a></li>
       <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>

<script type="text/javascript">

function chatbot() {
	var input = $("#chatbot-input").val() ;
	$("#chatbot-input").val("") ;
	var m = "<div class=\"row message\"><div class=\"message-sent\"><label>"+ input + "</label></div></div>" ;
	$("#chatbot-conversation").append(m) ;
	console.log(window.location.hostname) ;
	console.log("http://" + window.location.hostname + ":5000/chatbot");
	$.ajax({
		type: "GET",
        url: "http://localhost:5000/chatbot",
        crossDomain: true,
        dataType:'jsonp',
        data: {userInput:input} ,
        success: function (result) {
        	
        	var res = result.a;
        	console.log(res) ;
        	var mk = "<div class=\"row message\"><div class=\"message-recieved\"><label>"+ res + "</label></div></div>" ;
        	$("#chatbot-conversation").append(mk) ;
        	
        	console.log("Working!") ;
        },
        error: function(e) {
        	console.log("Ajax call for check Availability of userHandle failed\n");
        	console.log(e);
       }
	});
	
}

$("#submit-chatbot").submit(function(event) {
	console.log("hi!!!!") ;
	event.preventDefault() ;
	chatbot();
});


$(document).on("DOMSubtreeModified propertychange", ".box", function(e) {
	$(this).animate({ scrollTop: $(this).prop("scrollHeight") }, 100);
});


function openNav() {
    document.getElementById("mySidenav").style.width = "300px";
    document.getElementById("main").style.marginRight = "300px";
}

$("#myBot").click(function() {
	console.log("clicked") ;
	openNav() ;
});

function closeNav() {
	console.log("Hi") ;
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginRight = "0";
}

</script>

<div id="sidenav" class="sidenav1" style="width:250px;">
	<a href="home">Home</a>
	<a href="post">Post New Update</a>
	<a href="message">Send Message</a>
	<a href="profile">View Profile</a>
	<a href="result">Result Analysis</a>
	<a id="myBot" href="#">Talk to MANIT Bot !</a>
</div>


<div id="mySidenav" class="sidenav">
  
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="#">MANIT BOT!</a>
  
  <div style="height:70vh; position:relative; margin-left:10px;margin-right:10px;">
		<div id="chatbot-conversation" class="col-md-12 box" style="height:65vh; overflow:auto;">
			<div class="row message">
				<div class="message-recieved">
					<label>Hello! How can I help you?</label>
				</div>
			</div>
			
		</div>
		<div id="send-message-chatbot-container" style="position:absolute; bottom:0px;">
			<form id="submit-chatbot">
				<div class="col-md-8">
					<input id="chatbot-input" class="form-control" />
				</div>
				<div class="col-md-4">
					<button id="send-message-chatbot" class="btn btn-default btn-sm">Send Query!</button>	
				</div>
			</form>
		</div>
	</div>
  
</div>


</div>