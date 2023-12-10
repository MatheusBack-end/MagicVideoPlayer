private int actualFrameIndex = 0;
public boolean isPlaying = false;
private VideoLoader videoLoader = new VideoLoader();
public int fps = 24;
private double lastFrameTime = 0;

@AutoWired
private SUIImage canvas;

public void start() {
    videoLoader.load();
}

public void repeat() {
    if(!isPlaying)
         return;
    if(lastFrameTime == 0)
        lastFrameTime = java.lang.System.currentTimeMillis();

    if(actualFrameIndex < videoLoader.frames.size()) {
        if(getDeltaFrame() <= 1000 / fps)
            return;
                
        setFrame(videoLoader.frames.get(actualFrameIndex++));
        return;
    }
    
    isPlaying = !isPlaying;
    actualFrameIndex = 0;
    lastFrameTime = 0;
    canvas.setImage(new Texture(16, 16));
}

public void setFrame(Texture frame) {
    canvas.setImage(frame);
    lastFrameTime = java.lang.System.currentTimeMillis();
}

private double getDeltaFrame() {
    return java.lang.System.currentTimeMillis() - lastFrameTime;
}
