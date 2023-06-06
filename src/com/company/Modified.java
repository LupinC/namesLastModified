package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Modified {

    public List<File> getModifiedFiles(String directoryPath, Date date) {
        List<File> modifiedFiles = new ArrayList<>();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                try{for (File file : files) {
                        if(isModifiedAfter(file, date)){
                            modifiedFiles.add(file);
                        }
                    }
                }catch (RuntimeException ignored){}
            }
        }
        return modifiedFiles;
    }

    public boolean isModifiedAfter(File file, Date date){
        long givenDate = date.getTime();

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files != null) {
                boolean modified = false;
                for (File subFile : files) {
                    if (isModifiedAfter(subFile, date)) {
                        modified = true;
                        break;
                    }
                }

                if (!modified) {
                    return false;
                }
            }
        }

        return file.lastModified() >= givenDate;
    }



    public static void main(String[] args) {
        String directoryPath = "C:/Users/cy295/OneDrive/Desktop/转生成为史莱姆";
        Date date = new Date(123, 0,1);

        Modified a = new Modified();
        List<File> modifiedFiles = a.getModifiedFiles(directoryPath,date );

        File b = new File(directoryPath);

        System.out.println(b.isDirectory());
        System.out.println(a.isModifiedAfter(new File(directoryPath),date));
        System.out.println("Modified files:");
        for (File fileName : modifiedFiles) {
            System.out.println(fileName.getName());
        }
    }
}
