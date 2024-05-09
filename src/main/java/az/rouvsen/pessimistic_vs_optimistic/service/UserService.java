package az.rouvsen.pessimistic_vs_optimistic.service;

import az.rouvsen.pessimistic_vs_optimistic.model.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
}
