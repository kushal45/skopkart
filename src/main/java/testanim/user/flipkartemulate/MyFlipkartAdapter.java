package testanim.user.flipkartemulate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 26-Dec-15.
 */
public class MyFlipkartAdapter extends RecyclerView.Adapter<myViewHOlder>   {
    Context c;
    myViewHOlder hOlder;
    List<Flipkart> mycurrentList;

    Main2Activity flipkartActivity;
    public MyFlipkartAdapter(Context context,List<Flipkart> flipkartArrayList) {
        c=context;

        flipkartActivity= (Main2Activity) context;
        mycurrentList=flipkartArrayList;
    }


    @Override
    public myViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);



        View view=inflater.inflate(R.layout.grid_row,parent , false);

        hOlder=new myViewHOlder(view,c);

        return hOlder;
    }

    @Override
    public void onBindViewHolder(myViewHOlder holder, int position) {
//String uriphoto =Flipkart.getPhotouri();

       Flipkart item = mycurrentList.get(position);

//
//		int drawableResourceId = mContext.getResources().getIdentifier(item.getImageUri(), "drawable", mContext.getPackageName());
     //   Picasso.with(c).load(item.getResId()).into(holder.photo);
 //       int uriphoto =Flipkart.getPhotouri();
       // imagLoader=flipkartActivity.getmImageLoader();
  // imagLoader.loadImagePhoto(uriphoto, holder.photo);
   //     BitmapWorkerTask workerTask=new BitmapWorkerTask(holder.photo);
      //  workerTask.execute(uriphoto);
       // holder.nameOfProduct.setText(Flipkart.getNameofPhoto());
       // holder.Status.setText(Flipkart.getStatus());
      int resourceid= item.getResId();
        BitmapWorkerTask workerTask=new BitmapWorkerTask(holder.photo,c);
         workerTask.execute(resourceid);

        holder.nameOfProduct.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return mycurrentList.size();
    }


public  interface  itemclickListener
{
public  void itemclick(View v,int position);
}


}




class  myViewHOlder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView photo;
    TextView nameOfProduct;
    View itemViewOfColoumn=null;
    Context mContext;
 //   TextView Status;
    public myViewHOlder(View itemView,Context c) {
        super(itemView);
        itemViewOfColoumn=itemView;
photo= (ImageView) itemView.findViewById(R.id.image);
        nameOfProduct= (TextView) itemView.findViewById(R.id.title);
        itemView.setOnClickListener(this);
     //  Status= (TextView) itemView.findViewById(R.id.status);
        mContext=c;
    }

    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext,DetailActivity.class));
    }
}

