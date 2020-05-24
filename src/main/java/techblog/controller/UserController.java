package techblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import techblog.model.Post;
import techblog.model.User;
import techblog.model.UserProfile;
import techblog.repository.PostRepository;
import techblog.service.PostService;
import techblog.service.UserService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private PostService postService; //InstanceVariable. Its where the dependency injection is happening

    @Autowired
    private UserService userService; //InstanceVariable. Its where the dependency injection is happening

    @RequestMapping("users/login")
    public String login(){
        return "users/login";
    }

    @RequestMapping("users/registration")
    public String registration(Model model){
        User user = new User();
        UserProfile profile = new UserProfile();
        user.setProfile(profile);
        model.addAttribute("User", user);
        return "users/registration";
    }

    @RequestMapping(value = "users/registration", method=RequestMethod.POST)
    public String registerUser(User user) {
        System.out.println("*** Inside Registration ***");
        userService.registerUser(user);
        return "redirect:/users/login";
    }

    @RequestMapping(value = "users/login", method= RequestMethod.POST)
    public String loginUser(User user, HttpSession session){
        User existingUser = userService.login(user);
        if(existingUser != null)
        {
            session.setAttribute("loggeduser", existingUser);
            return "redirect:/posts/posts";
        }
        else{
            return "users/login";
        }
    }

    @RequestMapping(value = "users/logout", method= RequestMethod.POST)
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        List<Post> posts = postService.getAllPosts(); //Returns the ArrayList from the Service PostService
        model.addAttribute("posts", posts);
        return "index";
    }


}
