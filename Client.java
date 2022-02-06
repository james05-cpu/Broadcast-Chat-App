            package CHAT;
            
            import java.io.*;
            import java.net.Socket;
            import java.util.*;
            
            public class Client {
                private Socket socket;
                private BufferedReader bufferedReader;
                private BufferedWriter bufferedWriter;
                private String userName;
            Calendar calendar;
            String ampm;
            Date date;
            static DataRepo dataRepo=new DataRepo();
                public Client(Socket socket,String clientName) {
                    try {
                        this.socket = socket;
                        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        this.userName = clientName;
                    } catch (Exception e) {
                        closeEveryThing(socket, bufferedReader, bufferedWriter);
            
                    }
                }
            
                public void sendMessage() {
                    try {
                        bufferedWriter.write(userName);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        Scanner scanner = new Scanner(System.in);
                        while (socket.isConnected()) {
                          //  date=new Date();
                            calendar=new GregorianCalendar();
                            int hour=calendar.get(Calendar.HOUR);
                            int min=calendar.get(Calendar.MINUTE);
                            if (calendar.get(Calendar.AM_PM)==0){
                                ampm=" AM";
                            }
                            else {
                                ampm=" PM";
                            }
                            String messageTosend = scanner.nextLine();
                            bufferedWriter.write(userName + " : " + messageTosend+" "+hour+":"+min+ampm);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                    } catch (Exception e) {
                        closeEveryThing(socket, bufferedReader, bufferedWriter);
            
                    }
                }
            
                public void listenForMessage() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String messageFromGrp;
                            while (socket.isConnected()) {
                                try {
                                    messageFromGrp = bufferedReader.readLine();
                                    System.out.println(messageFromGrp);
                                } catch (IOException e) {
                                    closeEveryThing(socket, bufferedReader, bufferedWriter);
            
                                }
                            }
                        }
                    }).start();
                }
            
                public void closeEveryThing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                        public static void main(String[] args){
                    Scanner scanner=new Scanner(System.in);
                    System.out.println("Enter group UserName");
                    String username=scanner.nextLine();
                    dataRepo.collectData(username);
                            Socket socket= null;
                            try {
                                socket = new Socket("localhost",99);
                            } catch (IOException e) {
                            }
                            Client client=new Client(socket,username);
                    client.listenForMessage();
                    client.sendMessage();
                }
            }
