package com.sda.vendingmachine.service;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoinTest {

    @Test
    public void testingCoin(){

        //given
        double nickelValue = Coin.NICKEL.getValue();
        //then
        assert nickelValue == 0.05;

        double pennyValue = Coin.PENNY.getValue();
        assert pennyValue == 0.01;

        //testing adding coins
        assert pennyValue + pennyValue + pennyValue + pennyValue +
                pennyValue + nickelValue == Coin.DIME.getValue();

        assert Coin.DIME.getValue() + nickelValue + Coin.DIME.getValue()
                == Coin.QUARTER.getValue();


        Coin dummyCoinOne = Coin.DIME;

        assertEquals(dummyCoinOne,Coin.DIME);

        assertTrue(Coin.QUARTER.toString().equals("Coin{value=0.25}"));


    }



}
