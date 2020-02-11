package com.montaury.citadels;

import com.montaury.citadels.district.Card;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static com.montaury.citadels.district.Card.*;
import static org.junit.Assert.*;

public class CityTest {

    Board board;
    City city;

    @Before
    public void setUp() {
        board = new Board();
        city = new City(board);
    }

    @Test
    public void test_5_types_quartiers() {
        Possession possession = new Possession(0, null); // 0 or donc 0 score
        city.buildDistrict(Card.MANOR_1); // +3 score, type : NOBLE
        city.buildDistrict(Card.PRISON_2); // +2 score, type : MILITARY
        city.buildDistrict(Card.TAVERN_2); // +1 score, type : TRADE
        city.buildDistrict(Card.CHURCH_3); // +2 score, type : RELIGIOUS
        city.buildDistrict(Card.UNIVERSITY); // +8 score, type : SPECIAL
        city.buildDistrict(Card.PRISON_3); // +2 score, type : MILITARY
        city.buildDistrict((Card.MANOR_2)); // +3 score, type : NOBLE
        int score = city.score(possession);
        Assertions.assertThat(score).isEqualTo(24); // 21 + bonus des 5 types (+3) = 24
    }

    @Test
    public void test_bonus_carte_magique() {
        Possession laPossession = new Possession(9, null);
        city.buildDistrict(UNIVERSITY);
        int score = city.score(laPossession);
        Assertions.assertThat(score).isEqualTo(8);
    }

    @Test
    public void test_construction_cout(){
        Possession possession = new Possession(0,null);
        city.buildDistrict(Card.MANOR_5); // +3
        city.buildDistrict(Card.TAVERN_5); // +1
        city.buildDistrict(Card.WATCHTOWER_2); // +1
        int score = city.score(possession);
        Assertions.assertThat(score).isEqualTo(5);
    }
}
