import java.util.regex.*;

public class VideoLoader {
    
    public String path = "Data/";
    public File directorie;
    public List<Texture> frames = new LinkedList<Texture>();
    public List<String> frameNames = new ArrayList<String>();
    public int block = 0;
    public String[] allFiles;
    public int videoTotalSize = 0;
    public int sizeOfBlock = 60; // N frames per block
    public boolean inAsyncLoad = false;
    public int stack = 0;
    
    public void load() {
        directorie = new File(Directories.internal() + path);
        
        if(!directorie.isDirectory())
            return;
        
        String[] all = directorie.list(new FilenameFilter() {
            public boolean accept(java.io.File dir, String name) {
                return name.endsWith(".jpeg");
            }
        });
        
        allFiles = sort(all);
        
        loadNextBlock();
    }
    
    public void asyncLoad() {
        if(!inAsyncLoad)
            return;
            
        if(stack >= sizeOfBlock) {
            removePreviousBlock();
            inAsyncLoad = false;
            stack = 0;
            block++;
            return;
        }
            
        int index = (sizeOfBlock * block) + stack;
        
        if(index >= getTotalVideoSize()) {
            inAsyncLoad = false;
            return;
        }
        
        String fileName = allFiles[index];
        
        frames.add(Texture.loadFile(new File(directorie.getAbsolutePath() + "/"  + fileName)));
        
        /**
         * DEBUG
         *
         * frameNames.add(fileName);
         */
         
        stack++;
    }
    
    public void removePreviousBlock() {
        for(int i = 0; i < sizeOfBlock; i++) {
            int index = (sizeOfBlock * (block - 1)) + i;
            
            frames.set(index, null);
        }
    }
    
    public void loadNextBlock() {
        for(int i = 0; i < sizeOfBlock; i++) {
            int index = (sizeOfBlock * block) + i;

            if(index >= getTotalVideoSize())
                break;
            
            String file = allFiles[index];
            
            frames.add(Texture.loadFile(new File(directorie.getAbsolutePath() + "/" + file)));
            
            /**
             * DEBUG
             * 
             * frameNames.add(file);
             */
        }
        
        block++;
    }
    
    public int getTotalVideoSize() {
        if(videoTotalSize != 0)
            return videoTotalSize;
        
        videoTotalSize = allFiles.length;
        
        return videoTotalSize;
    }
    
    private int getFrameIndex(String frameName) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(frameName);

        while (matcher.find()) {
            String number = matcher.group();
            return Integer.parseInt(number);
        }
        
        return 0;
    }
    
    private String[] sort(String[] all) {
        String[] fileNames = new String[all.length];
        
        for(String file: all) {
            fileNames[getFrameIndex(file) - 1] = file;
        }
        
        return fileNames;
    }
}
