package ru.ncedu.java.tasks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sony on 10.09.2015.
 */
public class StringFilterImpl implements StringFilter {
    private Collection<String> string_array;
    private boolean isNullExist = false;

    public StringFilterImpl() {
        this.string_array = new ArrayList<>();
    }

    public StringFilterImpl(Collection<String> st) {
        this.string_array = st;
    }

    @Override
    public void add(String s) {
        String s1 = s;
        if (s == null) {
            if (!this.isNullExist) {
                this.isNullExist = true;
                if (this.string_array.getClass() == new ArrayList<String>().getClass())
                    this.string_array.add(s);
            }
        }
        else {
            s1 = s.toLowerCase();
            if (this.string_array.contains(s1) == false) {
                this.string_array.add(s1);
            }
        }
    }

    @Override
    public boolean remove(String s) {
        String s1 = s;
        if (s == null){
            if (this.isNullExist) {
                this.isNullExist = false;
                if (this.string_array.getClass() == new ArrayList<String>().getClass())
                    this.string_array.remove(s);
                return true;
            }
            else return false;
        }
        else {
            s1 = s.toLowerCase();
            if (this.string_array.contains(s1) == true) {
                this.string_array.remove(s1);
                return true;
            } else return false;
        }
    }

    @Override
    public void removeAll() {
        this.string_array.clear();
    }

    @Override
    public Collection<String> getCollection() {
        return this.string_array;
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        Iterator<String> it2 = this.string_array.iterator();
        Collection<String> result = new ArrayList<>();
        if (chars == "" || chars == null) return it2;
        else {
            chars = chars.toLowerCase();
            while (it2.hasNext()) {
                String temp = it2.next();
                if (temp != null)
                    if (temp.contains(chars)) result.add(temp);
            }
            it2 = result.iterator();
            return it2;
        }
    }

    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        Iterator<String> it = this.string_array.iterator();
        Collection<String> result = new ArrayList<>();
        if (begin == "" || begin == null) return it;
        else {
            begin = begin.toLowerCase();
            while (it.hasNext()) {
                String temp = it.next();
                if (temp != null)
                    if (temp.startsWith(begin)) result.add(temp);
            }
            it = result.iterator();
            return it;
        }
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        Iterator<String> it3 = this.string_array.iterator();
        Collection<String> result = new ArrayList<>();
        if (format == "" || format == null) return it3;
        else {
            format = format.toLowerCase();
            int i = 0;
            while(i < format.length()) {
                if (!(format.substring(i, i+1)).equals("#")) {
                    format = format.substring(0, i) + "\\" + format.substring(i, format.length());
                    i++;
                }
                i++;
            }
            format = format.replace("#", "\\d");
            while (it3.hasNext()) {
                String temp = it3.next();
                if (temp != null) {
                    Pattern p = Pattern.compile(format);
                    Matcher m = p.matcher(temp);
                    if (m.matches()) result.add(temp);
                }
            }
            it3 = result.iterator();
            return it3;
        }
    }

    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        Iterator<String> it7 = this.string_array.iterator();
        Collection<String> result = new ArrayList<>();
        if (pattern == "" || pattern == null) return it7;
        else {
            pattern = pattern.toLowerCase();
            pattern = pattern.replace("*", ".*");
            while (it7.hasNext()) {
                String temp = it7.next();
                if (temp != null) {
                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher(temp);
                    if (m.matches()) result.add(temp);
                }
            }
            it7 = result.iterator();
            return it7;
        }
    }
}
