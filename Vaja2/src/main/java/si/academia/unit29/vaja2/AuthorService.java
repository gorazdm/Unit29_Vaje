package si.academia.unit29.vaja2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class AuthorService {

    private Dictionary<String, Author> authors = new Hashtable<String, Author>();

    public void createAuthor(Author author) { authors.put(author.getName(), author); }
    public void deleteAuthor(Author author) { authors.remove(author.getName()); }
    public Author getAuthor(String name) { return authors.get(name); }
    public List<Author> getAuthors() {
        return new ArrayList<Author>(((Hashtable<String, Author>)authors).values());
    }   // return value je List z objekti Author -- dobimo ga iz Hashtable authors, iscemo va samo values



    // 1. SAVE - implementiraj serializacijo privatnega memberja 'authors' v datoteko z uporabo Jackson Object Mapper-ja
    public void save(String path) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        objMap.enable(SerializationFeature.INDENT_OUTPUT);
        objMap.writeValue(new File(path), authors);
    }

    // 2. LOAD - implementiraj deserializacijo privatnega memberja 'authors' iz datoteke z uporabo Jackson Object Mapper-ja
    public void load(String path) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        authors = objMap.readValue(new File(path), new TypeReference<Hashtable<String, Author>>() {});
    }


    // path
    public static String getPathString() {
        String path = AuthorService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return path + "service.json";
    }

}
