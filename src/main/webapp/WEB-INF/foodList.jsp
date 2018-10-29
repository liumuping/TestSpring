<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
			<img border="0" width="13" height="13" src="../detail/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<span>搜索</span>
			<input type="hidden" name="method" value="search">
			<input type="text" id="keyword" placeholder="请输入菜品名称">
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:forEach items="${sessionScope.foods}" var="food">
			<tr class="TableDetail1">
				<td align="center">${food.id}&nbsp;</td>
				<td align="center">${food.name}&nbsp;</td>
				<td align="center">${food.foodset}&nbsp;</td>
				<td align="center">${food.price}&nbsp;</td>
				<td align="center">${food.vipprice}&nbsp;</td>
				<td>
					<a href="/GetFoodServlet?foodId=${food.id}" class="FunctionButton">更新</a>
					<a href="/DeleteFoodServlet?foodId=${food.id}&pageNum=${sessionScope.pageNumber}" onClick="return delConfirm();"class="FunctionButton">删除</a>
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
			<a href="/FoodServlet?pageNumber=${i}" style="text-decoration:none">${i}</a>
		</div>
	</c:forEach>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="/InsertFood">添加</a></div>
    </div> 
</div>
</body>
</html>
