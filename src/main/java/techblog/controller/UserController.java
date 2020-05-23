package techblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import techblog.model.Post;
import techblog.model.User;
import techblog.repository.PostRepository;
import techblog.service.PostService;
import techblog.service.UserService;

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
    public String registration(){
        return "users/registration";
    }

    @RequestMapping(value = "users/registration", method=RequestMethod.POST)
    public String registerUser(User user) {
        System.out.println("*** Inside Registration ***");
        return "redirect:/users/login";
    }

    @RequestMapping(value = "users/login", method= RequestMethod.POST)
    public String loginUser(User user){
        if(userService.login(user))
        {
            return "redirect:/posts/posts";
        }
        else{
            return "users/login";
        }
    }

    @RequestMapping(value = "users/logout", method= RequestMethod.POST)
    public String logout(Model model) throws ClassNotFoundException, SQLException {
        List<Post> posts = postService.getAllPosts(); //Returns the ArrayList from the Service PostService
        model.addAttribute("posts", posts);
        return "index";
    }


}
