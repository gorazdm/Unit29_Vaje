package si.academia.unit29.vaja2;

import java.util.List;
import java.util.Objects;

public class Author {

    private String name;
    private int age;
    private boolean alive;
    private List<Book> books;

    public Author() {
    }

    public Author(String name, int age, boolean alive, List<Book> books) {
        this.name = name;
        this.age = age;
        this.alive = alive;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", alive=" + alive +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return age == author.age && alive == author.alive && name.equals(author.name) && books.equals(author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, alive, books);
    }
}
