package si.academia.unit29.vaja2;

import org.junit.Test;
import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorServiceTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testAuthors() {
        AuthorService service = new AuthorService();
        Author author1 = new Author("Dirk Amelburger", 67, true, null);
        service.createAuthor(author1);
        Author author2 = new Author("Steve Oualline", 46, true, null);
        service.createAuthor(author2);
        Author author11 = service.getAuthor("Dirk Amelburger");
        Author author22 = service.getAuthor("Steve Oualline");
        assertEquals(author1, author11);
        assertEquals(author2, author22);
        List<Author> authorsList = service.getAuthors();
        assertEquals(2, authorsList.size());    // it fails, if we pass 1
        assertEquals(author2, authorsList.get(0));
        service.deleteAuthor(author2);
        authorsList = service.getAuthors();
        assertEquals(1, authorsList.size());
    }

    @Test
    public void testJsonPersistence() throws IOException {
        AuthorService service = new AuthorService();
        List<Book> bookList_1 = new ArrayList<Book>();
        bookList_1.add(new Book("Born to run", "McDougall", new Date(), 319));
        Author newAuthor1 = new Author("Cristopher McDougall", 67, false, bookList_1);
        service.createAuthor(newAuthor1);

        Book book = new Book("Eat & Run", "Jurek", new Date(), 217);
        List<Book> bookList_2 = new ArrayList<Book>();
        bookList_2.add(book);
        Author newAuthor2 = new Author("Scott Jurek", 52, true, bookList_2);
        bookList_2.add(newAuthor2.getBooks().get(0));   // getBooks returns a List
        service.createAuthor(newAuthor2);


        String path = AuthorServiceTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path + "authors.json";

        // SAVE
        service.save(path);
        // LOAD
        AuthorService service2 = new AuthorService();
        service2.load(path);

        Author author1b = service2.getAuthor("Cristopher McDougall");
        assertEquals(newAuthor1, author1b);
        Author author2b = service2.getAuthor("Scott Jurek");
        assertEquals(newAuthor2, author2b);
        List<Author> authors = service2.getAuthors();
        assertEquals(2, authors.size());

    }

}