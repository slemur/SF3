package net.aurora.loader;

import java.net.URL;

/**
 * @author Rick van Biljouw
 *         Date: 19-11-11
 *         Time: 19:34
 */
class AudioClip implements java.applet.AudioClip {

    public static final short STATE_STOPPED = 0;

    public static final short STATE_PLAYING = 1;

    public static final short STATE_LOOPING = 2;

    private URL sourceURL;

    private short audioClipState;

    public AudioClip(URL sourceURL) {
        this.sourceURL = sourceURL;
        audioClipState = STATE_STOPPED;
    }

    public short getAudioClipState() {
        return audioClipState;
    }

    public URL getURL() {
        return sourceURL;
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof AudioClip))
            return false;
        AudioClip ac = (AudioClip) obj;
        return ac.getAudioClipState() == audioClipState
                && ac.getURL().equals(sourceURL);
    }

    public void play() {
        audioClipState = STATE_PLAYING;
    }

    public void loop() {
        audioClipState = STATE_LOOPING;
    }

    public void stop() {
        audioClipState = STATE_STOPPED;
    }

}
