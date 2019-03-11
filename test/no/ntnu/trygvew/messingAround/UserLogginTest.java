package no.ntnu.trygvew.messingAround;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserLogginTest {

    @Test
    public void loadUser() {
        UserLoggin userLoggin = new UserLoggin();

        userLoggin.loadUser("a", "a");
    }

    @Test
    public void saveUser() {
    }

    @Test
    public void isValidLoggin() {
    }

    @Test
    public void isValidUsername() {
    }

    @Test
    public void createUser() {
    }
}