package com.fan.essay.controller;

import com.fan.essay.pojo.Result;
import com.fan.essay.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @PostMapping
    public Result<String> upload(MultipartFile file) throws Exception {
        // 将文件内容存储到本地
        String originalFilename = file.getOriginalFilename();
        // 保证文件名唯一
//        String fileName= System.currentTimeMillis()+originalFilename;
        String fileName= UUID.randomUUID().toString().replace("-","")+originalFilename;
//        String fileName= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("E:\\code\\files\\"+fileName));
        String url = AliOssUtil.uploadFile(fileName, file.getInputStream());

        return Result.success(url);
    }

}
