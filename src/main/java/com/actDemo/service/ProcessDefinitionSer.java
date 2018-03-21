package com.actDemo.service;

import com.actDemo.entity.TempDefinition;
import org.activiti.engine.repository.Deployment;

import java.util.List;

/**
 * Created by Administrator on 18/2/28.
 */
public interface ProcessDefinitionSer {
    /**
     * 查询流程定义
     */
    public List<TempDefinition> queryDefinition();

    Deployment deployAction(String pngUrl , String bpmnUrl , String processName);

    /**
     * 删除流程定义
     */
    String deleteDefinition(String deployID);

    String downDefinitionPng(String deployID);
}
