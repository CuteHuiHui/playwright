//package com.example.demo.demos.web.init;
//
//import com.microsoft.playwright.*;
//import com.microsoft.playwright.options.Cookie;
//import com.microsoft.playwright.options.LoadState;
//import jdk.nashorn.internal.runtime.logging.Logger;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//@Logger
//public class BasicController4 {
//
//    private static final Log log = LogFactory.getLog(BasicController4.class);
//
//    @GetMapping("/login")
//    public void login(HttpServletResponse response) throws IOException {
////        String url = "https://10.20.85.40/console/index.html#/systemOverview";
////        String url = "http://10.20.144.3:5000/#/main/home";
//        String url = "https://10.20.85.40/tgwsahedr24tgwe/#/user_permission/user_manage";
////        String url = "https://10.20.85.40/tgwssycloudfirewall41tgwe/#/admin/dashboard";
////        String url = "https://10.20.85.40/myteam/overview";
//        List<Cookie> cookies = getEdrCookie(); // 假设这是一个返回有效cookie的方法
//        cookies.forEach(i -> {
//            log.info("Cookie:" + i.name +","+ i.value +","+ i.domain +","+ i.path +","+ i.secure +","+ i.httpOnly);
//            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(i.name, i.value);
//            cookie.setDomain(i.domain);
//            cookie.setPath(i.path);
//            cookie.setSecure(i.secure);
//            cookie.setHttpOnly(i.httpOnly);
//            response.addCookie(cookie);
//        });
//
//
//        response.sendRedirect(url);
//    }
//
//    private List<Cookie> getFwCookie() {
//        List<Cookie> cookies;
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            log.info("导航到登录页面");
//            page.navigate("https://10.20.85.40/tgwssycloudfirewall41tgwe/#/");
//
//            // 填写用户名
//            log.info("填写用户名密码");
//            String usernameSelector = ":nth-child(1) > .input_text > input";
//            page.fill(usernameSelector, "admin");
//
//            // 填写密码
//            String passwordSelector = ":nth-child(2) > .input_text > input";
//            page.fill(passwordSelector, "firewall");
//
//
//            // 点击登录按钮
//            log.info("点击登录按钮");
//            String loginButtonSelector = ".log_btn";
//            page.click(loginButtonSelector);
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            log.info("等待登录请求完成");
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//            String s = context.storageState();
//
//            cookies = context.cookies();
//            System.out.println(s);
//
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
//            // 关闭浏览器
//            browser.close();
//        }
//        return cookies;
//    }
//
//    private List<Cookie> getEdrCookie() {
//        List<Cookie> cookies;
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            log.info("导航到登录页面");
//            page.navigate("https://10.20.85.40/tgwsahedr24tgwe/#/login");
//
//            // 填写用户名
//            log.info("填写用户名密码");
//            String usernameSelector = ":nth-child(2) > .cut-form-item__content > .cut-input > .cut-input__inner";
//            page.fill(usernameSelector, "admin");
//
//            // 填写密码
//            String passwordSelector = ":nth-child(3) > .cut-form-item__content > .cut-input > .cut-input__inner";
//            page.fill(passwordSelector, "GCNpec314!");
//
//
//            // 点击登录按钮
//            log.info("点击登录按钮");
//            String loginButtonSelector = ".cut-button";
//            page.click(loginButtonSelector);
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            log.info("等待登录请求完成");
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//            String s = context.storageState();
//
//            cookies = context.cookies();
//
//
//            // 关闭浏览器
//            browser.close();
//        }
//        return cookies;
//    }
//
//    private List<Cookie> getZsCookie() {
//        List<Cookie> cookies;
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            log.info("导航到登录页面");
//            page.navigate("http://10.20.144.3:5000/#/login");
//
//            // 填写用户名
//            log.info("填写用户名密码");
//            String usernameSelector = "#account-name > input";
//            page.fill(usernameSelector, "admin");
//
//            // 填写密码
//            String passwordSelector = "#account-password > input";
//            page.fill(passwordSelector, "2jyTsS#Wa7P3");
//
//
//            // 点击登录按钮
//            log.info("点击登录按钮");
//            String loginButtonSelector = "#account-login-button";
//            page.click(loginButtonSelector);
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            log.info("等待登录请求完成");
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//            context.storageState();
//
//            cookies = context.cookies();
//
//
//            // 关闭浏览器
//            browser.close();
//        }
//        return cookies;
//    }
//
//    private List<Cookie> getAhCookie() {
//        List<Cookie> cookies;
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            log.info("导航到登录页面");
//            page.navigate("https://10.20.85.40/console/login.html");
//
//            // 填写用户名
//            log.info("填写用户名密码");
//            String usernameSelector = ":nth-child(1) > .ant-input-affix-wrapper > .ant-input";
//            page.fill(usernameSelector, "superadmin");
//
//            // 填写密码
//            String passwordSelector = ":nth-child(2) > .ant-input-affix-wrapper > .ant-input";
//            page.fill(passwordSelector, "Admin@123");
//
//
//            // 点击登录按钮
//            log.info("点击登录按钮");
//            String loginButtonSelector = ".ant-btn";
//            page.click(loginButtonSelector);
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            log.info("等待登录请求完成");
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//            context.storageState();
//
//            cookies = context.cookies();
//
//
//            // 关闭浏览器
//            browser.close();
//        }
//        return cookies;
//    }
//
//    private List<Cookie> getPortalCookie() {
//        List<Cookie> cookies;
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
//            Page page = context.newPage();
//
//            // 导航到登录页面（请替换为实际的登录页面 URL）
//            log.info("导航到登录页面");
//            page.navigate("https://10.20.85.40/login.html");
//
//            // 填写用户名
//            log.info("填写用户名密码");
//            String usernameSelector = "._65a15a04 > :nth-child(1) > .ant-input";
//            page.fill(usernameSelector, "admin");
//
//            // 填写密码
//            String passwordSelector = ".ant-input-affix-wrapper > .ant-input";
//            page.fill(passwordSelector, "Admin@123");
//
//
//            // 点击登录按钮
//            log.info("点击登录按钮");
//            String loginButtonSelector = ".ant-btn";
//            page.click(loginButtonSelector);
//
//            // 等待页面加载完成或进行其他验证
//            // 等待登录请求完成
//            log.info("等待登录请求完成");
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//            context.storageState();
//
//            cookies = context.cookies();
//
//
//            // 关闭浏览器
//            browser.close();
//        }
//        return cookies;
//    }
//}
