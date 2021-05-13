package ru.netology.domain;

import java.util.Comparator;

public class DateComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}
