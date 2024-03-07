package com.spring.GlobalExceptionHandling.service;

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
}
