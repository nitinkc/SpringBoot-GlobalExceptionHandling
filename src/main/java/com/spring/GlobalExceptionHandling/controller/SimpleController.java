package com.spring.GlobalExceptionHandling.controller;

import com.spring.GlobalExceptionHandling.dto.MyRequestDTO;
import com.spring.GlobalExceptionHandling.exception.business.BadInputException;
import com.spring.GlobalExceptionHandling.exception.business.WordsNotFoundException;
import com.spring.GlobalExceptionHandling.service.ServiceManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SimpleController {

    ServiceManager service;

    @PostMapping(path = "/word")
    @Validated
    public ResponseEntity<List<String>> runController(@RequestBody @Valid MyRequestDTO request) {

        String max = request.getMax();

        if(!StringUtils.isNumeric(max)){
            throw new BadInputException("String is not a Number");
        }

        List<String> data = service.getData(request.getWord(), max);

        if (data.isEmpty()){
            throw new WordsNotFoundException("No Words found");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
}