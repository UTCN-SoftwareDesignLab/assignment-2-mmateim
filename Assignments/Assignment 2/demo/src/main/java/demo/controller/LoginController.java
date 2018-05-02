package demo.controller;

import demo.Validator.UserValidator;
import demo.entity.User;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("LoginController : return login.html");
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        System.out.println("LoginController : return user-form.html");
        model.addAttribute("user", new User());
        model.addAttribute("message", "");
        return "user-form";
    }

    @RequestMapping(value = "/login/register", method = RequestMethod.POST)
    public String registerComplete(Model model, @ModelAttribute("user") User user) {
        UserValidator userValidator = new UserValidator(user);
        List<String> errors = userValidator.validate();
        if (errors.size() == 0) {
            user.setRole("USER");
            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            String pass = encoder.encodePassword(user.getPassword(), "");
            user.setPassword(pass);
            userService.create(user);
            System.out.println("LoginController : registration Done");
            return "login";
        } else {
            System.out.println("LoginController : error at registration");
            model.addAttribute("message", errors.toString());
            model.addAttribute("user", user);
            return "user-form";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String onError(Model model){
        System.out.println("Error");
        return "error";
    }
}