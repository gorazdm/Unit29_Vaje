package si.academia.unit29.vaja3.srv;

import si.academia.unit29.vaja3.itf.IAuthorService;
import si.academia.unit29.vaja3.svc.AuthorService;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {

    public static void main(String[] args) throws RemoteException {

        AuthorService svc = new AuthorService();

        //registracija 'IAuthorService' v RMI registru
        try {
            IAuthorService stub = (IAuthorService) UnicastRemoteObject.exportObject(svc, 0);
            final Registry rmiReg = LocateRegistry.getRegistry();  // final?
            rmiReg.bind("AuthorService", stub);
            System.out.println("Server ready");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    // System.out.println("Shutting down server");
                    try {
                        System.out.println("Shuting down server");
                        rmiReg.unbind("AuthorService");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (NotBoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
