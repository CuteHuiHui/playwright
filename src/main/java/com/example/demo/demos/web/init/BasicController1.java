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
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
// */
//@Controller
//public class BasicController1 {
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    // http://127.0.0.1:8080/hello?name=lisi
//    @GetMapping("/proxy1")
//    public ResponseEntity<byte[]> hello1(@RequestParam String url, HttpServletResponse response) throws IOException {
//        String ck = "";
//        try {
//            Playwright playwright = Playwright.create();
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
////            // 添加网络请求监听
////            page.onResponse(response -> {
////                String url = response.url();
////                if (url.contains("/login")) {
////                    try {
////                        String responseBody = response.text();
////                        System.out.println("登录响应: " + responseBody);
////                        // 这里可以解析responseBody来获取token
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////            });
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            page.navigate("https://10.20.85.40/console/login.html");
//
//            // 填写用户名
//            String usernameSelector = ":nth-child(1) > .ant-input-affix-wrapper > .ant-input";
////            String usernameSelector = "input[placeholder='请输入用户名']";
//            page.fill(usernameSelector, "superadmin");
//
//            // 填写密码
//            String passwordSelector = ":nth-child(2) > .ant-input-affix-wrapper > .ant-input";
////            String passwordSelector = "input[placeholder='请输入密码']";
////            page.fill(passwordSelector, "H68j3*#L320");
//            page.fill(passwordSelector, "Admin@123");
//
//            // 点击登录按钮
//            String loginButtonSelector = ".ant-btn";
//            page.click(loginButtonSelector);
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//
//            // 获取localStorage中的token（如果token存储在localStorage中）
//            String token = (String) page.evaluate("localStorage.getItem('token')");
//            if (token != null) {
//                System.out.println("Token: " + token);
//            }
//
//            // 获取sessionStorage中的token（如果token存储在sessionStorage中）
//            String sessionToken = (String) page.evaluate("sessionStorage.getItem('token')");
//            if (sessionToken != null) {
//                System.out.println("Session Token: " + sessionToken);
//            }
//
//            // 获取cookie中的token（如果token存储在cookie中）
//            List<Cookie> cookies = context.cookies();
//            for (Cookie cookie : cookies) {
//                if (cookie.name.contains("token") || cookie.name.contains("Token")) {
//                    ck = cookie.value;
//                    System.out.println(ck);
////                    return url;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        // 设置 Cookie
////        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("consoleToken", ck);
////        cookie.setPath("/");
////        cookie.setHttpOnly(false);
////        cookie.setSecure(true); // 如果使用 HTTPS，请启用 Secure 属性
////        response.addCookie(cookie);
////
////        // 重定向到目标 URL
////        response.setStatus(302);
////        response.setHeader("Location", url);
//
//// 设置Cookie
//        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("consoleToken", ck);
//        cookie.setPath("/");
//        cookie.setHttpOnly(false);
//        cookie.setSecure(true);
//        // cookie.setDomain("10.20.85.40"); // 移除这行，让浏览器自动处理域名
//        cookie.setMaxAge(3600); // 设置cookie过期时间为1小时
//        response.addCookie(cookie);
//
//        // 添加跨域响应头
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Origin", "https://10.20.85.40");
//
//        // 重定向到目标URL
//        // 使用JavaScript设置cookie
//        response.setContentType("text/html");
//        response.getWriter().write("<script>" +
//                "document.cookie = 'consoleToken=" + ck + "; path=/; secure=true; domain=10.20.85.40';" +
//                "window.location.href = 'https://10.20.85.40/console/';" +
//                "</script>");
//        return null;
//
//        // try {
//        //     // 创建一个信任所有证书的 TrustManager
//        //     TrustManager[] trustAllCerts = new TrustManager[]{
//        //             new X509TrustManager() {
//        //                 public X509Certificate[] getAcceptedIssuers() {
//        //                     return null;
//        //                 }
//
//        //                 public void checkClientTrusted(X509Certificate[] certs, String authType) {}
//
//        //                 public void checkServerTrusted(X509Certificate[] certs, String authType) {}
//        //             }
//        //     };
//
//        //     // 安装信任所有证书的 TrustManager
//        //     SSLContext sc = SSLContext.getInstance("TLS");
//        //     sc.init(null, trustAllCerts, new java.security.SecureRandom());
//        //     HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//        //     // 忽略主机名验证
//        //     HostnameVerifier allHostsValid = (hostname, session) -> true;
//        //     HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//        // } catch (Exception e) {
//        //     e.printStackTrace();
//        // }
//
//        // try {
//        //     // 构建目标 URL
//        //     URI targetUri = URI.create(url);
//
//        //     // 构建请求头，添加 Cookie
//        //     HttpHeaders headers = new HttpHeaders();
//        //     headers.add("Cookie", "consoleToken=" + ck);
//
//        //     // 创建请求实体
//        //     RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, targetUri);
//
//        //     // 转发请求到目标域名
//        //     ResponseEntity<byte[]> response = restTemplate.exchange(requestEntity, byte[].class);
//
//        //     // 返回响应给前端
//        //     return ResponseEntity.status(response.getStatusCode())
//        //             .headers(response.getHeaders())
//        //             .body(response.getBody());
//        // } catch (Exception e) {
//        //     e.printStackTrace();
//        //     return ResponseEntity.status(500).body(null);
//        // }
//
//    }
//
//    // http://127.0.0.1:8080/user
//    @RequestMapping("/user")
//    @ResponseBody
//    public User user() {
//        User user = new User();
//        user.setName("theonefx");
//        user.setAge(666);
//        return user;
//    }
//
//    // http://127.0.0.1:8080/save_user?name=newName&age=11
//    @RequestMapping("/save_user")
//    @ResponseBody
//    public String saveUser(User u) {
//        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
//    }
//
//    // http://127.0.0.1:8080/html
//    @RequestMapping("/html")
//    public String html() {
//        return "index.html";
//    }
//
//    @ModelAttribute
//    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name, @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
//        user.setName("zhangsan");
//        user.setAge(18);
//    }
//}
