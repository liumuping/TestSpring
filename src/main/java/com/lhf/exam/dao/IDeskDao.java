package com.lhf.exam.dao;

import com.lhf.exam.pojo.Desk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDeskDao {
    List<Desk> getDesks(@Param("start") int start, @Param("pagecount") int end);
    int insertDesk(Desk desk);
    boolean deleteDesk(int deskId);
    boolean updateDeskStateById(int deskId);
    boolean updateDeskStateByName(String deskname);
    int getCount();
    boolean updateDesk(@Param("oldname") String deskname, @Param("desk") Desk desk);
}
