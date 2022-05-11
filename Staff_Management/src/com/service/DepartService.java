package com.service;

import com.pojo.Depart;
import com.util.Page;

import java.util.List;

public interface DepartService {
    List<Depart> allDepart(Page page);
    List<Depart> allListDepart();
    int addDepart(Depart depart);
    int deleteDepart(int id);
    int updateDepart(Depart depart);
    int countDepart();
    int selectDepartId(String name);
    String selectDepartName(int id);
    int selectDepartIdByEid(int id);
}
