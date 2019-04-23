package com.example.hjk.controller;

import com.example.hjk.model.Account;
import com.example.hjk.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author linmr
 * @description: xx
 * @date 2019/1/8
 */
@Controller
@RequestMapping("demo")
public class LoginController {
    @Resource
    private AccountService accountService;

    @RequestMapping(value={"/","/toLogin"})
    public String loginIndex(){
        return  "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam  String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        // 判断用户名是否为空
        boolean checkName=(null==name||"".equals(name));
        // 判断密码是否为空
        boolean checkPassord=(null==password||"".equals(password));
        if(checkName||checkPassord){
            attributes.addFlashAttribute("nullNameAndPassword","用户名或密码不能为空");
            return "redirect:";
        }
        // 在数据库中查找
        Account account =accountService.login(name,password);
        if(null==account){
            attributes.addFlashAttribute("message","用户名或者密码错误");
            return  "redirect:";
        }
        session.setAttribute("user",account);
        return "index";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return  "regist";
    }

    @RequestMapping("/toConstellation")
    @ResponseBody
    public String toConstellation(){
        return sendGet("http://web.juhe.cn:8080/constellation/getAll?consName=%E7%8B%AE%E5%AD%90%E5%BA%A7&type=today&key=df03d0ddd3843f49d52e404d3b692e87");
    }

    @RequestMapping("/toLastToday")
    @ResponseBody
    public String toLastToday(){
        Calendar calendar = Calendar.getInstance();
        int mouth = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        return sendGet("http://api.juheapi.com/japi/toh?key=4001627a38cd2a260a51aee137a6f977&v=1.0&month="+mouth+"&day="+day);
    }

    public String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
