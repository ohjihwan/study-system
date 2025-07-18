package com.study.controller;

import com.study.model.Study;
import com.study.model.User;
import com.study.service.StudyService;
import com.study.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/studies")
public class StudyController {
    
    @Autowired
    private StudyService studyService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String listStudies(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             Model model) {
        List<Study> studies = studyService.findAll(page, size);
        int totalCount = studyService.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        
        model.addAttribute("studies", studies);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);
        
        return "study/list";
    }
    
    @GetMapping("/search")
    public String searchStudies(@RequestParam String keyword,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model) {
        List<Study> studies = studyService.searchStudies(keyword, page, size);
        int totalCount = studyService.getSearchCount(keyword);
        int totalPages = (int) Math.ceil((double) totalCount / size);
        
        model.addAttribute("studies", studies);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);
        
        return "study/search";
    }
    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("study", new Study());
        return "study/create";
    }
    
    @PostMapping("/create")
    public String createStudy(@Valid Study study, BindingResult result,
                             Authentication auth, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "study/create";
        }
        
        User user = userService.findByUsername(auth.getName());
        study.setCreatorId(user.getId());
        study.setCreatorName(user.getName());
        
        studyService.createStudy(study);
        redirectAttributes.addFlashAttribute("message", "스터디가 성공적으로 개설되었습니다.");
        return "redirect:/studies";
    }
    
    @GetMapping("/{id}")
    public String studyDetail(@PathVariable Long id, Model model, Authentication auth) {
        Study study = studyService.findById(id);
        if (study == null) {
            return "redirect:/studies";
        }
        
        boolean hasApplied = false;
        if (auth != null) {
            User user = userService.findByUsername(auth.getName());
            hasApplied = studyService.hasApplied(id, user.getId());
        }
        
        model.addAttribute("study", study);
        model.addAttribute("hasApplied", hasApplied);
        
        return "study/detail";
    }
    
    @PostMapping("/{id}/apply")
    public String applyToStudy(@PathVariable Long id, Authentication auth,
                              RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(auth.getName());
        
        if (studyService.applyToStudy(id, user.getId())) {
            redirectAttributes.addFlashAttribute("message", "스터디 신청이 완료되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "스터디 신청에 실패했습니다. (이미 신청했거나 정원이 초과되었습니다)");
        }
        
        return "redirect:/studies/" + id;
    }
}
