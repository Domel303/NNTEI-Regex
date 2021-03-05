import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageFinder {
    static String findImageSource(String text, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        String sources = "";
        while (m.find()){
            sources += m.group(1);
            sources+= ",\n";
        }
        return sources;
    }
}
