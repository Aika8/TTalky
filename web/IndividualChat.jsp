<%@ page import="java.util.ArrayList" %>
<%@ page import="talky.csse.db.Message" %>
<html>
<head>
    <meta charset="utf-8">
    <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@include file="includes/head.jsp"%>
    <title>Tootles</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>


<style>


    .card {
        margin: 0 auto;
        border: solid 1px #ccc;
        border-radius: 5px;
        overflow: hidden;
    }

    .chat-container {
        height: 400px;
        overflow: auto;
        -webkit-transform: rotate(180deg);
        transform: rotate(180deg);
        direction: rtl;
    }
    .chat-container .message {
        width: 400px;
        padding: 10px;
        transform: rotate(180deg);
        margin: 0;
    }


    .chat-container .message p{
       padding-left: 10px;
    }


    .msg_receive {
        background: #e5e5e5;
        padding-left: 0;
        margin-left: 0;
    }

    .msg_sent {
        background: #a1dbff;
        margin-right: 0;
    }

    .chat-container .message .datetime {
        float: right;
        color: #999;
    }

    .form-inline{
        padding-left: 40px;
        padding-top: 20px;
    }

    .btn-light{
        color: #180b7a;
        border-color: #180b7a;
    }


</style>


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
            <div class="card mt-4" style=" overflow: hidden;">
                <div class="chat-container">
                    <%
                        ArrayList<Message>messages = (ArrayList<Message>)request.getAttribute("messages");
                        if(messages != null){
                           for(Message message:messages){

                    %>
                    <%
                        if(message.getSenderId().equals(currentUser.getId())){

                    %>
                    <div class="cont_for_mess" style="direction: ltr">
                    <div class="message">
                        <div class="datetime" style="direction: rtl"><%=message.getSent_date()%></div>
                        <div class="messages msg_sent">
                            <p><%=message.getMessage_text()%></p>
                        </div>
                    </div>
                    </div>
                    <%
                        }else{

                    %>
                    <div class="message">
                        <div class="datetime"><%=message.getSent_date()%></div>
                        <div class="messages msg_receive" style="direction: ltr">
                            <p><%=message.getMessage_text()%></p>
                        </div>
                    </div>

                    <%
                        }

                    %>


                    <%
                            }
                        }

                        assert messages != null;%>

                </div>
            </div>
            <div class="card mt-4">
                <form class="form-inline" action="/addMessage" method="post" style="max-width: 600px;">
                    <input type="hidden" name="chat_id" value="<%=messages.get(0).getChatId()%>">
                    <input class="form-control mr-sm-2" type="text" placeholder="Your Message..." aria-label="Send" name="message" style="width: 430px;" autocomplete="off">
                    <button class="btn btn-light btn-sm-3 my-0" type="submit">
                        <span class="far fa-paper-plane"></span> Send</button>
                </form>
            </div>


        </div>
    </div>
</div>
</body>
</html>
