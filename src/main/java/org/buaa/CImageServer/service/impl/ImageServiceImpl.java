package org.buaa.CImageServer.service.impl;

import org.buaa.CImageServer.logic.ImageLogic;
import org.buaa.CImageServer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.util.List;

@Component("ImageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageLogic imageLogic;

    @Override
    public List<String> put(String product, String[] paths, String[] filenames, Boolean compress, Part[] images) {
        if ( paths.length != images.length ) return null;
        return imageLogic.put(product, paths, filenames, compress, images);
    }

    @Override
    public void delete(String product, String path) {
        imageLogic.delete(product, path);
    }
}
