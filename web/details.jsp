<%@ page import="talky.csse.db.Post" %>
<html>
<head>
    <title>Tootles</title>
    <%@include file="includes/head.jsp"%>
    <meta charset="UTF-8">
    <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">

    <% Post post = (Post)request.getAttribute("post");
        String back = (String) request.getAttribute("back");
        if (post != null) {

    %>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%=back.equals("profile")? "/profile?": "/myPosts?"%>"><svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-arrow-left-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path fill-rule="evenodd" d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5z"/>
            </svg></a></li>
            <li class="breadcrumb-item active" aria-current="page"><%=post.getTitle()%></li>
        </ol>
    </nav>
    <div class="container" style="display: flex;
    justify-content: center">

        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4 font-weight-bold"><%=post.getTitle()%></h1>
                <p class="lead font-weight-bold"><%=post.getShort_content()%> </p>
                <p class="lead"><%=post.getContent()%> </p>
            </div>
        </div>
        <% }

        %>

</div>
</div>

</body>
</html>
