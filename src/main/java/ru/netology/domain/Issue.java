package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private String name;
    private User author;
    private Set<User> assignees;
    private boolean statusOpen = true;
    private Set<Label> labels;
    private Calendar creationDate;

    public Issue(String name) {
        this.name = name;
    }

    public boolean matches(String name) {
        return (name.equalsIgnoreCase(this.name));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return name.equals(issue.name) && author.equals(issue.author) && assignees.equals(issue.assignees) && labels.equals(issue.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, assignees, labels);
    }

    @Override
    public int compareTo(Issue o) {
        return id - o.id;
    }
}
