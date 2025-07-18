package com.study.mapper;

import com.study.model.StudyApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StudyApplicationMapper {
    void insertApplication(StudyApplication application);
    boolean existsByStudyIdAndUserId(@Param("studyId") Long studyId, @Param("userId") Long userId);
    List<StudyApplication> findByUserId(@Param("userId") Long userId);
    int countByStudyId(@Param("studyId") Long studyId);
    void deleteByStudyIdAndUserId(@Param("studyId") Long studyId, @Param("userId") Long userId);
}
