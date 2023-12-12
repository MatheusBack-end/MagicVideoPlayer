public class VideoLoader {
    
    public String path = "Data/";
    public File directorie;
    public List<Texture> frames = new ArrayList<Texture>();
    public int block = 1;
    public String[] allFiles;
    public int videoTotalSize = 0;
    public int sizeOfBlock = 60; // N frames per block
    
    public void load() {
        directorie = new File(Directories.internal() + path);
        
        if(!directorie.isDirectory())
            return;
            
        allFiles = directorie.list(new FilenameFilter() {
            public boolean accept(java.io.File dir, String name) {
                return name.endsWith(".jpeg");
            }
        });
        
        block = 1; // declaration not found, @see header
        loadNextBlock();
    }
    
    public void loadNextBlock() {
        for(int i = 0; i < sizeOfBlock; i++) {
            String file = allFiles[(i * block) + sizeOfBlock];

            frames.add(Texture.loadFile(new File(directorie.getAbsolutePath() + "/" + file)));
        }
        
        block++;
    }
    
    public int getTotalVideoSize() {
        if(videoTotalSize != 0)
            return videoTotalSize;
        
        videoTotalSize = allFiles.length;
        
        return videoTotalSize;
    }
}
