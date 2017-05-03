package br.com.mavortius.jreadinglist.controller;

import br.com.mavortius.jreadinglist.domain.Book;
import br.com.mavortius.jreadinglist.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ReadingListController {

    private final BookRepository bookRepository;

    public ReadingListController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Book> bookList = bookRepository.findAll();

        if(bookList != null) {
            model.addAttribute("bookList", bookList);
        }

        return "index";
    }

    @Transactional
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        book.setReader("Marcelo");
        bookRepository.save(book);
        return "redirect:/";
    }

}
