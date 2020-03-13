package com.montaury.citadels;

import com.montaury.citadels.character.Character;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.player.HumanController;
import com.montaury.citadels.player.Player;
import com.montaury.citadels.round.Group;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    Board board;
    City city;
    Player player;

    @Before
    public void setUp() {
        board = new Board();
        city = new City(board);
        player = new Player("Didier", 20, city, new HumanController());
    }

    @Test
    public void test_alchemist_receive_refund(){
        Group group = new Group(player, Character.ALCHEMIST);
        group.player().add(6); // argent = 6
        group.player().buildDistrict(Card.MANOR_3); // coût = 3

        int gold = group.player().gold();
        Assertions.assertThat(gold).isEqualTo(6); // Remboursement du district
    }
}
