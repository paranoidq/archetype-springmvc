package me.webapp.sample.beans;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
