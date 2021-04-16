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
            <h4 class="mb-4 text-md-center">
                Sing In
            </h4>

            <form action="/login" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" required name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">

                    <%
                        String emailError = request.getParameter("emailerror");
                        if(emailError!=null){
                    %>
                    <p class="form-text text-danger">The email or phone number you’ve entered doesn’t match any account. <a class="btn btn-link" href="/register">Sign up for an account.</a></p>
                    <%
                        }else{
                    %>
                    <small id="emailHelp" class="form-text text-muted">Input your email</small>
                    <%
                        }
                    %>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">

                <%
                    String passwordError = request.getParameter("passworderror");
                    if(passwordError!=null){
                %>
                <p class="form-text text-danger">The password you’ve entered is incorrect.</p>
                <%
                }else{
                %>
                <small id="passwordHelp" class="form-text text-muted">Input your password</small>
                <%
                    }
                %>
                </div>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Remember me</label>
                </div>
                <button type="submit" class="btn btn-primary mt-5 text-center" style="background-color: #180b7a">Sign in</button>
            </form>

        </div>
    </div>
</div>


</body>
</html>
