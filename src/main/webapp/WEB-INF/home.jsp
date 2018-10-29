<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无线点餐后台管理</title>
</head>
	<frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="0">
		<frame src="WebServlet?name=top" scrolling="no" noresize />
		<frameset cols="178px,*">
			<frame noresize src="Web?name=left" scrolling="yes" />
			<frame noresize name="right" src="../detail/right.html" scrolling="yes" />
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="../detail/bottom.html" />
	</frameset>
</html>