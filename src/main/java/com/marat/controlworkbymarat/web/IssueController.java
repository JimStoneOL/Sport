package com.marat.controlworkbymarat.web;

import com.marat.controlworkbymarat.dto.IssueDTO;
import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.payload.response.IssueResponse;
import com.marat.controlworkbymarat.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
@CrossOrigin(origins = "*")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @PostMapping("/create")
    public Message createIssue(@RequestBody IssueDTO issueDTO){
        return issueService.createIssue(issueDTO);

    }

    @GetMapping("/get/{id}")
    public IssueResponse getIssue(@PathVariable Long id){
        return issueService.getIssueById(id);
    }

    @GetMapping("/get/all")
    public List<IssueResponse> getAllIssues(){
        return issueService.getIssueList();
    }
}
