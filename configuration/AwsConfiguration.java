package com.etrivium.backend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.s3.InMemoryBufferingS3OutputStreamProvider;
import io.awspring.cloud.s3.Jackson2JsonS3ObjectConverter;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.endpoints.S3EndpointProvider;
import software.amazon.awssdk.services.s3.endpoints.internal.AwsEndpointProviderUtils;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;


@Configuration
public class AwsConfiguration {

    @Autowired
    AwsCredentialsProvider awsCredentialsProvider;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(Region.EU_SOUTH_2)
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(Region.EU_SOUTH_2)
                .build();
    }

    @Bean
    public S3Template s3Template() {
        return new S3Template(s3Client(),
                new InMemoryBufferingS3OutputStreamProvider(s3Client(), null),
                new Jackson2JsonS3ObjectConverter(new ObjectMapper()),
                s3Presigner());
    }
}
