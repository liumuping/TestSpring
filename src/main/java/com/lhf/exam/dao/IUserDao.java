package com.lhf.exam.dao;

import com.lhf.exam.pojo.AccessRight;
import com.lhf.exam.pojo.Role;
import com.lhf.exam.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserDao {
    User getUserByNameAndPwd(@Param("username") String username, @Param("password") String password);
    List<Role> getRoleByUserId(@Param("userId") int userId);
    List<AccessRight> getRightByRoleId(@Param("roleId") int roleId);
}
