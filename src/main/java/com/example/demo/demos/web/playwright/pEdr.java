package com.example.demo.demos.web.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;

import java.util.List;


public class pEdr {
    public static void main(String[] args) {
        // 启动 Playwright
        try {
            Playwright playwright = Playwright.create();
            // 启动浏览器（这里以 Chromium 为例）
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setIgnoreHTTPSErrors(true)); // 忽略 HTTPS 错误
            Page page = context.newPage();

            // 导航到登录页面（请替换为实际的登录页面 URL）
            page.navigate("https://10.50.116.80/#/login");

            // 填写用户名
            String usernameSelector = ":nth-child(2) > .cut-form-item__content > .cut-input > .cut-input__inner";
            page.fill(usernameSelector, "admin");

            // 填写密码
            String passwordSelector = ":nth-child(3) > .cut-form-item__content > .cut-input > .cut-input__inner";
            page.fill(passwordSelector, "Admin@123");

            // 点击登录按钮
            String loginButtonSelector = ".cut-button";
            page.click(loginButtonSelector);

            // 等待页面加载完成或进行其他验证
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
            // 输出登录后的页面标题以确认登录成功
            System.out.println("登录后页面标题: " + page.title());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭浏览器
//            browser.close();
        }
    }
}
