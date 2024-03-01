package kai.example.timeTable.services;

import kai.example.timeTable.models.Session;
import org.springframework.stereotype.Service;

@Service
public class RefreshTimeTableService {
    public String deleteSession(Integer id) {
        return "удаление прошло успешно";
    }

    public String createSession(Session session, Integer group ,String day) {
        return "добавление прошло успешно";
    }

    public String refreshSession(Session session) {return "изменение прошло успешно";}
}
