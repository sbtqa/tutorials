package ru.sbtqa.patterns.structural.bridge.data;

import java.io.File;

public class FileDataSource implements DataSource {

    private File file;

    public FileDataSource(String path) {
        this.file = new File(path);
    }

    @Override
    public String getParameter(String param) {
        System.out.println("Opening " + file.getAbsolutePath() + " for " + param);
        //Find in File
        return "Parameter from file";
    }
}
