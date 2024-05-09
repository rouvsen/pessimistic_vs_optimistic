package az.rouvsen.pessimistic_vs_optimistic.service;

import az.rouvsen.pessimistic_vs_optimistic.model.response.GetUsersResponse;

import java.util.List;

public interface UserService {
    List<GetUsersResponse> getUsers();
}
