<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 18/3/9
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/resources/jquery-3.3.1.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/htJs/defOperate.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>


<div class="panel panel-default">
    <div class="panel-heading"></div>
    <div class="panel-body">
        <p>ActivitiDemo</p>
    </div>
    <ul class="list-group">
        <li class="list-group-item">
            <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#activitiFiles">
                流程查询
            </button>
            <div id="activitiFiles" class="collapse in">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th class="col-md-3">定义名</th>
                            <th class="col-md-3">流程状态</th>
                            <th class="col-md-2">流程名称</th>
                            <th class="col-md-4">操作</th>
                        </tr>
                        </thead>
                        <tbody id="actFileList">
                        </tbody>
                    </table>
                </div>
            </div>
        </li>
        <li class="list-group-item">
            <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#viewTask">
                任务查询
            </button>
            <div id="viewTask" class="collapse in">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th class="col-md-3">定义名</th>
                            <th class="col-md-3">流程状态</th>
                            <th class="col-md-2">流程名称</th>
                            <th class="col-md-4">操作</th>
                        </tr>
                        </thead>
                        <tbody id="taskList">
                        </tbody>
                    </table>
                </div>
            </div>
        </li>
        <li class="list-group-item">
            <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#executeTask">
                执行任务
            </button>
            <div id="executeTask" class="collapse in">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th class="col-md-3">定义名</th>
                            <th class="col-md-3">流程状态</th>
                            <th class="col-md-2">流程名称</th>
                            <th class="col-md-4">操作</th>
                        </tr>
                        </thead>
                        <tbody id="exeTaskList">
                        </tbody>
                    </table>
                </div>
            </div>
        </li>
    </ul>
</div>



</body>
<script>
    var fileList = JSON.parse('${fileList}');
    console.log(fileList);
    var definitionList = JSON.parse('${tempDefiList}');
    console.log(definitionList);
    var  tableStr = "" ;
    for (var i = 0; i < fileList.length; i++) {
        for (var j = 0 ; j < definitionList.length ; j++){
            var checkName = definitionList[j].pngPath.split("/")[1].split(".png")[0];
            if (fileList[i] == checkName){
                var deID = definitionList[j].deID;
                tableStr += '<tr>' +
                    '<td ' + 'id=fileName_' + deID + ' >' + fileList[i] + '</td>' +
                    '<td ' + 'id=status_' + deID + ' >' + '未部署' + '</td>' +
                    '<td ' + 'id=deKey_' + deID + ' >' + definitionList[j].deKey + '</td>' +
                    '<td>' +
                    '<button ' + 'id=deploy_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
                    '部署' +
                    '</button>' +
                    '<button ' + 'id=start_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
                    '启动' +
                    '</button>' +
                    '<button ' + 'id=delete_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
                    '删除' +
                    '</button>' +
                    '<button ' + 'id=view_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
                    '查看' +
                    '</button>' +
                    '</td>' +
                    '</tr>'
            } else{
                continue;
            }
        }
    }
    $("#actFileList").html(tableStr);
</script>
<script>
    $("#activitiFiles").find("button").click(
        function () {
            console.log("$(this):");
            console.log($(this));
            console.log("this:");
            console.log(this);
            var checkID = this.id.split("_")[1];
            var checkType = this.id.split("_")[0];
            var checkName = $("#activitiFiles").find("#fileName_" + checkID).text();
            var checkKey = $("#activitiFiles").find("#deKey_" + checkID).text();
            var checkStatus = $("#activitiFiles").find("#status_" + checkID).text();
            console.log(checkName);
            console.log(checkKey);
            console.log(checkStatus);
            console.log(checkType);
            if ("deploy" == checkType){
                $.ajax(
                    {
                        url:"/Main/deployAction?fileName=" + checkName + "&processName=" + checkKey ,
                        type:"post",
                        async:true,
                        dataType:"json",
                        success:function (data) {
                            console.log(data);
                            alert("部署成功");
                            $("#activitiFiles").find("#status_" + checkID).text("已部署");
                        },
                        error:function () {
                            alert("部署失败");
                        }
                    }
                );
            }else if ("start" == checkType){
                if (checkStatus == "已部署"){
                    $.ajax(
                        {
                            url:"/Main/startAction?actName=" + checkName,
                            type:"post",
                            async:true,
                            dataType:"json",
                            success:function (data) {
                                console.log(JSON.parse(data));
                            },
                        }
                    );
                }else {
//                    后面会加上Bootstrap 模态框（Modal）插件
                    alert("请先部署定义!");
                }
            }else if ("delete" == checkType){
                $.ajax(
                    {
                        url:"/Main/deleteDefinition?deployID" + checkID ,
                        type:"post",
                        async:true,
                        dataType:"json",
                        success:function (data) {
                            console.log(JSON.parse(data));
                        },
                    }
                );
            }else{

            }
        }
    );
</script>
</html>
