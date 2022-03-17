<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="" method="get">
					价格：<input id="min" type="text" name="min" value=""> 元 - 
						<input id="max" type="text" name="max" value=""> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有3件商品</span>
				<div>
					您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
				</div>
			</div>
			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button>加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<div id="page_nav">
			<%--大于首页才显示--%>
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="Client/BookServlet?action=page&pageNo=1">首页</a>
				<a href="Client/BookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--五连页码输出的开始--%>
			<c:choose>
				<%--情况1：如果总页码<5,页码的范围是1-总页码--%>
				<c:when test="${requestScope.page.pageTotal <= 5}">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
				</c:when>
				<%--情况2：如果总页码>5--%>
				<c:when test="${requestScope.page.pageTotal > 5}">
					<c:choose>
						<%--当前页码为前面3个:1,2,3的情况，页码范围1-5--%>
						<c:when test="${requestScope.page.pageNo<=3}">
							<c:set var="begin" value="1"></c:set>
							<c:set var="end" value="5"></c:set>
						</c:when>
						<%--当前页码为后三个，总页码范围是：总页码-4 - 总页码--%>
						<c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal-2}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
							<c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
						</c:when>
						<%--其他页码范围：当前页码-2到当前页码+2--%>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
							<c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${i == requestScope.page.pageNo}">
					[${i}]
				</c:if>
				<c:if test="${i != requestScope.page.pageNo}">
					<a href="Client/BookServlet?action=page&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--五连页码输出的结束--%>
			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="Client/BookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="Client/BookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
			<script type="text/javascript">
				$(function (){
					//跳转到指定页码
					$("#searchPageBtn").click(function (){
						var pageNo = $("#pn_input").val();
						var pageTotal = ${requestScope.page.pageTotal};
						if (pageNo<1 || pageNo> pageTotal){
							alert("请输入正确的页码");
						}
						//javaScript提供一个location地址栏对象，属性href地址可读可写
						location.href = "${pageScope.basePath}Client/BookServlet?action=page&pageNo="+pageNo;
					});
				});
			</script>
		</div>
	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>