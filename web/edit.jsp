
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
    <%
        String success = request.getParameter("success");
        if(success!=null){
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        Updates were Successful!
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%
        }
    %>
    <div class="row content mt-5">
        <div class="col-sm-3 sidenav ">

            <ul class="list-group">
                <img src="<%=currentUser.getPictureUrl()%>" class="img-thumbnail">
                <li class="list-group-item"><%=currentUser.getFull_name()%>, <%=2020-Integer.parseInt(currentUser.getBirthday().split("\\W")[0])%> years</li>
                <li class="list-group-item"><a class="btn btn-link font-weight-bold" href="/editProfile">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-hand-index" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M6.75 1a.75.75 0 0 0-.75.75V9a.5.5 0 0 1-1 0v-.89l-1.003.2a.5.5 0 0 0-.399.546l.345 3.105a1.5 1.5 0 0 0 .243.666l1.433 2.15a.5.5 0 0 0 .416.223h6.385a.5.5 0 0 0 .434-.252l1.395-2.442a2.5 2.5 0 0 0 .317-.991l.272-2.715a1 1 0 0 0-.995-1.1H13.5v1a.5.5 0 0 1-1 0V7.154a4.208 4.208 0 0 0-.2-.26c-.187-.222-.368-.383-.486-.43-.124-.05-.392-.063-.708-.039a4.844 4.844 0 0 0-.106.01V8a.5.5 0 0 1-1 0V5.986c0-.167-.073-.272-.15-.314a1.657 1.657 0 0 0-.448-.182c-.179-.035-.5-.04-.816-.027l-.086.004V8a.5.5 0 0 1-1 0V1.75A.75.75 0 0 0 6.75 1zM8.5 4.466V1.75a1.75 1.75 0 0 0-3.5 0v5.34l-1.199.24a1.5 1.5 0 0 0-1.197 1.636l.345 3.106a2.5 2.5 0 0 0 .405 1.11l1.433 2.15A1.5 1.5 0 0 0 6.035 16h6.385a1.5 1.5 0 0 0 1.302-.756l1.395-2.441a3.5 3.5 0 0 0 .444-1.389l.272-2.715a2 2 0 0 0-1.99-2.199h-.582a5.184 5.184 0 0 0-.195-.248c-.191-.229-.51-.568-.88-.716-.364-.146-.846-.132-1.158-.108l-.132.012a1.26 1.26 0 0 0-.56-.642 2.634 2.634 0 0 0-.738-.288c-.31-.062-.739-.058-1.05-.046l-.048.002zm2.094 2.025z"/>
                    </svg>
                    My Profile
                </a></li>
                <li class="list-group-item"><a class="btn btn-link font-weight-bold" href="/editProfile">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-hand-index" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M6.75 1a.75.75 0 0 0-.75.75V9a.5.5 0 0 1-1 0v-.89l-1.003.2a.5.5 0 0 0-.399.546l.345 3.105a1.5 1.5 0 0 0 .243.666l1.433 2.15a.5.5 0 0 0 .416.223h6.385a.5.5 0 0 0 .434-.252l1.395-2.442a2.5 2.5 0 0 0 .317-.991l.272-2.715a1 1 0 0 0-.995-1.1H13.5v1a.5.5 0 0 1-1 0V7.154a4.208 4.208 0 0 0-.2-.26c-.187-.222-.368-.383-.486-.43-.124-.05-.392-.063-.708-.039a4.844 4.844 0 0 0-.106.01V8a.5.5 0 0 1-1 0V5.986c0-.167-.073-.272-.15-.314a1.657 1.657 0 0 0-.448-.182c-.179-.035-.5-.04-.816-.027l-.086.004V8a.5.5 0 0 1-1 0V1.75A.75.75 0 0 0 6.75 1zM8.5 4.466V1.75a1.75 1.75 0 0 0-3.5 0v5.34l-1.199.24a1.5 1.5 0 0 0-1.197 1.636l.345 3.106a2.5 2.5 0 0 0 .405 1.11l1.433 2.15A1.5 1.5 0 0 0 6.035 16h6.385a1.5 1.5 0 0 0 1.302-.756l1.395-2.441a3.5 3.5 0 0 0 .444-1.389l.272-2.715a2 2 0 0 0-1.99-2.199h-.582a5.184 5.184 0 0 0-.195-.248c-.191-.229-.51-.568-.88-.716-.364-.146-.846-.132-1.158-.108l-.132.012a1.26 1.26 0 0 0-.56-.642 2.634 2.634 0 0 0-.738-.288c-.31-.062-.739-.058-1.05-.046l-.048.002zm2.094 2.025z"/>
                    </svg>
                    Settings
                </a></li>
                <li class="list-group-item"><a class="btn btn-link font-weight-bold text-danger" href="/logout">
                    <span class="material-icons">exit_to_app</span>Logout
                </a></li>
            </ul>
        </div>
        <div class="col-sm-6 text-left ">
            <h4 class="mb-4 text-md-center">
                Edit Profile
            </h4>
            <form action="/editProfile" method="post" class="border-bottom">
                <input type="hidden" name="id" value="<%=currentUser.getId()%>">
                <div class="form-group">
                    <label>Email Address : </label>
                    <input type="email"  class="form-control" value="<%=currentUser.getEmail()%>" readonly>
                </div>
                <div class="form-group">
                    <label>Full Name : </label>
                    <input type="text" name="full_name" class="form-control" value="<%=currentUser.getFull_name()%>">
                    <small  class="form-text text-muted">Change your full name </small>
                </div>
                <div class="form-group">
                    <label>Birthdate : </label>
                    <input type="date" name="birthday" class="form-control" value="<%=currentUser.getBirthday()%>">
                    <small  class="form-text text-muted">Change your birthday</small>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary"style="background-color: #180b7a">
                        Update Profile
                    </button>
                </div>
            </form>
            <h4 class="mb-4 text-md-center">
                Edit Picture
            </h4>
            <form action="/editProfile" method="post" class="border-bottom">
                <div class="form-group">
                    <label>URL : </label>
                    <input type="text" name="picture_url" class="form-control" value="<%=currentUser.getPictureUrl()%>">
                    <small  class="form-text text-muted">Input url of your profile photo</small>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" style="background-color: #180b7a">
                        Update URL
                    </button>
                </div>
            </form>
            <h4 class="mb-4 text-md-center">
                Update Password
            </h4>
            <form action="/editPassword" method="post" class="border-bottom">
                <div class="form-group">
                    <label > Old Password</label>
                    <input type="password" name="old_password" class="form-control"  placeholder="Password">
                    <%
                        String passwordError = request.getParameter("OldPasserror");
                        if(passwordError!=null){
                    %>
                    <p class="form-text text-danger">The password you’ve entered is incorrect.</p>
                    <%
                    }else{
                    %>
                    <small id="OldpasswordHelp" class="form-text text-muted">Input your old password</small>
                    <%
                        }
                    %>
                </div>
                <div class="form-group">
                    <label > New Password</label>
                    <input type="password" name="password" class="form-control">
                    <small id="passwordHelp" class="form-text text-muted">Input your new password</small>
                </div>
                <div class="form-group">
                    <label > Re-New Password</label>
                    <input type="password" name="re_password" class="form-control">
                    <%
                        String NewpasswordError = request.getParameter("passerror");
                        if(NewpasswordError!=null){
                    %>
                    <p class="form-text text-danger">Re-new password does not match with new password</p>
                    <%
                    }else{
                    %>
                    <small id="RepasswordHelp" class="form-text text-muted">Input your new password again</small>
                    <%
                        }
                    %>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" style="background-color: #180b7a">
                        Update Password
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
