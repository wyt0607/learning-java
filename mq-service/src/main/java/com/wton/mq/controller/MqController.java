package com.wton.mq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wton.mq.entity.KafkaMq;
import com.wton.mq.kafka.KafkaClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@Api(tags = "消息接口")
@RestController
@RequestMapping("/")
@EnableBinding(KafkaClient.class)
public class MqController {
    private final Logger logger = LogManager.getLogger(MqController.class);
    private final KafkaClient kafkaClient;

    public MqController(KafkaClient kafkaClient) {
        this.kafkaClient = kafkaClient;
    }


    @ApiIgnore
    @GetMapping
    public ModelAndView syncTodayData() {
        return new ModelAndView("doc.html");
    }

    @ApiOperation("发送消息")
    @GetMapping("/send")
    public String send() {
        for (int i = 0; i < 10; i++) {
            KafkaMq kafkaMq = new KafkaMq();
            Message<KafkaMq> message = MessageBuilder
                    .withPayload(kafkaMq)
                    .setHeader("classType", KafkaMq.class)
                    .build();
            kafkaClient.outChannel().send(message);
        }
        return "1";
    }

    /**
     * 批量消费消息。
     * 官方文档描述 @StreamListener 不支持 batch-mode
     * 但实际上是可以 批量进行消费的 . 只是返回的数据是个 List<byte[]> 需要手动进行数据转换
     *
     * <p>
     * * <b>NOTE: It is no longer recommended to use StreamListener in favor of functional programming model.
     * * It will be deprecated and subsequently removed in the future</b>
     * <p>
     * Spring Cloud Bus 使用了 @EnableBinding ,  functional programming model 被关闭
     *
     * @param list 接收到的消息 . 若要完整的消息 使用 Message<List<byte[]> 来接收
     */
    @StreamListener(value = "WTON_TEST")
    public void receive(List<byte[]> list) {
        ObjectMapper objectMapper = JacksonUtils.enhancedObjectMapper();
        for (byte[] bytes : list) {
            try {
                KafkaMq kafkaMq = objectMapper.readValue(bytes, KafkaMq.class);
                logger.info(kafkaMq);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
