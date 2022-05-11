package com.service.impl;

import com.mapper.DepartMapper;
import com.pojo.Depart;
import com.service.DepartService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartMapper departMapper;
    @Override
    public List<Depart> allDepart(Page page) {
        return departMapper.allDepart(page);
    }

    @Override
    public List<Depart> allListDepart() {
        return departMapper.allListDepart();
    }

    @Override
    public int addDepart(Depart depart) {
        return departMapper.addDepart(depart);
    }

    @Override
    public int deleteDepart(int id) {
        return departMapper.deleteDepart(id);
    }

    @Override
    public int updateDepart(Depart depart) {
        return departMapper.updateDepart(depart);
    }

    @Override
    public int countDepart() {
        return departMapper.countDepart();
    }

    @Override
    public int selectDepartIdByEid(int id) {
        return departMapper.selectDepartIdByEid(id);
    }

    @Override
    public String selectDepartName(int id) {
        return departMapper.selectDepartName(id);
    }

    @Override
    public int selectDepartId(String name) {
        return departMapper.selectDepartId(name);
    }
}
