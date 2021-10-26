package com.matheuscordeiro.user;

public interface UserRepository {
    public boolean isUsernameAlreadyExists(String s);

    User insert(User any);
}
