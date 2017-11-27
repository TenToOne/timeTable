package timetable;

import java.util.ArrayList;

public class Timetable {
    static final double pc=0.7;
    static final double pm = 0.0001;
    static final int N = 10;
    static final int length = 40;

    static ArrayList<String> setGene(ArrayList<String> genes){
        for(int i=0;i<N;i++){
            while(true) {
                StringBuilder gene = new StringBuilder();
                for (int j = 0; j < length; j++) {
                    switch (((int)Math.random()*6)){
                        case 0 : gene.append("0");
                        case 1 : gene.append("0");
                        case 2 : gene.append("0");
                        case 3 : gene.append("1");
                        case 4 : gene.append("1");
                        default : gene.append("1");
                    }
                }
                if(!genes.contains(gene.toString())) {
                    genes.add(gene.toString());
                    break;
                }
            }
        }
        return genes;
    }

    public static void main(String[] args) {
        ArrayList<String> genes = new ArrayList<String>();
        genes=setGene(genes);
        for(int age=0;age<1000;age++){
            System.out.println(genes);
            int[] goodness = new int[N];
            int goodsum=0;
            for(int i=0;i<N;i++){
                goodness[i]=goodnessTest(genes.get(i));
                goodsum+=goodness[i];
            }
            for(int i=0;i<N;i++){
                System.out.print(goodness[i]+" ");
            }
            System.out.println();
            ArrayList<String> newGenes = new ArrayList<String>();
            for(int i=0;i<N/2;i++) {
                ArrayList<String> geneAge = new ArrayList<String>();
                for (int j = 0; j < 2; j++) {
                    while (true) {
                        int s = (int) (Math.random() * goodsum);
                        System.out.print(s + "->");
                        int index = 0;
                        for (; index < N; index++) {
                            if ((s -= goodness[index]) < 0) break;
                        }
                        System.out.print(index + " ");
                        if(!geneAge.contains(genes.get(index).toString())) {
                            geneAge.add(genes.get(index));
                            break;
                        }
                    }
                }
                System.out.println(geneAge);
                if(Math.random()<pc) {
                    System.out.print("cross : ");
                    geneAge=crossover(geneAge);
                }
                for(int k=0;k<2;k++){
                    String newGene = modification(geneAge.get(0));
                    geneAge.remove(0);
                    geneAge.add(newGene);
                }
                System.out.println(geneAge);
                newGenes.add(geneAge.get(0));
                newGenes.add(geneAge.get(1));
                System.out.println();
            }
            genes=newGenes;
        }
        System.out.print(genes);
    }

    private static String modification(String s) {
        if(Math.random()<pm){
            StringBuilder newS = new StringBuilder();
            int m = (int) (Math.random()*s.length());
            for(int i=0;i<s.length();i++){
                if(i==m){
                    if(s.charAt(i)=='1') newS.append('0');
                    else newS.append('1');
                }
                else newS.append(s.charAt(i));
            }
            return newS.toString();
        }
        else return s;
    }

    private static ArrayList<String> crossover(ArrayList<String> geneAge) {
        StringBuilder newgene1 = new StringBuilder();
        StringBuilder newgene2 = new StringBuilder();
        ArrayList<String> newAge = new ArrayList<String>();
        for(int i=0;i<length;i++){
            if(Math.random()<0.5){
                newgene1.append(geneAge.get(1).charAt(i));
                newgene2.append(geneAge.get(0).charAt(i));
            }
            else{
                newgene1.append(geneAge.get(0).charAt(i));
                newgene2.append(geneAge.get(1).charAt(i));
            }
        }
        newAge.add(newgene1.toString());
        newAge.add(newgene2.toString());
        return newAge;

    }

    private static int goodnessTest(String s) {
        return ((int)(Math.random()*100));
    }

}
