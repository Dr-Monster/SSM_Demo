function selectActByIDAndType(ID , checkType){
    var urlTag = "" ;
    if ("deploy" == checkType){
        urlTag = "deployAction";
    }else if ("start" == checkType){
        urlTag = "startAction";
    }else if ("delete" == checkType){
        urlTag = "deleteDefinition";
    }else{
        urlTag = "downLoadDefinition";
    }

    $.ajax(
        {
            url:"/Main" + urlTag + "?",
            type:"post",
            async:true,
            dataType:"json",
            success:function () {

            },
        }
    );
}