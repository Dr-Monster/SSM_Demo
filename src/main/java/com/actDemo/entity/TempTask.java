package com.actDemo.entity;

/**
 * @Author:LuHaoran
 * @Description:
 * @Date:Created in ${Time} ${DATA}
 * @Modified By:
 */
public class TempTask {
    String tID ;
    String tName ;
    String deployID ;
    String deployKey ;

    public String getDeployID() {
        return deployID;
    }

    public void setDeployID(String deployID) {
        this.deployID = deployID;
    }

    public String getDeployKey() {
        return deployKey;
    }

    public void setDeployKey(String deployKey) {
        this.deployKey = deployKey;
    }

    public String gettID() {
        return tID;
    }

    public void settID(String tID) {
        this.tID = tID;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
