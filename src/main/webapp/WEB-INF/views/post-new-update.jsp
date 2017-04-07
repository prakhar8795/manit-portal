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
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-filestyle.min.js"/> "> </script>

<script src="<c:url value="/resources/js/post-new-update.js" />" ></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/user-nav-bar.jsp"></jsp:include>

<div class="container-fluid">
	<div class="col-md-2"></div>
	<form action="post" method="POST" enctype="multipart/form-data">
	<div>
		<input type="text" id="scope-list" name="scope-list" style="display:none"></input>
		<input type="text" id="user-id" style="display:none" value="${profile.userID}"></input>
	</div>
	<div class="col-md-10" style="border:1px solid black;">
		<h3>Post New Update</h3>
		<hr />
		<div class="col-md-6">
			<div id="post-update-container">
				<dl class="form-group">
					<dt><label for="post-content">Content</label></dt>
					<dd>
						<textarea id="post-content" name="post-content" class="form-control" rows="10"></textarea>
					</dd>
				</dl>
				
				<dl class="form-group">
					<div class="row">
						<div class="col-md-4">
							<input type="file" class="filestyle" data-input="false" data-buttonText="Upload an image" id="uploaded-image" name="uploaded-image">
						</div>
						<div class="col-md-6">
							<input type="file" class="filestyle" data-input="false" data-buttonText="Upload a file" id="uploaded-file" name="uploaded-file">
						</div>
					</div>
				</dl>
				
				<dl class="form-group">
					<label>Video Link</label>
					<input class="form-control" id="video-link" name="video-link" placeholder="Provide link to the video"/>
				</dl>
				
				<dl class="form-group">
					<dt><label for="user-name">Posting as: ${profile.userName}</label></dt>
				</dl>
				
				
				<dl class="form-group">
					<input type="submit" class="form-control btn btn-primary" id="post-button" onClick="return postVisibility()" value="Post"></button>
				</dl>
				<small id="is-post-visibility-selected-text"></small>
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
								<label><input type="radio" class="no-subjects" name="post-visibility" value="FO" />Faculties Only</label>
							</div>
							<div class="radio">
								<label><input type="radio" class="no-subjects" name="post-visibility" value="SF" />Both Students and Faculties</label>
							</div>
							<div class="radio">
								<label><input id="year" type="radio" name="post-visibility" value="BY" />Specific Branch and Year</label>
							</div>
							<div id="year-table" class="hidden">
								<table class="table">
									<thead>
										<tr>
											<th>Branch Name</th>
											<th>1st yr. </th>
											<th>2nd yr.</th>
											<th>3rd yr.</th>
											<th>4th yr.</th>
										</tr>
									</thead>
									<tbody id="checked-branches">
										<c:forEach items="${branchList }" var="branches">
											<tr>
												<td>${branches.branchName}</td>
												<td><input type="checkbox" name="${ branches.branchCode}1"/></td>
												<td><input type="checkbox" name="${ branches.branchCode}2"/></td>
												<td><input type="checkbox" name="${ branches.branchCode}3"/></td>
												<td><input type="checkbox" name="${ branches.branchCode}4"/></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="radio">
								<label><input type="radio" id="subjects" name="post-visibility" value="SS" />Specific Subjects</label>
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
									<tbody id="checked-subjects">
										<c:forEach items="${subjectList }" var="subjects">
											<tr>
												<td>${subjects.id }</td>
												<td>${subjects.subjectName }</td>
												<td><input type="checkbox" name="${subjects.id }"/></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	</form>
</div>

</body>
</html>