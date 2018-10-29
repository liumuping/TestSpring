<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>无线点餐平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="../detail/style/js/jquery.js"></script>
    <script type="text/javascript" src="../detail/style/js/page_common.js"></script>
    <link href="../detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../detail/style/css/index_1.css"/>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="../detail/style/images/title_arrow.gif"/> 餐厅订单列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>订单编号</td>
            <td>餐桌名</td>
            <td>下单日期</td>
            <td>总金额</td>
            <td>会员总金额</td>
            <td>联系人</td>
            <td>电话</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:forEach items="${sessionScope.orders}" var="order">
            <tr height="60" class="TableDetail1">
                <td align="center">${order.id}</td>
                <td align="center">${order.deskname}</td>
                <td align="center">${order.orderdate}</td>
                <td align="center">${order.price}</td>
                <td align="center">${order.vipprice}</td>
                <td align="center">${order.custom}</td>
                <td align="center">${order.tel}</td>
                <td align="center">
                    <c:choose>
                        <c:when test="${order.state eq 0}">
                            <span style="color: red">未结账</span>
                        </c:when>
                        <c:otherwise>
                            已结账
                        </c:otherwise>
                    </c:choose>
                </td>
                <td align="center">
                    <a href="/GetOrderFoodServlet?orderId=${order.id}" class="FunctionButton">点菜详细</a>
                    <c:choose>
                        <c:when test="${order.state eq 0}">
                            <a href="/FinishOrderServlet?orderId=${order.id}&deskname=${order.deskname}&pageNum=${sessionScope.pageNumber}" style="color: red" class="FunctionButton">结账</a>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <div class="FunctionButton">
        当前为的第<span>	${sessionScope.pageNumber}</span>页
    </div>
    <div class="FunctionButton">页码</div>
    <c:forEach begin="1" end="${total}" var="i">
        <div class="FunctionButton">
            <a href="/GetOrderServlet?pageNumber=${i}" style="text-decoration:none">${i}</a>
        </div>
    </c:forEach>
    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
    </div>
</div>
</body>
</html>
