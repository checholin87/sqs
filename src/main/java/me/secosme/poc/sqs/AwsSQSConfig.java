package me.secosme.poc.sqs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsSQSConfig {

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String queue;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient
            .builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKey, secretKey)))
            .build();
    }

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {

        // Register JavaTimeModule to serialize/deserialize LocalDateTime objects
        var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        // Create SqsTemplate with custom ObjectMapper
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient)
            .configureDefaultConverter(c -> c.setObjectMapper(om))
            .configure(c -> c.defaultQueue(queue)).build();
    }

}
