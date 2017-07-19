package io.impl;

import util.PropertiesHolder;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Elereman on 18.07.2017.
 */
public class FolderIndexer{
    private static final String SEPARATOR = "::";
    private static File indexFile = new File(PropertiesHolder.getClassPath() + PropertiesHolder.getIndexFileName());
    private static long lastIndex;

    static {
        createFileIfNotExists(indexFile);
        if(indexFile.exists()) {
            try {
                Scanner scanner = new Scanner(new FileInputStream(indexFile));
                String lastIndexString = "";
                while (scanner.hasNextLine()) {
                    lastIndexString = scanner.nextLine();
                }
                lastIndex = Integer.parseInt(lastIndexString.substring(0, lastIndexString.lastIndexOf("::")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void indexFile(File file) {
        try {
            if (!isIndexed(file)) {
                lastIndex++;
                FileWriter fileWriter = new FileWriter(indexFile, true);
                fileWriter.append(generateString(file));
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void indexFolder(File folder) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                indexFile(file);
            }
        } else {
            indexFile(folder);
        }
    }

    private static String generateString(File file) {
        StringBuffer res = new StringBuffer();
        res.append(lastIndex);
        res.append(PropertiesHolder.getSeparator());
        res.append(file.getAbsolutePath());
        res.append("\n");
        return res.toString();
    }

    private static void createFileIfNotExists(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isIndexed(File file) {
        StringBuffer filePath;
        try {
            Scanner in = new Scanner(new FileInputStream(indexFile));
            String fileTest = "";
            while (in.hasNextLine()) {
                filePath = new StringBuffer(in.nextLine());
                filePath.delete(0, filePath.lastIndexOf("::") + 2);
                if (file.getAbsolutePath().equals(filePath.toString())) {
                    fileTest = file.getAbsolutePath();
                    break;
                }
            }
            return file.getAbsolutePath().equals(fileTest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
