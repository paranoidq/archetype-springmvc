package me.webapp.dao;

import me.webapp.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public interface UserDao {

    List<User> selectAll();

    User selectOne(String username);
}
