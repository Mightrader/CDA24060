package sparadrap;

public class Medecins {
    private final int id;
    private final String nom;
    private final String prenom;
    private final String telephone;
    private final String ville;

    public Medecins(int id, String nom, String prenom, String telephone, String ville) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.telephone=telephone;
        this.ville=ville;
    }

    public int getId() { return id;
    }

    public String getNom() { return nom;
    }

    public String getPrenom() { return prenom;
    }

    public String getTelephone() { return telephone;
    }

    public String getVille() { return ville;
    }

    @Override public String toString(){ return "Dr " + (nom==null?"?":nom) + " " + (prenom==null?"":prenom);
    }
}
