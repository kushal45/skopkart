package testanim.user.flipkartemulate;

/**
 * Created by USER on 27-Dec-15.
 */
public class Flipkart {


    String imageUri;
    String title;
    int resId;


    public Flipkart(String imageUri,String title,int res){

        setImageUri(imageUri);
        setTitle(title);
        setResId(res);
    }


    public int getResId() {
        return resId;
    }




    public void setResId(int resId) {
        this.resId = resId;
    }




    public String getImageUri() {
        return imageUri;
    }
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }




}