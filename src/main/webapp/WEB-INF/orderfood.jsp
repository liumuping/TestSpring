<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>无线点餐平台</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="../detail/style/js/jquery.js"></script>
    <script type="text/javascript" src="../detail/style/js/page_common.js"></script>
    <script>
        $(function () {
            $("#MainArea #first tbody tr td .add").click(function () {
                // 创建一个行tr
                var foodid = $(this).next().val();
                var foodname = $(this).next().next().val();
                var foodset = $(this).next().next().next().val();
                var price = $(this).next().next().next().next().val();
                var vipprice = $(this).next().next().next().next().next().val();
                var tr = $("<tr class='TableDetail1'></tr>");
                // var foodid = $("#foodid");
                // var foodname = $("#foodname");
                // var foodset = $("#foodset");
                // var price = $("#price");
                // var vipprice = $("#vipprice");
                tr.append("<td align='center'>" + foodid + "</td>")
                    .append("<td align='center'>" + foodname + "</td>")
                    .append("<td align='center'>" + foodset + "</td>")
                    .append("<td align='center'>" + price + "</td>")
                    .append("<td align='center'>" + vipprice + "</td>")
                    .append("<td><button onclick='del($(this))' class='del'>删除</button></td>")
                    .append("<input type='hidden' name='foodid' value="+foodid+">")
                    .appendTo("#Table");
            })
        })

        function del(val) {
            $(val).parent().parent().remove();
        }
    </script>
    <link href="../detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../detail/style/css/index_1.css"/>
    <script type="text/javascript">
        function openWin() {
            window.open('common_page_list.html');
            this.close();
        }
    </script>
</head>
<body>


<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="../detail/style/images/title_arrow.gif"/> 预定
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <div class="ItemBlock_Title">
        <img width="4" height="7" border="0" src="../detail/style/images/item_point.gif"> 菜品信息&nbsp;
    </div>
    <div style="height: 150px;overflow: auto;background-color: white" >
        <table id="first" class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
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
            <c:forEach items="${sessionScope.Allfoods}" var="food">
                <tr class="TableDetail1">
                    <td align="center">${food.id}&nbsp;</td>
                    <td align="center">${food.name}&nbsp;</td>
                    <td align="center">${food.foodset}&nbsp;</td>
                    <td align="center">${food.price}&nbsp;</td>
                    <td align="center">${food.vipprice}&nbsp;</td>
                    <td>
                        <button id="add" class="FunctionButton add" style="height: 20px">添加</button>
                        <input type="hidden" name="foodid" value="${food.id}"/>
                        <input type="hidden" value="${food.name}"/>
                        <input type="hidden" value="${food.foodset}"/>
                        <input type="hidden" value="${food.price}"/>
                        <input type="hidden" value="${food.vipprice}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
<form action="/InsertOrderServlet" method="post">
    <div id="MainArea2">
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="../detail/style/images/item_point.gif"> 已选菜品&nbsp;
        </div>
        <div style="height: 250px;overflow: auto;background-color: white">
            <table id="second" class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
                <thead>
                <tr align="center" valign="middle" id="TableTitles">
                    <td>菜编号</td>
                    <td>菜名</td>
                    <td>所属菜系</td>
                    <td>价格</td>
                    <td>会员价格</td>
                    <td>操作</td>
                </tr>
                </thead>
                <!--显示数据列表 -->
                <tbody id="Table">
                </tbody>
            </table>
        </div>

    </div>
    <div id="MainArea3">
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="../detail/style/images/item_point.gif"> 其他信息&nbsp;
        </div>
        <div class="ItemBlock">
            <div class="ItemBlock2">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td>联系人</td>
                        <td><input type="text" name="custom" class="InputStyle"/> *</td>
                    </tr>
                    <tr>
                        <td>电话</td>
                        <td><input type="text" name="tel" class="InputStyle"/> *</td>
                        <input type="hidden" name="desk" value="${requestScope.desk}"/>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <br/>
    <div id="InputDetailBar">

        <input type="submit" value="添加" class="FunctionButtonInput">

        <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
    </div>
</form>


</body>
</html>
