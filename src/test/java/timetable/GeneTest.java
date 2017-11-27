package timetable;

import org.junit.Test;

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
    }
}
