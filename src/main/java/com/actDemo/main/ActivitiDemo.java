package com.actDemo.main;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * @Author:LuHaoran
 * @Description:
 * @Date:Created in ${Time} ${DATA}
 * @Modified By:
 */
public class ActivitiDemo {
    public static void main(String[] args){
        ActivitiDemo activitiDemo = new ActivitiDemo();
        activitiDemo.actionSelect();
    }

    /**
    * @Author:LuHaoRan
    * @Params: * @param null
    * @Description:
    * @Date:17:29 18/3/8
    */
    public void actionSelect(){
        System.out.println("Option Select:");
        System.out.println("0:View Definitions List.");
        System.out.println("1:Deploy A Definition.");
        System.out.println("2:Load A Definition.");
        System.out.println("3:View User Definition.");
        System.out.println("4:Execute A Definition.");
        System.out.println("5:Exit.");
        int selectOption = 6 ;
        try{
            selectOption = new Scanner(System.in).nextInt();
        }catch (Exception e){
            System.out.println("Illegal character,please try again");
            actionSelect();
        }finally {

        }
        switch (selectOption){
            default:{
                break;
            }
            case 0:{
                System.out.println("The current operation:View Definitions List.");
                viewDefinitions();
                actionSelect();
                break;
            }
            case 1:{
                System.out.println("The current operation:Deploy A Definition");
                System.out.println("Please input fileName and processName , split with \",\"");
                String inputString = new Scanner(System.in).next().toString();
                String fileName = inputString.split(",")[0];
                String processName = inputString.split(",")[1];
                System.out.println(fileName + "," + processName);
                Deployment deployment = deployAction(fileName , processName);
                System.out.println(deployment);
                actionSelect();
                break;
            }
            case 2:{
                System.out.println("The current operation:Load A Definition.");
                System.out.println("Please input actName");
                String actName = new Scanner(System.in).next().toString();
                System.out.println(actName);
                startAction(actName);
                actionSelect();
                break;
            }
            case 3:{
                System.out.println("The current operation:View User Definition.");
                System.out.println("Please input userAss");
                String userAss = new Scanner(System.in).next().toString();
                System.out.println(userAss);
                viewAction(userAss);
                actionSelect();
                break;
            }
            case 4:{
                System.out.println("The current operation:Execute A Definition.");
                System.out.println("Please input taskID");
                String taskID = new Scanner((System.in)).next().toString();
                System.out.println(taskID);
                executeAction(taskID);
                actionSelect();
                break;
            }
            case 5:{
                System.out.println("Exit");
                break;
            }
        }
    }


    /**
    * @Author:LuHaoRan
    * @Params: * @param null
    * @Description:
    * @Date:10:02 18/3/9
    */
    public void viewDefinitions(){
        // 获得指定文件对象
        File fileDir = new File("F:\\IdeaWorkSpace\\SSM_Demo\\src\\main\\resources\\activity_workflow");
        // 获得该文件夹内的所有文件
        File[] array = fileDir.listFiles();
        for (File file : array){
            if(file.isFile()){
                System.out.println(file.getName());
            }
        }
    }

    /**
    * @Author:LuHaoRan
    * @Params: * @param null
    * @Description:
    * @Date:16:16 18/3/8
    */
    public Deployment deployAction(String fileName , String processName) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String pngUrl = "activity_workflow/" + fileName + ".png";
        String bpmnUrl = "activity_workflow/" + fileName + ".bpmn";
        Deployment deployment = processEngine
                .getRepositoryService()
                .createDeployment()
                .name(processName)
                .addClasspathResource(pngUrl)
                .addClasspathResource(bpmnUrl)
                .deploy();
        System.out.println("Definition Deployed");
        System.out.println("-------------------------------------------------------------------------------");
        return deployment ;
    }

    /**
    * @Author:LuHaoRan
    * @Params:
    * @Description:
    * @Date:16:15 18/3/8
    */

    public void startAction(String actName) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(actName);
        System.out.println("Definition Started");
        System.out.println("-------------------------------------------------------------------------------");
    }

    /**
    * @Author:LuHaoRan
    * @Params: * @param null
    * @Description:
    * @Date:16:16 18/3/8
    */
    public List<Task> viewAction(String userAss) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Task> taskList = processEngine
                .getTaskService()
                .createTaskQuery()
                .taskCandidateOrAssigned(userAss)
                .list();
        System.out.println("TaskList:" + taskList);
        System.out.println("-------------------------------------------------------------------------------");
        return taskList;
    }


    /**
    * @Author:LuHaoRan
    * @Params: * @param null
    * @Description:
    * @Date:16:16 18/3/8
    */
    public void executeAction(String taskID) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete(taskID);
        System.out.println(taskID + " task completed");
        System.out.println("-------------------------------------------------------------------------------");
    }
}
