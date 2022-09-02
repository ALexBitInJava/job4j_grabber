package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaTest {

    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.SEPTEMBER, 2, 14, 29);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Ignore
    @Test//(expected = IllegalArgumentException.class)
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.SEPTEMBER, 2, 14, 20);
        Ticket ticket = cinema.buy(account, -1, -1, date);
    }

    @Ignore
    @Test//(expected = IllegalArgumentException.class)
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 18, 50, 14, 20);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test//(expected = IllegalArgumentException.class)
    public void whenThePlaceIsOccupied() {
        Account account1 = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.SEPTEMBER, 2, 14, 35);
        Ticket ticket1 = cinema.buy(account1, 1, 1, date);
        Ticket ticket2 = cinema.buy(account2, 1, 1, date);
    }

}