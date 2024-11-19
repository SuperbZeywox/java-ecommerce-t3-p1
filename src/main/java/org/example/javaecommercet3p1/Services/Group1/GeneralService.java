package org.example.javaecommercet3p1.Services.Group1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.javaecommercet3p1.Entities.Group1.Desktop;
import org.example.javaecommercet3p1.Entities.Group1.Phone;
import org.example.javaecommercet3p1.Entities.Template.Product;
import org.example.javaecommercet3p1.Repos.Group1.DesktopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class GeneralService {

    @Autowired
    @Qualifier("group1EntityManager")
    private EntityManager entityManager;
//    public GeneralService(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

//    public List<Object> performParallelSearch(String keyword) throws InterruptedException, ExecutionException {
    public Map<String, List<? extends Product>> performParallelSearch(String keyword) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4); // Using 4 thread
// Define the queries
        Callable<List<Desktop>> query1Task = () -> {
//            TypedQuery<Object> query1 = entityManager.createQuery("SELECT e FROM Desktop e WHERE e.name = :condition", Object.class);
//            TypedQuery<Desktop> query1 = entityManager.createQuery("SELECT e FROM Desktop e WHERE e.name like '%'+ :condition +'%'", Desktop.class);
//            TypedQuery<Desktop> query1 = entityManager.createQuery("SELECT e FROM Desktop e WHERE e.name like :condition", Desktop.class);
            TypedQuery<Desktop> query1 = entityManager
                    .createQuery("SELECT e FROM Desktop e WHERE e.name like :condition OR e.description like :condition"
                            , Desktop.class);
            query1.setParameter("condition", '%' + keyword + '%');
            return query1.getResultList();
        };
        Callable<List<Phone>> query2Task = () -> {
            TypedQuery<Phone> query2 = entityManager
                    .createQuery("SELECT e FROM Phone e WHERE e.name like :condition OR e.description like :condition"
                    , Phone.class);
            query2.setParameter("condition", '%' + keyword + '%');
            return query2.getResultList();
        };
// Submit tasks to the executor
        Future<List<Desktop>> future1 = executorService.submit(query1Task);
        Future<List<Phone>> future2 = executorService.submit(query2Task);
// Wait for results
//        List<Desktop> result1 = future1.get();
//        result1.forEach(System.out::println);
//        List<Phone> result2 = future2.get();
// Combine the results if necessary
//        List<Object> combinedResults = new ArrayList<>();
//        combinedResults.addAll(result1);
//        combinedResults.addAll(result2);

        Map<String,List<? extends Product>> combinedResultsMap = new HashMap<>();
//        combinedResultsMap.put("Desktop", result1);
//        combinedResultsMap.put("Phone", result2);
//        executorService.shutdown(); // Remember to shut down the executor
//        return combinedResults;
        combinedResultsMap.put("Desktop", future1.get());
        combinedResultsMap.put("Phone", future2.get());
        executorService.shutdown(); // Remember to shut down the executor
        return combinedResultsMap;
    }



}
