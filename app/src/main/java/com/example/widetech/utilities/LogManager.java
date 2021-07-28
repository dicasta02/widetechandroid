package com.example.widetech.utilities;

import android.util.Log;

import com.example.widetech.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class LogManager {
    private final boolean DEBUG = BuildConfig.SHOW_LOG;

    private String className = "UnknownClass";

    //Filter to make print
    private final boolean FILTER_BY_CLASS = false;
    private final boolean IGNORE_BY_CLASS = false;
    private String[] filterByClass = new String[]{};
    private String[] ignoreByClass = new String[]{};

    public LogManager(Class<?> c) {
        if (!c.getSimpleName().isEmpty())
            className = c.getSimpleName();
    }

    public void setClassName(Class<?> c) {
        className = c.getSimpleName();
    }

    public String getClassName() {
        return className;
    }

    private boolean canPrint() {
        boolean canPrint = false;
        if (DEBUG) {
            if (FILTER_BY_CLASS)
                canPrint = Arrays.asList(filterByClass).contains(className);
            else if (IGNORE_BY_CLASS)
                canPrint = !Arrays.asList(ignoreByClass).contains(className);
            else
                canPrint = true;
        }

        return canPrint;
    }

    public void printError(Object... args) {
        if (canPrint())
            Log.e(buildTag(), buildMsg(args));
    }

    public void printWarn(Object... args) {
        if (canPrint())
            Log.w(buildTag(), buildMsg(args));
    }

    public void printInfo(Object... args) {
        if (canPrint())
            Log.i(buildTag(), buildMsg(args));
    }

    public void printDebug(Object... args) {
        if (canPrint())
            Log.d(buildTag(), buildMsg(args));
    }

    public void printVerbose(Object... args) {
        if (canPrint())
            Log.v(buildTag(), buildMsg(args));
    }

    public void printError(String msg, Throwable t) {
        if (DEBUG)
            printError(buildTag(), msg, stackToString(t));
    }

    public void printWarn(String msg, Throwable t) {
        if (DEBUG) printWarn(buildTag(), msg, stackToString(t));
    }

    public void printInfo(String msg, Throwable t) {
        if (DEBUG) printInfo(buildTag(), msg, stackToString(t));
    }

    public void printDebug(String msg, Throwable t) {
        if (DEBUG) printDebug(buildTag(), msg, stackToString(t));
    }

    public void printVerbose(String msg, Throwable t) {
        if (DEBUG) printVerbose(buildTag(), msg, stackToString(t));
    }

    private String buildTag() {
        String tag;
        StringBuilder b = new StringBuilder(20);
        b.append(Constants.REFERENCE_ID);
        b.append(" -> ");
        b.append(getClassName());

        StackTraceElement stackEntry = Thread.currentThread().getStackTrace()[4];

        if (stackEntry != null) {
            b.append('.');
            b.append(stackEntry.getMethodName());
            b.append('(');
            b.append(stackEntry.getLineNumber());
            b.append(')');
        }

        tag = b.toString();

        return tag;
    }

    private String buildMsg(Object... args) {
        if (args != null) {
            StringBuilder b = new StringBuilder(args.length * 10);
            b.append("*****").append(DateUtils.getCurrentDateTimeFormat()).append("*****\n");

            for (Object arg : args) {
                if (arg == null) {
                    b.append("null");
                } else {
                    b.append(arg).append(" ");
                }
            }

            return b.toString().trim();
        }

        return "null";
    }

    private String stackToString(Throwable t) {
        if (t != null) {
            StringWriter errors = new StringWriter();
            t.printStackTrace(new PrintWriter(errors));

            return errors.toString();
        }

        return "";
    }
}
