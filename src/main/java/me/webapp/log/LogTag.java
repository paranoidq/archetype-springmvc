package me.webapp.log;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class LogTag {

    public static interface Status {
        String prefix = "[status-";

        String SERVICE = prefix + "service] ";
        String CONTROLLER = prefix + "controller] ";
        String DAO = prefix + "dao] ";
    }

}
