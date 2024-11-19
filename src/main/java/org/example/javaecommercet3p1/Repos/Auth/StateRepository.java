package org.example.javaecommercet3p1.Repos.Auth;

import org.example.javaecommercet3p1.Entities.Auth.p2.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stateRepository")
public interface StateRepository extends JpaRepository<State, Integer> {

    List<State> findStateByCountryCode(String code);

}
