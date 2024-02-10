package me.sanjeevsharma.websocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.sanjeevsharma.websocket.user.Status.OFFLINE;
import static me.sanjeevsharma.websocket.user.Status.ONLINE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getNickName())
                .orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(ONLINE);
    }
}
