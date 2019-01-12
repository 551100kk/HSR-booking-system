<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="model.*, java.util.*"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<rapid:override name="body">
	<div class="row">
		<div class="col-md-12">
			<div class="alert alert-dismissable alert-success">
				<h4>Hello ${user.username}</h4>
				<b>訂票即將完成</b>
			</div>
		</div>
	</div>
	<hr></hr>
	<%
		Order order = (Order) session.getAttribute("order");
	%>
	<div class="row" style="padding: 10px">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<div class="list-group">
				<form action="selectTrain" method="post">
					<div class="list-group-item active row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<strong>訂位明細</strong>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<!-- TODO -->
							<table class="table">
								<thead>
									<tr>
										<th scope="col" style="text-align: center">行程</th>
										<th scope="col" style="text-align: center">日期</th>
										<th scope="col" style="text-align: center">車次</th>
										<th scope="col" style="text-align: center">起程站</th>
										<th scope="col" style="text-align: center">到達站</th>
										<th scope="col" style="text-align: center">出發時間</th>
										<th scope="col" style="text-align: center">到達時間</th>
										<th scope="col" style="text-align: center">票價</th>
										<th scope="col" style="text-align: center">折扣</th>
										<th scope="col" style="text-align: center">小計</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-6 form-group">
							<a href="booking" class="btn btn-warning" style="width: 100%">Cancel</a>
						</div>
						<div class="col-md-6 form-group">
							<button type="submit" class="btn btn-primary" style="width: 100%">Submit</button>
						</div>
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