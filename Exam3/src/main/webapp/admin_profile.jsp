<%@page import="com.icreon.demo.entities.Users"%>
<%@page import="com.icreon.demo.entities.Message"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<%
Users users = (Users)session.getAttribute("currentUser");

if(users==null){
	response.sendRedirect("admin_login.jsp");
}
%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Profile</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <style>
            .banner-background{
                clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 91%, 63% 100%, 22% 91%, 0 99%, 0 0);
            }

            body{
                background:url(img/bg.jpeg);
                background-size: cover;
                background-attachment: fixed;
            }


        </style>
</head>
<body>

	<!-- start of navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark primary-background">
    <a class="navbar-brand" href="index.jsp"> <span class="fa fa-asterisk"></span>   Online Dictionary</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
            </li>

            

            <li class="nav-item">
                <a class="nav-link" href="#"> <span class="	fa fa-address-card-o"></span> Contact</a>
            </li>
            <li class="nav-item">

        </ul>
        <ul class="navbar-nav mr-right">
        			
        			<li class="nav-item">
                        <a class="nav-link" href="UserRequest" > <span class="fa fa-user "></span> Requests </a>
                    </li>
        			 <li class="nav-item">
                        <a class="nav-link" href="UserDisplay" > <span class="fa fa-user-circle "></span> See all users </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#!" data-toggle="modal" data-target="#profile-modal"> <span class="fa fa-user-circle "></span> <%= users.getName()%> </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="AdminLogout"> <span class="fa fa-user-circle "></span> Logout</a>
                    </li>
                </ul>
    </div>
</nav>
	
	
	
	
	<!-- end of navbar -->
	
	<!-- Start of Search  -->
	
	<div class="jumbotron primary-background text-white banner-background">
                <div class="container">
                    <h3 class="display-3">Welcome to Online Dictionary </h3>

            <form action="DictionaryServlet"   class="form-inline my-2 my-lg-4">
           
            <input name = "words" class="form-control mr-sm-2" type="text" placeholder="Search a word" aria-label="Search"/>
            <input type="submit" value="Search"/>
        </form>

                    
                </div>
            </div>
	
	
	
	
	<!-- End of Search  -->
	
	

		
        <!--profile modal-->



        <!-- Modal -->
        <div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header primary-background text-white text-center">
                        <h5 class="modal-title" id="exampleModalLabel"> </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <br>
                            <h5 class="modal-title mt-3" id="exampleModalLabel"> <%= users.getName()%> </h5>
                            <!--//details-->

                            <div id="profile-details">
                                <table class="table">

                                    <tbody>
                                        <tr>
                                            <th scope="row"> ID :</th>
                                            <td> <%= users.getId()%></td>

                                        </tr>
                                        
                                        <tr>
                                            <th scope="row"> Name : </th>
                                            <td><%= users.getName() %></td>

                                        </tr>
                                        <tr>
                                            <th scope="row"> Email : </th>
                                            <td><%= users.getEmail()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">User Type:</th>
                                            <td><%= users.getUserType() %></td>

                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>

                         <%--    <!--profile edit-->

                            <div id="profile-edit" style="display: none;">
                                <h3 class="mt-2">Please Edit Carefully</h3>
                                <form action="EditServlet" method="post" enctype="multipart/form-data">
                                    <table class="table">
                                        <tr>
                                            <td>ID :</td>
                                            <td><%= users.getId()%></td>
                                        </tr>
                                        <tr>
                                            <td>Email :</td>
                                            <td> <input type="email" class="form-control" name="user_email" value="<%= users.getEmail()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Password :</td>
                                            <td> <input type="password" class="form-control" name="user_password" value="<%= users.getPassword()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Name :</td>
                                            <td> <input type="text" class="form-control" name="user_name" value="<%= users.getName()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Password :</td>
                                            <td> <input type="password" class="form-control" name="user_password" value="<%= users.getPassword()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>User Type:</td>
                                            <td> <%= users.getUserType().toUpperCase()%> </td>
                                        </tr>
                                        
                         

                                    </table>

                                    <div class="container">
                                        <button type="submit" class="btn btn-outline-primary">Save</button>
                                    </div>

                                </form>    

                            </div> --%>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button  id="edit-profile-button" type="button" class="btn btn-primary">EDIT</button>
                    </div>
                </div>
            </div>
        </div>


        <!--end of profile modal-->
	
       
	 <%
            Message m = (Message) session.getAttribute("msg");
            if (m != null) {
        %>
        <div class="alert <%= m.getCssClass()%>" role="alert">
            <%=
            		m.getContent()%>
            
            
        </div> 
        
        <%
                session.removeAttribute("msg");
            }

        %>
        
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>