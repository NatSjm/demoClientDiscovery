package com.example.democlientdiscovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GroupController {
    private final RestTemplate restTemplate;

    @Autowired
    public GroupController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getGroupDetails/{groupName}")
    public GroupDetail getGroupDetails(@PathVariable String groupName) {
        List<Student> response = restTemplate
                .exchange("http://students-service/api/students/group/{groupName}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {}, groupName).getBody();

        return new GroupDetail(groupName, response);
    }
}