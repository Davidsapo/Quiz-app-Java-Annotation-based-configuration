package utils;

import exceptions.ResourceReaderException;
import org.springframework.core.io.*;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.lang.module.ResolutionException;

public class ResourceReader {

    public static String readFileToString(String path) throws ResourceReaderException {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(path);
            Reader reader = new InputStreamReader(resource.getInputStream());
            return FileCopyUtils.copyToString(reader);
        } catch (IOException exception) {
            throw new ResourceReaderException("Exception occurs while reading resource file " + path);
        }
    }

    public static File getResourceFile(String path) throws ResourceReaderException {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(path);
            return resource.getFile();
        } catch (IOException exception) {
            throw new ResourceReaderException("Exception occurs while reading resource file " + path);
        }
    }
}
