package com.etrivium.backend.repository;

import com.etrivium.backend.controller.response.GetCourseStructureResponse;
import com.etrivium.backend.controller.response.QuestionAnsweringResponse;
import com.etrivium.backend.repository.request.DataGetCourseStructureRequest;
import com.etrivium.backend.repository.request.DataQuestionAnsweringRequest;
import com.etrivium.backend.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.time.Duration;
import java.util.Optional;

@Service
public class DataRepository {

    private final LambdaClient lambdaClient;
    private final String functionName;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);


    private static final String EVENT_TYPE_QUESTION_ANSWERING = "QUESTION_ANSWERING";
    private static final String EVENT_TYPE_GET_COURSE_STRUCTURE = "GET_COURSE_STRUCTURE";

    private static final Duration LAMBDA_TIMEOUT = Duration.ofSeconds(900); // 15 minutes

    public DataRepository(@Value("${aws.lambda.function.name}") String functionName, ObjectMapper objectMapper) {
        this.lambdaClient = LambdaClient.builder()
                .region(Region.EU_CENTRAL_1)
                .overrideConfiguration(ClientOverrideConfiguration.builder()
                        .apiCallTimeout(LAMBDA_TIMEOUT)
                        .apiCallAttemptTimeout(LAMBDA_TIMEOUT)
                        .retryPolicy(RetryPolicy.builder()
                                .numRetries(3)
                                .build())
                        .build())
                .httpClientBuilder(ApacheHttpClient.builder()
                        .socketTimeout(LAMBDA_TIMEOUT))
                .build();
        this.functionName = functionName;
        this.objectMapper = objectMapper;
    }

    public QuestionAnsweringResponse questionAnswering(String fileName, String query) throws Exception {
        DataQuestionAnsweringRequest dataAnswerQuestionRequest =
                DataQuestionAnsweringRequest.builder()
                        .event_type(EVENT_TYPE_QUESTION_ANSWERING)
                        .file_name(fileName)
                        .query(query)
                        .build();

        return invokeLambdaQueryAnswer(dataAnswerQuestionRequest, QuestionAnsweringResponse.class);
    }

    private <T> T invokeLambdaQueryAnswer(Object invokePayload, Class<T> responseType) throws Exception {
        InvokeLambdaRequest invoqueLambdaRequest = InvokeLambdaRequest.builder().body(invokePayload).build();
        String payload = objectMapper.writeValueAsString(invoqueLambdaRequest);
        InvokeRequest request = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        InvokeResponse response = lambdaClient.invoke(request);
        String responseBody = response.payload().asUtf8String();

        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String statusCode = "200";
        if (jsonNode.get("statusCode") != null){
            statusCode = jsonNode.get("statusCode").asText();
            String actualBody = jsonNode.get("body").asText();
            if(statusCode.equals("500")) throw new Exception(actualBody);
        }
        // Parse the actual body into the desired response type
        return objectMapper.readValue(responseBody, responseType);
    }

}

@Data
@Builder
class InvokeLambdaRequest {
    Object body;
}