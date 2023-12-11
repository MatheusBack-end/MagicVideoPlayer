public class VideoLoader {
    
    public String path = "Data/";
    public File directorie;
    public List<Texture> frames = new ArrayList<Texture>();
    public int block = 1;
    public String[] allFiles;
    public int videoTotalSize = 0;
    public int sizeOfBlock = 60; // N frames per block
    
    public void load() {
        directorie = new File(Directories.getProjectFolder() + "Files/" + path);
        
        if(!directorie.isDirectory())
            return;
            
        allFiles = filterFrames();
        block = 1;
        loadNextBlock();
    }
    
    public void loadNextBlock() {
        for(int i = 0; i < sizeOfBlock; i++) {
            String file = allFiles[i * block];
            Console.log(i * block + " - " + block);
            
            if(!file.endsWith(".jpeg"))
                continue;
                
            frames.add(Texture.loadFile(new File(directorie.getAbsolutePath() + "/" + file)));
        }
        
        block++;
    }
    
    public int getTotalVideoSize() {
        if(videoTotalSize != 0)
            return videoTotalSize;
        
        String[] all = directorie.list();
        
        for(String file: all) {
            if(!file.endsWith(".jpeg"))
                continue;
                
            videoTotalSize++;
        }
        
        return videoTotalSize;
    }
    
    public String[] filterFrames() {
        String[] all = directorie.list();
        String[] files = new String[getTotalVideoSize()];
        int offset = 0;
        
        for(int i = 0; i < all.length; i++) {
            String file = all[i];
            
            if(file.endsWith(".jpeg")) {
                files[offset] = file;
                offset++;
            }
        }
        
        return files;
    }
}
