package com.srm.projectmanager.application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepos extends JpaRepository<ApplicationEntity, Long> {
}
