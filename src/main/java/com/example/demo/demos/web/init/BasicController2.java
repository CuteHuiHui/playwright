///*
// * Copyright 2013-2018 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.example.demo.demos.web.init;
//
//import com.microsoft.playwright.*;
//import com.microsoft.playwright.options.Cookie;
//import com.microsoft.playwright.options.LoadState;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//@Controller
//public class BasicController2 {
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @GetMapping("/login")
//    public void hello(HttpServletResponse response) throws IOException {
////        String url = "https://10.20.85.40/console/index.html#/systemOverview";
////        String url = "http://10.20.85.40:9091/index.html";
//        String url = "https://10.20.85.40/tgwsahedr24tgwe/#/user_permission/user_manage";
//        List<Cookie> cookies = getEdrCookie(); // 假设这是一个返回有效cookie的方法
//        System.out.println("edrCookie:"+cookies.);
//        List<Cookie> ahCookie = getAhCookie();
//        cookies.forEach(i -> {
//            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(i.name, i.value);
//            cookie.setDomain(i.domain);
//            cookie.setPath(i.path);
//            cookie.setSecure(i.secure);
//            cookie.setHttpOnly(i.httpOnly);
//            response.addCookie(cookie);
//        });
//
//        ahCookie.forEach(i -> {
//            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(i.name, i.value);
//            cookie.setDomain(i.domain);
//            cookie.setPath(i.path);
//            cookie.setSecure(i.secure);
//            cookie.setHttpOnly(i.httpOnly);
//            response.addCookie(cookie);
//        });
//
//        response.sendRedirect(url);
//    }
//
//    private List<Cookie> getEdrCookie() {
//        List<Cookie> cookies = Collections.emptyList();
//        try {
//            Playwright playwright = Playwright.create();
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            page.navigate("https://10.20.85.40/tgwsahedr24tgwe/#/login");
//
//            // 填写用户名
//            String usernameSelector = ":nth-child(2) > .cut-form-item__content > .cut-input > .cut-input__inner";
//            page.fill(usernameSelector, "admin");
//
//            // 填写密码
//            String passwordSelector = ":nth-child(3) > .cut-form-item__content > .cut-input > .cut-input__inner";
//            page.fill(passwordSelector, "GCNpec314!");
//
//            // 点击登录按钮
//            String loginButtonSelector = ".cut-button";
//            page.click(loginButtonSelector);
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//
//            context.storageState();
//
//            cookies = context.cookies();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cookies;
//    }
//
//
//    private List<Cookie> getAhCookie() {
//        List<Cookie> cookies = Collections.emptyList();
//        try {
//            Playwright playwright = Playwright.create();
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            page.navigate("https://10.20.85.40/console/login.html");
//
//            // 填写用户名
//            String usernameSelector = ":nth-child(1) > .ant-input-affix-wrapper > .ant-input";
//            page.fill(usernameSelector, "superadmin");
//
//            // 填写密码
//            String passwordSelector = ":nth-child(2) > .ant-input-affix-wrapper > .ant-input";
////            page.fill(passwordSelector, "H68j3*#L320");
//            page.fill(passwordSelector, "Admin@123");
//
//            // 点击登录按钮
//            String loginButtonSelector = ".ant-btn";
//            page.click(loginButtonSelector);
//
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//
//            context.storageState();
//
//            cookies = context.cookies();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return cookies;
//    }
//
//
//}
