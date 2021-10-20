package com.srm.projectmanager.buildhistory;

public enum BuildStatus {
    PENDING("pending", "준비"),
    RUNNING("running", "빌드 중"),
    SUCCESS("success", "빌드 성공"),
    FAIL("fail", "빌드 실패");

    private String status;
    private String description;

    BuildStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
