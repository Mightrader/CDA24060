package sparadrap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pharmacie {
    private List<Medecins> medecins = new ArrayList<>();
    private List<Clients> clients = new ArrayList<>();
    private List<Medicaments> medicaments = new ArrayList<>();
    private List<Mutuelle> mutuelles = new ArrayList<>();
    private List<Achat> achats = new ArrayList<>();

    public Pharmacie(){ seed(); }

    private void seed(){
        // Initialise un jeu de données minimal pour la démo (médecins, clients, mutuelles, médicaments)
        Mutuelle mA = new Mutuelle("Axa Sante", "0800123456", "contact@axa.fr", "Paris", 0.7);
        Mutuelle mB = new Mutuelle("MMA Sante", "0800654321", "info@mma.fr", "Rennes", 0.6);
        mutuelles.add(mA); mutuelles.add(mB);

        Medecins d1 = new Medecins(1,"Martin","Alice","0102030405","Paris");
        Medecins d2 = new Medecins(2,"Durand","Paul","0102030406","Lyon");
        medecins.add(d1); medecins.add(d2);

        Clients c1 = new Clients(1,"Dupont","Jean","12 rue des Fleurs","0600000000","Rennes");
        c1.setMutuelle(mA); c1.setMedecinReferent(d1);
        Clients c2 = new Clients(2,"Petit","Lucie","5 av de Paris","0611111111","Bordeaux");
        c2.setMutuelle(mB); c2.setMedecinReferent(d2);
        Clients c3 = new Clients(3,"Moreau","Lea","rue sans nom","0622222222","Lyon"); // pas de mutuelle volontairement
        clients.add(c1); clients.add(c2); clients.add(c3);

        medicaments.add(new Medicaments("Doliprane","Analgésique",2.3,120));
        medicaments.add(new Medicaments("Ibuprofène","Anti-inflammatoire",3.9,80));
        medicaments.add(new Medicaments("Zovirax","Antiviral",6.5,40));
    }

    public List<Medecins> getMedecins(){ return Collections.unmodifiableList(medecins);
    }
    public List<Clients> getClients(){ return Collections.unmodifiableList(clients);
    }
    public List<Medicaments> getMedicaments(){ return Collections.unmodifiableList(medicaments);
    }
    public List<Mutuelle> getMutuelles(){ return Collections.unmodifiableList(mutuelles);
    }
    public List<Achat> getAchats(){ return Collections.unmodifiableList(achats);
    }

    public Achat acheter(Clients client, Medecins medecin, Mutuelle mutuelle, Medicaments medicament, int quantite){
        // Règles de validation de base (quantités, prix, disponibilité)
        if (medicament == null || quantite <= 0) return null;
        if (medicament.getQuantite() < quantite) {
            return null;
        }
        if (medicament.getPrix() < 0) return null;
        // Cohérence mutuelle: si le client a une mutuelle, elle doit correspondre à celle fournie
        if (client != null) {
            Mutuelle mutuelleClient = client.getMutuelle();
            if (mutuelleClient != null && mutuelle != null && mutuelleClient != mutuelle) {
                return null; // incohérence mutuelle
            }
            if (mutuelleClient == null && mutuelle != null) {
                return null; // client sans mutuelle mais mutuelle fournie
            }
        }
        // Calcul du total avec éventuelle prise en charge de la mutuelle
        BigDecimal total = BigDecimal.valueOf(medicament.getPrix()).multiply(BigDecimal.valueOf(quantite));
        if (mutuelle != null) {
            BigDecimal taux = BigDecimal.valueOf(1.0 - mutuelle.getTauxPriseEnCharge());
            total = total.multiply(taux);
        }
        total = total.setScale(2, RoundingMode.HALF_UP);
        // Décrémente le stock après validation et calcul
        medicament.setQuantite(medicament.getQuantite() - quantite);
        // Construit et sauvegarde l'achat
        Achat a = new Achat(client, medecin, mutuelle, medicament, quantite, total);
        achats.add(a);
        return a;
    }
}
