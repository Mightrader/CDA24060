package sparadrap;

public class Clients {
    private final int id;
    private final String nom;
    private final String prenom;
    private final String telephone;
    private final String ville;
    private sparadrap.Mutuelle mutuelle;

    public Clients(int id, String nom, String prenom, String telephone, String ville) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.telephone=telephone;
        this.ville=ville; }

    public int getId(){ return id;
    }

    public String getNom(){ return nom;
    }

    public String getPrenom(){ return prenom;
    }

    public String getTelephone(){ return telephone;
    }

    public String getVille(){ return ville;
    }

    public sparadrap.Mutuelle getMutuelle(){ return mutuelle;
    } public void setMutuelle(sparadrap.Mutuelle m){ this.mutuelle=m;
    }

    public void setMedecinReferent(){
    }
    @Override public String toString(){return (nom==null?"Client":nom) + " " + (prenom==null?"":prenom);
    }

}
