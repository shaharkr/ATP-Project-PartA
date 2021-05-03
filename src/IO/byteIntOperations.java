package IO;

public class byteIntOperations {
    /**
     * convert byte number to int number. maximum int size is 1020.
     * @param a first byte
     * @param b second byte
     * @param c third byte
     * @param d fourth byte
     * @return integer represented by the four bytes.
     */
    public static int byteToInt(byte a,byte b,byte c,byte d){
        int ai,bi,ci,di;
        if((int)a<0){ai = 256+(int)a; }
        else{ai=(int)a;}
        if((int)b<0){bi = 256+(int)b; }
        else{bi=(int)b;}
        if((int)c<0){ci = 256+(int)c; }
        else{ci=(int)c;}
        if((int)d<0){di = 256+(int)d; }
        else{di=(int)d;}
        return ai+bi+ci+di;
    }
}
