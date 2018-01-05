$( document ).ready(function() {
    console.log( "ready!" );
    
    $("#subjects").click(function() {
		console.log("Here") ;
		$("#subjects-table").removeClass("hidden") ;
		$("#branch-table").removeClass("hidden") ;
		$("#year-table").removeClass("hidden") ;
		$("#year-table").addClass("hidden") ;
		$("#branch-table").addClass("hidden") ;
	});
    
    $("#branch").click(function() {
		console.log("Here") ;
		$("#branch-table").removeClass("hidden") ;
		$("#subjects-table").removeClass("hidden") ;
		$("#year-table").removeClass("hidden") ;
		$("#year-table").addClass("hidden") ;
		$("#subjects-table").addClass("hidden") ;
	});
    
    $("#year").click(function() {
		console.log("Here") ;
		$("#year-table").removeClass("hidden") ;
		$("#branch-table").removeClass("hidden") ;
		$("#subjects-table").removeClass("hidden") ;
		$("#subjects-table").addClass("hidden") ;
		$("#branch-table").addClass("hidden") ;
	});
	
	$(".no-subjects").click(function() {
		console.log("Hi") ;
		$("#year-table").removeClass("hidden") ;
		$("#branch-table").removeClass("hidden") ;
		$("#subjects-table").removeClass("hidden") ;
		$("#subjects-table").addClass("hidden") ;
		$("#branch-table").addClass("hidden") ;
		$("#year-table").addClass("hidden") ;
	});
});



function postVisibility(){
	var radioValue = $("input[name='post-visibility']:checked").val();
	var selected;
	selected = $('#user-id').val();
	selected = selected + "," + radioValue;
	if(radioValue) {
		if(radioValue == "BY")
		{
			$('#checked-branches input:checked').each(function() {
				selected = selected + "," + this.name;
	         });
		}
		else if(radioValue == "SS")
		{
			$('#checked-subjects input:checked').each(function() {
				selected = selected + "," + this.name;
			});    	 	
		}
		
		$("#scope-list").val(selected);
		console.log($("#scope-list").val());
		return true;
	}	
	
	$('#is-post-visibility-selected-text').html('<p>Please Select Post Visibilty</p>');
	$('#is-post-visibility-selected-text').addClass('text-danger');
	return false;
		
}
	
