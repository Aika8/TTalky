<%@ page import="java.util.ArrayList" %>
<%@ page import="talky.csse.db.Post" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@include file="includes/head.jsp"%>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/vendor/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
    <script>tinymce.init({selector:'textarea'});</script>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="modal fade" id="AddPost" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add New Post</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/addPost" method="post" id="addForm">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>TITLE : </label>
                            <input type="text" name="title" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>SHORT CONTENT : </label>
                            <textarea  name="short_content" class="form-control" form="addForm"></textarea>
                        </div>
                        <div class="form-group">
                            <label>CONTENT : </label>
                            <textarea  name="content" class="form-control" form="addForm"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" style="background-color: #344c85; border-color: #344c85">ADD</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


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
            <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#addPost" style="background-color: #180b7a ">
                + ADD NEW
            </button>
            <%
                ArrayList<Post>posts = (ArrayList<Post>)request.getAttribute("posts");
                if(posts != null){
                    for(Post post:posts){
            %>
            <div class="card mt-4">
                <div class="card-body">
                    <h5 class="card-title"><%=post.getTitle()==null? "": post.getTitle()%></h5>
                    <p class="card-text"><%=post.getShort_content()%></p>
                    <a href="/details?id=<%=post.getId()%>&back=myPosts" class="btn btn-light" style="border-color: #180b7a">More -></a>
                </div>
                <div class="card-footer text-muted">
                    Posted on <%=String.format(Locale.ENGLISH, "%1tB %<td, %<tY", post.getPostDate())%> by <%=post.getUser().getFull_name()%>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
