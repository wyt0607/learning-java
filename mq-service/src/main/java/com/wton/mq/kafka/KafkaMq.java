package com.wton.mq.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * @author wton
 */
public interface KafkaMq {

    @Input("WTON_TEST")
    MessageChannel wtonInput();

    @Output("WTON_TEST")
    MessageChannel wtonOutput();

}
