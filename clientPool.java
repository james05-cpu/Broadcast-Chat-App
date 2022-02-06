 package CHAT;
            import java.io.*;
            import java.net.Socket;
            import java.util.ArrayList;
            import java.util.Calendar;
            import java.util.Date;
            import java.util.GregorianCalendar;
            
            public class clientPool implements Runnable{
                public static ArrayList <clientPool>clientHandlers=new ArrayList<>();
                private Socket socket;
                private BufferedReader bufferedReader;
                private BufferedWriter bufferedWriter;
                private String clientName;
                Date date;
                Calendar calendar;
                String ampm;
                MESSAGES messages=new MESSAGES();
                public clientPool(Socket socket) {
            
                    try {
                        this.socket = socket;
                     this.bufferedWriter=new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
                    this. bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    this.clientName=bufferedReader.readLine();
                    clientHandlers.add(this);
                    broadCastMessage("SERVER "+clientName+" has entered "+showTime());
                    } catch (Exception e) {
                         closeEveryThing(socket,bufferedReader,bufferedWriter);
                    }
                }
            
                @Override
                public void run() {
                    String messageFromClient;
                    while (socket.isConnected()){
                        try {
                            messageFromClient=bufferedReader.readLine();
                            broadCastMessage(messageFromClient);
                        } catch (IOException e) {
                            closeEveryThing(socket,bufferedReader,bufferedWriter);
                              break;
                        }
                    }
                }
                public void broadCastMessage(String messageToSend){
            
                    for (clientPool clientHandler:clientHandlers){
                        try {
            
                            if (!clientHandler.clientName.equals(clientName));
                            clientHandler.bufferedWriter.write(messageToSend);
                            clientHandler.bufferedWriter.newLine();
                            clientHandler.bufferedWriter.flush();
                                messages.collectData(clientHandler.clientName,messageToSend);
                        } catch (Exception e) {
                            closeEveryThing(socket,bufferedReader,bufferedWriter);
                        }
                    }
                }
                public void removeClientHandler(){
                    RemoveUser removeUser=new RemoveUser();
                    clientHandlers.remove(this);
                    removeUser.pullOut(clientName);
                    broadCastMessage (clientName+" left chat"+" "+showTime());
                }
                public String showTime(){
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
                public  void closeEveryThing(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
                    removeClientHandler();
                    try{
                        if (bufferedReader!=null){
                            bufferedReader.close();
                        }
                        if (bufferedWriter!=null){
                            bufferedWriter.close();
                        }
                        if (socket!=null){
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
