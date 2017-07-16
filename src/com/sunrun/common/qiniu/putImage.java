package com.sunrun.common.qiniu;

import java.io.File;
import java.io.FileInputStream;
public class putImage {
/*
	String ak = "";
    String sk = "";    // 密钥配置
    Auth auth = Auth.create(ak, sk);    // TODO Auto-generated constructor stub 
    String bucketname = "";    //空间名
    String key = "";    //上传的图片名
    public String getUpToken() {        
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }    
    public void put64image() throws Exception {
        String file = "D:\\Documents\\Pictures\\1.png";//图片路径
        FileInputStream fis = null;        
        int l = (int) (new File(file).length());        
        byte[] src = new byte[l];
        fis = new FileInputStream(new File(file));
        fis.read(src);
        String file64 = Base64.encodeToString(src, 0);
        String url = "http://upload.qiniu.com/putb64/" + l+"/key/"+ UrlSafeBase64.encodeToString(key);      
   //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
    }   
     public static void main(String[] args) throws Exception {   
          new put64().put64image();
    }*/
}
