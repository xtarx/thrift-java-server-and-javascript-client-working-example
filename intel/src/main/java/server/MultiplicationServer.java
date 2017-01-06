package server;
import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.http.cors.CorsServiceBuilder;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.thrift.THttpService;
import com.linecorp.armeria.common.SerializationFormat;
import io.netty.handler.codec.http.HttpMethod;


public class MultiplicationServer {

  public static MultiplicationHandler handler;

  public static MultiplicationService.Processor processor;

  public static void main(String [] args) {
    System.out.println("ahahha the simple server...");

    
    //old example
//    try {
//        handler = new MultiplicationHandler();
//        processor = new MultiplicationService.Processor(handler);
//
//        Runnable simple = new Runnable() {
//          public void run() {
//            simple(processor);
//          }
//        };
//
//        new Thread(simple).start();
//      } catch (Exception x) {
//        x.printStackTrace();
//      }
//
//
    //old example
    
    //
    
//	  HelloService.AsyncIface helloHandler = new MyHelloService();


    

      handler = new MultiplicationHandler();
      ServerBuilder sb = new ServerBuilder();
      sb.port(9090, SessionProtocol.HTTP);
      sb.serviceAt(
              "/hello",
              THttpService.of(handler, SerializationFormat.THRIFT_JSON).decorate(CorsServiceBuilder.forOrigin("*")
                      .allowRequestMethods(com.linecorp.armeria.common.http.HttpMethod.POST)
                      .allowRequestHeaders("Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")

//                      .preflightResponseHeader("preflight", "Access-Control-All-Headers: Origin, X-Requested-With, Content-Type, Accept, Key\n")
                      .newDecorator())).build();

      Server server = sb.build();
      server.start();


  }

 
}
