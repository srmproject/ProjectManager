package com.srm.projectmanager.buildhistory;

import com.srm.projectmanager.common.BaseTimeEntity;
import javax.persistence.*;

@Entity
@Table(name = "Build_history")
public class BuildhistoryEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BuildStatus buildStatus;
}
