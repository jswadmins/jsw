package com.fh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.model.User.User;
import com.fh.service.UserService;
import com.fh.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserService userService;

    //发送验证码的请求路径URL
    private static final String
            SERVER_URL="https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String
            APP_KEY="0d56893eeed2c8729236dddd61671682";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="f09c561b5ef2";
    //随机数
    private static final String NONCE="123456";
    //短信模板ID
    private static final String TEMPLATEID="14835376";
    //手机号
    private static final String MOBILE="15554028557";
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN="6";

    @PutMapping("/login")
    @ResponseBody
    ResponseServer login(@RequestBody User user) throws IOException {
        if (StringUtils.isBlank(user.getPhone())) {
            return ResponseServer.error(ServerEnum.PHONE_ISNULL);
        }
        Map result = new HashMap();
        //User list = userService.login();
        //System.out.println(list.getPhone());
        //if(list.getPhone().equals(user.getPhone())){
           /*DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(SERVER_URL);
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
            httpPost.addHeader("AppKey", APP_KEY);
            httpPost.addHeader("Nonce", NONCE);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
            nvps.add(new BasicNameValuePair("mobile", user.getPhone()));
            nvps.add(new BasicNameValuePair("codeLen", CODELEN));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
            //System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            System.out.println("1" + CODELEN);
            System.out.println("2" + TEMPLATEID);
            System.out.println("3" + NONCE);
            //result.get("obj");
            String obj= JSON.parseObject(responseEntity).getString("obj");*/
            //System.out.println("==========="+obj);
            /*result.put("code",200);
            result.put("obj",111111);*/
      /*  }else{
            System.out.println("-------------------------");
        }*/
        redisTemplate.opsForValue().set("code_" + user.getPhone(), "123456", 300, TimeUnit.SECONDS);
        return ResponseServer.success();
    }
    @PutMapping("/logins")
    @ResponseBody
    ResponseServer logins(@RequestBody User user, HttpServletRequest request) {
        Map result = new HashMap();
        //判断手机号或者验证码不能为空
        if(StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getCheckCode())){
            return ResponseServer.error(ServerEnum.LOGIN_PHONEORCODE_INNULL);
        }
        //先验证验证码是否正确
        String code= (String) redisTemplate.opsForValue().get("code_" + user.getPhone());
        if(!user.getCheckCode().equals(code)){
            return ResponseServer.error(ServerEnum.LOGIN_CODE_ERROR);
        }
        //删除该手机的验证码
        redisTemplate.delete("code_" + user.getPhone());

            //判断手机号是否存在，不存在就注册
        CustomerBean customer = userService.isRegister(user.getPhone());
        redisTemplate.opsForValue().set("user_"+user.getPhone(),customer);
        redisTemplate.opsForValue().set("cartid_"+user.getPhone(),customer.getCartId());
        //生成Token
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("phone",user.getPhone());
        String token=JwtUtils.createToken(map);
        return ResponseServer.success(token);
    }

}
