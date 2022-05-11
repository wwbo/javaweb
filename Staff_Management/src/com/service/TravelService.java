package com.service;

import com.pojo.Travel;
import com.util.Page;

import java.util.List;

public interface TravelService {
    int insert(Travel travel);
    List<Travel> travelFillTime(int id);
    List<Travel> listtraveltimeById(Page page);
    int totalTravleRecord(int id);
    List<Travel> allTravelApproval(Page page);
    List<Travel> allTreavelApprovalDepart(Page page);
    int countTravelDepart(int id);
    int countTravel();
    int rejectTravel(Travel travel);
    int passTravel(Travel travel);
}
