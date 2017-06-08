package com.test;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WTON on 2017/5/21.
 */

public class JunitTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);//ThreadExecutor();

    public static void main(String[] args) {
        try {

            FileSystem fileSystem = FileSystems.getDefault();
            WatchService watchService = fileSystem.newWatchService();
            File file = new File("./service/src/main/resources/whileList.yml");
            boolean exists = file.exists();
            Path p = Paths.get("./service/src/main/resources");
            p.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println(exists);
            System.out.println("注册监听服务");
            while (true){
                executorService.execute(() -> {
                    try {
                        WatchKey watchKey = watchService.take();
                        watchKey.pollEvents().forEach((e) -> {
                            System.out.println("[" + p + "/" + e.context() + "]文件发生了[" + e.kind() + "]事件" + e.count());
                        });
                        watchKey.reset();
                        System.out.println("reset");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
