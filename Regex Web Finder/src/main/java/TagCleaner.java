import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagCleaner {
    private String tagRegex = "";
    public void createRegex(String tag, String param){
        String tagRegex = "(<"+tag+")[^>]*("+param+"=[^ ]*)";
        this.tagRegex = tagRegex;
    }

    public String findAndClean(String text ){
        Pattern p = Pattern.compile(tagRegex);
        Matcher m = p.matcher(text);
        String sources = "";
        while (m.find()){
            sources += m.group(1);
            sources+= " ";
            sources += m.group(2);
            sources += ",\n";
        }
        return sources;
    }

}
