package sparadrap.controller;

import sparadrap.Clients;
import sparadrap.dao.ClientDao;

import java.util.List;

public class ClientsController {

    private final ClientDao dao;

    public ClientsController() {
        this.dao = new ClientDao();
    }

    public List<Clients> listClients() {
        return dao.findAll();
    }

    public Clients createClient(String nom) {
        Clients client = new Clients();
        client.setNom(nom);
        return dao.insert(client);
    }

    public void updateClient(Clients client, String nouveauNom) {
        if (client == null) {
            return;
        }
        client.setNom(nouveauNom);
        dao.update(client);
    }

    public void deleteClient(Clients client) {
        if (client == null) {
            return;
        }
        dao.delete(client);
    }
}
