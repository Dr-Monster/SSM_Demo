package com.actDemo.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author:LuHaoran
 * @Description:
 * @Date:Created in ${Time} ${DATA}
 * @Modified By:
 */
@Repository
public interface UserDao {
    public boolean userLogin(int userID, String userPassword);
}
