package pl.tomeksworkshop.controller;


import org.springframework.web.bind.annotation.*;

import pl.tomeksworkshop.model.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping("/hello")
    public String hello(){
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book(1L,"9788324631766","Thinking in Java",
                "Bruce Eckel","Helion","programming");
    }

    private MemoryBookService memoryBookService;
    private Book book;

    public BookController(MemoryBookService memoryBookService){
        this.memoryBookService = memoryBookService;
    }

    @RequestMapping("/booklist")
    public List<Book> getBookList(){
        return memoryBookService.getList();
    }

    @RequestMapping("/booklist/{id}")
    public Book getBookById(@PathVariable long id){
        return memoryBookService.getBookById(id);
    }

    @PostMapping("/addbook")
    public void addBook(@RequestBody Book book) {
        memoryBookService.addBook(book.getId(),
                                        book.getIsbn(),
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getPublisher(),
                                        book.getType());
    }
}