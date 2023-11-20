import java.util.ArrayList;
import java.util.List;

public class VideoList<T extends Video> {
    private List<T> videoList;
    // inisiasi array list
    public VideoList() {
        videoList = new ArrayList<>();
    }
    // method method yang diperlukan
    public void insertVideo(T newVideo, boolean isFront) {
        if (isFront) {
            videoList.add(0, newVideo);
        } else {
            videoList.add(newVideo);
        }
    }

    public List<T> getVideoList() {
        return videoList;
    }

    public T deleteVideo() {
        if (!videoList.isEmpty()) {
            return videoList.remove(0);
        }
        return null;
    }

    public T getFirst() {
        if (!videoList.isEmpty()) {
            return videoList.get(0);
        }
        return null;
    }
}
