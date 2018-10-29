<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../detail/style/js/jquery.js"></script>
<script type="text/javascript" src="../detail/style/js/page_common.js"></script>
<script type="text/javascript" src="../detail/style/js/sel_common.js"></script>
	<link href="../detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../detail/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="../detail/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<span>搜索</span>
		<input type="hidden" name="method" value="search">
		<input type="text" id="keyword" placeholder="请输入餐桌名称">
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>容纳人数</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:forEach items="${sessionScope.desks}" var="desk">
			<tr class="TableDetail1">
				<td align="center">${desk.id}&nbsp;</td>
				<td align="center"> ${desk.name}&nbsp;</td>
				<td align="center">${desk.content}&nbsp;</td>
				<td align="center">
					<c:choose>
						<c:when test="${desk.state eq 0}">
							空闲
						</c:when>
						<c:otherwise>
							预定
						</c:otherwise>
					</c:choose>
				</td>
				<td align="center">
					<c:choose>
						<c:when test="${desk.state eq 0}">
							<a href="/InsertOrderServlet?deskId=${desk.id}&pageNum=${sessionScope.pageNumber}" class="FunctionButton">预定</a>
							<a href="/UpdateDeskServlet?olddeskname=${desk.name}&oldcontent=${desk.content}&pageNum=${sessionScope.pageNumber}" class="FunctionButton">更新</a>
							<a href="/DeleteDesk?deskId=${desk.id}&pageNum=${sessionScope.pageNumber}" onClick="return delConfirm();"class="FunctionButton">删除</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
			<%--<tr class="TableDetail1">--%>
				<%--<td align="center">1&nbsp;</td>--%>
				<%--<td align="center"> 纽约&nbsp;</td>--%>
				<%--<td align="center">预定</td>--%>
				<%--<td align="center">2014-12-08 23:31:12</td>--%>
				<%--<td>--%>
					<%--<a href="/wirelessplatform/board.html?method=update&id=1&isBook=0" class="FunctionButton">退桌</a>				--%>
					<%--<a href="/wirelessplatform/board.html?method=delete&id=1" onClick="return delConfirm();"class="FunctionButton">删除</a>				--%>
				<%--</td>--%>
			<%--</tr>--%>
        <%----%>
			<%--<tr class="TableDetail1">--%>
				<%--<td align="center">2&nbsp;</td>--%>
				<%--<td align="center"> 巴黎&nbsp;</td>--%>
				<%--<td align="center">空闲</td>--%>
				<%--<td align="center"></td>--%>
				<%--<td>--%>
					<%--<a href="/wirelessplatform/board.html?method=update&id=2&isBook=1" class="FunctionButton">预定</a>				--%>
					<%--<a href="/wirelessplatform/board.html?method=delete&id=2" onClick="return delConfirm();"class="FunctionButton">删除</a>				--%>
				<%--</td>--%>
			<%--</tr>--%>
        <%----%>
			<%--<tr class="TableDetail1">--%>
				<%--<td align="center">3&nbsp;</td>--%>
				<%--<td align="center"> 丹麦&nbsp;</td>--%>
				<%--<td align="center">空闲</td>--%>
				<%--<td align="center"></td>--%>
				<%--<td>--%>
					<%--<a href="/wirelessplatform/board.html?method=update&id=3&isBook=1" class="FunctionButton">预定</a>				--%>
					<%--<a href="/wirelessplatform/board.html?method=delete&id=3" onClick="return delConfirm();"class="FunctionButton">删除</a>				--%>
				<%--</td>--%>
			<%--</tr>--%>
        <%----%>
			<%--<tr class="TableDetail1">--%>
				<%--<td align="center">5&nbsp;</td>--%>
				<%--<td align="center"> 伦敦&nbsp;</td>--%>
				<%--<td align="center">空闲</td>--%>
				<%--<td align="center"></td>--%>
				<%--<td>--%>
					<%--<a href="/wirelessplatform/board.html?method=update&id=5&isBook=1" class="FunctionButton">预定</a>				--%>
					<%--<a href="/wirelessplatform/board.html?method=delete&id=5" onClick="return delConfirm();"class="FunctionButton">删除</a>				--%>
				<%--</td>--%>
			<%--</tr>--%>
        
        </tbody>
    </table>
	<div class="FunctionButton">
		当前为的第<span>	${sessionScope.pageNumber}</span>页
	</div>
	<div class="FunctionButton">页码</div>
	<c:forEach begin="1" end="${total}" var="i">
		<div class="FunctionButton">
			<a href="/deskServlet?pageNumber=${i}" style="text-decoration:none">${i}</a>
		</div>
	</c:forEach>
	<!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="../detail/saveBoard.html">添加</a></div>
	</div>
</div>
</body>
</html>
