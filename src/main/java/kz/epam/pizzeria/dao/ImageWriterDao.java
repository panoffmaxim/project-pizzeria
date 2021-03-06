package kz.epam.pizzeria.dao;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class ImageWriterDao {
    public static final String DOWNLOAD_LOCATION = "src\\main\\webapp\\static\\img\\";

    public File downloadFile(FileItem part) throws Exception {
        String pathFromFile = DOWNLOAD_LOCATION + part.getName();
        File file = new File(pathFromFile);
        file = calculateName(file);
        part.write(file);
        return file;
    }

    private File calculateName(File file) {
        File temp = file;
        if (temp.exists()) {
            int counter = 0;
            while (temp.exists()) {
                temp = new File(DOWNLOAD_LOCATION + counter + temp.getName());
            }
        }
        return temp;
    }

    public void deleteIfNeed(String photoName) {
        File file = new File(DOWNLOAD_LOCATION + photoName);
        if (file.exists()) {
            file.delete();
        }
    }
}
