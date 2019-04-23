package com.example.hjk.controller;

import com.example.hjk.model.Account;
import com.example.hjk.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class RegisterController {
    @Resource
    private AccountService accountService;

    @RequestMapping("/toRegist")
    public String toRegist(){
        return  "regist";
    }

    @RequestMapping("/regist")
    public String regist(@RequestParam String name,
                        @RequestParam  String password,
                         @RequestParam  String constellation,
                        HttpSession session,
                        RedirectAttributes attributes) {
        Account account = new Account();
        account.setUserName(name);
        account.setConstellation(constellation);
        account.setPassWord(password);
        accountService.insert(account);
        return "success";
    }

}
