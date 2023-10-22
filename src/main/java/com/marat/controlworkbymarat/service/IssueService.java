package com.marat.controlworkbymarat.service;

import com.marat.controlworkbymarat.dto.IssueDTO;
import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.entity.Issue;
import com.marat.controlworkbymarat.facade.IssueFacade;
import com.marat.controlworkbymarat.payload.response.IssueResponse;
import com.marat.controlworkbymarat.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final IssueFacade issueFacade;

    @Autowired
    public IssueService(IssueRepository issueRepository, IssueFacade issueFacade) {
        this.issueRepository = issueRepository;
        this.issueFacade = issueFacade;
    }

    public Message createIssue(IssueDTO issueDTO){
        issueRepository.save(issueFacade.issueDTOToIssue(issueDTO));
        return new Message("Пункт выдачи успешно создан");
    }

    public IssueResponse getIssueById(Long id){
        Optional<Issue> issueOptional=issueRepository.findById(id);
        if(issueOptional.isPresent()){
            return issueFacade.issueToIssueResponse(issueOptional.get());
        }
        return null;
    }

    public List<IssueResponse> getIssueList(){
        return issueFacade.issueListToIssueResponseList(issueRepository.findAll());
    }
}
