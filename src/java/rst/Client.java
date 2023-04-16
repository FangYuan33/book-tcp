package rst;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));

            OutputStream outputStream = socket.getOutputStream();

            System.out.println("start sleep, kill server!");

            TimeUnit.SECONDS.sleep(5);

            System.out.println("start first write");
            // 客户端不知道连接不在，触发RST，应用层是收不到的
            outputStream.write("hello".getBytes(StandardCharsets.UTF_8));

            TimeUnit.SECONDS.sleep(1);
            System.out.println("start second write");
            // 第二次write，触发Broken Pipe
//            outputStream.write("hi".getBytes(StandardCharsets.UTF_8));
            // 模拟Connection reset by peer
            socket.getInputStream().read();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
