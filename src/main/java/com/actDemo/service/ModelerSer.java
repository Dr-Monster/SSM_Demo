package com.actDemo.service;

import com.actDemo.entity.TempModeler;

import java.util.List;

/**
 * @Author:LuHaoran
 * @Description:
 * @Date:Created in ${Time} ${DATA}
 * @Modified By:
 */
public interface ModelerSer {

    void createModeler(String processName , String processKey , String description);

    List<TempModeler> queryModelList();

    String deployModeler(String modelerID);
}
