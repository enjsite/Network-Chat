package swing;

import server.ClientHandler;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Network implements Closeable {
    private static final String AUTH_PATTERN = "/auth %s %s";
    private static final String MESSAGE_SEND_PATTERN = "/w %s %s";
    private static final Pattern MESSAGE_PATTERN = Pattern.compile("^/w (\\w+) (.+)", Pattern.MULTILINE);

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private final MessageSender messageSender;
    private final Thread receiver;

    private String username;
    private final String hostName;
    private final int port;
    private HistoryStore historyStore;

    public Network(String hostName, int port, MessageSender messageSender) {
        this.hostName = hostName;
        this.port = port;
        this.messageSender = messageSender;

        this.receiver = createReceiverThread();
    }

    private Thread createReceiverThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        String text = in.readUTF();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("New message " + text);
                                Matcher matcher = MESSAGE_PATTERN.matcher(text);
                                if (matcher.matches()) {
                                    Message msg = new Message(matcher.group(1), username,
                                            matcher.group(2));
                                    historyStore.saveRecord(msg);
                                    messageSender.submitMessage(msg);

                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public List<Message> getHistory() {
        return historyStore.getLastMessages();
    }

    public void sendMessageToUser(Message message) {
        sendMessage(String.format(MESSAGE_SEND_PATTERN, message.getUserTo(), message.getText()));
        historyStore.saveRecord(message);
        //messageSender.submitMessage(message);
    }

    private void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authorize(String username, String password) throws IOException {
        socket = new Socket(hostName, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        out.writeUTF(String.format(AUTH_PATTERN, username, password));
        String response = in.readUTF();
        if (response.equals("/auth successful")) {
            this.username = username;
            historyStore = new HistoryStore(username);
            receiver.start();
        } else {
            throw new AuthException();
        }
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void close() throws IOException {
        socket.close();
        receiver.interrupt();
        try {
            receiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
