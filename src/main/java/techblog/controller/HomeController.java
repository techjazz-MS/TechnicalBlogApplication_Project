package techblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import techblog.model.Post;
import techblog.repository.PostRepository;
import techblog.service.PostService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    //Specifying the HomeController Default Constructor to add the SOP statement.
    public HomeController(){
        System.out.println("*** HomeController ***");
    }

    @Autowired
    private PostService postService; //InstanceVariable. Its where the dependency injection is happening

    @Autowired
    private PostRepository repository;

    @RequestMapping("/")
    public String getAllPosts(Model model) throws ClassNotFoundException, SQLException {
        List<Post> posts = postService.getAllPosts(); //Returns the ArrayList from the Service PostService
        model.addAttribute("posts", posts);
        return "index";

    }
}
