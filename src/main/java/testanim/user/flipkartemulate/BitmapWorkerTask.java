package testanim.user.flipkartemulate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by USER on 29-Dec-15.
 */
public class BitmapWorkerTask extends AsyncTask<Integer,Void,Bitmap> {
    private Object data;
    private final WeakReference<ImageView> imageViewReference;
private  int TARGET_WIDTH=200;
    private  int TARGET_HEIGHT=200;
    Context c;
    public BitmapWorkerTask(ImageView imageView,Context context) {
        imageViewReference=new WeakReference<ImageView>(imageView);
        c=context;
    }
/*
    @Override
    protected Bitmap doInBackground(String... params) {


       return loadPhotoThumbnail(params[0]);

    }
*/
@Override
protected Bitmap doInBackground(Integer... params) {


    return loadPhotoThumbnail(params[0]);

}
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ImageView viewImage=imageViewReference.get();
        if(viewImage!=null)
        {
            viewImage.setImageBitmap(bitmap);
        }
    }
/*
    public  Bitmap loadPhotoThumbnail(String photoUrl) {

BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        options.inSampleSize=CalculareInsampleSize(options);
        InputStream inputStream=null;
        Bitmap bitmap = null;
        try {
            URL url=new URL(photoUrl);
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            inputStream=urlConnection.getInputStream();
            bitmap  = BitmapFactory.decodeStream(inputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {if(inputStream!=null)
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }
*/
public  Bitmap loadPhotoThumbnail(int photoUrl) {

    Bitmap bitmap = null;




        bitmap  = BitmapFactory.decodeResource(c.getResources(),photoUrl);




    return bitmap;
}
    public  int CalculareInsampleSize(BitmapFactory.Options options)
    {
        int scaleFactor;
        final  int photoWidth=options.outWidth;
        final  int photoHeight=options.outHeight;
        scaleFactor=1;
        int halfphotoWidth=photoWidth/2;
        int halfphotoHeight=photoHeight/2;
        while (halfphotoWidth/scaleFactor> TARGET_WIDTH||halfphotoHeight/scaleFactor>TARGET_HEIGHT)
        {
            scaleFactor*=2;
        }
        return  scaleFactor;


    }

}
