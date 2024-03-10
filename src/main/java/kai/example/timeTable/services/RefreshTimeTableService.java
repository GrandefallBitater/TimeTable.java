package kai.example.timeTable.services;

import kai.example.timeTable.models.Session;
import kai.example.timeTable.repositories.session.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefreshTimeTableService {
    SessionRepository repository = new SessionRepository();
    public String deleteSession(Integer id) {
        repository.deleteSession(id);
        return "удаление прошло успешно";
    }

    public String createSession(Session session, Integer group ,String day) {
        repository.addSession(session, group, day);
        return "добавление прошло успешно";
    }

    public String refreshSession(Session session) {
        repository.replaceSession(session);
        return "изменение прошло успешно";
    }
}
