package org.achpay;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

import static org.achpay.utils.HttpUtils.executePost;
import static org.achpay.utils.SignUtils.getSign;

/**
 * Hello world!
 */
public class AchTest {
    public static String url = "https://paytest.alchemypay.cc/";

    public static void main(String[] args) throws IOException {
        new AchTest().createAccount();
    }

    public void createAccount() throws IOException {
        HashMap hashMap = new HashMap();
        hashMap.put("unionId", UUID.randomUUID().toString());
        hashMap.put("merchantCode", "ME1686925823752");
        hashMap.put("crypto", "c_TRON_USDT");//,c_BSC_USDT,c_ETH 填一个或多个
        hashMap.put("sign", getSign(hashMap, "ME1686925823752"));
        String ret = executePost("https://paytest.alchemypay.cc/openApi/createUser", JSONObject.toJSONString(hashMap));
        System.out.println(ret);

   /*     返回json
        {"code":"000000","msg":"成功","data":{"sign":"162715FA5986BA6460EA07E4E9D51988","merchantCode":"ME1686925823752","unionId":"f06d360e-18dc-4ffb-b2f0-49a0e665599b","dataInfo":[{"address":"TJ64pMxXXXdthnf754VZngbzbCWDG8NLhH","crypto":"c_TRON_USDT"},{"address":"0x357f91e12cfb2b362339e85de3378e89a773a107","crypto":"c_BSC_USDT"},{"address":"0xe55d40b36e67e5713ba78312925bbec51394a9d5","crypto":"c_ETH"}],"userid":"UID166972237268661452"}}
*/
    }
}
