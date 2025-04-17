package com.cs509team4.AirlineReservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightRepositoryImpl implements FlightRepositoryCustomSQL{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> searchWithLayovers(String departAirport, String arriveAirport, int numLayovers, int max) {
        StringBuilder sb = new StringBuilder("SELECT ");

        // SELECT
        for (int i = 1; i <= numLayovers + 1; i++) {
            sb.append("f").append(i).append(".Id");
            if (i != numLayovers + 1) sb.append(", ");
        }

        // FROM and JOIN
        sb.append(" FROM flights f1 ");
        for (int i = 2; i <= numLayovers + 1; i++) {
            sb.append("JOIN flights f").append(i)
                    .append(" ON f").append(i - 1).append(".ArriveAirport = f").append(i).append(".DepartAirport ")
                    .append("AND f").append(i - 1).append(".ArriveDateTime < f").append(i).append(".DepartDateTime ")
                    .append("AND TIMESTAMPDIFF(MINUTE, f").append(i - 1).append(".ArriveDateTime, f").append(i).append(".DepartDateTime) BETWEEN 60 AND 400 ");
        }

        // WHERE
        sb.append("WHERE f1.DepartAirport = :departAirport ");
        sb.append("AND f").append(numLayovers + 1).append(".ArriveAirport = :arriveAirport ");
        sb.append("LIMIT ").append(max);

        // Run
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("departAirport", departAirport);
        query.setParameter("arriveAirport", arriveAirport);

        return query.getResultList();
    }
}
