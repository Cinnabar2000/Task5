import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1:");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println("Task 2:");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println("Task 3:");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println("Task 4:");
        System.out.println(sumDigProd(new int[]{16,28}));
        System.out.println(sumDigProd(new int[]{0}));
        System.out.println(sumDigProd(new int[]{ 1, 2, 3, 4, 5, 6 }));
        System.out.println("Task 5:");
        System.out.println(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"}));
        System.out.println(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"}));
        System.out.println(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"}));
        System.out.println("Task 6:");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println("Task 7:");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println("Task 8:");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println("Task 9:");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println("Task 10:");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(127));
        System.out.println(hexLattice(21));

    }
    public static int[] encrypt(String s){
        int[] rez = new int[s.length()];
        rez[0] = (int)s.charAt(0);
        for (int i = 1; i< s.length(); i++){
            rez[i] = (int)s.charAt(i) - (int)s.charAt(i-1);
        }
        return rez;
    }

    public static String decrypt(int[] arr){
        String rez = "";
        rez += (char)arr[0];
        int buff = arr[0];
        for (int i = 1; i< arr.length; i++){
            buff += arr[i];
            rez += (char)buff;
        }
        return rez;
    }

    public static boolean canMove(String s, String position, String target){
        int positionRow = position.charAt(1)-48;
        int positionCol = position.charAt(0)-64;
        int targetRow = target.charAt(1)-48;
        int targetCol = target.charAt(0)-64;
        switch (s){
            case "Rook":
                if (((positionRow == targetRow)&&(positionCol != targetCol))||((positionRow != targetRow)&&(positionCol == targetCol))){
                    return true;
                }
                break;
            case "Bishop":
                if (Math.abs(positionRow-targetRow) == Math.abs(positionCol-targetCol)){
                    return true;
                }
                break;
            case "Queen":
                if ((Math.abs(positionRow-targetRow) == Math.abs(positionCol-targetCol))||(((positionRow == targetRow)&&(positionCol != targetCol))||((positionRow != targetRow)&&(positionCol == targetCol)))){
                    return true;
                }
                break;
            case "Pawn":
                if((positionCol == targetCol)&&(Math.abs(positionRow-targetRow) == 1)){
                    return true;
                }
                break;
            case "Knight":
                if(((Math.abs(positionRow-targetRow) == 2)&&(Math.abs(positionCol - targetCol) == 1))||(((Math.abs(positionRow-targetRow) == 1)&&(Math.abs(positionCol - targetCol) == 2)))){
                    return true;
            }
                break;
            case "King":
                if(((Math.abs(positionCol-targetCol) == 1)||(Math.abs(positionCol-targetCol) == 0) )&&((Math.abs(positionRow-targetRow) == 1)||(Math.abs(positionRow-targetRow) == 0))){
                    if(!((Math.abs(positionCol-targetCol) == 0) && (Math.abs(positionRow-targetRow) == 0))){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public static boolean canComplete(String s, String str){

        for (int i = 0; i< s.length(); i++){
            if (str.indexOf(s.charAt(i)) != -1){
                str = str.substring(str.indexOf(s.charAt(i))+1);
            }else{
                return false;
            }
        }
        return true;
    }

    public static int sumDigProd(int [] a){
        int buff=0;
        for (int i=0; i<a.length;i++){
            buff=buff+a[i];
        }
        int rez=1;
        if(buff==0){
            rez = 0;
        }
        while(buff>9){
            int len =(int) (Math.log10(buff) + 1);
            while (len>0){
                rez=rez*buff%10;
                len= len-1;
                buff=buff/10;
            }
            buff = rez;
        }
        return rez;

    }

    public static ArrayList<String> sameVowelGroup(String []x){
        ArrayList<String> result = new ArrayList<String>();
        boolean [] aeuio1 = new boolean[]{false, false, false, false, false};
        boolean [] aeuio2 = new boolean[]{false, false, false, false, false};
        String buff = x[0];
        result.add(buff);
        if(buff.indexOf("a")!=-1)
            aeuio1[0]=true;
        if(buff.indexOf("e")!=-1)
            aeuio1[1]=true;
        if(buff.indexOf("u")!=-1)
            aeuio1[2]=true;
        if(buff.indexOf("i")!=-1)
            aeuio1[3]=true;
        if(buff.indexOf("o")!=-1)
            aeuio1[4]=true;

        for (int k=1; k<x.length;k++){
            buff=x[k];
            if(buff.indexOf("a")!=-1)
                aeuio2[0]=true;
            if(buff.indexOf("e")!=-1)
                aeuio2[1]=true;
            if(buff.indexOf("u")!=-1)
                aeuio2[2]=true;
            if(buff.indexOf("i")!=-1)
                aeuio2[3]=true;
            if(buff.indexOf("o")!=-1)
                aeuio2[4]=true;

            if (aeuio1[0]==aeuio2[0] && aeuio1[1]==aeuio2[1] && aeuio1[2]==aeuio2[2] && aeuio1[3]==aeuio2[3] && aeuio1[4]==aeuio2[4]){
                result.add(buff);
            }
            for(int i = 0; i < aeuio2.length; i++){
                aeuio2[i] = false;
            }
        }
        return result;
    }

    public static boolean validateCard(Long x){
        long last=0;
        long x1=0;
        int len = (int) Math.ceil(Math.log10(x));
        String reversed = "";
        String str = "";
        long sum = 0;
        long reversedLong = 0;
        long [] mass = new long[len-1];
        int result;
        if (len>=14 || len<=19){
            last = x%10;
            x1=x/10;
            str = Long.toString(x1);
            for (int i=str.length()-1; i>=0;i--){
                reversed = reversed + str.charAt(i);
            }
            try {
                reversedLong = Long.parseLong(reversed);
            }
            catch (NumberFormatException e) {
                reversedLong = 0;
            }

            for (int j = mass.length - 1; j >= 0; j--){
                mass[j] = reversedLong % 10;
                reversedLong = reversedLong / 10;
            }
            for (int k = 0; k < mass.length; k++){
                if ( k % 2 == 0){
                    mass[k]=mass[k]*2;
                    if (mass[k]>9){
                        mass[k]= (mass[k]/10)+(mass[k]%10);
                    }
                }
            }
            for (int n=0; n< mass.length;n++){
                sum = sum + mass[n];
            }
            result = (int)(10 - sum%10);
            return (result == last);
        }
        return false;
    }

    public static String numToEng(int a){
        String rez ="";
        String [] numbers = new String[]{"one","two","three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        String [] numFrom11To19 = new String[]{"eleven", "twelve", "thirteen","fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String [] dozens = new String[]{"twenty", "thirty", "forty","fifty", "sixty", "seventy", "eighty", "ninety"};


        if (a==0)
            return "zero";

        if (a>0 && a<10)
            rez = numbers[a-1];

        if(a>10 && a<20)
            rez = numFrom11To19[a%10-1];

        if (a>19 && a<100){
            if(a%10==0){
                rez=dozens[a%10-2];
            }else{
                rez=dozens[a/10-2]+" "+numbers[a%10-1];
            }
        }
        if(a>99 && a<1000){
            if (a%100==0){
                rez = numbers[a/100-1]+ " "+ "hundred";
            }else if(a%100/10==0){
                rez = numbers[a/100-1]+ " "+ "hundred"+ " "+ numbers[a%10-1];
            }else {
                rez = numbers[a/100-1]+ " "+ "hundred"+ " "+ dozens[a%100/10-2]+" "+ numbers[a%10-1];
            }
        }
        return rez;
    }

    public static String getSha256Hash(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }



    public static String correctTitle(String a){
        String result = "";
        result = a.toLowerCase();
        String[] massStr = result.split(" ");


        String result2= "";

        for (String word : massStr){
            if ((word.equals("and")) || (word.equals("the")) || (word.equals("of")) || (word.equals("in"))){
                result2 = result2+ " " + word;
            }else{
                result2 = result2 + " " + word.substring(0,1).toUpperCase() + word.substring(1);
            }
        }
        return result2;
    }




    public static String hexLattice(int a){
        String rez = "";
        String x = "o ";
        int r = 1; // радиус
        int col;// количество точек
        int h; // высота
        int spaces; // количество пробелов перед строкой
        int buff = 0;
        int sum = 1;
        if (a==1){
            rez = rez + x + "\n";
            return rez;
        }

        while (sum < a){
            buff += 6;
            sum+=buff;
            r = r+1;
        }
        col = r;
        h = r*2 - 1;
        spaces = r - 1;
        if (sum == a){
        for (int i = 0; i < h; i ++){
            for (int j = 0; j < spaces; j++){
                rez += " ";
            }
            for (int j = 0; j < col; j++){
                rez += x;
            }
            if ( i + 1 < r){
                spaces --;
                col ++;
            }else{
                spaces ++;
                col --;
            }
            rez += "\n";

        }


        return rez;}else{
            return "Invalid";
        }
    }

}
