/**
 * Created with IntelliJ IDEA.
 * User: samweli
 * Date: 2/11/14
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */

import Classes.ObjForTransfer.*;

import java.util.*;
import java.io.*;



public class Transfer {

    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        char j;
        String ip,filename;
        File file;

        welcomeMessage();



        do{
            System.out.println("Enter S or s to enter Sending mode"+
                              "Enter R or r to enter Receiving mode");

            j = scanner.next().charAt(0);
            switch(j){
                case 'S':
                case 's':
                    System.out.println("Enter the IP address of "+
                                       "the destination Computer ");
                    ip = scanner.nextLine();

                    scanner.nextLine();// removing remained data

                    System.out.println("Enter the name of the file"+
                                       " (maximum size is 3MB)");
                    filename = scanner.nextLine();

                    file = new File(filename);


                    Sender senderObj = new Sender(file,ip);
                    senderObj.send();

                    break;
                case 'R':
                case 'r':
                    Receiver receiver = new Receiver();
                    receiver.receive();
                    break;
                default:
                    System.out.println("Wrong choice, Try Again");
                    break;
            }
        }    while((j!='S' & j!='s') & (j!='R' & j!='r'));





    }

    public static void welcomeMessage(){

        for(int i= 0; i < 5; i++){
            System.out.print("\t\t");
            for(int j = 0; j < 34; j++){
                if(i==0 || i== 4){
                    System.out.print("-");
                } else if (i == 2){
                    System.out.print(" WELCOME TO FILE TRANSFER PROGRAM");
                    break;
                } else {
                    System.out.print(" ");
                }
            } System.out.println();

        }
        System.out.println("\n");
    }

}
