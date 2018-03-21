package Activity_Action;

import com.actDemo.entity.TempDefinition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 18/3/1.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml" , "classpath:spring.xml"})
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
    public void queryDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> processDefinitionList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        for(ProcessDefinition pd : processDefinitionList){
            System.err.println(pd.getId() + "\t\n");
            System.err.println(pd.getName() + "\t\n");
            System.err.println(pd.getKey() + "\t\n");
            System.err.println(pd.getDeploymentId() + "\t\n");
            System.err.println(pd.getResourceName() + "\t\n");
            System.err.println(pd.getDiagramResourceName() + "\t\n");
            System.err.println(pd.getVersion() + "\t\n");
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
        String deName = "HtmlDemo1" ;
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(deName);
        System.out.println(processInstance);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void viewAction() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String userAss = "TaskPerson1";
        List<Task> taskList = processEngine
                .getTaskService()
                .createTaskQuery()
//                .taskCandidateOrAssigned(userAss)
                .list();
        System.out.println(taskList);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void executeAction() {
        String taskID = "150004" ;
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete(taskID);
        System.out.println("-------------------------------------------------------------------------------");
    }




    @Test
    public void deletlDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String deloyID = "125001" ;
        processEngine.getRepositoryService().deleteDeployment(deloyID , true);
    }


    @Autowired
    RepositoryService repositoryService;


    @Test
    public void modelList(){
        List<Model> list1 = repositoryService.createModelQuery().notDeployed().orderByModelVersion().asc().list();
        for(Model model : list1){
            System.err.println("MDeID:" + model.getDeploymentId() +
                    "\t" + "MName:" + model.getName() +
                    "\t" + "MKey:" + model.getKey() +
                    "\t" + "MID:" + model.getId() +
                    "\t");
        }
    }


    @Test
    public void deployModel(){
        String modelId = "110001" ;
        try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void viewDefinitionImage(){
        String deployID = "125001" ;
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<String> nameList = processEngine.getRepositoryService().getDeploymentResourceNames(deployID);
        String pngName = "" ;
        for (String name : nameList){
            if (name.indexOf(".png") > 0){
                pngName = name ;
            }
        }
        String filePath = "" ;
        if (pngName != null && (!"".equals(pngName))){
            filePath = "F:/ActivitiTemp" + "/" + pngName;
            File file = new File(filePath);
            InputStream inputStream = processEngine.getRepositoryService()
                    .getResourceAsStream(deployID , pngName);
            try {
                FileUtils.copyInputStreamToFile(inputStream , file);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
