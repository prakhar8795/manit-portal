<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/resources/css/user-nav-bar.css" />" rel="stylesheet">
<div class="container-fluid">
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Maulana Azad National Institute of Technology</a>
    </div>
    
    <ul class="nav navbar-nav navbar-right">
       <li class="active"><a href="home">Home</a></li>
       <li><a href="profile"><span class="glyphicon glyphicon-user"></span> ${profile.userName}</a></li>
       <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>
</div>