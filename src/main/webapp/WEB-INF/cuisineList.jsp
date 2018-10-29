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
				<img border="0" width="13" height="13"
					src="../detail/style/images/title_arrow.gif" /> 菜系列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>
	<!-- 过滤条件 -->
	<div id="QueryArea">
		<span>搜索</span>
		<input type="hidden" name="method" value="search">
		<input type="text" id="keyword" placeholder="请输入菜系名称">
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>菜系编号</td>
					<td>菜系名称</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach items="${sessionScope.foodSets}" var="foodset">
					<tr class="TableDetail1">
						<td align="center">${foodset.foodsetId}</td>
						<td align="center">${foodset.foodsetname}</td>
						<td align="center">
							<a href="/UpdateSetServlet?oldset=${foodset.foodsetname}" class="FunctionButton">更新</a>
							<a href="/DeleteFoodSetServlet?foodsetId=${foodset.foodsetId}&pageNum=${sessionScope.pageNumber}" class="FunctionButton" onClick="return delConfirm();">删除</a>
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
				<a href="/FoodSetServlet?pageNumber=${i}" style="text-decoration:none">${i}</a>
			</div>
		</c:forEach>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
			<div class="FunctionButton">
				<a href="../detail/saveCuisine.html">添加</a>
			</div>
		</div>
	</div>
</body>
</html>
