package com.example.asm7_phamvanthanh.controller;

import com.example.asm7_phamvanthanh.model.Role;
import com.example.asm7_phamvanthanh.model.User;
import com.example.asm7_phamvanthanh.repository.RoleRepository;
import com.example.asm7_phamvanthanh.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Kiểm tra xem tên người dùng đã tồn tại hay chưa
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Tên người dùng đã tồn tại");
            return "register";
        }

        // Kiểm tra xem email đã tồn tại hay chưa
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email đã tồn tại");
            return "register";
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Gán quyền mặc định là "USER" cho người dùng mới đăng ký
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(role));

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(user);

        // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
        return "redirect:/login";
    }
}
