package ru.ncedu.java.tasks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sony on 20.10.2015.
 */
public class WordProcessorImpl implements WordProcessor {
    private String source = null;

    @Override
    public String getText() {
        return this.source;
    }

    @Override
    public void setSource(String src) {
        if (src == null) throw new IllegalArgumentException();
        else source = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        if (srcFile == null)
            throw new IllegalArgumentException();
        else {
            try {
                FileReader fileReader = new FileReader(srcFile);
                //BufferedReader bufferedReader = new BufferedReader(fileReader);
                //String line = "";
                int content;
                StringBuffer result = new StringBuffer();
                //while ((line = bufferedReader.readLine()) != null) {
                //    result.append(line);
                //}
                while ((content = fileReader.read()) != -1) {
                    result.append((char) content);
                }
                source = result.toString();
            }
            catch (IOException e){
                throw new IOException();
            }
        }
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        if (fis == null)
            throw new IllegalArgumentException();
        else {
            try{
                int content;
                StringBuffer result = new StringBuffer();
                while ((content = fis.read()) != -1) {
                    result.append((char) content);
                }
                String res = result.toString();
                source = res;
            }
            catch (IOException e){
                throw new IOException();
            }
        }
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        if (source == null)
            throw new IllegalArgumentException();
        else {
            source = source.toLowerCase();
            String[] words = source.split("\\s+");
            Set<String> result = new HashSet<>();
            if (begin == null || begin == "") {
                for (String st: words)
                    result.add(st);
            }
            else {
                for (String st : words) {
                    if (st.startsWith(begin)) result.add(st);
                }
            }
            return result;
        }
    }
}
