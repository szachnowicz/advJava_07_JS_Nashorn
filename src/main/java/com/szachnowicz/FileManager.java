package com.szachnowicz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    final public static String RES_PATH = "src/main/resources";

    public static String readTextFile(String strPath) {
        try {
            BufferedReader objBufferedReader = new BufferedReader(new FileReader(strPath));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = objBufferedReader.readLine();

            while (strLine != null) {
                objStringBuilder.append(strLine);
                objStringBuilder.append(System.lineSeparator());
                strLine = objBufferedReader.readLine();
            }
            objBufferedReader.close();
            return objStringBuilder.toString();
        } catch (Exception ex) {
        }
        return "";
    }

    public static void writeToFile(String strPath, String strFileContent) {
        try {
            PrintWriter objPrintWriter = new PrintWriter(strPath);
            objPrintWriter.write(strFileContent);
            objPrintWriter.close();
        } catch (Exception e) {
        }
    }

    public static ArrayList<String> getAllFileNamesInDirectory(String strDirectory) {
        ArrayList<String> listFileNames = new ArrayList<>();
        try {
            File objDirectory = new File(strDirectory);
            File[] arrFiles = objDirectory.listFiles();
            for (File objFile : arrFiles) {
                if (objFile.isFile()) {
                    listFileNames.add(objFile.getName());
                }
            }
        } catch (Exception e) {
        }
        return listFileNames;
    }


    public List<String> getAllJsRecources() {
        List<String> pathList = new ArrayList<>();
        File resourcesDirectory = new File(RES_PATH);


        for (String path : resourcesDirectory.list()) {
        pathList.add(RES_PATH+"//"+path);
        }

        return pathList;
    }

    public List<String> getOnlyFileName(List<String> fullpaths)
    {
        List<String> fileNameList = new ArrayList<>();
        for (String fullpath : fullpaths) {
            fileNameList.add(fullpath.substring(RES_PATH.length(), fullpath.lastIndexOf(".")));
        }

        return fileNameList;
    }



}
