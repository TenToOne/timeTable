package timetable;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

public class GeneTest {

    @Test
    public void 연습테스트(){
        Gene gene = new Gene();
        gene.setGene(new StringBuilder("111011100100000111101010000011000000011101010100110101101000010100000001000111000111111100010111000101101000011000101111011110110011001010111011100000110001100001111100000111000001000000100000"));
        gene.testGene();
        gene.printTable();
        assertThat("테스트",true);


    }
}
