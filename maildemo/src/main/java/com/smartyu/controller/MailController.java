package com.smartyu.controller;

import com.smartyu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
public class MailController {


    @Autowired
    private MailService mailService;

    @Value("${spring.mail.username}")
    private String mailName;

    @Value("${spring.mail.toUsername}")
    private String toUsername;


    @GetMapping("/sendSimpleMail")
    public void sendSimpleMail() {
        mailService.sendSimpleMail(mailName,
                toUsername,
                "一个人即使已登上顶峰，也仍要自强不息",
                "意志命运往往背道而驰，决心到最后会全部推倒");
    }


    /**
     * 发送复杂邮件（文本+图片+附件）
     */
    @GetMapping("/sendMimeMail")
    public ResponseEntity<String> sendMimeMail() {
        return mailService.sendMimeMail(mailName,
                toUsername,
                "只有把抱怨环境的心情，化为上进的力量，才是成功的保证",
                "<h3>只有永远躺在泥坑里的人，才不会再掉进坑里~~</h3><br>" +
                        "意志命运往往背道而驰，决心到最后会全部推倒<br>" +
                        "<img src='cid:logo'>");
    }

    /**
     * 发送模板邮件
     *
     * @param
     * @return
     */
    @GetMapping("/sendTemplateMail")
    public ResponseEntity<String> sendTemplateMail() {
        Context context = new Context();
        context.setVariable("username", "潇洒宇");
        return mailService.sendTemplateMail(mailName,
                toUsername,
                "邮件发送「啦啦啦」",
                context);
    }
}
