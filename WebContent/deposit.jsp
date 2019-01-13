<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="model.*, java.util.*"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<rapid:override name="body">
	<div class="row">
		<div class="col-md-12">
			<% if (request.getParameter("error") != null)  {%>
			<div class="alert alert-dismissable alert-danger">
				<h4>Error</h4>
				<b>儲值失敗 請確認點卷代碼是否正確</b>
			</div>
			<% } else if (request.getParameter("success") != null)  {%>
			<div class="alert alert-dismissable alert-success">
				<h4>Success</h4>
				<b>儲值成功 目前有 ${user.hsrcoin} HSRCoin </b>
			</div>
			<% } else { %>
			<div class="alert alert-dismissable alert-success">
				<h4>Hello ${user.username}</h4>
				<b>歡迎使用HSRCoin儲值系統</b>
			</div>
			<% } %>
		</div>
	</div>
	<hr>
	<div class="row" style="padding: 10px">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<div class="list-group">
				<form action="deposit" method="post">
					<div class="list-group-item active row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<strong>充值點卷</strong>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<label for="serialcode">點卷代碼 :</label>
							<input class="form-control" type="text" id="serialcode" name="serialcode">
						</div>
					</div>
					<div class="list-group-item row">
						<button type="submit" class="btn btn-primary" style="width: 100%">Deposit</button>
					</div>
				</form>
			</div>
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
	
	<script>
	</script> 
</rapid:override>
<%@ include file="header.jsp"%>