package com.rauniyarp.fileHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        File output = new File(dire, file.getName());
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
          boolean z= file.delete();
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

        File output = new File(dire, file.getName());
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file)); BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(output))) {
            int data;
            byte[] buffer = new byte[16 * 1024];
            while ((data = stream.read(buffer)) != -1) {
                writer.write(buffer, 0, data);
                writer.flush();
            }
            boolean z=file.delete();
            file.deleteOnExit();
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
     * @param directoriesPath location of your directories
     *
     */
    public static void createDirectories(String directoriesPath) {
        File file = new File(directoriesPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void fileEncryption(File file, String outputPath, String password) throws FileNotFoundException {

        if (!file.exists()) {
            throw new FileNotFoundException("Invalid file path!");
        }
        StringBuilder content = new StringBuilder();
        try (BufferedInputStream reader = new BufferedInputStream(Files.newInputStream(file.toPath())); BufferedOutputStream writer = new BufferedOutputStream(Files.newOutputStream(Paths.get(outputPath)))) {
            int data;
            byte[] buffer = new byte[16 * 1024];
            while ((data = reader.read(buffer)) != -1) {
                content.append(new String(buffer, 0, data));
            }
            FileTextTransformation transformation = new FileTextTransformation();
            String output = transformation.encrypt(content.toString(), password);

            writer.write(output.getBytes());
            writer.flush();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void fileDecryption(File file, String outputPath, String password) throws FileNotFoundException {

        if (!file.exists()) {
            throw new FileNotFoundException("Invalid file path!");
        }
        StringBuilder content = new StringBuilder();
        try (BufferedInputStream reader = new BufferedInputStream(Files.newInputStream(file.toPath())); BufferedOutputStream writer = new BufferedOutputStream(Files.newOutputStream(Paths.get(outputPath)))) {
            int data;
            byte[] buffer = new byte[16 * 1024];
            while ((data = reader.read(buffer)) != -1) {
                content.append(new String(buffer, 0, data));
            }
            FileTextTransformation transformation = new FileTextTransformation();
            String output = transformation.decrypt(content.toString(), password);

            writer.write(output.getBytes());
            writer.flush();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * This method allow you to delete directory completely
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
