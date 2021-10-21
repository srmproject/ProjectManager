package com.srm.projectmanager.jenkins_trigger;

import com.srm.projectmanager.application.ApplicationEntity;
import com.srm.projectmanager.application.ApplicationRepos;
import com.srm.projectmanager.jenkins_trigger.dto.RequestJobTriggerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@Slf4j
@RequestMapping("/jenkins")
public class JenkinsController {
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    ApplicationRepos applicationRepos;

    @Value("${endpoint.jenkins_api}")
    private String jenkinsapi_endpoint;

    @Value("${api.jenkins.job.trigger}")
    private String jenkinsapi_jobtrigger;

    @PostMapping("/trigger/job/{id}")
    @Transactional(readOnly = true)
    public String triggerJob(@PathVariable Long id){
        log.info("----------------- jenkins job 실행API 호출 -----------------------");
        log.info(String.format("호출요청 id: %s", id));

        // todo service
        ApplicationEntity find_application = applicationRepos.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 애플리케이션"));

        String response = webClientBuilder.build()
            .post()
            .uri(jenkinsapi_endpoint + jenkinsapi_jobtrigger)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(
                new RequestJobTriggerDTO(find_application.getJenkins_job_url(), find_application.getToken())
            )
            .retrieve()
            .bodyToMono(String.class)
            .block();

        // todo response fail
        log.info("----------------- jenkins job 실행API 종료 -----------------------");
        return "done";
    }
}
