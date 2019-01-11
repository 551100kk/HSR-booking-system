<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="model.*, java.util.*"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<rapid:override name="body">
	<div class="row">
		<div class="col-md-12">
			<div class="alert alert-dismissable alert-success">
				<h4>Hello ${user.username}</h4>
				<b> 
				<% if (request.getAttribute("date") != null) { %>
				<%=request.getAttribute("date")%> <%=request.getAttribute("dayofweek")%> 時刻表
				<% } else { %>
				請選則查詢的日期
				<% } %>
				</b>
			</div>
		</div>
	</div>
	<hr></hr>

	<div class="row" style="padding: 10px">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<% if (request.getAttribute("date") != null) { %>
			<div class="list-group">
				<div class="list-group-item active">
					<strong>南下列車</strong>
				</div>
				<div class="list-group-item">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">車次</th>
								<%
									String[] station = (String[]) request.getAttribute("station");
									for (int i = 0; i < station.length; i += 1) {
								%>
								<th scope="col"><%=station[i]%></th>
								<% } %>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<TimeTableEntry> timeTableEntryList = (ArrayList<TimeTableEntry>) request
											.getAttribute("timeTableEntryList");
									for (TimeTableEntry timeTableEntry : timeTableEntryList) {
										if (timeTableEntry.getDirection() == 1)
											continue;
							%>
							<tr>
								<th scope="row"><%=timeTableEntry.getTrainID()%></th>
								<% for (Time time : timeTableEntry.getTimeList()) { %>
								<td><%=time.getDisplayTime() == "-1" ? "-": time.getDisplayTime()%></td>
								<% } %>
							</tr>
							<% } %>
						</tbody>
					</table>
				</div>
			</div>

			<div class="list-group">
				<div class="list-group-item active">
					<strong>北上列車</strong>
				</div>
				<div class="list-group-item">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">車次</th>
								<% for (int i = 0; i < station.length; i += 1) { %>
								<th scope="col"><%=station[station.length - i - 1]%></th>
								<% } %>
							</tr>
						</thead>
						<tbody>
							<%
								timeTableEntryList = (ArrayList<TimeTableEntry>) request.getAttribute("timeTableEntryList");
								for (TimeTableEntry timeTableEntry : timeTableEntryList) {
									if (timeTableEntry.getDirection() == 0)
										continue;
							%>
							<tr>
								<th scope="row"><%=timeTableEntry.getTrainID()%></th>
								<%
									Collections.reverse(timeTableEntry.getTimeList());
									for (Time time : timeTableEntry.getTimeList()) {
								%>
								<td><%=time.getDisplayTime() == "-1" ? "-": time.getDisplayTime()%></td>
								<%
									}
									Collections.reverse(timeTableEntry.getTimeList());
								%>
							</tr>
							<% } %>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-md-1"></div>
			<% } else { %>
				<div class="list-group">
					<form action="timetable" method="get">
						<div class="list-group-item active row" style="padding-bottom: 0;">
							<div class="col-md-12 form-group">
								<strong>查詢時刻表</strong>
							</div>
						</div>
						<div class="list-group-item row" style="padding-bottom: 0;">
							<div class="col-md-12 form-group">
								<label for="start">查詢日期 :</label>
								<input class="form-control" type="date" id="start" name="date" value="2019-01-15">
							</div>
						</div>
						<div class="list-group-item row">
							<button type="submit" class="btn btn-primary" style="width: 100%">Submit</button>
						</div>
					</form>
				</div>
			<% } %>
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