package dao;

import entity.User;

public interface LoginMapper {
    User login(String name);
}
