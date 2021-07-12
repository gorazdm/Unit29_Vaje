package si.academia.unit29.vaja3.client;

import si.academia.unit29.vaja3.itf.Author;
import si.academia.unit29.vaja3.itf.Book;
import si.academia.unit29.vaja3.itf.IAuthorService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {

        try {
            Registry rmiReg = LocateRegistry.getRegistry(null);
            IAuthorService svc = (IAuthorService) rmiReg.lookup("AuthorService");

            List<Book> auth1Books = new ArrayList<Book>();
            Book book = new Book("PSI", "Doberman",
                    new GregorianCalendar(2010, 31,3).getTime(), 777);
            auth1Books.add(new Book("Tripwire", "Bantam",
                    new GregorianCalendar(1999, 6, 2).getTime(), 300));
            auth1Books.add(book);

            Author auth1 = new Author("Lee Child", 55, true, auth1Books);
            svc.createAuthor(auth1);
            Author auth2 = new Author("Agatha Christie", 65, false, null);
            svc.createAuthor(auth2);

            List<Author> auths = svc.getAuthors();
            System.out.println("\nAuthors=" + auths.toString());
            System.out.println("\nAuthor('Lee Child') --> " + svc.getAuthor("Lee Child"));
            System.out.println("\nKoncno ti je uspelo!!!");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
