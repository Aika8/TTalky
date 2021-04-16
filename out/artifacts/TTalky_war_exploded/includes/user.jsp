<%@ page import="talky.csse.db.User" %>
<%
    User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
    boolean ONLINE = false;

    if(currentUser != null){
        ONLINE = true;
    }

    String number = (String) request.getSession().getAttribute("NUMBER_OF_REQUESTS");

    String numberChats = (String) request.getSession().getAttribute("NOt_READ_CHATS");
%>
