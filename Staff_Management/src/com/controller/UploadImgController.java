package com.controller;

import com.pojo.Picture;
import com.service.EmployeeService;
import com.service.PictureService;
import com.util.Page;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UploadImgController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private EmployeeService employeeService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now_time = dateFormat.format(new Date());

    @RequestMapping(value = "uploadImg.do", method = RequestMethod.POST)
    @ResponseBody
    public int uploadHead(@RequestParam("file") MultipartFile file, HttpSession session) {
        Picture picture = new Picture();
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        try {
            if (file != null) {
                //获取文件名
                String fileName = file.getOriginalFilename();
                System.out.println("fileName:" + fileName);
                //截取扩展名
                String extName = fileName.substring(fileName.lastIndexOf("."));
                List list = new ArrayList();
                list.add(".jpg");
                list.add(".png");
                list.add(".jpeg");
                list.add(".gif");
                if (list.contains(extName.toLowerCase())) {
                    //直接定义文件路径
                    String realPath = "D:\\images";

                    String photoFileName = new Date().getTime() + extName;
                    String databasePath = "/images/" + photoFileName;
                    file.transferTo(new File(realPath, photoFileName));
                    picture.setDate(dateFormat.parse(now_time));
                    picture.setName(photoFileName);
                    picture.setPath(databasePath);
                    picture.setEid(id);
                    pictureService.insertPicture(picture);
                    return 1;//成功
                } else {
                    return -1;//文件类型不正确
                }
            } else {
                return 0;//上传文件为空
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;//上传异常
        }
    }
    @RequestMapping(value = "pictureURL",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getPictureURL(){
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        String url = pictureService.selectPicture(id);
        if (url == null){
            url = "resources/images/20200218.png";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("url", url);
        return map;
    }
    @RequestMapping(value = "pictureURLInfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> pictureURLInfo(@RequestParam("id") Integer id){
        String url = pictureService.selectPicture(id);
        if (url == null){
            url = "resources/images/20200218.png";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("url", url);
        return map;
    }
    @RequestMapping(value = "allPictureInfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> allPictureInfo(HttpServletRequest request){
        Page page = new Page();
        int start = Integer.parseInt(request.getParameter("start"));
        int count = Integer.parseInt(request.getParameter("count"));
        System.out.println(start+" "+count);
        page.setStart(start);
        page.setCount(count);
        List<Picture> list = pictureService.selectAllPicture(page);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("total", employeeService.total());
        map.put("count", page.getCount());
        return map;
    }
}

