package com.griffith.rj_spring_boot.controller;

import com.griffith.rj_spring_boot.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequestMapping("/common")
@RestController
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传方法
     *
     * @param file fileObj
     * @return R<String>
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        log.info(file.toString());

        String originalFilename = file.getOriginalFilename();// abc.jpg

        // 类型断言 这边是ide要求补充的
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;

        File dir = new File(basePath);
        if (!dir.exists()) {
            // 这边会有一个bool返回值 可以看着处理 demo不用太在意
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name String
     * @param response HttpServletResponse
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try (
                //输入流 通过输入流获取文件内容
                FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

                //输出流 通过输出流把文件内容发送到浏览器
                ServletOutputStream servletOutputStream = response.getOutputStream()
        ){
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];

            while((len = fileInputStream.read(bytes)) != -1){
                servletOutputStream.write(bytes,0,len);
                servletOutputStream.flush();
            }


            /*
            流文件 放在try（）体里 就不用手动关闭
            servletOutputStream.close();
            fileInputStream.close();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
