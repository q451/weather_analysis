package com.example.demo.util;

import com.example.demo.common.codeEnum.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Random;

/**
 * 邮件公共类
 *
 * @author 杨小平
 */
public class EmailUtil {

    @Autowired
    public JavaMailSender javaMailSender;
    private final String from = "1393371859@qq.com";

    /**
     * 只发送文字
     * 方法5个参数分别表示：邮件发送者、收件人、抄送人、邮件主题以及邮件内容
     */
    public boolean sendSimpleMail(String to, String subject, String text) {
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(text);
            javaMailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 附件或者html邮件
     *
     * @param to 发送人
     * @param subject 主题
     * @param content 内容
     * @param files 文件
     * @return 返回
     * @throws MessagingException 异常
     */
    public boolean sendComplexMail(String to, String subject, String content, File[] files) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        // 设置发送发
        helper.setFrom(from);
        // 设置接收方
        helper.setTo(to);
        // 设置邮件主题
        helper.setSubject(subject);
        // 设置邮件内容
        helper.setText(content);
        // 添加附件（多个）
        if (files != null && files.length > 0) {
            for (File file : files) {
                helper.addAttachment(file.getName(), file);
            }
        }
        // 发送邮件
        javaMailSender.send(mimeMessage);

        return true;
    }

    /**
     * 随机生成6位数的验证码
     *
     * @return String code
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < Constants.RANDOM_LENGTH; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 测试
     * @param email 邮箱
     * @param session session
     * @return String
     */
    public String test(String email, HttpSession session){
        //将随机数放置到session中
        String code = randomCode();
        session.setAttribute("email",email);
        session.setAttribute("code",code);
        //获取session中的验证信息
        return code;
    }
}
