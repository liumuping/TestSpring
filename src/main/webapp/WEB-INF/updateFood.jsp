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
<script type="text/javascript">
    $(document).ready(function () {
        var key = $("#foodset").val();
        //根据值让option选中
        $("#foodsetselect option[value='" + key + "']").attr("selected", "selected");
    });
</script>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">


            <img border="0" width="13" height="13" src="../detail/style/images/title_arrow.gif"/> 更新新菜品


        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="/UpdateFoodServlet" method="post">
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="../detail/style/images/item_point.gif"> 菜品信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">菜系</td>
                            <td>
                                <select name="foodSets" style="width:80px">
                                    <c:forEach items="${requestScope.foodSets}" var="foodset">
                                        <option value="${foodset.foodsetId}">${foodset.foodsetname}</option>
                                    </c:forEach>
                                </select>*
                                <input type="hidden" id="foodset" value="${requestScope.food.foodsetId}"/>
                                <input type="hidden" name="method" value="2"/>
                                <input type="hidden" name="foodId" value="${requestScope.food.id}"/></td>
                        </tr>
                        <tr>
                            <td width="80px">菜名</td>
                            <td><input type="text" name="foodname" class="InputStyle"
                                       value="${requestScope.food.name}"/> *
                            </td>
                        </tr>
                        <tr>
                            <td>价格</td>
                            <td><input type="text" name="price" class="InputStyle" value="${requestScope.food.price}"/>
                                *
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>


        <!-- 表单操作 -->
        <div id="InputDetailBar">


            <input type="submit" value="修改" class="FunctionButtonInput">


            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>
</div>
</body>
</html>
