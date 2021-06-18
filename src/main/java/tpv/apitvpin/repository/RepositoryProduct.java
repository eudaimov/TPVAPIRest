package tpv.apitvpin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tpv.apitvpin.modelos.Product;

public interface RepositoryProduct extends JpaRepository<Product, Integer> {


}