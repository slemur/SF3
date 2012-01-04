package net.aurora.util;

import net.aurora.Application;

import java.util.ArrayList;

/**
 * @author Rick van Biljouw
 *         Date: 29-11-11
 *         Time: 5:09
 */
public class Logger {
    private static ArrayList<String> logData = new ArrayList<String>();

    public static void log(Object source, String message) {
        if(logData.size() > 100) logData.clear();

        logData.add("[" + source.getClass().getSimpleName() + "] " + message);
        /*
        if(Application.getUI() != null && Application.getUI().getDebugList() != null) {
            Application.getUI().getDebugList().setListData(logData.toArray(new String[logData.size()]));
        } */
    }
}
