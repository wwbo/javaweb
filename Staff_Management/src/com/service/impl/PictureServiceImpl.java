package com.service.impl;

import com.mapper.PictureMapper;
import com.pojo.Picture;
import com.service.PictureService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public int insertPicture(Picture picture) {
        deletePicture(picture.getEid());
        return pictureMapper.insertPicture(picture);
    }

    @Override
    public int deletePicture(int eid) {
        return pictureMapper.deletePicture(eid);
    }

    @Override
    public String selectPicture(int eid) {
        return pictureMapper.selectPicture(eid);
    }

    @Override
    public List<Picture> selectAllPicture(Page page) {
        return pictureMapper.selectAllPicture(page);
    }
}
