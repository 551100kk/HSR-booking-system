<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="model.*, java.util.*"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<rapid:override name="body">
	<div class="row">
		<div class="col-md-12">
			<div class="alert alert-dismissable alert-success">
				<h4>Hello ${user.username}</h4>
				<b>以下是您的車票</b>
			</div>
		</div>
	</div>
	<hr></hr>
	<%
		ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
	%>
	<div class="row" style="padding: 10px">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<div class="list-group">
				<form action="history" method="post">
					<div class="list-group-item active row">
						<div class="col-md-6 form-group" style="padding-top:12.5px">
							<strong>訂單明細</strong>
						</div class="col-md-6 form-group">
						<div>
							<div class="col-md-6 form-group" style="text-align: right;margin-bottom: 0;padding-top: 10px;">
                <button type="button" onclick="window.location='delete';" class="btn btn-default" style="padding: 2px 12px 2px">
                    <span class="glyphicon glyphicon-edit" style="">修改訂單</span>
                </button>
            </div>
						</div>
					</div>
					<div class="list-group-item row" style="padding-bottom: 0;">
						<div class="col-md-12 form-group">
							<table class="table table-bordered">
								<tbody>
									<%
										for (int i = 0; i < orders.size(); i++) {
												Order order = orders.get(i);
												ArrayList<Ticket> tickets = order.getTickets();
									%>
									<tr>
										<table class="table">
											<thead>
												<tr>
													<div class="col-md-6 form-group" style="text-align: left">
														<strong>訂單編號: <%=order.getOrderID()%></strong>
													</div>
													<input type="hidden" name="checkedall" id="<%=i%>" value=1 />

												</tr>
												<tr>
													<th scope="col" style="text-align: center">行程</th>
													<th scope="col" style="text-align: center">日期</th>
													<th scope="col" style="text-align: center">車次</th>
													<th scope="col" style="text-align: center">起程站</th>
													<th scope="col" style="text-align: center">到達站</th>
													<th scope="col" style="text-align: center">出發時間</th>
													<th scope="col" style="text-align: center">到達時間</th>
													<th scope="col" style="text-align: center">座位</th>
													<th scope="col" style="text-align: center">折扣種類</th>
													<th scope="col" style="text-align: center">折扣</th>
													<th scope="col" style="text-align: center">小計</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (int j = 0; j < tickets.size(); j++) {
																Ticket ticket = tickets.get(j);
																String fromStation = Constant.stationChineseName[ticket.getFromStation()];
																String toStation = Constant.stationChineseName[ticket.getToStation()];
																String discountType = Constant.discountType[ticket.getDiscountType()];
																String discount = "";
																if (ticket.getDiscount() < 1 && ticket.getDiscount() > 0)
																	discount = String.format ("%.2f", ticket.getDiscount());
												%>
												<tr>
													<th scope="col" style="text-align: center"><%=ticket.getDirection() == 0 ? "去程" : "回程"%></th>
													<th scope="col" style="text-align: center"><%=ticket.getDate().getDisplayDate()%></th>
													<th scope="col" style="text-align: center"><%=ticket.getTrainID()%></th>
													<th scope="col" style="text-align: center"><%=fromStation%></th>
													<th scope="col" style="text-align: center"><%=toStation%></th>
													<th scope="col" style="text-align: center"><%=ticket.getDepartureTime().getDisplayTime()%></th>
													<th scope="col" style="text-align: center"><%=ticket.getArrivalTime().getDisplayTime()%></th>
													<th scope="col" style="text-align: center"><%=ticket.getSeatID()%></th>
													<th scope="col" style="text-align: center"><%=discountType%></th>
													<th scope="col" style="text-align: center"><%=discount%></th>
													<th scope="col" style="text-align: center"><%=ticket.getPrice()%></th>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>


										<div class="col-md-3 form-group" style="text-align: left">
											<label>票數 : <%=tickets.size()%></label>
										</div>
										<div class="col-md-3 form-group"></div>
										<div class="col-md-3 form-group"></div>
										<div class="col-md-3 form-group" style="text-align: right">
											<label style="color: red">總票價 TWD : <%=order.getPrice()%></label>
										</div>
									</tr>

									<%
										}
									%>
								</tbody>
							</table>
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