package sparadrap.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sparadrap.Clients;
import sparadrap.db.AchatRepository;
import sparadrap.db.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientDaoTest {

    private static ClientDao dao;

    @BeforeAll
    static void initDatabase() {
        // Même initialisation que dans Main
        Database.init();
        AchatRepository repo = new AchatRepository();
        repo.createSchema();
        repo.seed();

        dao = new ClientDao();
    }

    @Test
    void findAllRetourneAuMoinsUnClient() {
        List<Clients> clients = dao.findAll();

        assertNotNull(clients);
        assertFalse(clients.isEmpty(), "La liste des clients ne doit pas être vide");
    }

    @Test
    void insertPuisFindByIdFonctionnent() {
        Clients nouveau = new Clients();
        nouveau.setNom("Client DAO Test");

        dao.insert(nouveau);
        int idGenere = nouveau.getId();

        assertTrue(idGenere > 0, "L'id généré doit être > 0");

        Clients depuisLaBase = dao.findById(idGenere);
        assertNotNull(depuisLaBase, "Le client doit être retrouvé en base");
        assertEquals("Client DAO Test", depuisLaBase.getNom());
    }

    @Test
    void updateModifieBienLeNom() {
        Clients client = new Clients();
        client.setNom("Client Avant Update");
        dao.insert(client);
        int id = client.getId();

        client.setNom("Client Après Update");
        dao.update(client);

        Clients depuisLaBase = dao.findById(id);
        assertNotNull(depuisLaBase);
        assertEquals("Client Après Update", depuisLaBase.getNom());
    }

    @Test
    void deleteSupprimeLeClient() {
        Clients client = new Clients();
        client.setNom("Client À Supprimer");
        dao.insert(client);
        int id = client.getId();

        dao.delete(id);

        Clients depuisLaBase = dao.findById(id);
        assertNull(depuisLaBase, "Le client supprimé ne doit plus être retrouvé");
    }
}
