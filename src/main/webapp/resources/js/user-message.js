$( document ).ready(function() {
    console.log( "ready!" );
    
    $(".message-block-container").hover(function() {
    	console.log("here atleast");
    	$(".message-block-container").css("background-color","white") ;
    	$(this).css("background-color","grey") ;
    });
    
    $("#student").click(function() {
    	$(".message-options-container").addClass("hidden") ;
    	$("#student-options").removeClass("hidden") ;
    });
    
    $("#faculty").click(function() {
    	$(".message-options-container").addClass("hidden") ;
    	$("#faculty-options").removeClass("hidden") ;
    });
    
});
