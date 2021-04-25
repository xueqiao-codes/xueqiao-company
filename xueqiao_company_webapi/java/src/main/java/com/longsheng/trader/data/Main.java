package com.longsheng.trader.data;

public class Main {

    public static void main(String[] args) {

        String ip ="127.0.0.1";
        for (int i=0;i< 10;i++) {
         boolean isValid=   IpSendCodeMap.getInstance().validIP(ip);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i isValid: "+ isValid);
        }
    }


}
