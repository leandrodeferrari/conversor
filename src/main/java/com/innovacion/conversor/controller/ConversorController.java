package com.innovacion.conversor.controller;

import com.innovacion.conversor.dto.ConvertRequest;
import com.innovacion.conversor.dto.ConvertResponse;
import com.innovacion.conversor.service.IConversorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convert")
public class ConversorController {

    private final IConversorService conversorService;

    public ConversorController(IConversorService conversorService) {
        this.conversorService = conversorService;
    }

    @PostMapping
    public ResponseEntity<ConvertResponse> convert(@RequestBody ConvertRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.conversorService.convert(dto));
    }

    @PostMapping("/example")
    public ResponseEntity<Integer> example(@RequestParam int a,
                                                 @RequestParam int b){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.conversorService.example(a, b));
    }
}
