package com.marat.controlworkbymarat.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class IssueResponse {
    private Long id;
    private String name;
    private List<Long> orders;
}
