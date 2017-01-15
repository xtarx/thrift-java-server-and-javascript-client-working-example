package server;
import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.http.cors.CorsServiceBuilder;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.thrift.THttpService;
import com.linecorp.armeria.common.SerializationFormat;
import de.tum.in.i22.uc.Controller;
import de.tum.in.i22.uc.pmp.PmpHandler;
import de.tum.in.i22.uc.thrift.server.IThriftServer;
import de.tum.in.i22.uc.thrift.server.TAny2PmpThriftServer;
import de.tum.in.i22.uc.thrift.server.TAny2PrpThriftServer;
import de.tum.in.i22.uc.thrift.server.ThriftServerFactory;
import io.netty.handler.codec.http.HttpMethod;


public class MultiplicationServer {

  public static MultiplicationHandler handler;
  public static TAny2PmpThriftServer pmpHandler;
//  public static ThriftServerFactory. pmpHandler.;

  public static MultiplicationService.Processor processor;

  public static void main(String [] args) {
    System.out.println("ahahha the simple server...");

//    pmpHandler=ThriftServerFactory.createPmpThriftServer(2020, new Controller());

    pmpHandler= new TAny2PmpThriftServer(new PmpHandler());
      handler = new MultiplicationHandler();


      ServerBuilder sb = new ServerBuilder();
      sb.port(9090, SessionProtocol.HTTP);
      sb.serviceAt(
              "/hello",
              THttpService.of(handler, SerializationFormat.THRIFT_JSON).decorate(CorsServiceBuilder.forOrigin("*")
                      .allowRequestMethods(com.linecorp.armeria.common.http.HttpMethod.POST)
                      .allowRequestHeaders("Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
                      .newDecorator())).build();

    sb.serviceAt(
            "/pmp",
            THttpService.of(pmpHandler, SerializationFormat.THRIFT_JSON).decorate(CorsServiceBuilder.forOrigin("*")
                    .allowRequestMethods(com.linecorp.armeria.common.http.HttpMethod.POST)
                    .allowRequestHeaders("Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
                    .newDecorator())).build();

      Server server = sb.build();
      server.start();


  }

 
}
