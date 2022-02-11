package kz.epam.pizzeria.service.helper;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public interface ImageWriterService {
    File downloadFile(FileItem part) throws Exception;

    void deleteImageIfNeed(String photoName);
}
