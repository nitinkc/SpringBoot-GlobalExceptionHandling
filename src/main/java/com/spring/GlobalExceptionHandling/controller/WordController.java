package com.spring.GlobalExceptionHandling.controller;

import com.spring.GlobalExceptionHandling.exception.WordsNotFoundException;
import com.spring.GlobalExceptionHandling.service.WordServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WordController {

    @Autowired
    WordServiceManager service;
    //Retrieve specific users
    @GetMapping(path = "/word/{id}/{max}")
    public List<String> retrieveUserById(@PathVariable String id,
                                         @PathVariable String max)
            throws WordsNotFoundException {

        List<String> words = service.getData(id,max);

        //If word is not found
        if(words.size() == 0){
            throw new WordsNotFoundException("Wirds with id " + id +" is not found");
        }
        return words;
    }

}
