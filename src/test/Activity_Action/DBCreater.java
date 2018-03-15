package Activity_Action;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Created by Administrator on 18/2/28.
 */

public class DBCreater {


    @Test
    public void createMySqlDB(){
        // 创建流程引擎配置信息对象
        ProcessEngineConfiguration pec = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        // 设置数据库的类型
//        pec.setDatabaseType("oracle");
        pec.setDatabaseType("mysql");
        //配置数据库驱动:对应不同数据库类型的驱动
        pec.setJdbcDriver("com.mysql.jdbc.Driver");
        // 设置jdbcURL
        pec.setJdbcUrl("jdbc:mysql://localhost:3306/activity_test?useUnicode=true&characterEncoding=utf8");
        // 设置用户名
        pec.setJdbcUsername("root");
        // 设置密码
        pec.setJdbcPassword("123456");
        // 设置创建数据库的方式
        // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE(true): 如果没有数据库表就会创建数据库表，有的话就修改表结构.
        // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE(false): 不会创建数据库表
        // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP(create-drop): 先创建、再删除.
        pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 构建流程引擎对象
        ProcessEngine pe = pec.buildProcessEngine(); // 调用访方法才会创建数据表
//        // 调用close方法时，才会删除
//        pe.close();
    }

    @Test
    public void createDB2(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
        System.out.println("processEngine:"+processEngine);
    }


    @Test
    public void createOracleDB(){
        // 创建流程引擎配置信息对象
        ProcessEngineConfiguration pec = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        // 设置数据库的类型
        pec.setDatabaseType("oracle");
        //配置数据库驱动:对应不同数据库类型的驱动
        pec.setJdbcDriver("oracle.jdbc.driver.OracleDriver");
        // 设置jdbcURL
        pec.setJdbcUrl("jdbc:oracle:thin:@//172.16.2.222:1521");
        // 设置用户名
        pec.setJdbcUsername("oracle");
        // 设置密码
        pec.setJdbcPassword("12345678");
        // 设置创建数据库的方式
        // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE(true): 如果没有数据库表就会创建数据库表，有的话就修改表结构.
        // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE(false): 不会创建数据库表
        // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP(create-drop): 先创建、再删除.
        pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 构建流程引擎对象
        ProcessEngine pe = pec.buildProcessEngine(); // 调用访方法才会创建数据表
//        // 调用close方法时，才会删除
//        pe.close();
    }
}
