package timetable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

public class Gene implements  Comparable<Gene>{
    private StringBuilder gene;
    private int goodness=-1;
    ArrayList<Integer> arrayT;
    ArrayList<Integer> arrayP;
    char[][] professorTable = new char[6][9];
    char[][] stodentTable = new char[6][9];
    int[][] timeTable = new int[6][9];

    public Gene(){
        arrayT = new ArrayList<Integer>();
        arrayP = new ArrayList<Integer>();
    }
    public void makeGene(){
        gene = new StringBuilder();
        for(int i=0;i<192;i++){
            if(Math.random()>0.5) gene.append('0');
            else gene.append('1');
        }
    }

/*    public int testGene() {
        return testGene(gene.toString());
    }*/

    public int testGene() {
        arrayT.clear();
        arrayP.clear();
        goodness = 10;
        professorTable = new char[6][9];
        stodentTable = new char[6][9];
        boolean division = true;
        BigInteger biT = new BigInteger("1");
        for(int i=0;i<139;i++){
            StringBuilder mul = new StringBuilder().append(2);
            BigInteger biMul = new BigInteger(mul.toString());
            biMul=biMul.pow(i);
            if(gene.toString().charAt(i)=='1')  biT= biT.add(biMul);
        }
        biT=biT.mod(new BigInteger("371993326789901217467999448150835200000000"));
        int t=0;
        for(int i=36;i>=1;i--){
            StringBuilder index = new StringBuilder().append((i));
            t+=biT.mod(new BigInteger(index.toString())).intValue();
            t%=i;
            while(t>=36||arrayT.contains(t)){
                t++;
                if(t==36) t=0;
            }
//            System.out.println(biT+","+t);
            arrayT.add(t);
            biT=biT.divide(new BigInteger(index.toString()));
        }
//        System.out.println(arrayT);
        for (int i : arrayT) {
            int x = (arrayT.indexOf(i)) / 6;
            int y = (arrayT.indexOf(i)) % 6;
            timeTable[y][x]=i;
            switch (i) {
                case 0:
                case 1:
                    professorTable[y][x] = 'a';
                    break;
                case 2:
                case 3:
                    stodentTable[y][x]='4';
                case 4:
                    professorTable[y][x] = 'b';
                    break;
                case 5:
                case 6:
                    stodentTable[y][x]='2';
                case 7:
                    professorTable[y][x] = 'c';
                    break;
                case 8:
                    professorTable[y][x] = 'd';
                    stodentTable[y][x]='4';
                    break;
                case 9:
                    professorTable[y][x] = 'e';
                    break;
                case 10:
                    stodentTable[y][x]='3';
                case 11:
                    professorTable[y][x] = 'f';
                    break;
                case 12:
                    stodentTable[y][x]='3';
                    professorTable[y][x] = 'g';
                    break;
                case 13:
                    stodentTable[y][x]='4';
                    professorTable[y][x] = 'g';
                    break;
                case 14:
                    professorTable[y][x] = 'h';
                    stodentTable[y][x]='4';
                    break;
                case 15:
                    professorTable[y][x] = 'i';
                    break;
                case 16:
                    professorTable[y][x] = 'j';
                    break;
                case 17:
                case 18:
                case 19:
                    professorTable[y][x] = 'l';
                    break;
                case 20:
                    professorTable[y][x] = 'm';
                    break;
                case 21:
                    professorTable[y][x] = 'n';
                    stodentTable[y][x]='3';
                    break;
            }
        }
        long longP = (Long.parseLong(gene.subSequence(139,192).toString(),2)%6402373705728000l);
//        System.out.println(longP);
        t=0;
        for(int i=18;i>=1;i--){
            t += ((int) ((longP%i)+1));
            t%=i;
            while(t>=18||arrayP.contains(t)){
                t++;
                t%=18;
            }
//            System.out.println(longP+","+t);
            arrayP.add(t);
            longP/=i;
        }
//        System.out.println();
//        System.out.println(arrayP);
        for (int i : arrayP) {
            int x = (arrayP.indexOf(i)) / 6;
            int y = (arrayP.indexOf(i)) % 6;
            timeTable[y][x+6]=i;
            switch (i) {
                case 0:
                case 1:
                    professorTable[y][x + 6] = 'a';
                    stodentTable[y][x+6] = '2';
                    break;
                case 2:
                    professorTable[y][x + 6] = 'c';
                    break;
                case 3:
                case 4:
                    professorTable[y][x + 6] = 'd';
                    break;
                case 5:
                    professorTable[y][x + 6] = 'e';
                    break;
                case 6:
                case 7:
                    professorTable[y][x + 6] = 'f';
                    stodentTable[y][x+6] = '2';
                    break;
                case 8:
                case 9:
                    professorTable[y][x + 6] = 'h';
                    break;
                case 10:
                case 11:
                    stodentTable[y][x+6]='3';
                case 12:
                    professorTable[y][x + 6] = 'i';
                    break;
                case 13:
                case 14:
                    professorTable[y][x + 6] = 'j';
                    break;
                case 15:
                case 16:
                    professorTable[y][x + 6] = 'n';
                    stodentTable[y][x+6]= '2';
                    break;
                case 17:
                    professorTable[y][x + 6] = 'o';
                    break;
            }
        }
/*     */
        for (int i = 0; i < 6; i++) {
            ArrayList<Character> professorArray = new ArrayList<Character>();
            ArrayList<Character> studentArray = new ArrayList<Character>();
            for (int j = 0; j < 9; j++) {
                if(professorTable[i][j]!=0) {
                    professorArray.add(professorTable[i][j]);
                }
                if(stodentTable[i][j]!=0) {
                    studentArray.add(stodentTable[i][j]);
                }
            }
            HashSet<Character> professorHs = new HashSet<>();
            HashSet<Character> studentHs = new HashSet<>();
            professorHs.addAll(professorArray);
            studentHs.addAll(studentArray);
            if(professorArray.size()!=professorHs.size()){
                goodness=0;
                return 0;
            }
            if(studentArray.size()!=studentHs.size()){
                goodness-=(studentArray.size()-studentHs.size())*1;
            }
        }
        boolean space = false;
        for(int i=0;i<9;i++){
            if(professorTable[1][i]==professorTable[2][i]&&professorTable[1][i]!=0){
                goodness+=1;
            }
            if(professorTable[4][i]==professorTable[5][i]&&professorTable[4][i]!=0){
                goodness+=1;
            }
            if(stodentTable[1][i]==stodentTable[2][i]&&stodentTable[1][i]!=0){
                goodness+=1;
            }
            if(stodentTable[4][i]==stodentTable[5][i]&&stodentTable[4][i]!=0){
                goodness+=1;
            }
            if(professorTable[0][i]==professorTable[1][i]&&professorTable[1][i]==professorTable[2][i]&&professorTable[2][i]==0){
                goodness-=1;
            }
            if(professorTable[3][i]==professorTable[4][i]&&professorTable[4][i]==professorTable[5][i]&&professorTable[5][i]==0){
                goodness-=1;
            }
            if(i<6) {
                int classNum = 0;
                for (int j = 0; j < 6; j++) {
                    if (professorTable[j][i] != 0) classNum++;
                }
                if (classNum != 3 && classNum != 4) division = false;
                if(classNum==0) space=true;
            }
        }
        if(division) goodness+=10;
        if(!space) goodness+=5;
        if(goodness<0) goodness=0;
        return goodness;
    }

    public String getGene() {
        return gene.toString();
    }

    public void setGene(StringBuilder gene) {
        this.gene = gene;
    }

    public int getGoodness(){
        if(goodness==-1){
            return testGene();
        }
        return goodness;
    }

    public void printTable(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(professorTable[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(stodentTable[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(timeTable[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public int compareTo(Gene o) {
        return getGoodness()-o.getGoodness();
    }

    public String toString(){
        return gene.toString();
    }
}
