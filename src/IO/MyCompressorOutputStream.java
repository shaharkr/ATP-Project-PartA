package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;
import java.util.Stack;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        int index=24,count=0,to_add=0,flag=0;
        int row=byteToInt(b[0],b[1],b[2],b[3]),col=byteToInt(b[4],b[5],b[6],b[7]);
        int temp = (int)(row*col/8);
        double temp2 = (row*col/8.0);
        temp2=temp2-(double)temp;
        if(temp2>0){temp++;}
        byte[] to_ret = new byte[temp + 24];
        for (int i = 0; i < b.length; i++) {
            if(i<24){
                to_ret[i] = b[i];
                continue;
            }
            if(count<8){
                to_add+=Math.pow(2,7-count)*(int)b[i];
                flag=0;
            }
            else{
                to_ret[index]=(byte)to_add;
                index++;
                flag=1;
                count=0;
                to_add =(int)Math.pow(2,7-count)*(int)b[i];
            }
            count++;
        }
        to_ret[index-flag]=(byte)to_add;
        for (int j = 0; j < to_ret.length; j++) {
            this.write(to_ret[j]);
        }
    }

    /**
     * convert byte number to int number. maximum int size is 1020.
     * @param a first byte
     * @param b second byte
     * @param c third byte
     * @param d fourth byte
     * @return integer represented by the four bytes.
     */
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
