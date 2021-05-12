package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private int id;
    private String name;
    private User author;
    private User assignee;
    private boolean statusOpen = true;
    private Set<Label> label;

//    private Date createDate;
//    private Date updateDate;

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
        return name.equals(issue.name) && author.equals(issue.author) && assignee.equals(issue.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, assignee);
    }

}
