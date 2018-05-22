package me.webapp.log;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class LogTag {

    public static interface Statis {
        String prefix = "[statis-";

        String SERVICE = prefix + "service] ";
        String CONTROLLER = prefix + "controller] ";
        String DAO = prefix + "dao] ";

        String METHOD_TIMING = prefix + "methodTiming] ";
    }


    public static interface LOGGING {
        String prefix = "[logging-";

        String METHOD_INVOKE = prefix + "methodInvoke] ";
    }

}
