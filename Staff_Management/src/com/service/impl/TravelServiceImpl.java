package com.service.impl;

import com.mapper.TravelMapper;
import com.pojo.Travel;
import com.service.TravelService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {
    @Autowired
    private TravelMapper travelMapper;
    @Override
    public int insert(Travel travel) {
        return travelMapper.insert(travel);
    }

    @Override
    public List<Travel> travelFillTime(int id) {
        return travelMapper.travelFillTime(id);
    }

    @Override
    public List<Travel> listtraveltimeById(Page page) {
        return travelMapper.listtraveltimeById(page);
    }

    @Override
    public int totalTravleRecord(int id) {
        return travelMapper.totalTravleRecord(id);
    }

    @Override
    public List<Travel> allTravelApproval(Page page) {
        return travelMapper.allTravelApproval(page);
    }

    @Override
    public List<Travel> allTreavelApprovalDepart(Page page) {
        return travelMapper.allTreavelApprovalDepart(page);
    }

    @Override
    public int countTravelDepart(int id) {
        return travelMapper.countTravelDepart(id);
    }

    @Override
    public int countTravel() {
        return travelMapper.countTravel();
    }

    @Override
    public int rejectTravel(Travel travel) {
        return travelMapper.rejectTravel(travel);
    }

    @Override
    public int passTravel(Travel travel) {
        return travelMapper.passTravel(travel);
    }
}
