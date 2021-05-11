package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void matches() {
        User user = new User(1, "Doroshenko", true);
        boolean actual = user.matches("doRoShenKo");
        assertTrue(actual);
    }
}
