package com.marat.controlworkbymarat.facade;

import com.marat.controlworkbymarat.dto.IssueDTO;
import com.marat.controlworkbymarat.entity.Issue;
import com.marat.controlworkbymarat.entity.Order;
import com.marat.controlworkbymarat.payload.response.IssueResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IssueFacade {

    public Issue issueDTOToIssue(IssueDTO issueDTO){
        Issue issue=new Issue();
        issue.setName(issueDTO.getName());
        return issue;
    }

    public IssueResponse issueToIssueResponse(Issue issue){
        IssueResponse issueResponse=new IssueResponse();
        issueResponse.setId(issue.getId());
        issueResponse.setName(issue.getName());

        List<Long> orders=new ArrayList<>();
        for(Order order:issue.getOrderList()){
            orders.add(order.getId());
        }

        issueResponse.setOrders(orders);

        return issueResponse;
    }

    public List<IssueResponse> issueListToIssueResponseList(List<Issue> issueList){
        List<IssueResponse> issueResponseList=new ArrayList<>();
        for(Issue issue:issueList){
            issueResponseList.add(issueToIssueResponse(issue));
        }
        return issueResponseList;
    }

}
