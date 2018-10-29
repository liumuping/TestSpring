package com.lhf.exam.service;

import com.lhf.exam.dao.IDeskDao;
import com.lhf.exam.dao.IFoodDao;
import com.lhf.exam.pojo.Desk;
import com.lhf.exam.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class DeskService {
    @Autowired
    private IDeskDao deskDao;
//    private SqlSession session = MybatisUtil.creatSqlSession(); // 使用mybatis 进行了修改需要commit 提交才可以奏效
    public List<Desk> getDesks(int start, int length) {
        List<Desk> desks = new ArrayList<>();
//        desks = session.getMapper(IDeskDao.class).getDesks(start,length);
        desks = deskDao.getDesks(start,length);
        return desks;
    }

    public int insertDesk(Desk desk) {
        int flag = 0;
        flag = deskDao.insertDesk(desk);
        return flag;
    }


    public boolean deleteDesk(int deskId) {
        boolean flag = false;
        flag = deskDao.deleteDesk(deskId);
        return flag;
    }


    public boolean updateDeskStateById(int deskId) {
        boolean flag = false;
        flag = deskDao.updateDeskStateById(deskId);
        return flag;
    }


    public boolean updateDeskStateByName(String deskname) {
        boolean flag = false;
        flag = deskDao.updateDeskStateByName(deskname);
        return flag;
    }


    public int getCount() {
        int count = 0;
        count = deskDao.getCount();
        return count;
    }

    public boolean updateDesk(String deskname, Desk desk) {
        boolean flag = false;
        flag = deskDao.updateDesk(deskname,desk);
        return flag;
    }


}
