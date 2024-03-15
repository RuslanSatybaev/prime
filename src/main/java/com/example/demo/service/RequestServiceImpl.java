package com.example.demo.service;

import com.example.demo.entity.Request;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;


    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void distributeRequests() {
        List<User> onlineUsers = userRepository.findByStatus(Status.ONLINE);
        List<User> offlineUsers = userRepository.findByStatus(Status.OFFLINE);


        for (User offlineUser : offlineUsers) {
            if (!onlineUsers.isEmpty()) {
                User randomOnlineUser = onlineUsers.get(new Random().nextInt(onlineUsers.size()));
                List<Request> openRequests = requestRepository.findByStatus(Status.OPEN);
                if (!openRequests.isEmpty()) {
                    Request randomOpenRequest = openRequests.get(new Random().nextInt(openRequests.size()));
                    randomOpenRequest.setResponsible(randomOnlineUser);
                    requestRepository.save(randomOpenRequest);
                }
            }
        }
    }
}
