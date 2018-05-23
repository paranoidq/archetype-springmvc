package me.webapp.dao.mysql;

import me.webapp.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Repository
public interface UserDao {

    List<User> selectAll();

    User selectOne(String username);
}