<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <script src="/resources/jquery-3.3.1.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading"></div>
    <div class="panel-body">
        <p>
        </p>
    </div>
    <ul class="list-group">
        <li class="list-group-item">
            <button type="button" class="btn btn-primary" id="accList">列表详情</button>
        </li>
        <li class="list-group-item">
            <!-- 按钮触发模态框 -->
            <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                创建定义
            </button>
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                        </div>
                        <div class="modal-body">
                            <input type="text" id="processName" class="form-control" placeholder="定义名称">
                            <br/>
                            <input type="text" id="processKey" class="form-control" placeholder="定义关键字">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-primary" id="upBtn">
                                提交更改
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
        </li>
    </ul>
</div>
</body>
<script>
    
    $("#accList").click(
        function () {
            window.location.href = "/Main/Index";
        }
    );
    
    $("#upBtn").click(
        function () {
            var pName = $("#processName").val();
            var pKey = $("#processKey").val();
            if ((pName == null || pName.length == 0) || (pKey == null || pKey.length == 0)){
                alert("");
            }else{
                console.log(pName , pKey);
                var url = "/user/create?name=" + pName + "&key=" + pKey ;
                window.location.href = url
            }
        }
    );
</script>

</html>
