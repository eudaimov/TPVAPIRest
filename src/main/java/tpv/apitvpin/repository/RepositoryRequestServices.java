package tpv.apitvpin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tpv.apitvpin.modelos.TableRequestServices;
import tpv.apitvpin.modelos.TableServices;

import java.util.List;

public interface RepositoryRequestServices extends JpaRepository<TableRequestServices, Integer> {
    public List<TableRequestServices> findByTableServices(TableServices tableServices);

}
