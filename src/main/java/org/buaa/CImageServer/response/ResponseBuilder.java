package org.buaa.CImageServer.response;

import com.alibaba.fastjson.JSONObject;

public class ResponseBuilder {

    public static JSONObject builder(Object data) {
        JSONObject response = new JSONObject();
        if ( data == null ) {
            response.put("code",-1);
            response.put("msg","failure");
        } else {
            response.put("code",0);
            response.put("msg","success");
            response.put("data",data);
        }
        return response;
    }

}
