package com.actDemo.service.impl;

import com.actDemo.entity.TempDefinition;
import com.actDemo.service.ProcessDefinitionSer;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 18/2/28.
 */
@Service
public class ProcessDefinitionSerImp implements ProcessDefinitionSer {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Override
    public List<TempDefinition> queryDefinition() {
        List<ProcessDefinition> processDefinitionList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();

        Map<String , ProcessDefinition> sortMap = new HashMap<String , ProcessDefinition>();
        for(ProcessDefinition pd : processDefinitionList){
            sortMap.put(pd.getKey() , pd);
        }

        List<TempDefinition> tempDefiList = new ArrayList<TempDefinition>();
        //for(ProcessDefinition pd : sortMap.values()){
        for(ProcessDefinition pd : processDefinitionList){
            TempDefinition tempDefi = new TempDefinition();
            tempDefi.setDeID(pd.getDeploymentId());
            tempDefi.setBpmnPath(pd.getResourceName());
            tempDefi.setPngPath(pd.getDiagramResourceName());
            tempDefi.setID(pd.getId());
            tempDefi.setDeKey(pd.getKey());
            tempDefi.setDeName(pd.getName());
            tempDefi.setDeVersion(pd.getVersion());
            tempDefiList.add(tempDefi);
        }
        return tempDefiList;
    }


    @Override
    public Deployment deployAction(String pngUrl , String bpmnUrl , String processName) {
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
    public String deleteDefinition(String deloyID) {
        processEngine.getRepositoryService().deleteDeployment(deloyID , true);
        return "true";
    }

    @Override
    public String downDefinitionPng(String deployID) {
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
        return filePath;
    }
}
