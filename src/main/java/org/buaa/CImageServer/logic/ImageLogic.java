package org.buaa.CImageServer.logic;

import org.springframework.web.context.ServletContextAware;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public interface ImageLogic extends ServletContextAware {

    List<String> put(String product, String[] paths, Boolean compress, Part[] images);

    void delete(String product, String path);

    void compress(String ipath, String opath, int width, int height, float quality) throws IOException;
}
