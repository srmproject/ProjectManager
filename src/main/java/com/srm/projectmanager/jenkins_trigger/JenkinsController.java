package com.srm.projectmanager.jenkins_trigger;

import com.srm.projectmanager.application.ApplicationEntity;
import com.srm.projectmanager.application.ApplicationRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${jenkins.endpoint}")
    private String endpoint;

    @Value("${jenkins.user}")
    private String jenkins_user;

    @Value("${jenkins.token}")
    private String jenkins_token;

    @Value("${jenkins.testapi}")
    private String test_url;

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
            .uri(getTriggerUrl(find_application))
            .headers(header -> header.setBasicAuth(jenkins_user, jenkins_token))
            .retrieve()
            .bodyToMono(String.class)
            .block();

        // todo response fail

        log.info("----------------- jenkins job 실행API 종료 -----------------------");

        return "done";
    }

    private String getTriggerUrl(ApplicationEntity applicationEntity){
        return endpoint + applicationEntity.getJenkins_job_url() + "/build?token=" + applicationEntity.getToken();
    }
}
