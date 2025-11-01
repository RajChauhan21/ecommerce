package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.DTO.HistoryDto;
import com.monolith.ecommerce.E_Commerce.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private HistoryService service;

    @PostMapping("/add")
    public ResponseEntity<?> addHistory(@RequestBody List<HistoryDto> historyDto){
        return service.addHistory(historyDto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHistory(@PathVariable int id){
        return service.getHistory(id);
    }
}

