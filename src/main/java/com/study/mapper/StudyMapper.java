package com.study.mapper;

import com.study.model.Study;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StudyMapper {
    void insertStudy(Study study);
    Study findById(@Param("id") Long id);
    List<Study> findAll(@Param("offset") int offset, @Param("limit") int limit);
    List<Study> findByCreatorId(@Param("creatorId") Long creatorId);
    List<Study> searchByTitleOrCreator(@Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);
    int countAll();
    int countBySearch(@Param("keyword") String keyword);
    void updateCurrentParticipants(@Param("id") Long id, @Param("count") int count);
}
