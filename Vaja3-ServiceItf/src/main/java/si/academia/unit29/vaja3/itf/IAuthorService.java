package si.academia.unit29.vaja3.itf;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAuthorService extends Remote {

    public void createAuthor(Author author) throws RemoteException;
    public void deleteAuthor(Author author) throws RemoteException;
    public Author getAuthor(String name) throws RemoteException;
    public List<Author> getAuthors() throws RemoteException;

    public void save(String path) throws RemoteException, IOException;
    public void load(String path) throws RemoteException, IOException;
}
