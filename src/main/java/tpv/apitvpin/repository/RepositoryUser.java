package tpv.apitvpin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tpv.apitvpin.modelos.User;

public interface RepositoryUser extends JpaRepository<User, Integer> {
    public User findUsuarioByNickAndPassword(String nick, String password);
}
