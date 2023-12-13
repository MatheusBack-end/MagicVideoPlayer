private int actualFrameIndex = 0;
public boolean isPlaying = false;
public VideoLoader videoLoader = new VideoLoader();
public int fps = 24;
private double lastFrameTime = 0;
public float videoTime;
public SUIText debug;

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
        
    if(actualFrameIndex < videoLoader.getTotalVideoSize()) {
        if(getDeltaFrame() <= 1000 / fps)
            return;
        
        if(endThisBlock())
            videoLoader.loadNextBlock();
                
        videoTime = ((1000 / fps) * actualFrameIndex) / 1000; // in seconds
        debug.setText(videoLoader.frameNames.get(actualFrameIndex) + " - " + actualFrameIndex); // frame name
        setFrame(videoLoader.frames.get(actualFrameIndex++));
        
        return;
    }
    
    isPlaying = false;
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

private boolean endThisBlock() {
    return videoLoader.frames.size() <= actualFrameIndex;
}
