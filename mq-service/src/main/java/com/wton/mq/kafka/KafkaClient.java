package com.wton.mq.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wton
 */
public interface KafkaClient {

    @Input("WTON_TEST")
    SubscribableChannel inputSubscribableChannel();


    @Output("WTON_TEST")
    MessageChannel outChannel();

}
