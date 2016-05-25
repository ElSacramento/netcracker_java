package ru.ncedu.java.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sony on 25.10.2015.
 */
public class CheckerImpl implements Checker{
    @Override
    public Pattern getPLSQLNamesPattern() {
        Pattern result = Pattern.compile("(?=^.{1,30}$)(^[A-Za-z][A-Za-z0-9_$]$)");
        return result;
    }

    @Override
    public Pattern getHrefURLPattern() {
        Pattern result = Pattern.compile("^<\\s*[Aa]\\s+[hrefHREF]{4}\\s*[=]\\s*(\"[A-Za-z0-9_-]\")\\s*([/>]|[>])$");
        return result;
    }

    @Override
    public Pattern getEMailPattern() {
        Pattern result = Pattern.compile("(?=^.{1,22}@.*$)(^(([A-Za-z0-9]+[A-Za-z0-9_.-]*[A-Za-z0-9]+)|([A-Za-z0-9]))@(([a-zA-Z0-9]+[A-Za-z0-9-]*[A-Za-z0-9]+)\\.)+(ru|com|net|org)$)");
        return result;
    }

    @Override
    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
        try {
            if (inputString == null && pattern == null) return true;
            Matcher m = pattern.matcher(inputString);
            return m.matches();
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        try {
            Matcher m;
            String[] test = inputString.toString().split("[\\s+]");
            List<String> result = new ArrayList<>();
            for (String s: test){
                m = pattern.matcher(s);
                if (m.matches()) result.add(s);
            }
            return result;
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }
}
