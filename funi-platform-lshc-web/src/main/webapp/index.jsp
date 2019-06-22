<%@ taglib prefix="mvc" uri="http://framework.funi.com/mvc" %>
<%@ page contentType="MIME-Type; charset=utf-8" %>
<html>
    <head>
<meta charset="UTF-8"/>
<title>房屋普查系统</title>
            <%--
            <mvc:theme name="eic.v1"/>
            --%>
<link href="http://dtt.funi.com/fcy/style.css" rel="stylesheet" type="text/css"/>
<script src="http://dtt.funi.com/fcy/ext/bootstrap.js" charset="utf-8" language="JavaScript"></script>
<script src="http://dtt.funi.com/fcy/app.js" charset="utf-8" language="JavaScript"></script>
<script language="javascript">
    (function () {
    //上下文对象
    window.Context = {
    security: {
    authorities:[]
    }
    };
    if("${name}"=="root" || "${name}"=="系统内置超级管理员") {//root
    Context.security.authorities.push("AUTH_CCS_CHECK_HOME_PAGE");
    } else {
    <c:forEach items="${authorities}" var="a">
        Context.security.authorities.push('${a}');
    </c:forEach>
    }
    })();
</script>

<style type="text/css">
    .logo {
    background-repeat: no-repeat;
    background-position: 15px 5px;
    width: 565px;
    height: 51px;
    background-image: url(images/logo.gif);
    }
</style>
        <%--<style>
            table,table tr th, table tr td { border:1px solid #0094ff; }
            table { width: 200px; min-height: 25px; line-height: 25px; text-align: center; border-collapse: collapse;}
        </style>--%>
</head>
<body>
</body>
</html>

