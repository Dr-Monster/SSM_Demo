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
                模型列表
            </button>
            <div id="activitiFiles" class="collapse in">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th class="col-md-3">定义名</th>
                            <th class="col-md-2">流程状态</th>
                            <th class="col-md-1">版本</th>
                            <th class="col-md-2">流程关键字</th>
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
            <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#definitions">
                定义列表
            </button>
            <div id="definitions" class="collapse in">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th class="col-md-3">定义名</th>
                            <th class="col-md-2">流程状态</th>
                            <th class="col-md-1">版本</th>
                            <th class="col-md-2">流程名称</th>
                            <th class="col-md-4">操作</th>
                        </tr>
                        </thead>
                        <tbody id="definitionList">
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
                            <th class="col-md-4">ID</th>
                            <th class="col-md-3">操作</th>
                        </tr>
                        </thead>
                        <tbody id="taskList">
                        </tbody>
                    </table>
                </div>
            </div>
        </li>
    </ul>
</div>


</body>

<%--定义列表--%>
<script>
    freshModelList('${modelerList}');
    freshDefinitionList('${definitionList}');
    freshTaskList('${taskList}');
</script>
<%--定义列表操作的逻辑功能--%>
<script>

    //第二次加载的时候需要点击两次按钮,暂时不知道为什么,是不是这个里面的ajax也要重新去调用一下这个方法?
    $(document).on("click", $("#actFileList").find("button"), function(){
        $(this).find("#actFileList").find("button").click(
            function () {
                console.log(this);
                var checkID = this.id.split("_")[1];
                var checkType = this.id.split("_")[0];
                var checkKey = $("#actFileList").find("#mKey_" + checkID).text();
                if ("mDeploy" == checkType) {
                    $.ajax(
                        {
                            url: "/Main/deployModeler?mDeID=" + checkID ,
                            type: "post",
                            async: true,
                            dataType: "json",
                            success: function () {
                                alert("部署成功");
                                $.ajax({
                                    url:"/Main/definitionList",
                                    type:"post",
                                    async:true,
                                    dataType:"json",
                                    success:function (data) {
                                        $("#definitionList").html("");
                                        freshDefinitionList(JSON.stringify(data));
                                    }
                                });

                                $.ajax({
                                    url:"/Main/modelerList",
                                    type:"post",
                                    async:true,
                                    dataType:"json",
                                    success:function (data) {
                                        $("#actFileList").html("");
                                        freshModelList(JSON.stringify(data));
                                    }
                                });
                            },
                        }
                    );
                } else if ("mDelete" == checkType) {
                    $.ajax(
                        {
                            url:"/Main/deleteDefinition?deployID=" + checkID ,
                            type:"post",
                            async:true,
                            dataType:"json",
                            success:function () {
                                $.ajax(
                                    {
                                        url:"/Main/definitionList",
                                        type:"post",
                                        async:true,
                                        dataType:"json",
                                        success:function (data) {
                                            freshDefinitionList(data)
                                        }
                                    }
                                );
                            },
                        }
                    );
                } else {

                }
            }
        );
    });


    $("#actFileList").find("button").on('click',
        function () {
            var checkID = this.id.split("_")[1];
            var checkType = this.id.split("_")[0];
            var checkKey = $("#actFileList").find("#mKey_" + checkID).text();
            if ("mDeploy" == checkType) {
                $.ajax(
                    {
                        url: "/Main/deployModeler?mDeID=" + checkID ,
                        type: "post",
                        async: true,
                        dataType: "json",
                        success: function () {
                            alert("部署成功");
                            $.ajax({
                                url:"/Main/definitionList",
                                type:"post",
                                async:true,
                                dataType:"json",
                                success:function (data) {
                                    $("#definitionList").html("");
                                    freshDefinitionList(JSON.stringify(data));
                                }
                            });

                            $.ajax({
                                url:"/Main/modelerList",
                                type:"post",
                                async:true,
                                dataType:"json",
                                success:function (data) {
                                    $("#actFileList").html("");
                                    freshModelList(JSON.stringify(data));
                                }
                            });
                        },
                    }
                );
            } else if ("mDelete" == checkType) {
                $.ajax(
                    {
                        url:"/Main/deleteDefinition?deployID=" + checkID ,
                        type:"post",
                        async:true,
                        dataType:"json",
                        success:function () {
                            $.ajax(
                                {
                                    url:"/Main/definitionList",
                                    type:"post",
                                    async:true,
                                    dataType:"json",
                                    success:function (data) {
                                        freshDefinitionList(data)
                                    }
                                }
                            );
                        },
                    }
                );
            } else {

            }
        }
    );
</script>
<%--模型列表操作的逻辑功能--%>
<script>

    $(document).on("click", $("#definitionList").find("button"), function(){
        $(this).find("#definitionList").find("button").on('click' , function () {
           console.log(this);
        });
    });

    $("#definitionList").find("button").on('click',
        function () {
            var checkID = this.id.split("_")[1];
            var checkType = this.id.split("_")[0];
            var checkName = $("#activitiFiles").find("#mName_" + checkID).text();
            var checkKey = $("#activitiFiles").find("#mKey_" + checkID).text();
            var checkStatus = $("#activitiFiles").find("#mVtatus_" + checkID).text();
            if ("deStart" == checkType) {
                $.ajax(
                    {
                        url:"/Main/deployModeler?mDeID=" + checkID ,
                        type:"post",
                        async:true,
                        dataType:"json",
                        success:function () {
                            $.ajax(
                                {
                                    url:"/Main/modelerList",
                                    type:"post",
                                    async:true,
                                    dataType:"json",
                                    success:function (data) {
                                        alert("启动成功");
                                        freshModelList(JSON.stringify(data))
                                    },
                                    error:function () {

                                    },
                                }
                            );
                        },
                    }
                );
            } else if ("deDelete" == checkType) {
                $.ajax(
                    {
                        url: "/Main/deleteDefinition?deployID=" + checkID,
                        type: "post",
                        async: true,
                        dataType: "json",
                        success: function () {
                            alert("删除成功");
                            $.ajax(
                                {
                                    url:"/Main/modelerList",
                                    type:"post",
                                    async:true,
                                    dataType:"json",
                                    success:function (data) {
                                        freshModelList(JSON.stringify(data));
                                    },
                                    error:function () {

                                    },
                                }
                            );
                        }
                    }
                );
            } else {

            }
        }
    )
</script>
<%--任务列表操作的逻辑功能--%>
<script>
    $("#taskList").find("button").on('click',
        function () {
            var taskID = this.id.split("_")[1];
            console.log(taskID);
            $.ajax(
                {
                    url: "/Main/executeAction?taskID=" + taskID,
                    type: "post",
                    async: true,
                    dataType: "json",
                    success: function (data) {

                    },
                    error: function () {
                    }
                }
            );
        }
    );
</script>
</html>
