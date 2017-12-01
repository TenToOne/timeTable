package timetable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Heredity {
    static final double Pc=0.7;
    static final double Pm = 0.0001;
    static final int N = 100000;
    static final int Age = 100;

    public static void main(String[] args) {
        ArrayList<Gene>  genes = new ArrayList<Gene>();
        int max=0;
        int[] goodness = new int[N];
        int[] roulette = new int[N];
        long goodsum=0;
        int maxInt=0;
        int[] array = new int[Age];
        for(int i=0;i<N;i++){
            if(i%(N/100)==0) System.out.print("i");
            genes.add(new Gene());
            genes.get(i).makeGene();
            int good=genes.get(i).testGene();
            goodness[i]=good;
            goodsum+=good;
        }
        System.out.print("E");

        int zero = 0 ;
        double avg;

        for(int i:goodness){
            array[0]+=i;
            if(max<i)   max=i;
            if(i==0) zero++;
        }
        System.out.print("N");
        avg=(double)(Math.round(array[0]/N)*100)/100.0;
        System.out.println("D");
        System.out.println("Age 0 : "+array[0] +"& Max : "+max+" | Avg : "+avg+" | Zero : "+zero);

        for(int a =0;a<Age-1;a++) {
            ArrayList<Gene> newGenes = new ArrayList<Gene>();
            int rouletteSum=0;
            for(int i=0;i<N;i++){
                goodness[i] = genes.get(i).testGene();
                roulette[i] = goodness[i]*goodness[i];
                rouletteSum+=roulette[i];
            }
            for (int i = 0; i < N / 2; i++) {
                if(i%(N/200)==0) System.out.print("i");
                ArrayList<Gene> geneAge = new ArrayList<Gene>();
                ArrayList<Gene> newGeneAge = new ArrayList<Gene>();
                int temp=0;
                int tempIndex=0;
                for (int j = 0; j < 2; j++) {
                        int s = (int) (Math.random() * rouletteSum);
                        int index = 0;
                        for (; ; index++) {
                            index%=N;
                            if ((s -= roulette[index]) < 0) break;
                        }
                        if (j==0) {
                            tempIndex=index;
                            geneAge.add(genes.get(index));
                            temp=goodness[index];
                            goodness[index]=0;
                        }
                        else {
                            geneAge.add(genes.get(index));
                            goodness[tempIndex]=temp;
                        }
                }
                newGeneAge = crossover(geneAge);

                for (int k = 0; k < 2; k++) {
                    Gene newGene = modification(newGeneAge.get(0));
                    newGeneAge.remove(0);
                    newGeneAge.add(newGene);
                }
                newGenes.addAll(newGeneAge);

            }
            System.out.print("E");
            Collections.sort(genes);
//            System.out.println(genes.get(0).getGoodness()+"|"+genes.get(N-1).getGoodness());
            Collections.sort(newGenes);
//            System.out.println(newGenes.get(0).getGoodness()+"|"+newGenes.get(N-1).getGoodness());
            genes.addAll(newGenes);
            Collections.sort(genes);
//            System.out.println(genes.get(0).getGoodness()+"|"+genes.get(N-1).getGoodness());
            newGenes.clear();
            for(int i=0;i<N;i++){
                genes.remove(0);
            }
//            genes.clear();
//            genes.addAll(newGenes);
            newGenes.clear();
            System.out.print("N");
            max=0;
            maxInt=0;
            zero=0;
            for (Gene i : genes) {
                int num = i.getGoodness();
                array[a+1]+=num;
                if(max<num){
                    max=num;
                    maxInt=genes.indexOf(i);
                }
                if(num==0) zero++;
            }
            System.out.print("D");
            avg=(double)(Math.round(array[a+1]/N)*100)/100.0;
            System.out.println();
            System.out.println("Age "+(a+1)+" : "+array[a+1]+"& Max : "+max+" | Avg : "+avg+" | Zero : "+zero);
        }
        System.out.println(genes.get(maxInt).getGene());
        genes.get(maxInt).printTable();
    }

    private static Gene modification(Gene gene) {
        if(Math.random()<Pm){
            StringBuilder s = new StringBuilder(gene.getGene());
            StringBuilder newS = new StringBuilder();
            int mod =(int)(Math.random()*192);
            for(int i=0;i<s.length();i++){
                if(i==mod){
                    if(s.charAt(i)=='1') newS.append('0');
                    else newS.append('1');
                }
                else newS.append(s.charAt(i));
            }
            gene.setGene(newS);
        }
        return gene;
    }

    private static ArrayList<Gene> crossover(ArrayList<Gene> geneAge) {
//        System.out.println("Test2");
//        System.out.println(geneAge.get(0).testGene());
//        System.out.println(geneAge.get(1).testGene());
        if(Math.random()<Pc) {
            StringBuilder newgene1 = new StringBuilder();
            StringBuilder newgene2 = new StringBuilder();
            StringBuilder gene1 = new StringBuilder(geneAge.get(0).getGene());
            StringBuilder gene2 = new StringBuilder(geneAge.get(1).getGene());
            geneAge.clear();
            for(int i=0;i<192;i++){
                if(Math.random()>0.5){
                    newgene1.append(gene1.toString().charAt(i));
                    newgene2.append(gene2.toString().charAt(i));
                }
                else{
                    newgene1.append(gene2.toString().charAt(i));
                    newgene2.append(gene1.toString().charAt(i));
                }
            }
            geneAge.add(new Gene()); geneAge.get(0).setGene(newgene1);
            geneAge.add(new Gene()); geneAge.get(1).setGene(newgene2);
        }
//        System.out.println(geneAge);
//        System.out.println(geneAge.get(0).testGene());
//        System.out.println(geneAge.get(1).testGene());
        return geneAge;
    }
}
