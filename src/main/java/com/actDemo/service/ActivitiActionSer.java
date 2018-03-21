package com.actDemo.service;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Set;

/**
 * @Author:LuHaoran
 * @Description:
 * @Date:Created in ${Time} ${DATA}
 * @Modified By:
 */
public interface ActivitiActionSer {

    Set<String> viewDefinitions();

    Deployment deployAction(String fileName , String processName);

    ProcessInstance startAction(String actName);

    List<String> viewAction();

    String executeAction(String taskID);
}
