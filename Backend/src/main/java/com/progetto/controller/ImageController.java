package com.progetto.controller;

import com.progetto.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    
    @Autowired
    private ImageService imageService;

    @GetMapping("/{url}")
    public byte[] getImageByUrl(@PathVariable String url) {
        return imageService.recuperaImmagineDaUrl(url);
    }

}
