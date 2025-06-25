package org.dnyanyog.repo;
import org.dnyanyog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    // ✅ Case-Insensitive Search by ID and Name
    @Query("SELECT p FROM Product p WHERE p.productId = :productId AND LOWER(p.productName) = LOWER(:productName)")
    List<Product> findByProductIdAndProductName(@Param("productId") int productId, @Param("productName") String productName);

	
}

