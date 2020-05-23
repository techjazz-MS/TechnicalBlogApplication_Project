package techblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techblog.model.Post;
import techblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    //Specifying the PostService Default Constructor to add the SOP statement.
    public PostService(){
        System.out.println("*** PostService ***");
    }

    public List<Post> getAllPosts(){
        return repository.getAllPosts();
    }
//    public List<Post> getAllPosts() throws ClassNotFoundException, SQLException {
//        List<Post> posts = new ArrayList<>();
//
//
////        Connection connection = null;
////        try{
////            Class.forName("org.postgresql.Driver");
////            connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechnicalBlog","postgres", "Monitoring01");
////            Statement statement = connection.createStatement();
////            ResultSet rs = statement.executeQuery("SELECT * FROM posts");
////            while(rs.next()){
////                Post post = new Post();
////                post.setTitle(rs.getString("title"));
////                post.setBody(rs.getString("body"));
////                post.setDate(rs.getDate("date"));
////                posts.add(post);
////            }
////        } catch (ClassNotFoundException | SQLException e){
////            e.printStackTrace();
////        } finally {
////            try{
////                connection.close();
////            }catch(SQLException e){
////                e.printStackTrace();
////            }
////        }
//
//
//        return resultList;
//    }

    public Post getOnePost(){
//        ArrayList<Post> posts = new ArrayList<>();
////        Post post = new Post();
////        post.setTitle("This is your Post");
////        post.setBody("This is your first post. This post contains some valid information.");
////        post.setDate(new Date());
////
////        posts.add(post);
//        Connection connection = null;
//        try{
//            Class.forName("org.postgresql.Driver");
//            connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechnicalBlog","postgres", "Monitoring01");
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM posts WHERE id = 4");
//            while(rs.next()){
//                Post post = new Post();
//                post.setTitle(rs.getString("title"));
//                post.setBody(rs.getString("body"));
//                post.setDate(rs.getDate("date"));
//                posts.add(post);
//            }
//        } catch (ClassNotFoundException | SQLException e){
//            e.printStackTrace();
//        } finally {
//            try{
//                connection.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
        return repository.getLatestPost();
    }

    public void createPost(Post newPost){
        newPost.setDate(new Date());
        repository.createPost(newPost);
        System.out.println("New Post: " + newPost);
    }

    public Post getPost(Integer postId){
        return repository.getPost(postId);
    }

    public void updatePost(Post updatedPost){
        updatedPost.setDate(new Date());
        repository.updatePost(updatedPost);
    }

    public void deletePost(Integer postId){
        repository.deletePost(postId);
    }
}
