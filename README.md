# FileHandler
 File utility class for java I/O: support reading text from file, copy file from one destination to another also
 moving file from one destination to another destination.
 It can also create and delete directory.
```
   String content = FileHandler.readText("fileName.txt");
        System.out.println(s);
        FileHandler.copyFile("filepath", "your_directoriesPath");
        FileHandler.moveFile("filePath", "your directoriesPath");
        FileHandler.deleteFile("fileName");
        FileHandler.deleteDirectory(new File("yourDirectory"));
        FileHandler.fileEncryption(filePath,OutputPath,password);
        FileHandler.filedecryption(filePath,OutputPath,password);
```
