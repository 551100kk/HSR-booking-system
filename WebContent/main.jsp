<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="body">
<div class="row">
    <div class="col-md-12">
        <div class="alert alert-dismissable alert-success">
            <h4>Hello!</h4>
            Welcome ${user.username}
        </div>
    </div>
</div>

<div class="row" style="padding: 10px">
    <div class="col-md-12">
        <h3><strong>系統公告</strong></h3>
        <ul>
            <li class="list-item">2018-01-14 高鐵訂票系統正式啟用</li>
        </ul>
        <hr>
        <img src="https://upload.wikimedia.org/wikipedia/zh/thumb/0/06/THSR.svg/1280px-THSR.svg.png">
    </div>
</div>

<style>
    img {
		display: block;
		margin-left: auto;
		margin-right: auto;
        width: 40%;
        text-align: center;
    }
</style>

</rapid:override>
<%@ include file="header.jsp"%>