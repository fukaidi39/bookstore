<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%--静态包含登录成功后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>收货</td>
			</tr>
			<%--遍历订单项--%>
			<c:forEach items="${requestScope.orderList}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td>
						<c:if test="${order.status == 0}">未发货</c:if>
						<c:if test="${order.status == 1}">已发货</c:if>
						<c:if test="${order.status == 2}">已签收</c:if>
					</td>
					<td><a href="OrderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<td>
						<c:if test="${order.status == 0}">未发货</c:if>
						<c:if test="${order.status == 1}">
							<a href="OrderServlet?action=receiveOrder&orderId=${order.orderId}">签收订单</a>
						</c:if>
						<c:if test="${order.status == 2}">已签收</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>
		
	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>