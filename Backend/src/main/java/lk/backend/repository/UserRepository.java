package lk.backend.repository;

import java.util.List;
import java.util.Optional;
import lk.backend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<AppUser, String> {

    @Query(value = "from AppUser where (email=?1 or contactNumber=?1) and password=?2")
    Optional<AppUser> login(String email, String password);

    List<AppUser> getAllByUserType(String type);
}
