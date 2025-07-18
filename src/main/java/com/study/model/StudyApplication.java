package com.study.model;

import java.time.LocalDateTime;

public class StudyApplication {
    private Long id;
    private Long studyId;
    private Long userId;
    private String userName;
    private String studyTitle;
    private LocalDateTime appliedAt;
    
    // 생성자
    public StudyApplication() {}
    
    public StudyApplication(Long studyId, Long userId) {
        this.studyId = studyId;
        this.userId = userId;
        this.appliedAt = LocalDateTime.now();
    }
    
    // Getter, Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getStudyId() { return studyId; }
    public void setStudyId(Long studyId) { this.studyId = studyId; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getStudyTitle() { return studyTitle; }
    public void setStudyTitle(String studyTitle) { this.studyTitle = studyTitle; }
    
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
}
