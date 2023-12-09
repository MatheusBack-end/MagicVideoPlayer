public class VideoLoader {
    
    public String path = "Data/";
    public List<Texture> frames = new ArrayList<Texture>();
    
    public void load() {
        File directorie = new File(Directories.getProjectFolder() + "Files/" + path);
        
        if(!directorie.isDirectory())
            return;
            
        String[] list = directorie.list();
        
        for(String file: list) {
            frames.add(Texture.loadFile(new File(directorie.getAbsolutePath() + "/" + file)));
        }
    }
}
