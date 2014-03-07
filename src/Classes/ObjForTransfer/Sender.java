
package Classes.ObjForTransfer;


import java.io.*;
   import java.io.DataOutputStream;
   import java.io.IOException;
   import java.io.OutputStream;
   import java.net.*;

    public class Sender{

        private File file;
        private String destinationAddress;
        private byte [] fileBuffer;

        public Sender(File file,String destinationAddress) {

            this.file = file;
            this.destinationAddress = destinationAddress;
            this.fileBuffer = new byte[(int)this.file.length()];
        }

        public void send(){
            Socket socket = null;
            OutputStream out = null;
            boolean bool = true;

            while(bool){

            try{
                System.out.println("entered first try");
                socket = new Socket(destinationAddress,8081) ;
                System.out.println("created socket");

                out = socket.getOutputStream();
                System.out.println("created outputstream");

                FileInputStream fin = new FileInputStream("file.txt");
                BufferedInputStream bin = new BufferedInputStream(fin);

                System.out.println(" take file properties");



                try{
                    sendFileProperties(out);

                    //Writing the file bytes into our buffering array
                    bin.read(fileBuffer, 0, fileBuffer.length);
                    out.write(fileBuffer, 0, fileBuffer.length);

                    System.out.println("Sending .  .  . ");
                    out.flush();
                    socket.close();
                    System.out.println("File has been sent successfully");
                    bool = false;
                }
                catch(IOException e){
                    System.out.println("Program exited, Problem in sending the file");

                }

            }
            catch(IOException e)  {
                System.out.println("Cannot Establish Connection,Trying Again"+
                                   " press Ctrl+C to stop repeating ");
                bool = true;
            }
            }
        }

        private void sendFileProperties(OutputStream out){
            try{
            DataOutputStream dos = new DataOutputStream(out);

            dos.writeUTF(this.file.getName());
            dos.close();
            }catch(IOException e){
                System.out.println("Problem in Sending file properties");
            }


        }


    }