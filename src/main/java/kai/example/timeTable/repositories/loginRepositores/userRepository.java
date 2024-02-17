package kai.example.timeTable.repositories.loginRepositores;

import kai.example.timeTable.dto.login.userDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userDto, String> {
    userDto findByName(String name);
}
