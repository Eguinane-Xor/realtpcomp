package pack.prod;

import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq", allocationSize=50)
    protected int id;

    protected double prix;
    protected TypeProduit type;
    protected String adresse;
    protected int nb_Places;
    protected int IDloueur;
    protected int IDlocataire;
    protected boolean enAttente;

    public Produit(){}

    public Produit(double prix, TypeProduit type, String adresse, int nbPlaces,int loueur){
        this.prix = prix;
        this.type = type;
        this.adresse = adresse;
        this.nb_Places = nbPlaces;
        this.IDloueur = loueur;
        this.enAttente = false;
        this.IDlocataire = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return nb_Places;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nb_Places = nbPlaces;
    }

    public int getLoueur() {
        return IDloueur;
    }

    public void setLoueur(int loueur) {
        this.IDloueur = loueur;
    }

    public int getIDlocataire() {
        return IDlocataire;
    }

    public void setIDlocataire(int IDlocataire) {
        this.IDlocataire = IDlocataire;
    }

    public int getIDloueur() {
        return IDloueur;
    }

    public void setIDloueur(int IDloueur) {
        this.IDloueur = IDloueur;
    }

    public boolean isEnAttente() {
        return enAttente;
    }

    public void setEnAttente(boolean enAttente) {
        this.enAttente = enAttente;
    }
}
