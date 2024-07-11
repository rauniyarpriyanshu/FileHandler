package com.rauniyarp.fileHandler;

import java.io.*;

public class FileHandler {

    /**
     * This method read text from your file and return it as a String
     *
     * @param filePath location of your file
     * @return String content from file
     */
    public static String readText(String filePath) {
        StringBuilder builder = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
            int read;
            while ((read = reader.read()) != -1) {
                builder.append((char) read);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    /**
     * This method allow you to copy your file to destination
     *
     * @param currentFilePath location of your file
     * @param outputPath      directory path where you want to copy your file
     */
    public static void copyFile(String currentFilePath, String outputPath) {
        File file = new File(currentFilePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + currentFilePath);

        }
        File dire = new File(outputPath);
        if (!dire.exists()) {
            dire.mkdirs();
        }

        File output = new File(dire, file.getPath());
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file)); BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(output))) {
            int data;
            byte[] buffer = new byte[16 * 1024];
            while ((data = stream.read(buffer)) != -1) {
                writer.write(buffer, 0, data);
                writer.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete the file from the path
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        } else {
            throw new RuntimeException("File not found: " + filePath);
        }
    }

    /**
     * This method allow you to move your file to destination
     *
     * @param currentFilePath location of your file
     * @param outputPath      directory path where you want to move your file
     */
    public static void moveFile(String currentFilePath, String outputPath) {
        File file = new File(currentFilePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + currentFilePath);

        }
        File dire = new File(outputPath);
        if (!dire.exists()) {
            dire.mkdirs();
        }

        File output = new File(dire, file.getPath());
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file)); BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(output))) {
            int data;
            byte[] buffer = new byte[16 * 1024];
            while ((data = stream.read(buffer)) != -1) {
                writer.write(buffer, 0, data);
                writer.flush();
            }
            file.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method allow you to create directory to destination
     *
     * @param directoryPath location of your directory
     *
     */
    public static void createDirectory(String directoryPath) {
        File file = new File(directoryPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }


    /**
     * This method allow you to create directory to destination
     *
     * @param directoriesPath location of your directories
     *
     */
    public static void createDirectories(String directoriesPath) {
        File file = new File(directoriesPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**This method allow you to delete directory completely
    * */
    public static boolean deleteDirectory(File file){
        if (file.isDirectory()){
            File[] fileList=file.listFiles();
            if (fileList!=null){
                for (File subFile:fileList){
                    deleteDirectory(subFile);
                }
            }
        }
        return file.delete();
    }

}
