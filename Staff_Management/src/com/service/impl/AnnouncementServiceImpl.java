package com.service.impl;

import com.mapper.AnnouncementMapper;
import com.pojo.Announcement;
import com.service.AnnouncementService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public int deleteAnnounce(int id) {
        return announcementMapper.deleteAnnounce(id);
    }

    @Override
    public int countAnnounceByE() {
        return announcementMapper.countAnnounceByE();
    }

    @Override
    public List<Announcement> selectAnnounceByE(Page page) {
        return announcementMapper.selectAnnounceByE(page);
    }

    @Override
    public int countAnnounce() {
        return announcementMapper.countAnnounce();
    }

    @Override
    public int deleteBatchAnnounce(int[] ids) {
        int result = 0;
        for (int i=0; i<ids.length; i++){
            deleteAnnounce(ids[i]);
            result ++;
        }
        return result;
    }

    @Override
    public int updateAnnounce(Announcement announcement) {
        return announcementMapper.updateAnnounce(announcement);
    }

    @Override
    public List<Announcement> selectAnnounce(Page page) {
        return announcementMapper.selectAnnounce(page);
    }

    @Override
    public String selectRoleById(int id) {
        return announcementMapper.selectRoleById(id);
    }

    @Override
    public int insertAnnounce(Announcement announcement) {
        return announcementMapper.insertAnnounce(announcement);
    }
}
