package com.example.demo.demos.web.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;


public class pZstack {
    public static void main(String[] args) {
        // 启动 Playwright
        try {
            Playwright playwright = Playwright.create();
            // 启动浏览器（这里以 Chromium 为例）
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // 导航到登录页面（请替换为实际的登录页面 URL）
            page.navigate("http://10.20.144.3:5000/#/login");

            // 填写用户名
            String usernameSelector = "#account-name > input";
            page.fill(usernameSelector, "admin");

            // 填写密码
            String passwordSelector = "#account-password > input";
            page.fill(passwordSelector, "2jyTsS#Wa7P3");

            // 点击登录按钮
            String loginButtonSelector = "#account-login-button";
            page.click(loginButtonSelector);

            // 等待页面加载完成或进行其他验证
            page.waitForLoadState(LoadState.NETWORKIDLE);

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
