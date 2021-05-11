package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Label {
    int id;
    String prefix;
    String condition;
    String colour;

    public boolean matches(String prefix) {
        return (prefix.equalsIgnoreCase(this.prefix));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id && prefix.equals(label.prefix) && condition.equals(label.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prefix, condition);
    }

    public String getLabel() {
        return Label.this.prefix;
    }
}
