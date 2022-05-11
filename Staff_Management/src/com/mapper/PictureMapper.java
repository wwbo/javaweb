package com.mapper;

import com.pojo.Picture;
import com.util.Page;

import java.util.List;

public interface PictureMapper {
    int insertPicture(Picture picture);
    int deletePicture(int eid);
    String selectPicture(int eid);
    List<Picture> selectAllPicture(Page page);
}
