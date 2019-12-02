package pack.prod;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends CrudRepository<Produit,Long>{

    @Override
    List<Produit> findAll();

}
