<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//页面加载之后
		$(function (){
			//在事件function函数中this对象时当前正在响应事件的dom对象
			//给删除的标签绑定单击事件
			$("a.deleteClass").click(function (){
				//return false即阻止元素的默认行为，不提交请求。返回true继续当前元素事件
				return confirm("你确定要删除["+$(this).parent().parent().find("td:first").text()+"]图书嘛?");
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%--静态包含manager菜单管理模块--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/BookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/BookServlet?action=deleteBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
			<%--大于首页才显示--%>
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="manager/BookServlet?action=page&pageNo=1">首页</a>
				<a href="manager/BookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
						<a href="manager/BookServlet?action=page&pageNo=${i}">${i}</a>
					</c:if>
				</c:forEach>
			<%--五连页码输出的结束--%>
			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="manager/BookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="manager/BookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
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
							location.href = "${pageScope.basePath}manager/BookServlet?action=page&pageNo="+pageNo;
						});
					});
				</script>
		</div>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>