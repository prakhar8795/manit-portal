var currHandle;
var handleAvailable = false;

window.onload = function(){
 currHandle = $('#userHandle').val();
};

$(document).ready(function() {
	
	$("#checkButton").click(function() {
		checkUserHandle() ;
	});
	
	function checkUserHandle(){
		
		var handle = $('#userHandle').val();
		console.log(handle);
		console.log("sadsad");
		$('#checkButton').prop('disabled',true);
		console.log(currHandle);
		if(handle == currHandle){
			$('#userHandleResultText').removeClass('text-danger');
			$('#userHandleResultText').addClass('text-success');
			$('#userHandleResultText').html('<p>It is your current Handle</p>');
			
	    	$('#userHandleResultIcon').removeClass('glyphicon glyphicon-remove');
	    	$('#userHandleResultIcon').addClass('glyphicon glyphicon-ok');
		}
		else {
			$.ajax({
		            type: "POST",
		            url: "/portal/checkUserHandle",
		            data: { userHandle : handle},
		            
		            success: function (result) {
		            	
		            	var res= JSON.parse(result);
		            	
		            	if(res.isAvailable == true){
		            		$('#updateResultText').hide();
			            	$('#userHandleResultText').html('<p>User Handle Available</p>');
			            	$('#userHandleResultText').removeClass('text-danger');
			            	$('#userHandleResultText').addClass('text-success');
			            	
			            	$('#userHandleResultIcon').removeClass('glyphicon glyphicon-remove');
			            	$('#userHandleResultIcon').addClass('glyphicon glyphicon-ok');
			            	$('#update').prop('disabled',false);
			            	
			            	handleAvailable = true;
		            	}
		            	else{
		            		$('#userHandleResultText').html('<p>User Handle Not Available</p>');
		                	$('#userHandleResultText').removeClass('text-success');
		                	$('#userHandleResultText').addClass('text-danger');
		                	
		                	$('#userHandleResultIcon').removeClass('glyphicon glyphicon-ok');
			            	$('#userHandleResultIcon').addClass('glyphicon glyphicon-remove');
			            	
			            	$('#update').prop('disabled',true);
			            	handleAvailable = false;
		            	}
		            },
		            error: function(e) {
		            	console.log("Ajax call for check Availability of userHandle failed\n");
		            	console.log(e);
		           }
			});
		}
		
		$('#checkButton').prop('disabled',false);

	}
	
	$("#submit-form").click(function(event) {
		event.preventDefault() ;
		var userID = $("#userHandle").val() ;
		console.log(userID) ;
		var loginType = "faculty";
		var imageURL = "/resources/images/user-profile-blank-image.png" ;
		var userName = $("#name").val() ; ;
		console.log(userName) ;
		var userHandle = $("#userHandle").val() ; ;
		console.log(userHandle) ;
		var userAbout = $("#about").val() ; ;
		console.log(userAbout) ;
		var branch = $("#branch").val() ;
		console.log(branch) ;
		var batch = "1001" ;
		console.log(batch) ;
		var pointer = "NA" ;
		var type = "NA" ;
		var contact = $("#number").val();
		console.log(contact) ;
		var email = $("#email").val(); ;
		console.log(email) ;
		var dob = $("#dob").val();
		console.log(dob) ;
		
		$.ajax({
            type: "POST",
            url: "/portal/addFaculty",
            data: { userID : userID, loginType: loginType, imageURL: imageURL, userName: userName, userHandle:userHandle,
            	userAbout: userAbout, branch: branch, batch: batch, pointer: pointer, type:type, contact: contact,
            	email:email, dob:dob},
            success: function (result) {
            	alert(result) ;
            },
            error: function(e) {
            	console.log("Ajax call for check Availability of userHandle failed\n");
            	console.log(e);
           }
		});
		
		
	});

});