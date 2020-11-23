package vn.edu.poly.beecinema.controller;


import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.TaikhoanService;
import vn.edu.poly.beecinema.service.impl.TaikhoanNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private TaikhoanService taikhoanService;

    @Autowired
    private JavaMailSender mailSender;

    public String setLayout(Authentication authentication) {
        String page = "client/layout";
        if (authentication != null && authentication.isAuthenticated()){
            page = "client/layout_da_dang_nhap";
        }
        return page;
    }

    @GetMapping("/forgot-pass")
    public String resetPass(Model model, Authentication authentication) {
        model.addAttribute("pageTitle", "Quên mật khẩu");
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/forgot-password";
    }

    @PostMapping("/forgot-pass")
    public String resetPass(HttpServletRequest request, Model model, Authentication authentication) {
        String email = request.getParameter("email");
        String token = RandomString.make(45);
        try {
            taikhoanService.updateResetPasswordToken(token,email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Chúng tôi đã gửi một liên kết đặt lại mật khẩu đến email của bạn. Hãy kiểm tra!");

        } catch (TaikhoanNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Lỗi khi gửi email");
        }
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        model.addAttribute("pageTitle", "Quên mật khẩu");
        return "client/forgot-password";
    }

    private void sendEmail(String email, String resetPasswordLink)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@Beecinema.com", "Beecinema");
        helper.setTo(email);

        String subject = "Đây là liên kết để đặt lại mật khẩu của bạn";
        String content = "<p>Xin chào, </p>"
                + "<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
                + "<p>Nhấn vào liên kết bên dưới để thay đổi mật khẩu của bạn:</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Thay đổi mật khẩu của tôi </a></b></p>"
                + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình hoặc bạn chưa thực hiện yêu cầu.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public String changePass(@Param(value = "token") String token,
                             Model model, Authentication authentication){
        Taikhoan taikhoan = taikhoanService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        if (taikhoan == null) {
            model.addAttribute("message", "Mã không hợp lệ");
            return "message";
        }
        return "client/reset-password";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model, Authentication authentication) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Taikhoan taikhoan = taikhoanService.getByResetPasswordToken(token);

        if (taikhoan == null) {
            model.addAttribute("title", "Đặt lại mật khẩu của bạn");
            model.addAttribute("message", "Mã không hợp lệ");
            return "message";
        } else {
            taikhoanService.updatePassword(taikhoan, password);

            model.addAttribute("message", "Bạn đã thay đổi thành công mật khẩu của bạn.");
        }
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/forgot-password";
    }
}
