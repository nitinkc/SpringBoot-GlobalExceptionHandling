package com.spring.GlobalExceptionHandling.service;

import com.spring.GlobalExceptionHandling.dto.WordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordServiceManager {

    public List<String> getData(String ...args){
        String word = args[0];
        String max = args[1];

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("word", word);
        uriVariables.put("max", max);

        RestTemplate restTemplate = new RestTemplate();
        //Response response = restTemplate.getForObject("https://api.datamuse.com/words?ml={word}&amp;max={max}", Response.class);
        ResponseEntity<WordResponse[]> response = restTemplate.getForEntity("https://api.datamuse.com/words?ml={word}&max={max}", WordResponse[].class, uriVariables);

        //RestResponse responseNEW = response.getBody();
        System.out.println("==== RESTful API Response using Spring RESTTemplate START =======");

        List<String> wordList = new ArrayList<>();
        for (WordResponse x : response.getBody()) {
            //System.out.println(x.getWord());
            wordList.add(x.getWord());
        }
        System.out.println("==== RESTful API Response using Spring RESTTemplate END =======");

//        throw new ApiException(ErrorCodes.ERR_122.getCode(),
//                HttpStatus.CONFLICT, ErrorCodes.ERR_124.getErrorMessage(), null);

        return wordList;
    }
}
