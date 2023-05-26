package com.company;


import java.io.File;
import java.util.Date;

public class LastModified {

    public File[] getFiles(String path, Date givenDate){

        File directory = new File(path);

        File[] files = directory.listFiles();
        if(files == null){
            return new File[0];
        }

        return getFiles(files, givenDate);
    }

    public File[] getFiles(File[] files, Date givenDate){
        long givenTime = givenDate.getTime();
        File[] resultFiles = new File[0];

        for (File file : files)
        {
            if(file.lastModified() > givenTime){
                resultFiles = appendToFileArray(resultFiles, file);
            }
        }

        return resultFiles;
    }

    public File[] appendToFileArray(File[] files, File newFile){

        File[] newFiles = new File[files.length+1];
        System.arraycopy(files, 0, newFiles, 0, files.length);

        newFiles[files.length] = newFile;
        return newFiles;

    }

    public String writeToFile(File[] files){
        StringBuilder sb = new StringBuilder();

        for(File file: files){
            sb.append(file.getName()).append(System.lineSeparator());
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        String path1 = "C:\\Users\\cy295\\OneDrive\\Desktop";

        String path2 = "C:\\Users\\TA 204\\Desktop";

        Date givenDate = new Date(123,0,1);

        LastModified a = new LastModified();
        File[] files = a.getFiles(path1, givenDate);
        System.out.println(a.writeToFile(files));
    }
}