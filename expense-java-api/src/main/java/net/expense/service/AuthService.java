package net.expense.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public String login(String email, String password);

}
