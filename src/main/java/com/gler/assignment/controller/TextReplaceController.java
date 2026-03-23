package com.gler.assignment.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextReplaceController {

    @GetMapping("/replace")
    public ResponseEntity<String> replace(@RequestParam String text) {

        int length = text.length();

        if (length < 2) {
            return ResponseEntity.badRequest().build();
        }

        if (length == 2) {
            return ResponseEntity.ok("");
        }

        String result = "*" + text.substring(1, length - 1) + "$";
        return ResponseEntity.ok(result);
    }
}