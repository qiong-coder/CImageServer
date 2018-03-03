package org.buaa.CImageServer.service;

import javax.servlet.http.Part;
import java.util.List;

public interface ImageService {

    List<String> put(String product, String[] paths, Boolean compress, Part[] images);

    void delete(String product, String path);
}
