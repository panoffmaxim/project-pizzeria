package kz.epam.pizzeria.service.helper.impl;

import kz.epam.pizzeria.dao.DAOFactory;
import kz.epam.pizzeria.dao.ImageWriterDao;
import kz.epam.pizzeria.service.helper.ImageWriterService;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class ImageWriterServiceImpl implements ImageWriterService {
    private static ImageWriterServiceImpl INSTANCE = new ImageWriterServiceImpl();
    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ImageWriterDao imageWriterDao = dAOFactory.getImageWriterDao();

    public static ImageWriterServiceImpl getInstance() {
        return INSTANCE;
    }

    private ImageWriterServiceImpl() {
    }


    @Override
    public File downloadFile(FileItem part) throws Exception {
        return imageWriterDao.downloadFile(part);
    }

    @Override
    public void deleteImageIfNeed(String photoName) {
        if (photoName != null) {
            imageWriterDao.deleteIfNeed(photoName);
        }
    }
}
