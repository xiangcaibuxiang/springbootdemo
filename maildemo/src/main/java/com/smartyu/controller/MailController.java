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
                "欢迎关注微信公众号「武培轩」",
                "感谢你这么可爱，这么优秀，还来关注我，关注了就要一起成长哦~~回复【资料】领取优质资源！");
    }


    /**
     * 发送复杂邮件（文本+图片+附件）
     */
    @GetMapping("/sendMimeMail")
    public ResponseEntity<String> sendMimeMail() {
        return mailService.sendMimeMail(mailName,
                toUsername,
                "欢迎关注微信公众号「武培轩」",
                "<h3>感谢你这么可爱，这么优秀，还来关注我，关注了就要一起成长哦~~</h3><br>" +
                        "回复【资料】领取优质资源！<br>" +
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
        context.setVariable("username", "武培轩");
        return mailService.sendTemplateMail(mailName,
                toUsername,
                "欢迎关注微信公众号「武培轩」",
                context);
    }
}
