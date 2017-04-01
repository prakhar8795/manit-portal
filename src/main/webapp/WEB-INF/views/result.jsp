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
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link href="<c:url value="/resources/css/class.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/resultsApril16/data.js" />"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script src="<c:url value="/resources/js/resultsApril16/class.js" />"></script>
<script src="<c:url value="/resources/js/resultsApril16/statistics.js" />"></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/user-nav-bar.jsp"></jsp:include>

<div class="content clearfix container">
	<div id="divBranchSem">
		<label for="selBranchSem" class="control-label">Select the class: </label>
		<select id="selBranchSem" class="form-control" style="border: 1px solid blue;"></select>
	</div>
	<div id="divClassResult" class="container-fluid">
		<div class="container-fluid">
			<div class="panel panel-info">
				<div class="panel-heading">Subjects</div>
				<div class="panel-body container-fluid">
					<div id="divSubList" class="row"></div>
				</div>
			</div>
		</div>
		<div id="divVisualResult" class="container-fluid">
			<div id="divResultTable"></div>
			<div id="divResultStats"></div>
		</div>
		<div class="container-fluid" style="text-align: center;">
			<a href="#" class="btn btn-primary">Go to top</a>
		</div>
	</div>
</div>
</body>

</html>