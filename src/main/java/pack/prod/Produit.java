package pack.prod;

import javax.persistence.*;

@Entity
@Table(name = "Produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    protected double prix;
    protected TypeProduit type;
    protected String adresse;
    protected int nbPlaces;

    public Produit(){}

    public Produit(double prix, TypeProduit type, String adresse, int nbPlaces) {
        this.prix = prix;
        this.type = type;
        this.adresse = adresse;
        this.nbPlaces = nbPlaces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(TypeProduit type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
}
