package sparadrap;

public class Clients {
    private int id;
    private String nom;
    private String prenom;
    private String address;
    private String telephone;
    private String ville;
    private String email;
    private Mutuelle mutuelle;
    private Medecins medecinReferent;

    public Clients() {}
    public Clients(int id, String nom, String prenom, String address, String telephone, String ville) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.address=address;
        this.telephone=telephone;
        this.ville=ville; }

    public int getId(){ return id;
    } public void setId(int id){ this.id=id;
    }
    public String getNom(){ return nom;
    } public void setNom(String nom){ this.nom=nom;
    }
    public String getPrenom(){ return prenom;
    } public void setPrenom(String prenom){ this.prenom=prenom;
    }
    public String getAddress(){ return address;
    } public void setAddress(String address){ this.address=address;
    }
    public String getTelephone(){ return telephone;
    } public void setTelephone(String telephone){ this.telephone=telephone;
    }
    public String getVille(){ return ville;
    } public void setVille(String ville){ this.ville=ville;
    }
    public String getEmail(){ return email;
    } public void setEmail(String email){ this.email=email;
    }
    public Mutuelle getMutuelle(){ return mutuelle;
    } public void setMutuelle(Mutuelle m){ this.mutuelle=m;
    }
    public Medecins getMedecinReferent(){ return medecinReferent;
    } public void setMedecinReferent(Medecins m){ this.medecinReferent=m;
    }
    @Override public String toString(){return (nom==null?"Client":nom) + " " + (prenom==null?"":prenom);
    }
}
