private int actualFrameIndex = 0;
public boolean isPlaying = false;
public VideoLoader videoLoader = new VideoLoader();

@AutoWired
private SUIImage canvas;

public void start() {
    videoLoader.load();
}

public void repeat() {
    if(!isPlaying)
         return;
         
    if(actualFrameIndex < videoLoader.frames.size()) {
        setFrame(videoLoader.frames.get(actualFrameIndex++));
        return;
    }
    
    isPlaying = !isPlaying;
    actualFrameIndex = 0;
    canvas.setImage(new Texture(16, 16));
}

public void setFrame(Texture frame) {
    canvas.setImage(frame);
}
