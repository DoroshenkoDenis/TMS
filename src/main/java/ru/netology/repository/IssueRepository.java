package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> findAll() {
        return issues;
    }

    public Issue findById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        throw new NotFoundException("====> Element with id: " + id + " not found <====");
    }

    public void removeById(int id) {
        issues.removeIf(issue -> issue.getId() == id);
    }
}


