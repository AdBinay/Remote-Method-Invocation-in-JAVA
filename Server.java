import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends ImplExample {
    public Server() {}

    public static void main(String[] args) {
        try {
            // Instantiating the implementation class
            ImplExample obj = new ImplExample();

            // Exporting the remote object to create a stub
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Creating the registry (on default port 1099) if it doesn't already exist
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("RMI registry created on port 1099");
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry();
                System.out.println("RMI registry found on port 1099");
            }

            // Binding the stub in the registry
            registry.rebind("Hello", stub); // Use rebind to avoid AlreadyBoundException

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
