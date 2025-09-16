public class Medicaments {
    private String nom;
    private String categorie;
    private double prix;
    private int quantite;
    public Medicaments() {}
    public Medicaments(String nom,
                       String categorie, double prix, int quantite){ this.nom=nom;
        this.categorie=categorie;
        this.prix=prix; this.quantite=quantite;
    }
    public String getNom(){ return nom;
    } public void setNom(String nom){ this.nom=nom;
    }
    public String getCategorie(){ return categorie;
    } public void setCategorie(String categorie){ this.categorie=categorie;
    }
    public double getPrix(){ return prix;
    } public void setPrix(double prix){ this.prix=prix;
    }
    public int getQuantite(){ return quantite;
    } public void setQuantite(int q){ this.quantite=q;
    }
    @Override public String toString(){ return nom + " (" + String.format(java.util.Locale.FRANCE, "%.2f", prix) + "€)";
    }
}
