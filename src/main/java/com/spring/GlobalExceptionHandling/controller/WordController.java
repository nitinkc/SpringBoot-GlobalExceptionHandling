package com.spring.GlobalExceptionHandling.controller;

import com.spring.GlobalExceptionHandling.exception.business.WordsNotFoundException;
import com.spring.GlobalExceptionHandling.service.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WordController {

    @Autowired
    ServiceManager service;
    //Retrieve specific users
    @PostMapping(path = "/word")
    public ResponseEntity<List<String>> retrieveUserById(@RequestBody Map<String,Object> request)
            throws WordsNotFoundException {

        String word = (String) request.get("word");
        String max = (String) request.get("max");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getData(word,max));
    }
}