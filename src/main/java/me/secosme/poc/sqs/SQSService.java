package me.secosme.poc.sqs;

import org.springframework.stereotype.Service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SQSService {

    private final SqsTemplate template;

    public void sendMessage(LetterDTO dto) {
        log.info("Sending message to SQS: {}", dto);
        template.send(dto);
    }

    @SqsListener("${spring.cloud.aws.sqs.endpoint}")
    public void receiveMessage(LetterDTO dto) {
        log.info("Receiving message to SQS: {}", dto);
    }

}
