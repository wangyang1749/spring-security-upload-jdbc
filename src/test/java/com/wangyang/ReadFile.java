package com.wangyang;

import java.io.*;

public class ReadFile {
    public static void main(String[] args) {
        test1();
    }
    public static String byte2HexStr(byte[] b) {
        String hs="";
        String stmp="";
        for (int n=0;n<b.length;n++) {
            stmp=(Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
        }
        return hs.toLowerCase();
    }

    public static void test2(){
        try {
            StringBuffer sb = new StringBuffer();
            FileInputStream fis = new FileInputStream("/home/wy/Pictures/th.jpeg");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream bos=
                    new java.io.ByteArrayOutputStream();
            byte[] buff=new byte[1024];
            int len=0;
            while((len=fis.read(buff))!=-1){
                bos.write(buff,0,len);
            }
            //得到图片的字节数组
            byte[] result=bos.toByteArray();
            System.out.println("++++"+byte2HexStr(result));
            //字节数组转成十六进制
            String str=byte2HexStr(result);
//            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void test1(){
        try {
            FileInputStream fileInputStream = new FileInputStream("/home/wy/Pictures/th.jpeg");
            byte[] b = new byte[1024];
            int end=0;
//            int read=fileInputStream.read();
//            int read2=fileInputStream.read();
            /**
             * 1个字节=8位二进制表示=两个十六进制的数值来表示
             * 4位二进制合为 1位十六进制
             * 11111111 二进制
             * ff   十六进制
             *
             */
//            System.out.println(read);
//            System.out.println(Integer.toHexString(read));
//            System.out.println(Integer.toBinaryString(read));
//            System.out.println(read2);
//            System.out.println(Integer.toHexString(read2));
//            System.out.println(Integer.toBinaryString(read2));
            /**
             * b[0]表示一个字节占8位，以补码的方式表示
             */
            while ((end=fileInputStream.read(b))!=-1){
                int result=toInt(b);
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int toInt(byte[] bytes){
        int number = 0;
        for(int i = 0; i < 4 ; i++){
            number += bytes[i] << i*8;
        }
        return number;
    }
}
