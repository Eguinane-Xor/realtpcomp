package pack.prod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {

    @Query("SELECT p FROM Produit p WHERE p.IDlocataire=:IDlocataire")
    Produit findByIDlocataire(int IDlocataire);

    @Query("SELECT p FROM Produit p WHERE p.id=:ID")
    Produit findByID(int ID);

}
