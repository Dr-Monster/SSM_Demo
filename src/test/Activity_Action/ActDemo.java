package Activity_Action;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 18/3/1.
 */
public class ActDemo {


    @Test
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



    @Test
    public void deployAction() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String pngUrl = "activity_workflow/ActivityTest.png" ;
        String bpmnUrl = "activity_workflow/ActivityTest.bpmn";
        String processName = "ActDemo1" ;
        Deployment deployment = processEngine
                .getRepositoryService()
                .createDeployment()
                .name(processName)
                .addClasspathResource(pngUrl)
                .addClasspathResource(bpmnUrl)
                .deploy();
        System.out.println(deployment);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void startAction() {
        //act_re_procdef 表下的key字段
        String deName = "ActDemo1" ;
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(deName);
        System.out.println(processInstance);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void viewAction() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String userAss = "Person_One";
        List<Task> taskList = processEngine
                .getTaskService()
                .createTaskQuery()
                .taskCandidateOrAssigned(userAss)
                .list();
        System.out.println(taskList);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void executeAction() {
        String taskID = "2504" ;
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete(taskID);
        System.out.println("-------------------------------------------------------------------------------");
    }


    @Test
    public void queryDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> processDefinitionList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().desc()
                .list();
        for(ProcessDefinition pd : processDefinitionList){
            System.out.println(pd.toString());
            System.out.println(pd.getDeploymentId());
            System.out.println(pd.getDiagramResourceName());
            System.out.println(pd.getId());
            System.out.println(pd.getKey());
            System.out.println(pd.getResourceName());
            System.out.println("-----------------------------------------------------------");
        }
    }

    @Test
    public void deletlDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String deloyID = "" ;
        processEngine.getRepositoryService().deleteDeployment(deloyID , true);
    }
}
