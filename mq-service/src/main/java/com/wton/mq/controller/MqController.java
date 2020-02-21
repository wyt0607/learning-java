package com.wton.mq.controller;

import com.wton.mq.eneity.QcSrcRecord;
import com.wton.mq.kafka.KafkaMq;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@EnableBinding(KafkaMq.class)
public class MqController {
    private KafkaMq kafkaMq;

    public MqController(KafkaMq kafkaMq) {
        this.kafkaMq = kafkaMq;
    }

    @GetMapping("/send")
    public String test() {
        for (int i = 0; i < 100; i++) {
            QcSrcRecord qcSrcRecord = new QcSrcRecord();
            qcSrcRecord.setIiiii("11111");
            qcSrcRecord.setAddress("22222" + System.currentTimeMillis());
            GenericMessage<QcSrcRecord> message = new GenericMessage<>(qcSrcRecord);
            kafkaMq.wtonOutput().send(message);
        }
        return "1";
    }


    @StreamListener("WTON_TEST")
    public void input(List<byte[]> list) {
        System.out.println(list);
    }
}
