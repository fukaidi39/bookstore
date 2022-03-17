<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2022/3/17
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <%--大于首页才显示--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--五连页码输出的结束--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
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
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
            });
        });
    </script>
</div>
