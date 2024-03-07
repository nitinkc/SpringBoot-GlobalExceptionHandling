package com.spring.GlobalExceptionHandling.service;

import com.spring.GlobalExceptionHandling.FileMetadata;
import com.spring.GlobalExceptionHandling.dto.WordResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceManager {
    final String URL = "https://api.datamuse.com/words?ml={word}&max={max}";
    final String URL2 = "https://test:Pa$$w0rd@httpbin.org/post";
    final String URL3 = "https://@httpbin.org/post";


    private RestTemplate restTemplate;


    public List<String> getData(String ...args){
        String word = args[0];
        String max = args[1];

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("word", word);
        uriVariables.put("max", max);

        ResponseEntity<WordResponse[]> response = restTemplate.getForEntity(URL, WordResponse[].class, uriVariables);

        //RestResponse responseNEW = response.getBody();
        log.info("==== RESTful API Response using Spring RESTTemplate START =======");

        List<String> wordList = new ArrayList<>();
        for (WordResponse x : response.getBody()) {
            wordList.add(x.getWord());
        }
        log.info("==== RESTful API Response using Spring RESTTemplate END =======");

        return wordList;
    }


    public Map<String, Object> uploadFile(MultipartFile file, HttpHeaders headers){

        FileMetadata fileMetadata = new FileMetadata(file);

        // Create the request body with multipart data
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileMetadata);

        // Set the headers
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create the request entity with body and headers
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Create parameterized type reference for Map<String, Object>
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {};

        // Send the POST request to the server
        ResponseEntity<Map<String, Object>> responseEntity =
                restTemplate.exchange(URL2, HttpMethod.POST,
                requestEntity,responseType);

        // Extract and return the response body
        Map<String, Object> responseBody = responseEntity.getBody();
        if (responseBody != null) {
            // Assuming the response contains a list of strings under the key "result"
            return responseBody;
        } else {
            // Handle the case where the response body is null
            return null;
        }
    }
}
