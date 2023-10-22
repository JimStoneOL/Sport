package com.marat.controlworkbymarat.repository;

import com.marat.controlworkbymarat.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Long> {
}
