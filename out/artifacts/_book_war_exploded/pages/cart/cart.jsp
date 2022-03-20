<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			//给删除绑定单击事件
			$("a.deleteItem").click(function (){
				return confirm("亲，你确定要删除["+$(this).parent().parent().find("td:first").text()+"]吗？");
			});
			//给清空购物车绑定单击事件
			$("#clearCart").click(function (){
				return confirm("亲，你确定要清空购物车吗？")
			});
			//给输入框绑定失去焦点事件 + 判断是否改变值 == onchange内容改变事件
			$(".updateCount").change(function (){
				//获取商品名称
				var name = $(this).parent().parent().find("td:first").text();
				//获取商品数量
				var count = $(this).val();
				//获取商品id
				var bookId = $(this).attr("bookId");
				if (confirm("你确定要将["+ name +"]商品修改数量为："+count+"吗？")){
					//发起请求给服务器，保存修改
					location.href = "${basePath}CartServlet?action=updateCount&id="+bookId+"&count="+count;
				}else{
					//defaultValue是表单项dom对象的属性，它表示默认的值
					this.value = this.defaultValue;
				}
			});

		});
	</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--静态包含登录成功后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>

	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<%--如果购物车空的情况--%>
				<td colspan="5"><a href="index.jsp">亲，当前的购物车为空哦！快去首页浏览商品吧！</a> </td>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<%--如果购物车非空的情况--%>
				<c:forEach items="${sessionScope.cart.items.entrySet()}" var="entry">
					<%--遍历商品信息--%>
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 40px" type="text" bookId="${entry.value.id}" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="CartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<%--如果购物车非空才输出页面内容--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="CartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="OrderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>