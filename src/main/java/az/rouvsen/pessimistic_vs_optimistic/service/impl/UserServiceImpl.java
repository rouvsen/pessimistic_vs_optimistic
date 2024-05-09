package az.rouvsen.pessimistic_vs_optimistic.service.impl;

import az.rouvsen.pessimistic_vs_optimistic.model.response.UserResponse;
import az.rouvsen.pessimistic_vs_optimistic.repository.UserRepository;
import az.rouvsen.pessimistic_vs_optimistic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        List<UserResponse> responseList = new ArrayList<>();
        userRepository.findAll().forEach(user -> responseList.add(new UserResponse(user.getName())));
        return responseList;
    }
}
