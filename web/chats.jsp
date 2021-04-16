<%@ page import="java.util.ArrayList" %>
<%@ page import="talky.csse.db.Chat" %>
<html>
<head>

    <meta charset="utf-8">
    <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@include file="includes/head.jsp"%>
    <title>Title</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

</head>
<body>
<%@include file="includes/navbar.jsp"%>
<style>

    .btn-light{
        color: #180b7a;
        border-color: #180b7a;
    }
    .profile{
        width: 140px;
        height: 140px;
    }

    .profile img {
        width: 140px;
        height: 140px;
        border-radius: 50%
    }

</style>

</div>

<div class="container">

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
                    <img src="https://img.icons8.com/dotty/25/000000/gears.png"/>
                    Settings
                </a></li>
                <li class="list-group-item"><a class="btn btn-link font-weight-bold text-danger" href="/logout">
                    <span class="material-icons">exit_to_app</span>Logout
                </a></li>
            </ul>
        </div>
        <div class="col-sm-8 text-left ">
            <form class="form-inline" action="/chat" method="get" style="max-width: 540px;">
                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="searchChat" style="width: 430px;" value="<%=request.getSession().getAttribute("searchChat")%>">
                <button class="btn btn-light btn-sm-3 my-0" type="submit">
                    <span class="fas fa-search"></span> Search</button>
            </form>

            <%

                ArrayList<Chat> chats = (ArrayList<Chat>)request.getAttribute("chats");
                ArrayList<Long> notReadChats = (ArrayList<Long>)request.getAttribute("notReadChats");
                if(chats != null){
                    for(Chat chat:chats){
            %>

            <%
                if(currentUser.getId() == chat.getOpponent().getId()){
            %>
            <div class="card mb-3" style="max-width: 540px;">
                <div class="row no-gutters">
                    <div class="col-md-3 mt-2 ml-2 profile" >
                        <a href="/friendProfile?email=<%=chat.getUser().getEmail()%>"><img src="<%=chat.getUser().getPictureUrl()%>" alt=""></a>
                    </div>

                    <div class="col-md-8">
                        <%
                            if(notReadChats.contains(chat.getId())){
                        %>
                        <div class="card-body">
                            <h5 class="card-title font-weight-bold"><a href="${pageContext.request.contextPath}/individualChat?id=<%=chat.getId()%>"><span class="fab fa-angellist"></span><%=chat.getUser().getFull_name()%></a></h5>
                            <p class="card-text font-weight-bold"><%=chat.getLatestMessage()%></p>
                            <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;" class="font-weight-bold">
                                <%=chat.getLatestMessageTime()%>
                            </div>
                        </div>
                        <%
                            }else{
                        %>
                        <div class="card-body">
                            <h5 class="card-title "><a href="${pageContext.request.contextPath}/individualChat?id=<%=chat.getId()%>"><%=chat.getUser().getFull_name()%></a></h5>
                            <p class="card-text"><%=chat.getLatestMessage()%></p>
                            <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
                                <%=chat.getLatestMessageTime()%>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <%
                }else{
            %>
            <div class="card mb-3" style="max-width: 540px;">
                <div class="row no-gutters">
                    <div class="col-md-3 mt-2 ml-2 profile" >
                        <a href="/friendProfile?email=<%=chat.getOpponent().getEmail()%>"><img src="<%=chat.getOpponent().getPictureUrl()%>" alt=""></a>
                    </div>
                    <div class="col-md-8">
                        <%
                            if(notReadChats.contains(chat.getId())){
                        %>
                        <div class="card-body">
                            <h5 class="card-title font-weight-bold"><a href="${pageContext.request.contextPath}/individualChat?id=<%=chat.getId()%>"><span class="fab fa-angellist"></span> <%=chat.getOpponent().getFull_name()%></a></h5>
                            <p class="card-text font-weight-bold"><%=chat.getLatestMessage()%></p>
                            <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;" class="font-weight-bold">
                                <%=chat.getLatestMessageTime()%>
                            </div>
                        </div>
                        <%
                        }else{
                        %>
                        <div class="card-body">
                            <h5 class="card-title "><a href="${pageContext.request.contextPath}/individualChat?id=<%=chat.getId()%>"><%=chat.getOpponent().getFull_name()%></a></h5>
                            <p class="card-text"><%=chat.getLatestMessage()%></p>
                            <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
                                <%=chat.getLatestMessageTime()%>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>

            <%
                }
            %>


            <%
                    }
                }
            %>

        </div>
    </div>
</div>
</body>
</html>
