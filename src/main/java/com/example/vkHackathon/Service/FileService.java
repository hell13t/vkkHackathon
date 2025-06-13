package com.example.vkHackathon.Service;

import com.example.vkHackathon.dto.ResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseDto upload (MultipartFile file) {
        String url = "localhost:8091/upload";

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);

        HttpHeaders httpHeaders  = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return new ResponseDto();
    }
}
