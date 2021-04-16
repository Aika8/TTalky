<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@include file="includes/head.jsp"%>
    <title>Title</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-6 offset-3">
            <%
                String passError = request.getParameter("passworderror");
                if(passError!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Passwords are not the same!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String emError = request.getParameter("emailerror");
                if(emError!=null){
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Such user already exist!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String success = request.getParameter("success");
                if(success!=null){
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Congrats! Your registration went successfully! now go and sign in!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <h4 class="mb-4 text-md-center">
                Create the account
            </h4>
            <form action="/register" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" required name="email"   class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                    <small id="emailHelp" class="form-text text-muted">Input your email</small>
                </div>
                <div class="form-group">
                    <label >Password</label>
                    <input type="password" name="password" class="form-control"  placeholder="Password">
                    <small id="passwordHelp" class="form-text text-muted">Input your password</small>
                </div>
                <div class="form-group">
                    <label > Re-Password</label>
                    <input type="password" name="re_password" class="form-control">
                    <small id="RepasswordHelp" class="form-text text-muted">Input your password again</small>
                </div>
                <div class="form-group">
                    <label > Full Name </label>
                    <input type="text" name="full_name" class="form-control">
                    <small id="nameHelp"  class="form-text text-muted">Input your full name</small>
                </div>
                <div class="form-group">
                    <label>Birthday:</label>
                    <input type="date" name="birthday" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary" style="background-color: #180b7a">Sign Up</button>
            </form>

        </div>
    </div>
</div>


</body>
</html>
