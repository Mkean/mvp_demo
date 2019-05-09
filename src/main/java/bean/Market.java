package bean;

/**
 * 作者：王庆
 * 时间：2017/12/12
 */

public class Market {
    private int img;
    private String title;

    public Market(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
