<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="model.*, java.util.*"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<rapid:override name="body">
	<div class="row">
		<div class="col-md-12">
			<div class="alert alert-dismissable alert-success">
				<h4>Hello ${user.username}</h4>
				<b>請填寫訂票資訊</b>
			</div>
		</div>
	</div>
	<hr></hr>

	<div class="row" style="padding: 10px">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<div class="list-group">
				<form action="booking" onSubmit="return validateForm();" method="post">
					<div class="list-group-item active row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<strong>線上訂票</strong>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-6 form-group">
							<label for="fromStation">起站</label>
							<select class="form-control" id="fromStation" name="fromStation">
								<%	int i = 0; 
								for (String station: Constant.stationChineseName) { %>
								<option value="<%= i++ %>"><%= station %></option>
								<% } %>
							</select>
						</div>
						<div class="col-md-6 form-group">
							<label for="toStation">起站</label>
							<select class="form-control" id="toStation" name="toStation">
								<%	i = 0; 
								for (String station: Constant.stationChineseName) { %>
								<option value="<%= i++ %>"><%= station %></option>
								<% } %>
							</select>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-3 form-group">
							<label for="seatClass">訂票種類 : </label><br>
							<input class="form-check-input" type="radio" name="seatClass" id="seatClass0" value="0" checked>
							<label class="form-check-label" for="seatClass0">標準車廂</label>
							<input class="form-check-input" type="radio" name="seatClass" id="seatClass1" value="1">
							<label class="form-check-label" for="seatClass1">商務車廂</label>
							<input class="form-check-input" type="radio" name="seatClass" id="seatClass2" value="1">
							<label class="form-check-label" for="seatClass2">學生</label>
							<input class="form-check-input" type="radio" name="seatClass" id="seatClass3" value="1">
							<label class="form-check-label" for="seatClass3">愛心/敬老</label>
						</div>
						<div class="col-md-3 form-group">
							<label for="seatPreference">座位喜好 : </label><br>
							<input class="form-check-input" type="radio" name="seatPreference" id="seatPreference0" value="0" checked>
							<label class="form-check-label" for="seatPreference0">無</label>
							<input class="form-check-input" type="radio" name="seatPreference" id="seatPreference1" value="1">
							<label class="form-check-label" for="seatPreference1">商務車廂</label>
							<input class="form-check-input" type="radio" name="seatPreference" id="seatPreference2" value="2">
							<label class="form-check-label" for="seatPreference2">商務車廂</label>
						</div>
						<div class="col-md-3 form-group">
							<label for="searchType">座位喜好 : </label><br>
							<input class="form-check-input" type="radio" name="searchType" id="searchType0" value="0" checked>
							<label class="form-check-label" for="searchType0">依時間搜尋合適車次 </label>
							<input class="form-check-input" type="radio" name="searchType" id="searchType1" value="1">
							<label class="form-check-label" for="searchType1">直接輸入車次號碼</label>
						</div>
						<div class="col-md-3 form-group">
							<label for="isReturn">回程票 : </label><br>
							<input class="form-check-input" type="radio" name="isReturn" id="isReturn0" value="0" checked>
							<label class="form-check-label" for="isReturn0">單程 </label>
							<input class="form-check-input" type="radio" name="isReturn" id="isReturn1" value="1">
							<label class="form-check-label" for="isReturn1">回程</label>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;" id="outDateTimeDiv">
						<div class="col-md-6 form-group">
							<label for="dateOut">去程日期 :</label>
							<input class="form-control" type="date" id="dateOut" name="dateOut" value="2019-01-15">
						</div>
						<div class="col-md-6 form-group" id="timeOutDiv">
							<label for="departureTimeOut">去程時間 :</label>
							<input class="form-control" type="time" id="departureTimeOut" name="departureTimeOut" value="15:30">
						</div>
						<div class="col-md-6 form-group" id="trainIDOutDiv">
							<label for="trainIDOut">去程列車班次 :</label>
							<input class="form-control" type="number" id="trainIDOut" name="trainIDOut" value="123" step="1" min="1" max="10000">
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;" id="inDateTimeDiv">
						<div class="col-md-6 form-group">
							<label for="dateIn">回程日期 :</label>
							<input class="form-control" type="date" id="dateIn" name="dateIn" value="2019-01-15">
						</div>
						<div class="col-md-6 form-group" id="timeInDiv">
							<label for="departureTimeIn">回程時間 :</label>
							<input class="form-control" type="time" id="departureTimeIn" name="departureTimeIn" value="15:30">
						</div>
						<div class="col-md-6 form-group" id="trainIDInDiv">
							<label for="trainIDIn">回程列車班次 :</label>
							<input class="form-control" type="number" id="trainIDIn" name="trainIDIn" value="123" step="1" min="1" max="10000">
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-6 form-group">
							<label for="passengers">訂票張數 :</label>
							<input class="form-control" type="number" id="passengers" name="passengers" value="1" step="1" min="1" max="10">
						</div>
						<div class="col-md-6 form-group">
							<label for="isEarlyBird">早鳥票 : </label><br>
							<input class="form-check-input" type="radio" name="isEarlyBird" id="isEarlyBird0" value="0" checked>
							<label class="form-check-label" for="isEarlyBird0">都顯示</label>
							<input class="form-check-input" type="radio" name="isEarlyBird" id="isEarlyBird1" value="1">
							<label class="form-check-label" for="isEarlyBird1">只顯示早鳥票</label>
						</div>
					</div>
					<div class="list-group-item row">
						<button type="submit" class="btn btn-primary" style="width: 100%">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script>
	function validateForm() {
		var fromStation = document.getElementById("fromStation").selectedIndex;
		var toStation = document.getElementById("toStation").selectedIndex;
		if (fromStation == toStation) {
			alert("起站和終點站不得一樣");
			return false;
		}
		return true;
	}
	
	(function () {
		document.getElementById("outDateTimeDiv").style.display = "";
		document.getElementById("inDateTimeDiv").style.display = "none";
		document.getElementById("trainIDOutDiv").style.display = "none";
		document.getElementById("trainIDInDiv").style.display = "none";

		document.getElementsByName("isReturn").forEach(function(elem) {
		    elem.addEventListener("click", function() {
		        if (document.querySelector('input[name=isReturn]:checked').value == "1")
		        	document.getElementById("inDateTimeDiv").style.display = "";
		        else
		        	document.getElementById("inDateTimeDiv").style.display = "none";
		    });
		});
		
		document.getElementsByName("searchType").forEach(function(elem) {
		    elem.addEventListener("click", function() {
		        if (document.querySelector('input[name=searchType]:checked').value == "1") {
		    		document.getElementById("trainIDOutDiv").style.display = "";
		    		document.getElementById("trainIDInDiv").style.display = "";
		    		document.getElementById("timeOutDiv").style.display = "none";
		    		document.getElementById("timeInDiv").style.display = "none";
		        } else {
		    		document.getElementById("trainIDOutDiv").style.display = "none";
		    		document.getElementById("trainIDInDiv").style.display = "none";
		    		document.getElementById("timeOutDiv").style.display = "";
		    		document.getElementById("timeInDiv").style.display = "";
		        }
		    });
		});
		
		
	}) ();

	</script>  
</rapid:override>
<%@ include file="header.jsp"%>