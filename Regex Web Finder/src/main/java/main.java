import java.sql.SQLOutput;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String imageRegex = "<img [^>]*src=\"(((https)|(http))[^\"]*)\"";

        Scanner scannerInput = new Scanner(System.in);

        try {

            boolean end = false;
            System.out.println("Vítejte v regex automatu");



            while(!end) {


                String url;
                String source;
                String result = "";
                TagCleaner cleaner = new TagCleaner();

                System.out.println("Vyberte co chtete od tohoto automatu.");
                System.out.println("Pro vypsání zdrojů všech obrázků ze zadané adresy vyberte 1");
                System.out.println("Pro vyčištění tagů a jejich vypsání ze zadané adresy vyberte 2");
                System.out.println("Pro ukončení zvlote 3");
                int choice = scannerInput.nextInt();


                switch (choice) {
                    case 1:
                        System.out.println("Zadejte adresu webu, ze kterého chcete vypsat zdroje obrázků napříkald: www.raventia.cz");
                        url = "https://";
                        url += scannerInput.next();
                        if (url == "https://"){
                            url = "https://www.raventia.cz";
                        }

                        source = SocketConnection.getURLSource(url);
                        System.out.println("zdroje orázků jsou:");
                        System.out.println(ImageFinder.findImageSource(source, imageRegex).toString());
                        break;
                    case 2:
                        System.out.println("Zadejte adresu webu, ze kterého chcete vypsat zdroje obrázků napříkald: www.raventia.cz");
                        System.out.println("Pozor na jazyk webu, nejlépe Angličtina pro tento program");
                        url = "https://";
                        url += scannerInput.next();
                        if (url == "https://"){
                            url = "https://www.raventia.cz";
                        }
                        source = SocketConnection.getURLSource(url);
                        System.out.println("Zadejte tag, který chtece vyčistit, například img");
                        String tag = scannerInput.next();
                        System.out.println("Zadejte počet parametrů, které chcete zachovat:");
                        int numberOfParams = scannerInput.nextInt();
                        for (int i = 0; i<numberOfParams; i++ ){
                            System.out.println("Zadejte " + (i+1) + " parametr:");
                            String param = scannerInput.next();
                            cleaner.createRegex(tag,param);
                            result += "\n" + " Parametr " + param + "\n";
                            result += cleaner.findAndClean(source);

                        }
                        System.out.println("Vaše vyčištěné tagy jsou");
                        System.out.println(result);
                        break;
                    case 3:
                        end = true;
                }
            }
            System.out.println("Děkujeme, že používáte náš regex automat");
        }catch (Exception e){
            System.out.println("Nastala chyba!");
        }
    }
}
