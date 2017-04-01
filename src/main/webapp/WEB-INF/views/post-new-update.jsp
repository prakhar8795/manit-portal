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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/news-feed.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/home.js" />" ></script>

<script type="text/javascript">

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

</script>

</head>
<body>

<jsp:include page="/WEB-INF/views/user-nav-bar.jsp"></jsp:include>

<div class="container-fluid">
	<div class="col-md-2"></div>
	<div class="col-md-10" style="border:1px solid black;">
		<h3>Post New Update</h3>
		<hr />
		<div class="col-md-6">
			<div id="post-update-container">
				<dl class="form-group">
					<dt><label for="post-content">Content</label></dt>
					<dd>
						<textarea id="post-content" class="form-control" rows="10"></textarea>
					</dd>
				</dl>
				
				<dl class="form-group">
					<button class="btn btn-default">Upload an image</button>
					<button class="btn btn-default">Upload a file</button>
				</dl>
				
				<dl class="form-group">
					<label>Video Link</label>
					<input class="form-control" placeholder="Provide link to the video"/>
				</dl>
				
				<dl class="form-group">
					<dt><label for="user-name">Posting as: {profile.userName}</label></dt>
				</dl>
				
				
				<dl class="form-group">
					<button class="form-control btn btn-primary">Post Update</button>
				</dl>
				
			</div>
		</div>
		<div class="col-md-6">
			<div>
			<dl class="form-group">
					<dt><label for="user-name">Post Visibility</label></dt>
					<dd>
						<div id="post-visibility">
							<div class="radio">
								<label><input type="radio" class="no-subjects" name="post-visibility" value="SO" />Students Only</label>
							</div>
							<div class="radio">
								<label><input type="radio" class="no-subjects" name="post-visibility" value="SO" />Faculties Only</label>
							</div>
							<div class="radio">
								<label><input type="radio" class="no-subjects" name="post-visibility" value="SO" />Both Students and Faculties</label>
							</div>
							<div class="radio">
								<label><input id="branch" type="radio" name="post-visibility" value="SO" />Specific Branches</label>
							</div>
							<div id="branch-table" class="hidden">
								<table class="table">
									<thead>
										<tr>
											<th>Subject Code</th>
											<th>Subject Name</th>
											<th>Select</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="radio">
								<label><input id="year" type="radio" name="post-visibility" value="SO" />Specific Year</label>
							</div>
							<div id="year-table" class="hidden">
								<table class="table">
									<thead>
										<tr>
											<th>Subject Code</th>
											<th>Subject Name</th>
											<th>Select</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="radio">
								<label><input type="radio" id="subjects" name="post-visibility" value="SO" />Specific Subjects</label>
							</div>
							<div id="subjects-table" class="hidden">
								<table class="table">
									<thead>
										<tr>
											<th>Subject Code</th>
											<th>Subject Name</th>
											<th>Select</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td><input type="checkbox" /></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</dd>
				</dl>
			</div>
		</div>
	</div>
</div>

</body>
</html>