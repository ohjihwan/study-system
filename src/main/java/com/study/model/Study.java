package com.study.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Study {
    private Long id;
    
    @NotBlank(message = "제목은 필수입니다")
    private String title;
    
    @NotBlank(message = "설명은 필수입니다")
    private String description;
    
    @NotNull(message = "모집 인원은 필수입니다")
    @Min(value = 1, message = "모집 인원은 1명 이상이어야 합니다")
    private Integer maxParticipants;
    
    private Integer currentParticipants = 0;
    
    @NotNull(message = "마감일은 필수입니다")
    private LocalDateTime deadline;
    
    private Long creatorId;
    private String creatorName;
    private LocalDateTime createdAt;
    
    // 생성자
    public Study() {}
    
    // Getter, Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }
    
    public Integer getCurrentParticipants() { return currentParticipants; }
    public void setCurrentParticipants(Integer currentParticipants) { this.currentParticipants = currentParticipants; }
    
    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    
    public Long getCreatorId() { return creatorId; }
    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }
    
    public String getCreatorName() { return creatorName; }
    public void setCreatorName(String creatorName) { this.creatorName = creatorName; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public boolean isFull() {
        return currentParticipants >= maxParticipants;
    }
    
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(deadline);
    }
}
