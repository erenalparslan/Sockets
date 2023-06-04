# What is Socket and What Does It Do?

Socket is a communication socket that enables data communication between computers. Socket programming is a communication protocol used to exchange data between a server and a client. The client sends a request to the server, and the server grants it.

Socket communicates over a network address defined by its IP address and port number. The client socket sends requests to connect to the server, while the server socket listens and accepts incoming requests. Data flow between client and server takes place over socket.

Sockets run on the TCP/IP protocol. TCP (Transmission Control Protocol) provides a reliable data flow and ensures that data is received in the correct order. IP (Internet Protocol) manages data transmission over the network.

# Usage areas
<ol>
<li/>Client-Server Communication: Sockets are used in applications based on the client-server model. Clients connect to the server and send requests, and the server fulfills these requests.

<li/>File Transfer: Sockets can be used to transfer files from one device to another. The client sends the file to the server, or the server sends the file to the client.

<li/>Internet Applications: Internet-based applications such as web browsers, e-mail clients, instant messaging applications exchange data over sockets.
</ol>
# How Sockets Work

<ol>
  <li/>On the server side, a server socket is created to listen on a specific port. The server socket accepts incoming connection requests and processes these requests separately.
  <li/>On the client side, a client socket is created to connect to the server. The client socket sends a connection request to the server, specifying the server's IP address and port.
  <li/>The server socket accepts incoming connection requests and creates a client socket for each connection. Data flows between the client socket and the server socket.
  <li/>The client socket uses an OutputStream to send data to the server, while the server socket uses an InputStream to receive incoming data.
  <li/>After the data exchange between the client and the server is completed, the socket connection is closed. Before the socket is closed, necessary actions are taken to free buffers and free up resources.
</ol>

![resim_2023-06-04_143833007-removebg-preview](https://github.com/erenalparslan/Sockets/assets/100201401/559f78c7-4246-43e1-9504-c14bcdf27ae6)


  









# What does it do ?

<ul>
<li/>System Notifications: They are used to respond to system notifications sent by the Android operating system. For example, monitoring low battery level warnings, network state changes, screen unlock notifications, and taking appropriate actions based on them.

<li/>Broadcasts: They capture custom broadcasts sent by other applications or the system. This allows responding to specific events sent by other applications. For instance, capturing events when the camera button is pressed, internet connectivity changes, or when a custom broadcast is sent, and performing specific tasks accordingly.

<li/>Scheduled Tasks: Broadcast Receivers can also be used for executing scheduled tasks. For example, displaying a notification at a specific time or performing a specific operation within a certain time frame.
  </ul>
