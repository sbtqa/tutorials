package com.github.dmtest.utils;

import io.qameta.allure.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonFunctions {
    private static final String RESOURCES = "src/main/resources";

    private CommonFunctions() {
        throw new IllegalAccessError("Utility class");
    }

    @Attachment()
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get(RESOURCES, resourceName));
    }

    @Attachment(value = "Вложение", type = "text/plain", fileExtension = ".doc")
    public static byte[] getBytesAnnotationWithArgs(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get(RESOURCES, resourceName));
    }

}
