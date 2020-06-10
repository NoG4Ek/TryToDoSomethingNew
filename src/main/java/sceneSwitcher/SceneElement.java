package sceneSwitcher;

public class SceneElement {
    private String sceneName;
    private String fileName;
    private int height;
    private int width;

    public SceneElement(String sceneName, String fileName, int height, int width) {
        this.sceneName = sceneName;
        this.fileName = fileName;
        this.height = height;
        this.width = width;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
