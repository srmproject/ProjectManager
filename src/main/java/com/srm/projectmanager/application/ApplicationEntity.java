package com.srm.projectmanager.application;

import com.srm.projectmanager.buildhistory.BuildhistoryEntity;
import com.srm.projectmanager.common.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Application")
@Getter
public class ApplicationEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String token;

    @Column
    private String git_url;

    @Column
    private String jenkins_job_url;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Build_history_id")
    private List<BuildhistoryEntity> build_histories = new ArrayList<>();
}
