package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import server.MultiplicationService;
import org.apache.thrift.async.AsyncMethodCallback;


public class MultiplicationClient {
  public static void main(String [] args) {


    try {
      TTransport transport;

      transport = new TSocket("tbinary+localhost/hello", 9090);
      transport.open();

      TProtocol protocol = new  TBinaryProtocol(transport);
      MultiplicationService.Client client = new MultiplicationService.Client(protocol);



      perform(client);

      transport.close();
    } catch (TException x) {
      x.printStackTrace();
    }
  }

  private static void perform(MultiplicationService.Client client) throws TException
  {
   
    int product = client.multiply(10,5);
    System.out.println("momo3*5=" + product);
  }
}
