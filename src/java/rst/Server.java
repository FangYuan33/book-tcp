package rst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            // 获取连接
            Socket socket = serverSocket.accept();

            OutputStream outputStream = socket.getOutputStream();
            while (true) {
                // 读取数据
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = bufferedReader.readLine();

                System.out.println("Receive:" + line);
                outputStream.write("hello, this is server".getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
