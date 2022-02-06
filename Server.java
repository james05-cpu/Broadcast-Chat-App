            package CHAT;
            
            import java.io.BufferedReader;
            import java.io.IOException;
            import java.net.ServerSocket;
            import java.net.Socket;
            import java.util.Calendar;
            import java.util.Date;
            import java.util.GregorianCalendar;
            import java.util.Scanner;
            
            public class Server {
            
                ServerSocket serverSocket;
                Date date;
                static Calendar calendar;
                static String ampm;
               static Scanner scanner=null;
               private BufferedReader bufferedReader=null;
               DROPTABLE droptable=null;
            
                public Server(ServerSocket serverSocket) {
                    this.serverSocket = serverSocket;
            
                }
            
                public void startServer() {
                    droptable=new DROPTABLE();
                    try {
                        while (!serverSocket.isClosed()) {
                            Socket socket = serverSocket.accept();
                            System.out.println("new client joined "+showTime());
                            clientPool clientHandler = new clientPool(socket);
                            Thread thread = new Thread(clientHandler);
                            thread.start();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            
                public void closeServerSocket() {
                    try {
                        if (serverSocket != null) {
                            serverSocket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                public static String showTime(){
                    calendar=new GregorianCalendar();
                    int hour=calendar.get(Calendar.HOUR);
                    int min=calendar.get(Calendar.MINUTE);
                    if (calendar.get(Calendar.AM_PM)==0){
                        ampm=" AM";
                    }
                    else {
                        ampm=" PM";
                    }
                    return ""+hour+":"+min+ampm;
                }
            
               public static void main(String[] args)  {
                    makeTable makeTable=new makeTable();
                   ServerSocket serverSocket = null;
                   try {
                       serverSocket = new ServerSocket(99);
                   } catch (IOException e) {
                   }
                   scanner=new Scanner(System.in);
                   System.out.println("Enter Password");
                   String password= scanner.nextLine();
                   Server server=null;
                   if (password.equals("1234")) {
                       //makeTable.baseManage();
                       System.out.println("Server Started "+showTime());
                       server = new Server(serverSocket);
                       server.startServer();
                   }
                   else {
                       for (int i=0; i<2; i++) {
                           System.out.println("Wrong! try again");
                           password = scanner.nextLine();
                           if (password.equals("1234")){
                               makeTable.baseManage();
                               System.out.println("Server Started"+showTime());
                               server = new Server(serverSocket);
                               server.startServer();
                               break;
                           }
                       }
                       System.out.println("Trials Exhausted");
                   }
                 }
                }
