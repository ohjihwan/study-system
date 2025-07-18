package com.study.controller;

import com.study.model.Study;
import com.study.model.StudyApplication;
import com.study.model.User;
import com.study.service.StudyService;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private StudyService studyService;
    
    @GetMapping
    public String myPage(Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName());
        List<Study> myStudies = studyService.findByCreatorId(user.getId());
        List<StudyApplication> myApplications = studyService.findApplicationsByUserId(user.getId());
        
        model.addAttribute("user", user);
        model.addAttribute("myStudies", myStudies);
        model.addAttribute("myApplications", myApplications);
        
        return "mypage/index";
    }
}
