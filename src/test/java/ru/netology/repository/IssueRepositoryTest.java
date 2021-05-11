package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.User;
import ru.netology.exception.NotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();
    private List<Issue> issues = new ArrayList<>();
    private Set<Label> labelSet = new HashSet<>();

    private User user1 = new User(1, "Doroshenko", true);
    private User user2 = new User(2, "Ivanov", false);
    private User user3 = new User(3, "Smith", true);
    private User user4 = new User(4, "Sidorov", false);
    private User user5 = new User(5, "Kim", true);
    private User user6 = new User(6, "Kim", true);

    private Label label1 = new Label(1, "type", "bug", "#E32636");
    private Label label2 = new Label(2, "component", "Platform", "#FFBF00");
    private Label label3 = new Label(3, "theme", "concurrency", "#9966CC");
    private Label label4 = new Label(4, "status", "waiting-for-feedback", "#FAEBD7");
    private Label label5 = new Label(5, "type", "question", "#8DB600");

    private Issue issue1 = new Issue(1, "issue1", user1, user5, true, label1);
    private Issue issue2 = new Issue(2, "issue2", user2, null, false, label2);
    private Issue issue3 = new Issue(3, "issue3", user3, user3, true, label3);
    private Issue issue4 = new Issue(4, "issue4", user4, user1, false, label4);
    private Issue issue5 = new Issue(5, "issue5", user5, null, true, label5);
    private Issue issue6 = new Issue(6, "issue6", user6, null, true, label2);

    @BeforeEach
    public void setUpIssues() {
        labelSet.addAll(List.of(label1, label2, label3, label4, label5));
        issues.addAll(List.of(issue1, issue2, issue3, issue4, issue5));
        repository.save(issue1);
        repository.save(issue2);
        repository.save(issue3);
        repository.save(issue4);
        repository.save(issue5);
    }

    @Test
    void save() {
        int expected = 6;
        repository.save(issue6);
        int actual = repository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void FindAll() {
        List<Issue> expected = issues;
        List<Issue> actual = repository.findAll();
        assertIterableEquals(expected, actual);
    }

    @Test
    void removeById() {
        repository.removeById(1);
        List<Issue> actual = repository.findAll();
        List<Issue> expected = List.of(issue2, issue3, issue4, issue5);
        assertIterableEquals(expected, actual);
    }

    @Test
    void findById() {
        Issue actual = repository.findById(3);
        Issue expected = issue3;
        assertEquals(expected, actual);
    }

    @Test
    void findByIdIfNotExists() {
        int id = 9;
        assertThrows(NotFoundException.class, () -> repository.findById(id));
    }

    @Test
    void removeByIdIfNotExists() {
        int id = 9;
        repository.removeById(id);
    }
}