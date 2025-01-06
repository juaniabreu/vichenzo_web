package com.example.vichenzobackend.security.config.filter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    public CloudinaryConfig() {
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(new Object[]{"cloud_name", "dmrr5dtfk", "api_key", "275582412142532", "api_secret", "bFU9HXMoz5VksgtvVZraXEfSUTc"}));
    }
}