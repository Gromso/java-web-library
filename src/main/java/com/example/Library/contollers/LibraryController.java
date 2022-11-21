package com.example.Library.contollers;

import com.example.Library.hibernate.dao.AuthorsDAO;
import com.example.Library.hibernate.dao.BookDAO;
import com.example.Library.parsing.HTMLRender;
import com.example.Library.utils.MainMenu;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class LibraryController  {

    @GetMapping("/")
    public @ResponseBody String homeHandler() throws TemplateException, IOException {
        HashMap<String,Object> modelData = new HashMap<>();
        modelData.put("main_menu", MainMenu.get());
        modelData.put("books", BookDAO.all());
        return HTMLRender.render("home.ftl", modelData);
       }

       @GetMapping("/books/{books_id}")
    public @ResponseBody String BookDetailsHandler(@PathVariable("books_id") int books_id) throws TemplateException, IOException {
        HashMap<String, Object> modelData = new HashMap<>();
        modelData.put("main_menu", MainMenu.get());
        modelData.put("book", BookDAO.one(books_id));

        return HTMLRender.render("book_details.ftl", modelData);

       }


       @GetMapping("/authors")
       public @ResponseBody String AuthorsHandler() throws TemplateException, IOException {
           HashMap<String, Object> modelData = new HashMap<>();
           modelData.put("main_menu", MainMenu.get());
           modelData.put("authors", AuthorsDAO.all());

           return HTMLRender.render("authors.ftl", modelData);
       }
}