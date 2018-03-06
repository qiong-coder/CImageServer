package org.buaa.CImageServer.logic.impl;

import com.google.common.collect.Lists;
import net.coobird.thumbnailator.Thumbnails;
import org.buaa.CImageServer.logic.ImageLogic;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component("ImageLogic")
public class ImageLogicImpl implements ImageLogic {

    private String prefix;

    private void mkdir(String dir) {
        File f = new File(dir);
        if (!f.exists()) f.mkdirs();
    }

    private void delete(String dir) {
        File f = new File(dir);
        if (f.exists()) f.delete();
    }

    private String ftype(String filename)
    {
        int begin = filename.lastIndexOf(".");
        if ( begin == -1 ) return null;
        else return filename.substring(filename.lastIndexOf(".")+1);
    }

    private String fname(String filename)
    {
        int begin = filename.lastIndexOf("/");
        int end = filename.lastIndexOf(".");
        return filename.substring(begin==-1?0:begin,end==-1?filename.length():end);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        prefix = servletContext.getRealPath("/") + "../images";
        mkdir(prefix);
    }

    @Override
    public List<String> put(String product, String[] paths, String[] filenames, Boolean compress, Part[] images) {
        List<String> locations = Lists.newArrayList();

        for ( int i = 0; i < paths.length; ++ i ) {

            String path = paths[i];
            String filename_prefix = prefix + "/" +product + "/" + path + "/";

            Part image = images[i];
            if ( image.getSize() == 0 ) return null;

            mkdir(filename_prefix);

            String filename = filenames == null?fname(image.getSubmittedFileName()):fname(filenames[i]);

            String filetype = ftype(image.getSubmittedFileName());
            if ( filetype == null ) filetype = "jpg";

            try {
                image.write(filename_prefix+filename+"."+filetype );
                if ( compress ) compress(filename_prefix+filename+"."+filetype,
                        filename_prefix+filename+"_compress.jpg",
                        320,
                        240,
                        1.0f);
            } catch (IOException e) {
                return null;
            }

            locations.add(product+ "/" + path +"/"+filename+"."+filetype);
        }
        return locations;
    }

    @Override
    public void delete(String product, String path) {
        delete(prefix+"/"+product+"/"+path);
    }

    @Override
    public void compress(String ipath, String opath, int width, int height, float quality) throws IOException {
        Thumbnails.of(ipath)
                .height(height).width(width)
                .outputQuality(quality)
                .outputFormat("jpg")
                .toFile(opath);
    }
}
