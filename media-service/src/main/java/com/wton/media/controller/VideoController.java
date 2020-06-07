package com.wton.media.controller;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@RestController
public class VideoController {

    @Value("${upload.disk}")
    private String disk;
    @Value("${upload.file-path}")
    private String filePath;
    @Value("${upload.server-url}")
    private String serverUrl;

    @GetMapping("/")
    public String test() {

        Properties properties = System.getProperties();

        return "1";
    }

    @GetMapping("/hls")
    public String test2(HttpServletRequest httpServletRequest) {

        Properties properties = System.getProperties();

        return "1";
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        String fullPath = "";
        if (!"/".equals(disk)) {
            fullPath = disk + ":" + filePath;
        } else {
            fullPath = disk + filePath;
        }
        File root = new File(fullPath);
        if (!root.exists()) {
            root.mkdirs();
        }

        try {
            file.transferTo(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }


}
