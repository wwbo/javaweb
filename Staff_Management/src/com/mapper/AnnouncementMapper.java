package com.mapper;

import com.pojo.Announcement;
import com.util.Page;

import java.util.List;

public interface AnnouncementMapper {
    List<Announcement> selectAnnounce(Page page);
    List<Announcement> selectAnnounceByE(Page page);
    int insertAnnounce(Announcement announcement);
    String selectRoleById(int id);
    int updateAnnounce(Announcement announcement);
    int deleteAnnounce(int id);
    int countAnnounce();
    int countAnnounceByE();
}
