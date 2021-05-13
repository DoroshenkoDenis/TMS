package ru.netology.manager;

import lombok.Data;
import ru.netology.domain.*;
import ru.netology.repository.IssueRepository;

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
        for (Issue issue : getAll()) {
            if (!issue.isStatusOpen()) {
                closedIssue.add(issue);
            }
        }
        return closedIssue;
    }

    //  фильтрация по полю prefix коллекции Set<Label>
    public List<Issue> filterByLabelPrefix(Predicate<String> prefix) {
        List<Issue> filteredByLabelPrefix = new ArrayList<>();
        for (Issue issue : getAll()) {
            for (Label label : issue.getLabels()) {
                if (prefix.test(label.getPrefix())) {
                    filteredByLabelPrefix.add(issue);
                }
            }
        }
        return filteredByLabelPrefix;
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

    // фильтрация по assignee
    public List<Issue> filterByAssignees(Predicate<String> name) {
        List<Issue> filteredByAssignees = new ArrayList<>();
        for (Issue issue : getAll()) {
            for (User assignee : issue.getAssignees()) {
                if (name.test(assignee.getName())) {
                    filteredByAssignees.add(issue);
                }
            }
        }
        return filteredByAssignees;
    }

    // сортировка по ID
    public List<Issue> sortById() {
        List<Issue> issues = getAll();
        Collections.sort(issues);
        return issues;
    }

    //    // сортировка по дате создания
    public List<Issue> sortByCreationDate(DateComparator comparator) {
        List<Issue> sortList = getAll();
        sortList.sort(comparator);
        return sortList;
    }

    // сортировка по дате создания в обратном порядке
    public List<Issue> sortByCreationDateRevers(DateComparatorRevers comparator) {
        List<Issue> sortListRevers = getAll();
        sortListRevers.sort(comparator);
        return sortListRevers;
    }

}


