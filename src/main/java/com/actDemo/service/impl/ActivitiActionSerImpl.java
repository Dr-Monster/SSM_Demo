package com.actDemo.service.impl;

import com.actDemo.entity.TempModeler;
import com.actDemo.entity.TempTask;
import com.actDemo.service.ActivitiActionSer;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:LuHaoran
 * @Description:
 * @Date:Created in ${Time} ${DATA}
 * @Modified By:
 */
@Service
public class ActivitiActionSerImpl implements ActivitiActionSer {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Override
    public Set<String> viewDefinitions() {
        Set<String> fileNameList = new HashSet<String>();
        // 获得指定文件对象
        File fileDir = new File("F:\\IdeaWorkSpace\\SSM_Demo\\src\\main\\resources\\activity_workflow");
        // 获得该文件夹内的所有文件
        File[] array = fileDir.listFiles();
        List<String> pngFileList = new ArrayList<String>();
        List<String> bpmnFileList = new ArrayList<String>();
        for (File file : array){
            if(file.isFile()){
                //fileNameList.add(file.getName());
                if(file.getName().contains(".png")){
                    pngFileList.add(file.getName().split(".png")[0]);
                }
                if(file.getName().contains(".bpmn")){
                    bpmnFileList.add(file.getName().split(".bpmn")[0]);
                }
            }
        }
        for(int i = 0 ; i < pngFileList.size() ; i++){
            if (bpmnFileList.contains(pngFileList.get(i))){
                fileNameList.add(pngFileList.get(i));
            }
        }
        return fileNameList;
    }

    @Override
    public Deployment deployAction(String fileName, String processName) {
        String pngUrl = "activity_workflow/" + fileName + ".png";
        String bpmnUrl = "activity_workflow/" + fileName + ".bpmn";
        Deployment deployment = processEngine
                .getRepositoryService()
                .createDeployment()
                .name(processName)
                .addClasspathResource(pngUrl)
                .addClasspathResource(bpmnUrl)
                .deploy();
        return deployment ;
    }

    @Override
    public ProcessInstance startAction(String deID) {
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(deID);
        return processInstance ;
    }

    @Override
    public List<TempTask> viewAction() {
        List<Task> taskList = processEngine
                .getTaskService()
                .createTaskQuery()
                .list();
        List<TempTask> tempTaskList = new ArrayList<TempTask>();
        for(Task task : taskList){
            TempTask tempTask = new TempTask();
            tempTask.settID(task.getId());
            tempTask.settName(task.getName());
            tempTask.setDeployID(task.getProcessDefinitionId());
            tempTask.setDeployKey(task.getTaskDefinitionKey());
            tempTaskList.add(tempTask);
        }
        return tempTaskList;
    }

    @Override
    public String executeAction(String taskID) {
        processEngine.getTaskService()
                .complete(taskID);
        return "true";
    }
}
