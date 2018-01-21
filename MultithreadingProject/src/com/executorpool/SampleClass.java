package com.executorpool;

public class SampleClass extends Thread
{
static String message[] =
{ "Java", "is", "hot,", "aromatic,", "and", "invigorating."};
 
    public SampleClass(String id)
    {
        super(id);
    }
 
    public void run()
    {
        SynchronizedOutput.displayList(getName(),message);
    }
 
    void randomWait()
    {
        try {
           sleep((long)(3000*Math.random()));
        } catch (InterruptedException x) {
           System.out.println("Interrupted!");
        }
    }
}
