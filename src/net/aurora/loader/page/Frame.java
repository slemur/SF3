package net.aurora.loader.page;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Rick van Biljouw
 *         Date: 19-11-11
 *         Time: 20:38
 */
public class Frame {
    private Server.Language language;
    private Server server;

    public Frame(Server.Language language) {
        this.language = language;
        this.load();
    }

    public void load() {
        try {
            URL url = new URL("http://www.runescape.com/g=runescape/" + language.toString() + "game.ws?j=1");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String HTML = "";
            while((HTML = reader.readLine()) != null) {
                if(HTML.contains("<iframe id=\"game\"")) {
                    String serverURL = HTML.substring(HTML.indexOf("src=\"") + 5, HTML.indexOf("\" f"));
                    this.server = new Server(serverURL, this.language);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Server getServer() {
        return this.server;
    }

}
