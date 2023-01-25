package io.knoldus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DataController {
    @GetMapping("/patientData")
    public void getData() throws IOException {
        int i = 0;
        while (i<200) {
            String uri =  "https://api.mockaroo.com/api/ff323100?count=1000&key=d3581dc0";
            RestTemplate restTemplate = new RestTemplate();
            Object dataObject = restTemplate.getForObject(uri, Object.class);
            ObjectMapper obj = new ObjectMapper();
            String jsonStr = obj.writeValueAsString(dataObject);
            Path path = Paths.get("src/main/resources/patient/record"+i+".json");
            Files.write(path, jsonStr.getBytes());
            i++;
        }
    }
}
