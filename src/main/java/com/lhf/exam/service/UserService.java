package com.lhf.exam.service;

import com.lhf.exam.dao.IUserDao;
import com.lhf.exam.pojo.AccessRight;
import com.lhf.exam.pojo.Role;
import com.lhf.exam.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Scope("prototype") // 单例
public class UserService implements IUserService{
    @Autowired
    private IUserDao iUserDao;
    public User login(String username, String password){
        User user;
        user = iUserDao.getUserByNameAndPwd(username,password);
        List<Role> roles;
        roles = initRole(user.getUserid());
        for (Role role : roles) {
            role.setRights(initAccess(role.getId()));
        }
        user.setRoles(roles);
        return user;
    }

    public List<Role> initRole(int userId){
        List<Role> roles;
        roles = iUserDao.getRoleByUserId(userId);
        return roles;
    }
    public List<AccessRight> initAccess(int roldId){
        List<AccessRight> accessRights;
        accessRights = iUserDao.getRightByRoleId(roldId);
        return accessRights;
    }

}
