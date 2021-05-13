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

    private Set<Label> labelSet1 = new HashSet<>(List.of(label1,label5,label3));
    private Set<Label> labelSet2 = new HashSet<>(List.of(label2,label4));
    private Set<Label> labelSet3 = new HashSet<>(List.of(label3,label1));
    private Set<Label> labelSet4 = new HashSet<>(List.of(label4,label5));
    private Set<Label> labelSet5 = new HashSet<>(List.of(label5));
    private Set<Label> labelSet6 = new HashSet<>(List.of(label1,label2));

    private Set<User> AssigneesSet1 = new HashSet<>(List.of(user1, user3));
    private Set<User> AssigneesSet2 = new HashSet<>(List.of(user1, user5));
    private Set<User> AssigneesSet3 = new HashSet<>(List.of(user3, user5));

    private Issue issue1 = new Issue(1, "issue1", user1, AssigneesSet1, true, labelSet1, new GregorianCalendar());
    private Issue issue2 = new Issue(2, "issue2", user2, AssigneesSet2, false, labelSet2, new GregorianCalendar());
    private Issue issue3 = new Issue(3, "issue3", user3, AssigneesSet3, true, labelSet3, new GregorianCalendar());
    private Issue issue4 = new Issue(4, "issue4", user4, AssigneesSet1, false, labelSet4, new GregorianCalendar());
    private Issue issue5 = new Issue(5, "issue5", user3, AssigneesSet2, true, labelSet1, new GregorianCalendar());
    private Issue issue6 = new Issue(6, "issue5", user6, AssigneesSet3, true, labelSet6, new GregorianCalendar());

    @BeforeEach
    public void setUpIssues() {
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