<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-inverse">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <a class="navbar-brand" href="">HSR Booking System</a>
                            </div>
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="">Home</a></li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="#myModalRegister" data-toggle="modal"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                                <li><a href="#myModalLogin" data-toggle="modal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <%
                    	String status = request.getParameter("status");
                    	int type = 0;
                    	try {
				            type = Integer.parseInt(status);
                    	} catch (Exception e) {
                    		type = 0;	
                    	}
                    %>
                    <% if (type == 0) { %>
                    <div class="alert alert-dismissable alert-info">
                        <h4>Warning!</h4>
                        Please login first!
                    </div>
                    <% } else if (type == 1) { %>
                    <div class="alert alert-dismissable alert-danger">
                        <h4>Error!</h4>
                        Wrong username or password!
                    </div>
                    <% } else if (type == 2) { %>
                    <div class="alert alert-dismissable alert-danger">
                        <h4>Error!</h4>
                        The username has been used!
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="myModalLogin" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Login</h4>
                    </div>
                    <div class="modal-body">
                        <form action="login" method="post">
                            <div class="form-group">
                                <label for="usr">Username:</label>
                                <input type="text" class="form-control" name="username">
                            </div>
                            <div class="form-group">
                                <label for="pwd">Password:</label>
                                <input type="password" class="form-control" name="password">
                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="myModalRegister" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Register</h4>
                    </div>
                    <div class="modal-body">
                        <form action="register" method="post">
                            <div class="form-group">
                                <label for="username">Username:</label>
                                <input type="text" class="form-control" name="username">
                            </div>
                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" class="form-control" name="password">
                            </div>
                            <button type="submit" class="btn btn-default">Register</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
