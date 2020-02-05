package com.montaury.citadels;

import com.montaury.citadels.district.Card;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.montaury.citadels.district.Card.*;
import static org.junit.Assert.*;

public class CityTest {
    @Test
    public void test_5_types_quartiers(){

        Board board = new Board();
        City city = new City(board);
        Possession possession = new Possession(0,null); // 0 or donc 0 score
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
    public void test_bonus_carte_magique(){
        Board myBoard = new Board();
        City myCity = new City(myBoard);
        Possession laPossession = new Possession(9, HashSet.empty());
        myCity.buildDistrict(UNIVERSITY);
        int score = myCity.score(laPossession);
        Assertions.assertThat(score).isEqualTo(8);
    }
}
