$( document ).ready(function() {
    console.log( "ready!" );
    
    $(".message-block-container, .message-block-container-new").mouseenter(function() {
    	$(this).css("background-color","#c7c8c9") ;
    });
    
    $(".message-block-container, .message-block-container-new").mouseleave(function() {
    	$(this).css("background-color","transparent") ;
    });
    
    $("#student").click(function() {
    	$(".message-options-container").addClass("hidden") ;
    	$("#student-options").removeClass("hidden") ;
    });
    
    $("#faculty").click(function() {
    	$(".message-options-container").addClass("hidden") ;
    	$("#faculty-options").removeClass("hidden") ;
    });
    
    $("#new-message").click(function() {
    	$("#conversation-container").addClass("hidden") ;
    	$("#new-message-container").removeClass("hidden") ;
    	$("#message-chat").text("Create New Message!") ;
    });
    
    $(".message-block-container").click(function() {
    	$("#new-message-container").addClass("hidden") ;
    	$("#conversation-container").removeClass("hidden") ;
    	$("#message-chat").text($(this).find(".message-name").text()) ;
    	var currentUser = $("#form-senderID").val() ;
    	var conversationID = $(this).attr("id");
    	
    	$("#conversation-data").text("") ;
    	
    	$.ajax({
            type: "POST",
            url: "/portal/getConversationsByID",
            data: { conversationID : conversationID },
            dataType: "json",
            success: function (result) {
            	
            	var arrayLength = result.length;
            	console.log(arrayLength) ;
            	for(var i=0 ; i<arrayLength ; i++) {
            		var resul = JSON.parse(result[i]) ;
            		console.log(resul["conversationID"]) ;
            		var sender = JSON.parse(resul["sender"])["userID"] ;
            		if(sender == currentUser) {
            			//right
            			var m = "<div class=\"row message\"><div class=\"message-sent\"><label>"+ resul["message"] + "</label></div></div>" ;
            			$("#conversation-data").append(m) ;
            		}
            		else {
            			//left
            			var m = "<div class=\"row message\"><div class=\"message-recieved\"><label>"+ resul["message"] + "</label></div></div>" ;
            			$("#conversation-data").append(m) ;
            		}
            		console.log(JSON.parse(resul["sender"])["userID"]) ;
            		console.log(resul["message"]) ;
            	}
            	
            	console.log("Working!") ;
            },
            error: function(e) {
            	console.log("Ajax call for check Availability of userHandle failed\n");
            	console.log(e);
           }
    	});
    	
    	
    });
    
    
    
    
    $("#branch").change(function() {
    	var year = $("#year").val() ;
    	console.log(year) ;
    	var branch = this.value ;
    	console.log(branch) ;
    	
    	$.ajax({
            type: "POST",
            url: "/portal/getDataByYearBranch",
            data: { year : year, branch : branch},
            dataType: "json",
            success: function (result) {
            	
            	var arrayLength = result.length;
            	$("#names").empty() ;
            	console.log(arrayLength) ;
            	for(var i=0 ; i < arrayLength ; i++) {
            		var resul = JSON.parse(result[i]) ;
            		console.log(resul["userScholar"]) ;
            		console.log(resul["userName"]) ;
            		var mk = "<option value=\"" + resul.userID + "\" imageURL=\""+ resul.imageURL+"\">" + resul["userName"] + "</option>" ;
            		$("#names").append(mk) ;
            		console.log(mk);
            	}
            	
            	console.log("Working!") ;
            },
            error: function(e) {
            	console.log("Ajax call for check Availability of userHandle failed\n");
            	console.log(e);
           }
    	});
    });
    
    $("#submit-message").click(function() {
    	var senderID = $("#form-senderID").val() ;
    	console.log("Sender: " + senderID) ;
    	
    	var userID = $("#names").val() ;
    	$("#form-userID").val($("#names").val());
    	console.log("Reciever: " + $("#names").val()) ;
    	
    	$("#form-userName").val($("#names option:selected").text()) ;
    	console.log($("#names option:selected").text()) ;
    	
    	$("#form-userProfileImage").val($("#names option:selected").attr("imageURL")) ;
    	console.log($("#names option:selected").attr("imageURL")) ;
    	
    	$("#form-message").val($("#message-content").val()) ;
    	console.log($("#message-content").val()) ;
    	
    	if(senderID < userID) {
    		$("#form-conversationID").val(senderID + ":" + userID) ;
    		console.log(senderID + ":" + userID) ;
    	}
    	else {
    		$("#form-conversationID").val(userID + ":" + senderID) ;
    		console.log(userID + ":" + senderID) ;
    	}
    	
    	$("#submit-message-form").submit() ;
    	
    });
    
    $(document).on("DOMSubtreeModified propertychange", ".box", function(e) {
		$(this).animate({ scrollTop: $(this).prop("scrollHeight") }, 100);
	});
    
});
