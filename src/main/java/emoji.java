import java.util.ArrayList;

public class emoji {
    final String sun = "☀";
    final String cloud = "☁";
    final String cloudSun = "⛅";
    final String cloudRainLighningi = "⛈";
    final String cloudBigSun = "\uD83C\uDF24";
    final String cloudSmallSun = "\uD83C\uDF25";
    final String cloudSunRain = "\uD83C\uDF26";
    final String cloudRain = "\uD83C\uDF27";
    final String cloudSnow = "\uD83C\uDF28";
    final String cloudLightning = "\uD83C\uDF29";
    final String snow = "❄";
    final String fog = "\uD83C\uDF2B";
    final String night = "\uD83C\uDF19";
    String [] emoji = {"", "", "", ""};
    ArrayList<String> list = new ArrayList<>();

    public emoji(ArrayList<String> list){
        this.list = list;
    }

void check() {
    for (int i = 0; i < emoji.length; i++) {
        String s = list.get(i).toLowerCase();

        if (s.contains("дождь")) {
            emoji[i] = cloudRain;
//                System.out.println("1");
        }
        else if (s.contains("пасмурно")) {
            emoji[i] = cloud;
//                System.out.println("2");
        }
        else if (s.contains("малооблачно")) {
            emoji[i] = sun;
//                System.out.println("3");
        }
        else if (s.contains("ясно") && i>=2) {
            emoji[i] = night;
//                System.out.println("4");
        }
        else if (s.contains("ясно") && i<2) {
            emoji[i] = sun;
//                System.out.println("4.1");
        }
        else if (s.contains("облачно")) {
            emoji[i] = cloud;
//                System.out.println("5");
        }
        else if (s.contains("снег")) {
            emoji[i] = snow;
        }
    }
}

    String emojiCheck(int number) {
        return emoji[number];
    }

}
