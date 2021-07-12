package si.academia.unit29.vaja3.svc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import si.academia.unit29.vaja3.itf.Author;
import si.academia.unit29.vaja3.itf.IAuthorService;

import java.io.File;
import java.io.IOException;
//import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class AuthorService implements IAuthorService {

    private Dictionary<String, Author> authors = new Hashtable<String, Author>();

    public AuthorService() {
    }

    @Override
    public void createAuthor(Author author) { authors.put(author.getName(), author); }      // => Hashtable
    @Override
    public void deleteAuthor(Author author) { authors.remove(author); }
    @Override
    public Author getAuthor(String name) { return authors.get(name); }                      // => object Author
    public List<Author> getAuthors() {                                                      // => ArrayList
        return new ArrayList<Author>(((Hashtable<String, Author>)authors).values());
    }
    @Override
    public void save(String path) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        objMap.enable(SerializationFeature.INDENT_OUTPUT);
        objMap.writeValue(new File(path), authors);
    }
    @Override
    public void load(String path) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        authors = objMap.readValue(new File(path), new TypeReference<Hashtable<String, Author>>() {});
    }
}
