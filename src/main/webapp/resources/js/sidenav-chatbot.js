$(document).ready(function() {
	
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
	    document.getElementById("main").style.marginRight = "400px";
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
	
});