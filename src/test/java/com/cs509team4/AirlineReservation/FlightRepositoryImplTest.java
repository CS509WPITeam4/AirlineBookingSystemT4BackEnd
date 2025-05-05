package com.cs509team4.AirlineReservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightRepositoryImplTest {

    @Mock
    private EntityManager em;

    @Mock
    private Query query;

    @InjectMocks
    private FlightRepositoryImpl repo;

    @Captor
    private ArgumentCaptor<String> sqlCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // whenever createNativeQuery is called, capture the SQL and return our mock Query
        when(em.createNativeQuery(sqlCaptor.capture())).thenReturn(query);
    }

    @Test
    void searchWithLayovers_withoutDate_bindsExpectedParameters() {
        // prepare a single-row result
        List<Object[]> expected = Collections.singletonList(new Object[]{1L, 2L, 3L});
        when(query.getResultList()).thenReturn(expected);

        // call with 2 layovers, no date
        List<Object[]> result = repo.searchWithLayovers(
                "AAA", "ZZZ",
                2,    // numLayovers
                5,    // max rows
                null, // date
                120   // maxLayover minutes
        );

        // should return exactly our mock list
        assertSame(expected, result);

        // verify SQL construction captured
        String sql = sqlCaptor.getValue();
        assertTrue(sql.startsWith("SELECT f1.Id, f2.Id, f3.Id"));
        assertTrue(sql.contains("FROM flights f1"));
        assertTrue(sql.contains("JOIN flights f2"));
        assertTrue(sql.contains("JOIN flights f3"));
        assertTrue(sql.contains("LIMIT 5"));

        // verify parameters
        verify(query).setParameter("departAirport", "AAA");
        verify(query).setParameter("arriveAirport", "ZZZ");
        verify(query).setParameter("maxLayover", 120);
        verify(query, never()).setParameter(eq("date"), any());
        verify(query).getResultList();
    }

    @Test
    void searchWithLayovers_withDate_bindsDateParameter() {
        LocalDate d = LocalDate.of(2025, 12, 31);
        // empty result
        when(query.getResultList()).thenReturn(Collections.emptyList());

        // call with zero layovers and a date
        List<Object[]> result = repo.searchWithLayovers(
                "LAX", "JFK",
                0,   // no layovers
                10,
                d,   // date supplied
                90
        );

        assertEquals(0, result.size());

        String sql = sqlCaptor.getValue();
        assertTrue(sql.startsWith("SELECT f1.Id"));
        assertTrue(sql.contains("WHERE f1.DepartAirport = :departAirport"));
        assertTrue(sql.contains("AND DATE(f1.DepartDateTime) = :date"));
        assertTrue(sql.contains("LIMIT 10"));

        verify(query).setParameter("departAirport", "LAX");
        verify(query).setParameter("arriveAirport", "JFK");
        verify(query).setParameter("date", d);
        verify(query).setParameter("maxLayover", 90);
        verify(query).getResultList();
    }
}
