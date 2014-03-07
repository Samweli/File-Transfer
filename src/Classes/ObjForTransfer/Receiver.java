
 package Classes.ObjForTransfer;

 import java.io.*;
 import java.net.*;
 import java.util.*;

 public class Receiver{

     public Receiver(){

     }

     public void receive(){
         ServerSocket serverSocket = null;
         Socket socket = null;
         InputStream in= null;
         Scanner scanner = new Scanner(System.in);

         FileOutputStream fout;
         BufferedOutputStream bo;
         String fileName;
         byte [] incomingFileBuffer;
         final int maximumFileSize = 3 * 1024;   //maximum incoming file size is 3 Megabyte

         int writtenBytes;


         try{
             serverSocket = new ServerSocket(8081);
             socket = serverSocket.accept();

             System.out.println("There is an incoming file from " + socket.getInetAddress() +
                                 "to receive press Y or y if no press N or n");

              char answer = scanner.next().charAt(0);
             do{
                 switch(answer){
                 case 'Y':
                 case 'y':
                     System.out.println("Downloading the file .  .  .");

                     try{
                         in = socket.getInputStream();
                         incomingFileBuffer = new byte[maximumFileSize];

                         //Creating file with the same properties
                          fileName  = findFilename(in);
                          fout = new FileOutputStream(fileName);
                          bo = new BufferedOutputStream(fout);


                         writtenBytes = in.read(incomingFileBuffer,0,in.available());

                         while((writtenBytes < in.available()) & (writtenBytes != -1)){
                             writtenBytes = writtenBytes + in.read(incomingFileBuffer,writtenBytes,(in.available() - writtenBytes));
                         }
                            bo.write(incomingFileBuffer,0,writtenBytes);
                            bo.close();
                            socket.close();
                     }
                     catch(IOException e){
                         System.out.println("Problem in downloading the file");
                     }
                     break;

                 case 'N':
                 case 'n':
                     System.out.println("Exiting the program");
                     break;
                 default:
                     System.out.println("Couldn't understand your input \n Try again");
                     break;

                 }
             }while((answer!='Y' & answer!='y') & (answer!='N' & answer!='y'));
         }  catch(IOException e){
             System.out.println("Couldn't enter receive mode, exiting program");
         }

     }

     private String findFilename(InputStream in){

         String fileName;
         try{
          DataInputStream din = new DataInputStream(in);
          String receivedFileProperties = din.readUTF();
         //Assigning the filename from the sent file properties
          fileName = receivedFileProperties.substring(1,receivedFileProperties.indexOf('\n'));
         }
         catch(IOException e){
             fileName= null;
             System.out.println("Problem in downloading the file properties");
         }

         return fileName;

     }

 }