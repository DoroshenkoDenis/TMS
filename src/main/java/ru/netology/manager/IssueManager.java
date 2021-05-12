package ru.netology.manager;

import lombok.Data;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

@Data
public class IssueManager {
    private IssueRepository repository = new IssueRepository();

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> getAll() {
        return repository.findAll();
    }

    public Issue getById(int id) {
        return repository.findById(id);
    }

    // закрытие Issue по id
    public void closeIssue(int id) {
        Issue issue = repository.findById(id);
        issue.setStatusOpen(false);
    }

    // открытие Issue по id
    public void openIssue(int id) {
        Issue issue = repository.findById(id);
        issue.setStatusOpen(true);
    }

    // создание списка открытых Issue
    public List<Issue> showOpenIssues() {
        List<Issue> openIssue = new ArrayList<>();
        for (Issue issue : getAll()) {
            if (issue.isStatusOpen()) {
                openIssue.add(issue);
            }
        }
        return openIssue;
    }

    // создание списка закрытых Issue
    public List<Issue> showClosedIssues() {
        List<Issue> closedIssue = new ArrayList<>();
        for (Issue item : getAll()) {
            if (!item.isStatusOpen()) {
                closedIssue.add(item);
            }
        }
        return closedIssue;
    }

    //  фильтрация по Label
    public List<Issue> filterByLabel(Label label) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : getAll()) {
            if (issue.getLabel().equals(label)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    // фильтрация по author
    public List<Issue> filterByAuthor(Predicate<String> name) {
        List<Issue> filteredByAuthor = new ArrayList<>();
        for (Issue issue : getAll()) {
            if (name.test(issue.getAuthor().getName())) {
                filteredByAuthor.add(issue);
            }
        }
        return filteredByAuthor;
    }
}
//   TODO SORT

