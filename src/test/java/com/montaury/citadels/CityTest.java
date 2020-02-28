package com.montaury.citadels;

import com.montaury.citadels.character.Character;
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
    public void test_quarters_5_types() {
        Possession possession = new Possession(0, null); // 0 or donc 0 score
        city.buildDistrict(Card.MANOR_1); // +3 score, type : NOBLE
        city.buildDistrict(Card.PRISON_2); // +2 score, type : MILITARY
        city.buildDistrict(Card.TAVERN_2); // +1 score, type : TRADE
        city.buildDistrict(Card.CHURCH_3); // +2 score, type : RELIGIOUS
        city.buildDistrict(Card.UNIVERSITY); // +8 score, type : SPECIAL
        int score = city.score(possession);
        Assertions.assertThat(score).isEqualTo(19); // 16 + bonus des 5 types (+3) = 19
    }

    @Test
    public void test_bonus_magic_card() {
        Possession laPossession = new Possession(9, null);
        city.buildDistrict(UNIVERSITY);
        int score = city.score(laPossession);
        Assertions.assertThat(score).isEqualTo(8);
    }

    @Test
    public void test_construction_cost() {
        Possession possession = new Possession(0, null);
        city.buildDistrict(Card.MANOR_5); // +3
        city.buildDistrict(Card.TAVERN_5); // +1
        city.buildDistrict(Card.WATCHTOWER_2); // +1
        int score = city.score(possession);
        Assertions.assertThat(score).isEqualTo(5);
    }

    @Test
    public void test_plus_four_if_player_finishes_first() {
        city.buildDistrict(Card.MARKET_3);
        city.buildDistrict(Card.TOWN_HALL_2);
        city.buildDistrict(Card.KEEP_2);
        city.buildDistrict(Card.MARKET_1);
        city.buildDistrict(Card.TOWN_HALL_1);
        city.buildDistrict(Card.KEEP_1);
        city.buildDistrict(Card.MARKET_2);
        Possession possession = new Possession(0, null);
        int score = city.score(possession);

        Assertions.assertThat(score).isEqualTo(26);
    }

    @Test
    public void test_bonus_university(){
        city.buildDistrict(UNIVERSITY);
        Possession possession = new Possession(0, null);
        int score = city.score(possession);
        Assertions.assertThat(score).isEqualTo(8); // Coût : 6 / Bonus : 2 => 8 points
    }

    // Test si la fonction "" retourne bien le bon nombre de quartiers possédés, en l'occurrence 8
    @Test
    public void test_full_quarter() {
        int nbQuarters;
        city.buildDistrict(MANOR_1);
        city.buildDistrict(CASTLE_1);
        city.buildDistrict(WATCHTOWER_1);
        city.buildDistrict(PRISON_1);
        city.buildDistrict(BATTLEFIELD_3);
        city.buildDistrict(TAVERN_4);
        city.buildDistrict(TRADING_POST_3);
        city.buildDistrict(DOCKS_2);

        nbQuarters = city.districts().size();
        Assertions.assertThat(nbQuarters).isEqualTo(8);
    }
}