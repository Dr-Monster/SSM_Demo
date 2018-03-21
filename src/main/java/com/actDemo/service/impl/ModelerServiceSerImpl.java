package com.actDemo.service.impl;

import com.actDemo.entity.TempModeler;
import com.actDemo.service.ModelerSer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:LuHaoran
 * @Description:
 * @Date:Created in ${Time} ${DATA}
 * @Modified By:
 */
@Service
public class ModelerServiceSerImpl implements ModelerSer {

    @Autowired
    RepositoryService repositoryService;


    @Override
    public void createModeler(String processName, String processKey , String description) {

    }

    @Override
    public List<TempModeler> queryModelList() {
        List<Model> list = repositoryService.createModelQuery().orderByModelVersion().asc().list();
        List<TempModeler> modelerList = new ArrayList<TempModeler>();
        for(Model model : list){
            TempModeler tempModeler = new TempModeler();
            tempModeler.setmVersion(model.getVersion());
            tempModeler.setmDeID(model.getDeploymentId());
            tempModeler.setmID(model.getId());
            tempModeler.setmName(model.getName());
            tempModeler.setmKey(model.getKey());
            modelerList.add(tempModeler);
        }
        return modelerList;
    }

    @Override
    public String deployModeler(String modelerID) {
        String result = "";
        try {
            Model modelData = repositoryService.getModel(modelerID);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
            if(deployment != null){
                result = "true";
            }else{
                result = "false";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
