<%@ page import="talky.csse.db.Post" %>
<%@ page import="java.util.ArrayList" %>
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
    <div class="row content mt-5">
        <div class="col-sm-3 sidenav ">

            <div class="modal fade" tabindex="-1" role="dialog" id="sendMess" aria-hidden="true" aria-labelledby="sendMessLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Send Message to
                                <input id="receiver_full_name" name="receiver_full_name" type="text" style="outline:none; border: 0px none;" readonly></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form method="post" action="/chatHandler">
                            <div class="modal-body">
                                <input type="hidden" id="redirect" name="redirect" value="/friend?search=">
                                <input type="hidden" id="receiver_email" name="receiver_email">
                                <textarea class="form-control" id="message" name="message"></textarea>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">Send</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <%
                User user = (User)request.getAttribute("user");
                if (user != null) {
            %>
            <ul class="list-group">
                <img src="<%=user.getPictureUrl()%>" class="img-thumbnail">
                <li class="list-group-item"><%=user.getFull_name()%>, <%=2020-Integer.parseInt(user.getBirthday().split("\\W")[0])%> years</li>
                <%
                    Boolean isFriend = (Boolean)request.getAttribute("isFriend");
                    Boolean isReq = (Boolean)request.getAttribute("isReq");
                    if(isFriend){
                %>
                <li class="list-group-item">
                <form action="/deleteFriend" method="post">
                <input type="hidden" name="email" value="<%=user.getEmail()%>">
                <button type="submit" class="btn btn-link font-weight-bold">
                    <span class="fas fa-trash-alt"></span>
                    Remove From Friends</button>
                </form>
                </li>
                <li class="list-group-item">
                <button id="friend" class="btn btn-link font-weight-bold" onclick="sendMessage(<%=user.getId()%>, '<%=user.getFull_name()%>', '<%=user.getEmail()%>')" data-toggle="modal" data-target="#sendMess">
                    <span class="far fa-paper-plane"></span> Send Message
                </button>
                </li>
                <%
                    }else if(isReq){
                %>
                <li class="list-group-item">
                <button id="requested" class="btn btn-link font-weight-bold" >
                    <span class="far fa-check-circle"></span> Request Sent
                </button>
                </li>
                <%
                    }else{
                %>
                <li class="list-group-item">
                <button id="makeFriend" class="btn btn-link font-weight-bold makeFriend" data-loading-text="loading..." data-arg ="<%=user.getEmail()%>">
                    <span class="fas fa-plus-circle"></span> Add to Friends
                </button>
                </li>
                <%
                    }
                %>
                <li class="list-group-item"><a class="btn btn-link font-weight-bold text-danger" href="/logout">
                    <span class="material-icons">exit_to_app</span>Logout
                </a></li>
            </ul>
            <%

                }
            %>
        </div>
        <div class="col-sm-8 text-left ">

            <%
                ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("posts");
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

            <script type="text/javascript">
                const sendMessage = (id, full_name, email) => {
                    document.getElementById("receiver_email").value = email;
                    document.getElementById("receiver_full_name").value = full_name;
                }
            </script>

            <script type='text/javascript'>

                jQuery(function($) {

                    $('#makeFriend').on('click', function() {
                        var $el = $(this), textNode = this.lastChild;
                        if($el.hasClass('makeFriend')) {

                            var dataLoadingText = $(this).attr("data-loading-text");
                            if (typeof dataLoadingText !== typeof undefined && dataLoadingText !== false) {
                                $el.find('span').toggleClass('fab fa-blackberry');
                                textNode.nodeValue = dataLoadingText ;
                            }
                            setTimeout(function () {
                                $el.find('span').toggleClass('far fa-check-circle');
                                textNode.nodeValue = ' Request Sent' ;
                                $el.toggleClass('Sent');
                            }, 2000);
                        }

                        var email = $(this).attr('data-arg');
                        console.log('email');
                        console.log($.ajax);
                        $.ajax('/makeFriend', {

                            data:{email:email},
                            type:'POST',
                            dataType:"text"
                        });
                    });
                });

            </script>

        </div>
    </div>
</div>
</div>
</body>
</html>
