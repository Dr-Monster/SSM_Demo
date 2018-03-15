package com.actDemo.service;

import com.actDemo.entity.TempDefi;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 18/2/28.
 */
public interface ProcessDefinitionSer {
    /**
     * 查询流程定义
     */
    public List<TempDefi> queryDefinition();

    /**
     * 删除流程定义
     */
    String deletlDefinition(String deployID);

    String downDefinitionPng(String deployID);
}
