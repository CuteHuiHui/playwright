package com.example.demo.demos.web.init;

import cn.hutool.json.JSONUtil;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// 1.跨域问题，需要代理，需要适配
// 2.非cookie校验无法实现，例如EDR: Cookie + Authorization

@Controller
@Logger
public class BasicController {

    private final Log log = LogFactory.getLog(BasicController.class);

    @GetMapping("/login")
    public void login(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        log.info("开始");
        if ("edr".equals(token)) {
            handleEdr(response);
        } else if ("ah".equals(token)) {
            handleAh(response);
        }
    }

    private void handleAh(HttpServletResponse response) throws IOException {
        List<Cookie> cookies = null;
        String url = null;

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
            Page page = context.newPage();

            // 导航到登录页面（请替换为实际的登录页面 URL）
            log.info("导航到登录页面");
            page.navigate("https://10.20.85.62/console/login.html");
            url = "https://10.20.85.62/console/index.html#/systemOverview";

            // 填写用户名i
            log.info("填写用户名密码");
            String usernameSelector = ":nth-child(1) > .ant-input-affix-wrapper > .ant-input";
            page.fill(usernameSelector, "superadmin");

            // 填写密码
            String passwordSelector = ":nth-child(2) > .ant-input-affix-wrapper > .ant-input";
            page.fill(passwordSelector, "Admin@123");


            // 点击登录按钮
            log.info("点击登录按钮");
            String loginButtonSelector = ".ant-btn";
            page.click(loginButtonSelector);

            // 等待页面加载完成或进行其他验证
            // 等待登录请求完成
            log.info("等待登录请求完成");
            page.waitForLoadState(LoadState.LOAD);


            // Save storage state into the file.
//                context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
            cookies = context.cookies();
        }

        cookies.forEach(i -> {
            log.info("Cookie:" + i.name + "," + i.value + "," + i.domain + "," + i.path + "," + i.secure + "," + i.httpOnly);
            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(i.name, i.value);
            cookie.setDomain(i.domain);
            cookie.setPath(i.path);
            cookie.setSecure(i.secure);
            cookie.setHttpOnly(i.httpOnly);
            response.addCookie(cookie);
        });
        response.sendRedirect(url);
    }

    private void handleEdr(HttpServletResponse response) throws IOException {
        List<Cookie> cookies = null;
        String url = null;
        AtomicReference<String> auth = new AtomicReference<>();
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
            Page page = context.newPage();

            page.route("**/login", route -> {
                APIResponse apiResponse = route.fetch();
                auth.set(JSONUtil.parseObj(apiResponse.body()).getStr("token"));
            });

            // 导航到登录页面（请替换为实际的登录页面 URL）
            log.info("导航到登录页面");
            page.navigate("https://10.20.85.62/tgwsahedr8tgwe/#/login");
            url = "https://10.20.85.62/tgwsahedr8tgwe/#/user_permission/user_manage";

            // 填写用户名
            log.info("填写用户名密码");
            String usernameSelector = ":nth-child(2) > .cut-form-item__content > .cut-input > .cut-input__inner";
            page.fill(usernameSelector, "admin");

            // 填写密码
            String passwordSelector = ":nth-child(3) > .cut-form-item__content > .cut-input > .cut-input__inner";
            page.fill(passwordSelector, "GCNpec314!");


            // 点击登录按钮
            log.info("点击登录按钮");
            String loginButtonSelector = ".cut-button";
            page.click(loginButtonSelector);

            // 等待页面加载完成或进行其他验证
            // 等待登录请求完成
            log.info("等待登录请求完成");
            page.waitForLoadState(LoadState.LOAD);
//                page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(100000));

            // Save storage state into the file.
//            context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
            cookies = context.cookies();


        }
        cookies.forEach(i -> {
            log.info("Cookie:" + i.name + "," + i.value + "," + i.domain + "," + i.path + "," + i.secure + "," + i.httpOnly);
            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(i.name, i.value);
            cookie.setDomain(i.domain);
            cookie.setPath(i.path);
            cookie.setSecure(i.secure);
            cookie.setHttpOnly(i.httpOnly);
            response.addCookie(cookie);
        });

        String str = "Bearer " + auth.get();
        log.info("Authorization:" + str);
        response.sendRedirect(url);
    }

}


