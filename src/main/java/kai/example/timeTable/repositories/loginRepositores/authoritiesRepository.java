package kai.example.timeTable.repositories.loginRepositores;

import kai.example.timeTable.dto.login.authoritiesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface authoritiesRepository extends JpaRepository<authoritiesDto, String> {
    authoritiesDto findByName(String name);
}
