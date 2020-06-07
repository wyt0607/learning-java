目录说明:

    ffmpeg:                             视频解析库  
    jTessBoxEditor:                     tesseract 训练工具  
    jTessBoxEditor/tesseract-ocr:       图片提取文字
    test:               测试类
        CodeGenerator:  mybatis-plus 的代码生成器
        FfmpegTest:     视频解析测试
        Tess4jTest:     文字提取测试
        
dplay缩略图:
       
    安装:  npm install -g dplayer-thumbnails
    
    命令:  dplayer-thumbnails --help
           dplayer-thumbnails -o ./thumbnails.jpg -q 60 demo.mp4
        


视频推流:

    ffmpeg -re -i test.mp4 -c copy -f flv rtmp://ip:1935/hls/mystream