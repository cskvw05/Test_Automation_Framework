package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class ApiUtils {

 //constructor
    public ApiUtils(){

    }

    public static RequestSpecification getRequestSpec(
            HashMap<String,String> headerSpec,String baseUrl){
        return  new RequestSpecBuilder()
                .addHeaders(headerSpec)
                .setBaseUri(baseUrl)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification addQueryParams(RequestSpecification spec,
                                                      HashMap<String, String> querySpec) {
        return spec.queryParams(querySpec);
    }

    public static RequestSpecification addPathParams(RequestSpecification spec,
                                                     HashMap<String, String> pathParamsSpec) {
        return spec.pathParams(pathParamsSpec);
    }

    public static RequestSpecification addCookies(RequestSpecification spec,
                                                  HashMap<String, String> cookieSpec) {
        return spec.cookies(cookieSpec);
    }

    public static RequestSpecification addParams(RequestSpecification spec, HashMap<String, String> paramsSpec) {
        return spec.params(paramsSpec);
    }

    public static ResponseSpecification getResponseSpec(
            HashMap<String,Object> headerSpec ,
            HashMap<String,Object> cookiesSpec,
            ApiResponseCode expectedStatusCode,
            long maxResponseTimeInMillis) {

        return new ResponseSpecBuilder()
                .expectCookies(cookiesSpec)
                .expectHeaders(headerSpec)
                .expectResponseTime(Matchers.lessThan(maxResponseTimeInMillis), TimeUnit.MILLISECONDS)
                .log(LogDetail.ALL)
                .build();

    }

    // Method to read Request Body from File
    public static String getRequestBodyFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }

    // Method to read Request Body from JSON File and Convert to String
    public static String getRequestBodyFromJsonFile(String filePath) throws IOException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object json = objectMapper.readValue(new File(filePath), Object.class);
        return objectMapper.writeValueAsString(json);
    }

    // Method to validate JSON Schema for List
    public static void validateJsonSchemaForList(String responseBody, String schemaFilePath) {
        File schemaFile = new File(schemaFilePath);
        if (!schemaFile.exists()) {
            throw new IllegalArgumentException("Schema file not found: " + schemaFilePath);
        }
        JsonSchemaValidator.matchesJsonSchema(schemaFile).matches(responseBody);
    }

    // Method to validate JSON Schema for Single Element
    public static void validateJsonSchemaForSingleElement(String responseBody, String schemaFilePath) {
        File schemaFile = new File(schemaFilePath);
        if (!schemaFile.exists()) {
            throw new IllegalArgumentException("Schema file not found: " + schemaFilePath);
        }
        JsonSchemaValidator.matchesJsonSchema(schemaFile).matches(responseBody);
    }

    // Method to Convert Object to JSON String (POJO to JSON)
    public static String convertPojoToJson(Object pojo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(pojo);
    }

    // Method to Convert JSON String to POJO (JSON to POJO)
    public static <T> T convertJsonToPojo(String jsonString, Class<T> pojoClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, pojoClass);
    }




































}
