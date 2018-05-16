package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;

public interface AddUserMapper {
    int saveUser(@Param("user")User user);
}
