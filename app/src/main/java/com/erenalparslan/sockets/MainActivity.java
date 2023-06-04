package com.erenalparslan.sockets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    TextView textView;
    TextView textView2;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }

    public void printClientLog(final String data){
        Log.d("MainActivity", data);

        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });
    }

    public void printServerLog(final String data){
        Log.d("MainActivity", data);

        handler.post(new Runnable() {
            @Override
            public void run() {
                textView2.append(data + "\n");
            }
        });
    }

    public void send(String data){
        try{
            int portNumber = 5001;
            Socket sock = new Socket("localhost", portNumber);
            //Socket nesnesi oluştur (istemci)

            printClientLog("soket bağlantı kutusu");

            ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
            // String nesnesini olduğu gibi kullanmak için + çıktı akışı nesnesini elde et
            outputStream.writeObject(data);
            //işlemi ve teslimatı yaz
            outputStream.flush();
            printClientLog("veri gönderildi");

            ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
            // string nesnesini olduğu gibi almak için
            printClientLog("sunucudan alınan: " + inputStream.readObject());

            sock.close(); // soketi kapat
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer(){
        try{
            int portNumber = 5001;

            ServerSocket server = new ServerSocket(portNumber);
            //soket sunucu nesnesi oluştur (sunucu)
            printServerLog("Sunucu başladı:" + portNumber);

            while(true){
                Socket sock = server.accept();
                // Soket nesnesini alın ve istemci soketinin bağlantı bilgilerini kontrol edin
                InetAddress clientHost = sock.getLocalAddress();
                //İstemci soketinin IP adresini al (InetAddress türü olarak)
                int clientPort = sock.getPort();
                //İstemci soketinin portunu al
                printServerLog("İstemci Bağlandı: " + clientHost + " : " + clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
                Object obj = inputStream.readObject();
                // (veri) nesnesini al
                printServerLog("Alınan veri: " + obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
                outputStream.writeObject(obj + "from Server");
                outputStream.flush();


                sock.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}