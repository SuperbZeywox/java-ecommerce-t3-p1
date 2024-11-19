package org.example.javaecommercet3p1.Controllers.p2;

import org.example.javaecommercet3p1.Entities.Auth.p2.State;
import org.example.javaecommercet3p1.Services.Auth.p2.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/shop/")
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping(path = "states/search/findByCountryCode")
    ResponseEntity<List<State>> getStateByCountryCode(
            @RequestParam(name = "code") String code
    ){
        return stateService.findStateByCountryCode(code);
    }
}
