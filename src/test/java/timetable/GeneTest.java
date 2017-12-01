package timetable;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

public class GeneTest {

    @Test
    public void 연습테스트(){
        Gene gene = new Gene();
        int index=0;
        while(true){
            index++;
            gene.makeGene();
            gene.testGene();
            if(gene.getGoodness()>0){
                System.out.println(gene.getGoodness());
                System.out.println(gene.getGene());
                gene.printTable();
                break;
            }
        }
        System.out.println(index);
        assertThat("테스트",true);

/*       BigInteger bi = new BigInteger("1");
        for(int i=1;i<=36;i++){
            StringBuilder s = new StringBuilder();
            s.append(i);
            System.out.println(bi=bi.multiply(new BigInteger(s.toString())));
        }
        Random random = new Random() ;*/
    }
}
