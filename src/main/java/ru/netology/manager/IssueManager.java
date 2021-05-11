package ru.netology.manager;

import lombok.Data;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
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
        repository.removeById(id);
        issue.setStatusOpen(false);
        repository.save(issue);
    }

    // открытие Issue по id
    public void openIssue(int id) {
        Issue issue = repository.findById(id);
        repository.removeById(id);
        issue.setStatusOpen(true);
        repository.save(issue);
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

    // фильтрация по лэйблу
    public List<Issue> filterByLabelType(Predicate<String> prefix) {
        List<Issue> filteredByLabel = new ArrayList<>();
        for (Issue issue : getAll()) {
            if (prefix.test(issue.getLabel().getPrefix())) {
                filteredByLabel.add(issue);
            }
        }
        return filteredByLabel;
    }
}
//   TODO SORT
