package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public int read(byte b[]) throws IOException{
        int index=0,row,col;
        int cat = in.read();
        while (index<24){
            b[index] = (byte)cat;
            index++;
            cat = in.read();
        }
        row = byteToInt(b[0],b[1],b[2],b[3]);
        col = byteToInt(b[4],b[5],b[6],b[7]);

        int mod = (col*row)%8;

        while(cat!=-1){
            if(index<24){
                b[index] = (byte)cat;
                index++;
            }
            else{
                Stack<Byte> tempStack;
                if(b.length-index==mod){
                    tempStack = numToBin(cat,1);
                }
                else{tempStack = numToBin(cat,0);}
                while (!tempStack.isEmpty()){
                    b[index]=tempStack.pop();
                    index++;
                }
            }
            cat=in.read();
        }
        return index;
    }

    
    private Stack<Byte> numToBin(int num,int flag){
        int bit=0, count =0;
        Stack<Byte> to_ret = new Stack<>();
        while(num>=1){
            bit = num%2;
            to_ret.push((byte) bit);
            num =(int)num/2;
            count++;
        }
        while(count<8 && flag!=1){
            to_ret.push((byte) 0);
            count++;
        }
        return to_ret;
    }

    private int byteToInt(byte a,byte b,byte c,byte d){
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
