package cn.baisee.fileserver;

import cn.baisee.fileserver.util.Ftputil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Description:
 * @Date: 2021/12/12 9:28
 * @Author: YueSong
 * @Version: 1.0
 **/
@Controller
public class controller {

    @PostMapping("/user")
    public String addUser(String user, @RequestParam("headPic") MultipartFile
            file) throws IOException {
        // 获取上传的文件流
        InputStream input = file.getInputStream();
        // 获取上传的文件名
        String fileName = file.getOriginalFilename();
        // 截取后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 使用 UUID 拼接后缀，定义一个不重复的文件名
        String finalName = UUID.randomUUID() + suffix;
        // 调用自定义的 FTP 工具类上传文件
        boolean flag = Ftputil.uploadFile(finalName, input);
        System.out.println(user + "上传文件：" + flag);
        return "success";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
