package org.example.javaecommercet3p1.Services.Auth.p2;

import org.example.javaecommercet3p1.Entities.Auth.p2.State;
import org.example.javaecommercet3p1.Repos.Auth.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.javaecommercet3p1.CachedData.CachedResources.NOT_FOUND;
import static org.example.javaecommercet3p1.CachedData.CachedResources.OK_STATUS;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public ResponseEntity<List<State>> findStateByCountryCode(String code) {
        List<State> state = stateRepository.findStateByCountryCode(code);
        return !state.isEmpty() ? new ResponseEntity<>(state, OK_STATUS) : NOT_FOUND;
    }

}
