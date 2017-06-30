package pe.open.client.escale.common.rest.util;

import java.util.HashMap;

public class QueryParamURL {

    public static String getParam(String url,String param ){

        HashMap<String,String> hm = new HashMap<String, String>();
        String[] paramwithValue = url.split("&");

        for (int i = 0; i < paramwithValue.length; i++) {
            String[] obj = paramwithValue[i].split("=");
            if(obj.length>1)
                hm.put(obj[0], obj[1]);
            else
                hm.put(obj[0], "");
        }

        return hm.get(param);
    }
}
