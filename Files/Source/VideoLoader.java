import java.util.regex.*;

public class VideoLoader {
    
    public String path = "Data/";
    public File directorie;
    public List<Texture> frames = new ArrayList<Texture>();
    public List<String> frameNames = new ArrayList<String>();
    public int block = 0;
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
        
        loadNextBlock();
    }
    
    public void loadNextBlock() {
        for(int i = 0; i < sizeOfBlock; i++) {
            int index = (sizeOfBlock * block) + i;

            if(index >= getTotalVideoSize())
                break;
            
            String file = allFiles[index];
            Console.log(getFrameIndex(file) + " - " + file + " (" + i + ")");
            
            frames.add(Texture.loadFile(new File(directorie.getAbsolutePath() + "/" + file)));
            frameNames.add(file);
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
}
