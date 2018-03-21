package com.actDemo.controller;

import com.actDemo.entity.TempDefinition;
import com.actDemo.entity.TempModeler;
import com.actDemo.service.ActivitiActionSer;
import com.actDemo.service.ModelerSer;
import com.actDemo.service.ProcessDefinitionSer;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 18/3/1.
 */
@Controller
@RequestMapping(value = "/Main")
public class ActionController {

    @Autowired
    ActivitiActionSer activitiActionSer ;

    @Autowired
    ProcessDefinitionSer processDefinitionSer ;

    @Autowired
    ModelerSer modelerSer ;



    @RequestMapping(value = "/create")
    public void create(@RequestParam("name") String name, @RequestParam("key") String key, @RequestParam("description") String description,
                       HttpServletRequest request, HttpServletResponse response) {

    }



    @RequestMapping(value = "/Index")
    @ResponseBody
    public ModelAndView getModelAndView(HttpServletRequest request , HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        List<TempDefinition> tempDefiList = processDefinitionSer.queryDefinition();
        modelAndView.addObject("modelerList" , JSON.toJSONString(modelerSer.queryModelList()));
        modelAndView.addObject("definitionList" , JSON.toJSONString(tempDefiList));
        modelAndView.addObject("taskList" , JSON.toJSONString(activitiActionSer.viewAction()));
        modelAndView.setViewName("jsp/activitiTest");
        return modelAndView;
    }


    @RequestMapping(value = "/modelerList")
    @ResponseBody
    public String getModelerList(HttpServletResponse response , HttpServletRequest request){
        List<TempModeler> modelerList = modelerSer.queryModelList();
        return JSON.toJSONString(modelerList);
    }

    @RequestMapping(value = "/deployModeler")
    @ResponseBody
    public String deployModeler(HttpServletRequest request , HttpServletResponse response){
        String id = request.getParameter("mDeID");
        return JSON.toJSONString(modelerSer.deployModeler(id)) ;
    }


    @RequestMapping(value = "/deployDefinition")
    @ResponseBody
    public String deployDefinition(HttpServletRequest request , HttpServletResponse response){
        String pngUrl = request.getParameter("pngUrl");
        String bpmnUrl = request.getParameter("bpmnUrl");
        String processName = request.getParameter("processName");
        return JSON.toJSONString(processDefinitionSer.deployAction(pngUrl , bpmnUrl , processName));
    }

    @RequestMapping(value = "/viewDefinitionFiles")
    @ResponseBody
    public String viewDefinitionFiles(HttpServletRequest request , HttpServletResponse response){
        Set<String> fileSet = activitiActionSer.viewDefinitions();
        return JSON.toJSONString(fileSet);
    }


    @RequestMapping(value = "/definitionList")
    @ResponseBody
    public String viewDefinitions(HttpServletRequest request , HttpServletResponse response){
        List<TempDefinition> tempDefiList = processDefinitionSer.queryDefinition();
        return JSON.toJSONString(tempDefiList);
    }


    @RequestMapping(value = "/deleteDefinition")
    @ResponseBody
    public String deleteDefinitions(HttpServletRequest request , HttpServletResponse response){
        String deployID = request.getParameter("deployID");
        String result = processDefinitionSer.deleteDefinition(deployID);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/downLoadDefinition")
    @ResponseBody
    public String downLoadDefinitions(HttpServletRequest request , HttpServletResponse response){
        String deployID = request.getParameter("deployID");
        String result = processDefinitionSer.downDefinitionPng(deployID);
        return JSON.toJSONString(result);
    }


    @RequestMapping(value = "/deployAction")
    @ResponseBody
    public String deployAction(HttpServletRequest request , HttpServletResponse response){
        String fileName = request.getParameter("fileName");
        String processName = request.getParameter("processName");
        Deployment deployment = activitiActionSer.deployAction(fileName , processName);
        return JSON.toJSONString(deployment);
    }

    @RequestMapping(value = "/startAction")
    @ResponseBody
    public String startAction(HttpServletRequest request , HttpServletResponse response){
        String actName = request.getParameter("actName");
        ProcessInstance processInstance = activitiActionSer.startAction(actName);
        return JSON.toJSONString(processInstance);
    }

    @RequestMapping(value = "/viewAction")
    @ResponseBody
    public String viewAction(HttpServletRequest request , HttpServletResponse response){
        List<String> taskList  = activitiActionSer.viewAction();
        return JSON.toJSONString(taskList) ;
    }

    @RequestMapping(value = "/executeAction")
    @ResponseBody
    public String executeAction(HttpServletRequest request , HttpServletResponse response){
        String taskID = request.getParameter("taskID");
        String exeResult = activitiActionSer.executeAction(taskID);
        return exeResult ;
    }
}
