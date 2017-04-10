function isMailSent(){
	
	var isSignUp = $('#isSignUp').val();
	var input = $('#userInput').val();

		$.ajax({
	            type: "POST",
	            url: "/portal/passwordRecovery",
	            data: { userInput : input},
	            
	            success: function (result) {
	            	
	            	var res= JSON.parse(result);
	            	
	            	if(res.flag == true){
		            	
		            	$('#isMailSentText').removeClass('text-danger');
		            	$('#isMailSentText').addClass('text-success');
		            	
		            	$('#isMailSentIcon').removeClass('glyphicon glyphicon-remove');
		            	$('#isMailSentIcon').addClass('glyphicon glyphicon-ok');
		            	
		            	$('#isMailSentText').html('<p>A password has been sent to the registered e-mail</p>');
		            	
	            	}
	            	else{
	            		$('#isMailSentText').html('<p>User Handle Not Available</p>');
	                	$('#isMailSentText').removeClass('text-success');
	                	$('#isMailSentText').addClass('text-danger');
	                	
	                	$('#isMailSentIcon').removeClass('glyphicon glyphicon-ok');
		            	$('#isMailSentIcon').addClass('glyphicon glyphicon-remove');
		            	
		            	if(isSignUp == "Sign Up"){
		            		$('#isMailSentText').html('<p>Sorry the user-ID is not registered</p>');
		            	}
		            	else{
		            		$('#isMailSentText').html('<p>Sorry the user-ID or user-handle does not exist</p>');
		            	}
	            	}
	            },
	            error: function(e) {
	           }
		});
}