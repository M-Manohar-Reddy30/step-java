import java.util.*;

public class TextCompression {
    static char[] chars; 
    static int[] freq; 
    static String[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        count(text); codes();
        String comp = compress(text), decomp = decompress(comp);
        show(text, comp, decomp);
    }

    static void count(String t) {
        StringBuilder c = new StringBuilder(); int[] f = new int[t.length()];
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i); int k = c.indexOf(ch+"");
            if (k==-1){c.append(ch); f[c.length()-1]=1;}
            else f[k]++; 
        }
        chars = new char[c.length()]; freq = new int[c.length()];
        for(int i=0;i<c.length();i++){chars[i]=c.charAt(i); freq[i]=f[i];}
    }

    static void codes() {
        map = new String[chars.length][2];
        for(int i=0;i<chars.length;i++){map[i][0]=chars[i]+""; map[i][1]=i+"";}
    }

    static String compress(String t){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<t.length();i++){
            for(String[] m:map) if(m[0].equals(t.charAt(i)+"")) sb.append(m[1]);
        } return sb.toString();
    }

    static String decompress(String c){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<c.length();i++){int k=c.charAt(i)-'0'; sb.append(map[k][0]);}
        return sb.toString();
    }

    static void show(String o,String c,String d){
        System.out.println("Char Freq:");
        for(int i=0;i<chars.length;i++) System.out.println(chars[i]+" "+freq[i]);
        System.out.println("Original: "+o);
        System.out.println("Compressed: "+c);
        System.out.println("Decompressed: "+d);
    }
}
