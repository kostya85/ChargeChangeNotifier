package com.gorbunovgroup.chargechangenotifier;

class JThread extends Thread {


    JThread(String name){
        super(name);
    }

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());

        while (true) {
            try {
                if (!isInterrupted()) {
                    //MainActivity.Info();


                    MainActivity.Notify();
                    Thread.sleep(100);

                }
                else {throw new InterruptedException();}

            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
                break;
            }

        }

    }




}
