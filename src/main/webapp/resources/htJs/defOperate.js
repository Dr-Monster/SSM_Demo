function freshModelList(modelData){
    var modelList = JSON.parse(modelData);
    var tableStr = "";

    for (var i = 0; i < modelList.length; i++) {
        var mID = modelList[i].mID;
        tableStr +=
            '<tr>' +
            '<td ' + 'id=mName_' + mID + ' >' + modelList[i].mName + '</td>' +
            '<td ' + 'id=mStatus_' + mID + ' >' + '未部署' + '</td>' +
            '<td ' + 'id=mVersion_' + mID + '>' + modelList[i].mVersion + '</td>' +
            '<td ' + 'id=mKey_' + mID + ' >' + modelList[i].mKey + '</td>' +
            '<td>' +
            '<button ' + 'id=mDeploy_' + mID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
            '部署' +
            '</button>' +
            // '<button ' + 'id=mDelete_' + mID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
            // '删除' +
            // '</button>' +
            '<button ' + 'id=mView_' + mID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
            '查看' +
            '</button>' +
            '</td>' +
            '</tr>'
    }
    $("#actFileList").html(tableStr);
}

function freshDefinitionList(definitionData){
    var deStr = "" ;
    var definitionList = JSON.parse(definitionData);
    for (var i = 0 ; i < definitionList.length ; i++ ){
        var deID = definitionList[i].deID;
        deStr +=
            '<tr>' +
            '<td ' + 'id=deName_' + deID + ' >' + definitionList[i].deName + '</td>' +
            '<td ' + 'id=deStatus_' + deID + ' >' + '已部署' + '</td>' +
            '<td ' + 'id=deVersion_' + deID + '>' + definitionList[i].deVersion + '</td>' +
            '<td ' + 'id=deKey_' + deID + ' >' + definitionList[i].deKey + '</td>' +
            '<td>' +
            '<p hidden="hidden" ' + 'id=pngUrl_' + deID + '>' + definitionList[i].pngPath + '</p>' +
            '<p hidden="hidden" ' +  'id=bpmnUrl_'+ deID + '>' + definitionList[i].bpmnPath + '</p>' +
//            '<button ' + 'id=deDeploy_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
//            '部署' +
//            '</button>' +
            '<button ' + 'id=deStart_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
            '启动' +
            '</button>' +
            '<button ' + 'id=deDelete_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
            '删除' +
            '</button>' +
            '<button ' + 'id=deView_' + deID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
            '查看' +
            '</button>' +
            '</td>' +
            '</tr>'
    }
    $("#definitionList").html(deStr);
}


function freshTaskList(taskData) {
    var taskList = JSON.parse(taskData);
    var taskStr = "" ;
    for (var i = 0 ; i < taskList.length ; i++){
        var taskID = taskList[i];
        taskStr +=
            '<tr>' +
            '<td ' + 'id=taskID_' + taskID + ' >' + taskID + '</td>' +
            '<td>' +
            '<button ' + 'id=tButton_' + taskID + ' type="button" class="btn btn-default" style="padding-left: 20px;padding-right: 20px;margin-left: 20px;margin-right: 20px;">' +
            '执行' +
            '</button>' +
            '</td>' +
            '</tr>'
    }
    $("#taskList").html(taskStr);
}