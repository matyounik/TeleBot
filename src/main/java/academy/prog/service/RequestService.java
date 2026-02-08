package academy.prog.service;

import academy.prog.model.Request;
import academy.prog.repo.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request save(String text) {
        return requestRepository.save(new Request(text));
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}