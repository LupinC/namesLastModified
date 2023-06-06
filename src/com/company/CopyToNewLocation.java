package com.company;

import java.io.File;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyToNewLocation {


    public List<String> extract(String input){
        String[] e = input.split("\\n");
        List<String> out = new ArrayList<>();
        out.addAll(Arrays.asList(e));
        return out;
    }

    public void cutAndPasteFiles(List<File> fileList, String destinationDirectory) {
        File destinationDir = new File(destinationDirectory);

        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }

        for (File file : fileList) {
            try {
                File destinationFile = new File(destinationDir, file.getName());
                Path sourcePath = file.toPath();
                Path destinationPath = destinationFile.toPath();

                // Cut (Move) the file to the destination directory
                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Moved file: " + file.getAbsolutePath() + " to " + destinationFile.getAbsolutePath());
            } catch (AccessDeniedException e){
                System.out.println("please run as admin");
            }catch (Exception e) {
                System.err.println("Failed to move file: " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }
}
