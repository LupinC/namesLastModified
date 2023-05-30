package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class aaa {

    public List<String> getModifiedFiles(String directoryPath, Date date) {
        List<String> modifiedFiles = new ArrayList<>();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                        if(isModifiedAfter(file, date)){
                            modifiedFiles.add(file.getName());
                        }
                    }
                }
            }
        return modifiedFiles;
    }

    public boolean isModifiedAfter(File file, Date date){

        long givenDate = date.getTime();
        if(file.isDirectory()) {

            File[] files = file.listFiles();
            List<Boolean> check = new ArrayList<>();
            int count = 0;

            for(File file1: files) {
                boolean flag = isModifiedAfter(file1,date);
                check.add(flag);
            }

            for(Boolean a: check) {
                if(a == false) {
                    count++;
                }
            }

            if (count== files.length)
                return false;
        }
        return file.lastModified() >= givenDate;
    }


    public String joinWithNewLine(List<String> items) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String item : items) {
            stringBuilder.append(item);
            stringBuilder.append(System.lineSeparator()); // Adds a new line separator
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String directoryPath = "C:/Users/cy295/OneDrive/Desktop/";
        Date date = new Date(123, 0,1);

        aaa a = new aaa();
        List<String> modifiedFiles = a.getModifiedFiles(directoryPath,date );

        File b = new File(directoryPath);

        System.out.println(b.isDirectory());
        System.out.println(a.isModifiedAfter(new File(directoryPath),date));
        System.out.println("Modified files:");
        for (String fileName : modifiedFiles) {
            System.out.println(fileName);
        }
    }
}
