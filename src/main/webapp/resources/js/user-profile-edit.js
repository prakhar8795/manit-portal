
var currHandle;
var handleAvailable = false;

window.onload = function(){
 currHandle = $('#userHandle').val();
};

function checkUserHandle(){
	
	var handle = $('#userHandle').val();
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


function isFormValid(){
	var handle = $('#userHandle').val();
	
	if(handleAvailable==true || currHandle==handle){
		console.log("true");
		return true;
	}
	$('#updateResultText').show();
	$('#updateResultText').html('<p>Fisrt check for handle availability then update details.</p>');
	$('#updateResultText').removeClass('text-success');
	$('#updateResultText').addClass('text-danger');
	console.log("false");
	return false;
}


function updatePersonalDetails(){
	
	var number = $('#contact').val();
	var date = $('#dob').val();
	
	$.ajax({
	    type: "POST",
	    url: "/portal/updatePersonalData",
	    data: { contact : number, dob : date},
	    
	    success: function (result) {
	    	
	    		$('#updatePersonalDetailsResultText').html('<p>Profile Updated Successfully</p>');
	    		$('#updatePersonalDetailsResultText').addClass('text-success');
	    },
	    
	    error: function(e) {
	    	console.log("Ajax call for update personal details has failed\n");
	    	console.log(e);
	   }
	});
}

