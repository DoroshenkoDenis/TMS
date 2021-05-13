package ru.netology.domain;

import java.util.Comparator;

public class DateComparatorRevers implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getCreationDate().compareTo(o1.getCreationDate());
    }
}
