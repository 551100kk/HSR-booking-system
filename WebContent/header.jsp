<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <head>
        <title>HSR Booking System</title>
    </head>
    <body>
        <div class="container-fluid wrapper">
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
                                <li><a><span class="glyphicon glyphicon-user"></span> ${user.username} </a></li>
                                <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <rapid:block name="body">
		    </rapid:block>
        </div>
        <div class="row footer">
            <div class="col-md-12">
                <hr>
                <dl>
                    <dt>
                        <strong>Powered by Kai-Chieh Chang @ 2018</strong>
                    </dt>
                    <dt>
                        <span class="glyphicon glyphicon-user"></span>
                        <a href="https://www.facebook.com/masj08">Facebook: Kai-Chieh Chang</a>
                    </dt>
                    <dt>
                        <span class="glyphicon glyphicon-envelope"></span>
                        <a href="mailto:551100kk@gmail.com">551100kk@gmail.com</a>
                    </dt>
                </dl>
        	</div>
        </div>
    </body>
    <style>
        .footer {
			left: 0;
			position:absolute;
			bottom: 100px;
			width: 100%;
			text-align: center;
        }
        .wrapper {
            min-height: 85%;
        }
    </style>
</html>
