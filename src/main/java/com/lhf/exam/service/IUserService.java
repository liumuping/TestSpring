package com.lhf.exam.service;

import com.lhf.exam.pojo.AccessRight;
import com.lhf.exam.pojo.Role;
import com.lhf.exam.pojo.User;

import java.util.List;

public interface IUserService {
    User login(String username, String password);
    List<Role> initRole(int userId);
    List<AccessRight> initAccess(int roldId);
}
