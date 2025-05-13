package com.example.demo.demos.web.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.Cookie;

import java.util.List;


public class pAh {
    public static void main(String[] args) {
        try {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setIgnoreHTTPSErrors(true));
            Page page = context.newPage();

//            // 添加网络请求监听
//            page.onResponse(response -> {
//                String url = response.url();
//                if (url.contains("/login")) {
//                    try {
//                        String responseBody = response.text();
//                        System.out.println("登录响应: " + responseBody);
//                        // 这里可以解析responseBody来获取token
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

            // 导航到登录页面（请替换为实际的登录页面 URL）
            page.navigate("https://10.20.85.40/console/login.html");

            // 填写用户名
            String usernameSelector = ":nth-child(1) > .ant-input-affix-wrapper > .ant-input";
            page.fill(usernameSelector, "superadmin");

            // 填写密码
            String passwordSelector = ":nth-child(2) > .ant-input-affix-wrapper > .ant-input";
            page.fill(passwordSelector, "Admin@123");


            // 点击登录按钮
            String loginButtonSelector = ".ant-btn";
            page.click(loginButtonSelector);

            // 等待页面加载完成或进行其他验证
            // 等待登录请求完成
            page.waitForLoadState(LoadState.NETWORKIDLE);

            // 获取localStorage中的token（如果token存储在localStorage中）
            String token = (String) page.evaluate("localStorage.getItem('token')");
            if (token != null) {
                System.out.println("Token: " + token);
            }

            // 获取sessionStorage中的token（如果token存储在sessionStorage中）
            String sessionToken = (String) page.evaluate("sessionStorage.getItem('token')");
            if (sessionToken != null) {
                System.out.println("Session Token: " + sessionToken);
            }

            // 获取cookie中的token（如果token存储在cookie中）
            List<Cookie> cookies = context.cookies();
            for (Cookie cookie : cookies) {
                if (cookie.name.contains("token") || cookie.name.contains("Token")) {
                    String url = "https://10.50.44.99/console/index.html#/systemOverview?"+cookie.name + "=" + cookie.value;
                    System.out.println(url);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
