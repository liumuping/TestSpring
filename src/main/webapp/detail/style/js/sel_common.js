// 模糊查询
$().ready(function(){
    $("#keyword").keyup(
        function(){
            //获取索引大于0的所有行，所有的行
            $("table tr:gt(0)").hide();
            var $d = $("table tr:gt(0)").
            filter(":contains('"+$.trim($("#keyword").val())+"')");
            $d.show();
        }
    )
})