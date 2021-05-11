package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueTest {
    private List<Issue> issues = new ArrayList<>();

    @Test
    void matches() {
        Issue issue = new Issue("issue1");
        boolean actual = issue.matches("ISSUE1");
        assertTrue(actual);
    }
}