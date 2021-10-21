package com.srm.projectmanager.jenkins_trigger.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestJobTriggerDTO {
    private String jenkins_url;
    private String token;

    public RequestJobTriggerDTO(String jenkins_url, String token) {
        this.jenkins_url = jenkins_url;
        this.token = token;
    }
}
