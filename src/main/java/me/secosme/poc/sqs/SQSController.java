package me.secosme.poc.sqs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/sqs")
public class SQSController {

    private final SQSService service;

    @PostMapping("/send")
    public void sendMessage(@RequestBody LetterDTO dto) {
        service.sendMessage(dto);
    }

}
