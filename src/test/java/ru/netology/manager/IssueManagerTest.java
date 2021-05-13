package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.User;
import ru.netology.exception.NotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueManager manager = new IssueManager();
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

    private Set<Label> labelSet1 = new HashSet<>(List.of(label1, label5));
    private Set<Label> labelSet2 = new HashSet<>(List.of(label2, label4));
    private Set<Label> labelSet3 = new HashSet<>(List.of(label3, label1));
    private Set<Label> labelSet4 = new HashSet<>(List.of(label4, label5));
    private Set<Label> labelSet5 = new HashSet<>(List.of(label5));
    private Set<Label> labelSet6 = new HashSet<>(List.of(label1, label2));

    private Set<User> AssigneesSet1 = new HashSet<>(List.of(user1, user3));
    private Set<User> AssigneesSet2 = new HashSet<>(List.of(user1, user5));
    private Set<User> AssigneesSet3 = new HashSet<>(List.of(user3, user5));

    private Issue issue1 = new Issue(1, "issue1", user1, AssigneesSet1, true, labelSet1);
    private Issue issue2 = new Issue(2, "issue2", user2, AssigneesSet2, false, labelSet2);
    private Issue issue3 = new Issue(3, "issue3", user3, AssigneesSet3, true, labelSet3);
    private Issue issue4 = new Issue(4, "issue4", user4, AssigneesSet1, false, labelSet4);
    private Issue issue5 = new Issue(5, "issue5", user3, AssigneesSet2, true, labelSet1);
    private Issue issue6 = new Issue(6, "issue5", user6, AssigneesSet3, true, labelSet6);

    @BeforeEach
    public void setUpIssues() {
        issues.addAll(List.of(issue1, issue2, issue3, issue4, issue5));
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
    }

    @Test
    void add() {
        manager.add(issue6);
        int expected = 6;
        int actual = manager.getAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void FindAll() {
        List<Issue> expected = issues;
        List<Issue> actual = manager.getAll();
        assertIterableEquals(expected, actual);
    }

    @Test
    void getById() {
        assertEquals(issue3, manager.getById(3));
    }

    @Test
    void getByIdIfNotExists() {
        int id = 9;
        assertThrows(NotFoundException.class, () -> manager.getById(id));
    }

    @Test
    void shouCloseIssue() {
        manager.closeIssue(5);
        assertFalse(issue5.isStatusOpen());
    }

    @Test
    void shouOpenIssue() {
        manager.openIssue(2);
        assertTrue(issue2.isStatusOpen());
    }

    @Test
    void showOpenIssues() {
        List<Issue> actual = manager.showOpenIssues();
        List<Issue> expected = List.of(issue1, issue3, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void showClosedIssues() {
        List<Issue> actual = manager.showClosedIssues();
        List<Issue> expected = List.of(issue2, issue4);
        assertEquals(expected, actual);
    }

    @Test
    void filterByLabelPrefix() {
        List<Issue> actual = manager.filterByLabelPrefix(prefix -> prefix.equals("status"));
        List<Issue> expected = List.of(issue2, issue4);
        assertEquals(expected, actual);
        System.out.println(actual);
        System.out.println(expected);
    }

    @Test
    void filterByAuthor() {
        List<Issue> actual = manager.filterByAuthor(name -> name.equals("Smith"));
        List<Issue> expected = List.of(issue3, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void filterByAssignee() {
        List<Issue> actual = manager.filterByAssignees(name -> name.equals("Doroshenko"));
        List<Issue> expected = List.of(issue1, issue2, issue4, issue5);
        assertEquals(actual, expected);
    }

}