package com.actDemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Controller  
@Scope("prototype")   
@RequestMapping("/user")  
public class Test {


    @Autowired
    RepositoryService repositoryService;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value="/test",method=RequestMethod.GET)  
    public ModelAndView  goTest(String name,String password)  
    {  
        ModelAndView mov=new ModelAndView();  
        mov.setViewName("jsp/test");
        return mov;  
    }
    
    /**
     * 创建模型
     */
//    @RequestMapping("/create")
//    public void create(HttpServletRequest request, HttpServletResponse response) {
//        try {
//        	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//        	RepositoryService repositoryService = processEngine.getRepositoryService();
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            ObjectNode editorNode = objectMapper.createObjectNode();
//            editorNode.put("id", "canvas");
//            editorNode.put("resourceId", "canvas");
//            ObjectNode stencilSetNode = objectMapper.createObjectNode();
//            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
//            editorNode.put("stencilset", stencilSetNode);
//            Model modelData = repositoryService.newModel();
//
//            ObjectNode modelObjectNode = objectMapper.createObjectNode();
//            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "ProcessDemo");
//            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
//            String description = "Just ProcessDemo";
//            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
//            modelData.setMetaInfo(modelObjectNode.toString());
//            modelData.setName("ProcessDemo");
//            modelData.setKey("ProcessKey");
//
//            //保存模型
//            repositoryService.saveModel(modelData);
//            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
//            response.sendRedirect(request.getContextPath() + "/processEditor/modeler.html?modelId=" + modelData.getId());
//        } catch (Exception e) {
//            System.out.println("创建模型失败：");
//        }
//    }


    @RequestMapping(value = "/create")
    public void create(@RequestParam("name") String name, @RequestParam("key") String key ,
                       HttpServletRequest request, HttpServletResponse response) {
        String description = request.getParameter("description");
        if (description == null){
            description = "" ;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            description = StringUtils.defaultString(description);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(StringUtils.defaultString(key));

            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            response.sendRedirect(request.getContextPath() + "/processEditor/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            logger.error("创建模型失败：", e);
        }
    }

}  
