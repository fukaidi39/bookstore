<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%--静态包含manager菜单管理模块--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<%--遍历订单信息--%>
			<c:forEach items="${requestScope.orders}" var="order">
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
						<c:if test="${order.status == 0}"><a href="OrderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a></c:if>
						<c:if test="${order.status != 0}">已发出</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>