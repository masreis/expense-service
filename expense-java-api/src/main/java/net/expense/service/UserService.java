package net.expense.service;

import net.expense.model.User;

public interface UserService {

    public User findByEmail(String email);

    public User save(User user);

}
