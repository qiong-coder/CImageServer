package org.buaa.CImageServer.action;

import com.alibaba.fastjson.JSONObject;
import org.buaa.CImageServer.response.ResponseBuilder;
import org.buaa.CImageServer.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;

@RestController
@RequestMapping(value = "/image")
public class ImageAction {

    private static Logger logger = LoggerFactory.getLogger(ImageAction.class);

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/{product}", method = RequestMethod.POST)
    public JSONObject put(@PathVariable String product,
                          @RequestParam String[] paths,
                          @RequestParam(value = "compress", required = false, defaultValue = "false") Boolean compress,
                          @RequestPart Part[] images) {
        return ResponseBuilder.builder(imageService.put(product, paths, compress, images));
    }

    @RequestMapping(value = "/{product}", method = RequestMethod.DELETE)
    public JSONObject delete(@PathVariable String product,
                             @RequestParam String path) {
        imageService.delete(product,path);
        return ResponseBuilder.builder(true);
    }
}
