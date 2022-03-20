<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
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
				<td>编号</td>
				<td>名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>总价格</td>
				<td>订单号</td>

			</tr>
			<%--遍历订单项的信息--%>
			<c:forEach items="${requestScope.orderDetail}" var="orderItem">
				<tr>
					<td>${orderItem.id}</td>
					<td>${orderItem.name}</td>
					<td>${orderItem.count}</td>
					<td>${orderItem.price}</td>
					<td>${orderItem.totalPrice}</td>
					<td>${orderItem.orderId}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>