package mockitTest;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;


import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpSocketFactorySupport;
import org.springframework.integration.ip.tcp.connection.TcpSocketSupport;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

public class MockitT {

	@Test
	public void testNetClient() throws Exception {
	    TcpSocketFactorySupport factorySupport = mock(TcpSocketFactorySupport.class);
	    SocketFactory factory = Mockito.mock(SocketFactory.class);
	    when(factorySupport.getSocketFactory()).thenReturn(factory);
	    Socket socket = mock(Socket.class);
	    InputStream is = mock(InputStream.class);
	    when(is.read()).thenReturn(-1);
	    when(socket.getInputStream()).thenReturn(is);
	    InetAddress inetAddress = InetAddress.getLocalHost();
	    when(socket.getInetAddress()).thenReturn(inetAddress);
	    when(factory.createSocket("x", 0)).thenReturn(socket);
	    TcpSocketSupport socketSupport = Mockito.mock(TcpSocketSupport.class);
	 
	    TcpNetClientConnectionFactory connectionFactory = new TcpNetClientConnectionFactory("x", 0);
	    connectionFactory.setTcpSocketFactorySupport(factorySupport);
	    connectionFactory.setTcpSocketSupport(socketSupport);
	    connectionFactory.start();
	    connectionFactory.getConnection();
	 
	    verify(socketSupport).postProcessSocket(socket);
	    connectionFactory.stop();
	}


}
