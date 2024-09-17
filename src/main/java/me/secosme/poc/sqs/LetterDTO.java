package me.secosme.poc.sqs;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LetterDTO {

    private String to;
    private String from;
    private String subject;
    private String body;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("send_at")
    private LocalDate sendAt;

}
