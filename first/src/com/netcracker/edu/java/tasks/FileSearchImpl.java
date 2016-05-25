package com.netcracker.edu.java.tasks;

import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;

/**
 * Created by Sony on 30.07.2015.
 */
public class FileSearchImpl implements FileSearch {

    private String fileNameToSearch;
    private List<File> result = new ArrayList<File>();
    private int fileNameMaxLength;
    private int parentNameMaxLength = 3;
    private boolean flag_all = false;

    public FileSearchImpl(String fileNameToSearch) {
        if (fileNameToSearch.equals("*") || fileNameToSearch.equals("?")) flag_all = true;
        this.fileNameToSearch = fileNameToSearch;
        this.fileNameMaxLength = fileNameToSearch.length();
    }

   // public String getFileNameToSearch() {
   //     return this.fileNameToSearch;
   // }

    @Override
    public void getResult() {
        if (result.size() == 0) {
            System.out.println("I have not found anything :(");
        }
        else {
            int a = -(this.fileNameMaxLength + 5);
            int b = -(this.parentNameMaxLength + 5);
            System.out.println("I have found:");
            for (int i = 0; i < result.size(); i++) {
                File temp_file = result.get(i);
                Date timeLastModification = new Date(temp_file.lastModified());
                String time_answer = timeLastModification.toString();
                if (temp_file.isDirectory()) {
                    System.out.printf("%s", "Name: ");
                    System.out.printf("%" + a + "s", temp_file.getName());
                    System.out.printf("%s%s", "It is a Directory", "\tParent: ");
                    System.out.printf("%" + b + "s", temp_file.getParent());
                    System.out.printf("%s%-6s%s%s%s%n", "Size: ", temp_file.length(), "bytes", "\tModification date: ", time_answer);
                } else {
                    System.out.printf("%s", "Name: ");
                    System.out.printf("%" + a + "s", temp_file.getName());
                    System.out.printf("%-17s%s", "It is a File", "\tParent: ");
                    System.out.printf("%" + b + "s", temp_file.getParent());
                    System.out.printf("%s%-6s%s%s%s%n", "Size: ", temp_file.length(), "bytes", "\tModification date: ", time_answer);
                }
            }
        }
    }

    @Override
    public void search(String directoryToSearch) {
        File directory = new File(directoryToSearch);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().contains(fileNameToSearch) || flag_all == true || pathname.isDirectory()) return true;
                else return false;
            }
        };
        File[] list_files = directory.listFiles(filter);
        for (int i = 0; i < list_files.length; i++){
            if (list_files[i].isFile() || flag_all == true) {
                result.add(list_files[i]);
                if (list_files[i].getName().length() > this.fileNameMaxLength)
                    this.fileNameMaxLength = list_files[i].getName().length();
                if (list_files[i].getParent().length() > this.parentNameMaxLength)
                    this.parentNameMaxLength = list_files[i].getParent().length();
            }
            if (list_files[i].isDirectory()) {
                search(list_files[i].getPath());
            }
        }
    }
}
