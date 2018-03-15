package com.actDemo.controller;

import com.actDemo.entity.TempDefi;
import com.actDemo.service.ActivitiActionSer;
import com.actDemo.service.ProcessDefinitionSer;
import com.alibaba.fastjson.JSON;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/Index")
    @ResponseBody
    public ModelAndView getModelAndView(HttpServletRequest request , HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        List<TempDefi> tempDefiList = processDefinitionSer.queryDefinition();
        Set<String> fileSet = activitiActionSer.viewDefinitions();
        modelAndView.addObject("tempDefiList" , JSON.toJSONString(tempDefiList));
        modelAndView.addObject("fileList" , JSON.toJSON(fileSet));
        modelAndView.setViewName("jsp/activitiTest");
        return modelAndView;
    }



    @RequestMapping(value = "/viewDefinitionFiles")
    @ResponseBody
    public String viewDefinitionFiles(HttpServletRequest request , HttpServletResponse response){
        Set<String> fileSet = activitiActionSer.viewDefinitions();
        return JSON.toJSONString(fileSet);
    }


    @RequestMapping(value = "/viewDefinitions")
    @ResponseBody
    public String viewDefinitions(HttpServletRequest request , HttpServletResponse response){
        List<TempDefi> tempDefiList = processDefinitionSer.queryDefinition();
        return JSON.toJSONString(tempDefiList);
    }


    @RequestMapping(value = "/deleteDefinition")
    @ResponseBody
    public String deleteDefinitions(HttpServletRequest request , HttpServletResponse response){
        String deployID = request.getParameter("deployID");
        String result = processDefinitionSer.deletlDefinition(deployID);
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
        String userAss = request.getParameter("userAss");
        List<Task> taskList  = activitiActionSer.viewAction(userAss);
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
