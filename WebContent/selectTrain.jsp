<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="model.*, java.util.*"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<rapid:override name="body">
	<div class="row">
		<div class="col-md-12">
			<div class="alert alert-dismissable alert-success">
				<h4>Hello ${user.username}</h4>
				<b>請選擇車次</b>
			</div>
		</div>
	</div>
	<hr></hr>
	<%
		BookCondition bookCondition = (BookCondition) session.getAttribute("bookCondition");
		@SuppressWarnings("unchecked")
		ArrayList<Train> trainListOut = (ArrayList<Train>) session.getAttribute("trainListOut");
		@SuppressWarnings("unchecked")
		ArrayList<Train> trainListIn = (ArrayList<Train>) session.getAttribute("trainListIn");
	
		String fromStation = Constant.stationChineseName[bookCondition.getFromStation()];
		String toStation = Constant.stationChineseName[bookCondition.getToStation()];
		String dateOut = bookCondition.getDateOut().getDisplayDate();
		String dayOfWeekOut = Constant.daysOfWeek[bookCondition.getDateOut().getDayOfWeek()];
		String dateIn = bookCondition.getDateIn().getDisplayDate();
		String dayOfWeekIn = Constant.daysOfWeek[bookCondition.getDateIn().getDayOfWeek()];
		String seatClass = Constant.ticketType[bookCondition.getSeatClass()];
		
		int tickets = bookCondition.getPassengers();
		if (bookCondition.isReturn())
			tickets *= 2;
	%>
	<input type="hidden" id="dateOut" value="<%= dateOut %>"/>
	<% if (bookCondition.isReturn()) { %>
	<input type="hidden" id="dateIn" value="<%= dateIn %>"/>
	<% } %>
	<div class="row" style="padding: 10px">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<div class="list-group">
				<form action="selectTrain" method="post">
					<!-- Outbound Train -->
					<div class="list-group-item active row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<strong>去程： <%= fromStation %> - <%= toStation %> <%= dateOut %> <%= dayOfWeekOut %></strong>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<!-- TODO -->
							<table class="table">
								<thead>
									<tr>
										<th scope="col" style="text-align: center">選擇</th>
										<th scope="col" style="text-align: center">車次</th>
										<th scope="col" style="text-align: center">全票優惠*</th>
										<th scope="col" style="text-align: center">出發時間</th>
										<th scope="col" style="text-align: center">到達時間</th>
										<th scope="col" style="text-align: center">行車時間</th>
									</tr>
								</thead>
								<tbody>
									<% 
										for (int i = 0; i < trainListOut.size(); i++) {
											Train train = trainListOut.get(i);
											String discount = "";
											if (train.getDiscount() < 1 && train.getDiscount() > 0)
												discount = Double.toString(train.getDiscount());
									%>
									<tr>
										<th scope="col" style="text-align: center">
											<input class="form-check-input" type="radio" name="outTrainID" id="outTrainID<%= i %>" value="<%= i %>" checked>
										</th>
										<th id="outID<%= i %>" scope="col" style="text-align: center"><%= train.getTrainID() %></th>
										<th id="outDiscount<%= i %>" scope="col" style="text-align: center; color: red"><%= discount %></th>
										<td id="outDepartureTime<%= i %>" scope="col" style="text-align: center"><%= train.getDepartureTime().getDisplayTime() %></td>
										<td id="outArrivalTiem<%= i %>" scope="col" style="text-align: center"><%= train.getArrivalTime().getDisplayTime() %></td>
										<td id="outDuration<%= i %>" scope="col" style="text-align: center"><%= train.getDuration().getDisplayTime() %></td>
									</tr>
									<% } %>
								</tbody>
							</table>
						</div>
					</div>
					
					<!-- Inbound Train -->
					<% if (bookCondition.isReturn()) { %>
					<div class="list-group-item active row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<strong>回程： <%= toStation %> - <%= fromStation %> <%= dateIn %> <%= dayOfWeekIn %></strong>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<!-- TODO -->
							<table class="table">
								<thead>
									<tr>
										<th scope="col" style="text-align: center">選擇</th>
										<th scope="col" style="text-align: center">車次</th>
										<th scope="col" style="text-align: center">全票優惠*</th>
										<th scope="col" style="text-align: center">出發時間</th>
										<th scope="col" style="text-align: center">到達時間</th>
										<th scope="col" style="text-align: center">行車時間</th>
									</tr>
								</thead>
								<tbody>
									<% 
										for (int i = 0; i < trainListIn.size(); i++) {
											Train train = trainListIn.get(i);
											String discount = "";
											if (train.getDiscount() < 1 && train.getDiscount() > 0)
												discount = Double.toString(train.getDiscount());
									%>
									<tr>
										<th scope="col" style="text-align: center">
											<input class="form-check-input" type="radio" name="inTrainID" id="inTrainID<%= i %>" value="<%= i %>" checked>
										</th>
										<th id="inID<%= i %>" scope="col" style="text-align: center"><%= train.getTrainID() %></th>
										<th id="inDiscount<%= i %>" scope="col" style="text-align: center; color: red"><%= discount %></th>
										<td id="inDepartureTime<%= i %>" scope="col" style="text-align: center"><%= train.getDepartureTime().getDisplayTime() %></td>
										<td id="inArrivalTiem<%= i %>" scope="col" style="text-align: center"><%= train.getArrivalTime().getDisplayTime() %></td>
										<td id="inDuration<%= i %>" scope="col" style="text-align: center"><%= train.getDuration().getDisplayTime() %></td>
									</tr>
									<% } %>
								</tbody>
							</table>
						</div>
					</div>
					<% } %>
					
					<!-- Order -->
					<div class="list-group-item active row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<strong>訂票明細</strong>
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
										<th scope="col" style="text-align: center">出發時間</th>
										<th scope="col" style="text-align: center">到達時間</th>
										<th scope="col" style="text-align: center">行車時間</th>
									</tr>
								</thead>
								<tbody>
									<tr id="outTrainDetails"></tr>
									<% if (bookCondition.isReturn()) { %>
									<tr id="inTrainDetails"></tr>
									<% } %>
								</tbody>
							</table>
						</div>
						<hr>
						<div class="col-md-3 form-group"></div>
						<div class="col-md-3 form-group" style="text-align: center">
							<label>訂票種類 : <%= seatClass %></label>
						</div>
						<div class="col-md-3 form-group" style="text-align: center">
							<label>票數 : <%= tickets %> 張</label>
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
	(function () {
		document.getElementsByName("outTrainID").forEach(function(elem) {
		    elem.addEventListener("click", function() {
		        var index = document.querySelector('input[name=outTrainID]:checked').value;
		        var parent = document.getElementById("outTrainDetails");
		        parent.innerHTML = "";
		        
		        var node = document.createElement("th");
		        node.innerText = "去程";
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("dateOut").value;
		        node.style.textAlign = "center";
		        parent.appendChild(node);

		        var node = document.createElement("td");
		        node.innerText = document.getElementById("outID" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("outDepartureTime" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("outArrivalTiem" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("outDuration" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		    });
		});
		document.getElementsByName("inTrainID").forEach(function(elem) {
		    elem.addEventListener("click", function() {
		        var index = document.querySelector('input[name=inTrainID]:checked').value;
		        var parent = document.getElementById("inTrainDetails");
		        parent.innerHTML = "";
		        
		        var node = document.createElement("th");
		        node.innerText = "回程";
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("dateIn").value;
		        node.style.textAlign = "center";
		        parent.appendChild(node);

		        var node = document.createElement("td");
		        node.innerText = document.getElementById("inID" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("inDepartureTime" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("inArrivalTiem" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		        
		        var node = document.createElement("td");
		        node.innerText = document.getElementById("inDuration" + index).innerText;
		        node.style.textAlign = "center";
		        parent.appendChild(node);
		    });
		});
		document.getElementById("outTrainID0").click();
		if (document.getElementById("inTrainID0"))
			document.getElementById("inTrainID0").click();
	}) ();

	</script>  
</rapid:override>
<%@ include file="header.jsp"%>