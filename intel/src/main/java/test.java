import java.util.Arrays;

/**
 * Created by xtarx on 06/01/2017.
 */
public class test {

    public static void main( String[] args )
    {

String url="/asdas";
//        System.out.println("URI "+request.getRequestURI());
        String[] types = {".png",".jpg",".gif",".jpeg"};

        String uri="/";
        if(!Arrays.asList(types).contains(uri.substring(uri.lastIndexOf('.') + 1))){
            System.out.println("A7A no match exit ");

        }

        String image = "/examples/servlets/images/return.gif";

        if (image.contains("."))
            System.out.println("bobo "+Arrays.asList(types).contains(
                    image.substring(image.lastIndexOf('.'), image.length())));


//        System.out.println( "Hello World!" );
    }

}
