package com.srm.projectmanager.project;

import com.srm.projectmanager.application.ApplicationEntity;
import com.srm.projectmanager.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long account_id;

    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="application_id")
    private List<ApplicationEntity> applications = new ArrayList<>();

    public ProjectEntity(Long account_id, String title) {
        this.account_id = account_id;
        this.title = title;
    }

    /***
     * 애플리케이션 추가
     * @param applicationEntity
     */
    public void addApplication(ApplicationEntity applicationEntity){
        this.applications.add(applicationEntity);
    }
}
