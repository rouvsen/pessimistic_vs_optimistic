package az.rouvsen.pessimistic_vs_optimistic.service.impl;

import az.rouvsen.pessimistic_vs_optimistic.model.response.GetUsersResponse;
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
    public List<GetUsersResponse> getUsers() {
        List<GetUsersResponse> responseList = new ArrayList<>();
        userRepository.findAll().forEach(user -> responseList.add(new GetUsersResponse(user.getName())));
        return responseList;
    }
}
