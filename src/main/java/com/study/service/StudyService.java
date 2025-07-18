package com.study.service;

import com.study.mapper.StudyMapper;
import com.study.mapper.StudyApplicationMapper;
import com.study.model.Study;
import com.study.model.StudyApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudyService {
    
    @Autowired
    private StudyMapper studyMapper;
    
    @Autowired
    private StudyApplicationMapper applicationMapper;
    
    public void createStudy(Study study) {
        studyMapper.insertStudy(study);
    }
    
    public Study findById(Long id) {
        Study study = studyMapper.findById(id);
        if (study != null) {
            int currentCount = applicationMapper.countByStudyId(id);
            study.setCurrentParticipants(currentCount);
        }
        return study;
    }
    
    public List<Study> findAll(int page, int size) {
        int offset = page * size;
        List<Study> studies = studyMapper.findAll(offset, size);
        updateCurrentParticipants(studies);
        return studies;
    }
    
    public List<Study> findByCreatorId(Long creatorId) {
        List<Study> studies = studyMapper.findByCreatorId(creatorId);
        updateCurrentParticipants(studies);
        return studies;
    }
    
    public List<Study> searchStudies(String keyword, int page, int size) {
        int offset = page * size;
        List<Study> studies = studyMapper.searchByTitleOrCreator(keyword, offset, size);
        updateCurrentParticipants(studies);
        return studies;
    }
    
    public int getTotalCount() {
        return studyMapper.countAll();
    }
    
    public int getSearchCount(String keyword) {
        return studyMapper.countBySearch(keyword);
    }
    
    @Transactional
    public boolean applyToStudy(Long studyId, Long userId) {
        if (applicationMapper.existsByStudyIdAndUserId(studyId, userId)) {
            return false;
        }
        
        Study study = findById(studyId);
        if (study == null || study.isFull() || study.isExpired()) {
            return false;
        }
        
        StudyApplication application = new StudyApplication(studyId, userId);
        applicationMapper.insertApplication(application);
        return true;
    }
    
    public List<StudyApplication> findApplicationsByUserId(Long userId) {
        return applicationMapper.findByUserId(userId);
    }
    
    public boolean hasApplied(Long studyId, Long userId) {
        return applicationMapper.existsByStudyIdAndUserId(studyId, userId);
    }
    
    private void updateCurrentParticipants(List<Study> studies) {
        for (Study study : studies) {
            int currentCount = applicationMapper.countByStudyId(study.getId());
            study.setCurrentParticipants(currentCount);
        }
    }
}
