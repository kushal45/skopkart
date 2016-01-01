package testanim.user.flipkartemulate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 29-Dec-15.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>  {
    private List<List<Flipkart>> mRows;
    Context mContext;
    String LOGTAG="MyFlipkartItemScroll";
    MyFlipkartAdapter myFlipkartAdapter;
    public MainRecyclerAdapter(Context context,List<List<Flipkart>> objects) {
        mRows=objects;
        mContext=context;
    }

    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)   {
      LayoutInflater inflater=LayoutInflater.from(mContext);
        View convertView= inflater.inflate(R.layout.row,parent,false);


        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(MainRecyclerAdapter.ViewHolder holder, int position) {
        List<Flipkart> RowItems = mRows.get(position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        holder.mRecyclerView.setLayoutManager(layoutManager);
        holder.mRecyclerView.setHasFixedSize(true);
        MyFlipkartAdapter rowsRecyclerAdapter = new MyFlipkartAdapter(mContext,RowItems);
        holder.mRecyclerView.setAdapter(rowsRecyclerAdapter);

        final RecyclerView finalRecyclerView = holder.mRecyclerView;
        finalRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("MyFlipkartItemScroll", "X = " + dx + " and Y = " + dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState)
                {
                    /*
                     I used OnScrollListener and when the states goes SCROLL_STATE_IDLE I check top
                    and bottom views to see which of them has more visible region then scroll to that position.
                    for each rows I do so for left and right views for each row adapter. In this way always
                    one side of your carousels or rows fit. for example if top is fitted the bottom is not or vise versa.
                    I think if you play a little more you can achieve that but you must know the dimension of window and
                     change the dimension of carousels at runtime.
                     */
                    case RecyclerView.SCROLL_STATE_IDLE:
                        float targetBottomPosition1=recyclerView.getX();
                        /**
                         * getX()----->
                         * The visual x position of this view, in pixels. This is equivalent to the
                         * {@link #setTranslationX(float) translationX} property plus the current
                         * {@link #getLeft() left} property.
                         *
                         * @return The visual x position of this view, in pixels.
                         * public float getX() {
                        return mLeft + getTranslationX();
                        }
                         */
                     float recyclerwidth=recyclerView.getWidth();
                        //return width of the recycycler view
                        float targetBottomPosition2=recyclerView.getX()+recyclerView.getWidth();
                        Log.i(LOGTAG,"targetBottomPosition1 = " + targetBottomPosition1);
                        Log.i(LOGTAG,"targetBottomPosition2 = " + targetBottomPosition2);
View v1=finalRecyclerView.findChildViewUnder(targetBottomPosition1,0);


                      int ChildINdex1=  finalRecyclerView.indexOfChild(v1);
            int postion1=            finalRecyclerView.getChildAdapterPosition(v1);
                        int postion11=          recyclerView.getChildAdapterPosition(v1);
                        View v2=recyclerView.findChildViewUnder(targetBottomPosition2, 0);
                        int ChildINdex2=  recyclerView.indexOfChild(v2);
                  int position2=      recyclerView.getChildAdapterPosition(v2);

                    int   position22= finalRecyclerView.getChildAdapterPosition(v2);

                        float x1 = targetBottomPosition1;
                        if(v1!=null){
                            x1 =v1.getX();
                        }

                        float x2 = targetBottomPosition2;
                        if(v2!=null){
                            x2 =v2.getX();
                        }

                        Log.i(LOGTAG,"x1 = " + x1);
                        Log.i(LOGTAG,"x2 = " + x2);
                        float dx1=Math.abs(recyclerView.getX()-x1);
                        float dx2=Math.abs(recyclerView.getX()+recyclerView.getWidth()-x2);
                        Log.i(LOGTAG,"dx1 = " + dx1);
                        Log.i(LOGTAG,"dx2 = " + dx2);
                        float visiblePortionOfItem1 = 0;
                        float visiblePortionOfItem2 = 0;
if(x1<0&&v1!=null)

{
    int v1width=v1.getWidth();
    visiblePortionOfItem1=v1.getWidth()-dx1;

}
                        if(v2!=null)
                        {
                            int v2width=v2.getWidth();
                            visiblePortionOfItem2=v2.getWidth()-dx2;
                        }
                        Log.i(LOGTAG,"visiblePortionOfItem1 = " + visiblePortionOfItem1);
                        Log.i(LOGTAG,"visiblePortionOfItem2 = " + visiblePortionOfItem2);
                        int position = 0;
                        if(visiblePortionOfItem1>=visiblePortionOfItem2)
                        {
position=recyclerView.getChildPosition(recyclerView.findChildViewUnder(targetBottomPosition1,0));
                        }
                        else
                        {
                            position=recyclerView.getChildPosition(recyclerView.findChildViewUnder(targetBottomPosition2,0));
                        }
                        recyclerView.scrollToPosition(position);
                        /*
                         * Convenience method to scroll to a certain position.
     *
     * RecyclerView does not implement scrolling logic, rather forwards the call to
     * {@link android.support.v7.widget.RecyclerView.LayoutManager#scrollToPosition(int)}
     * @param position Scroll to this adapter position
                         */

                    case RecyclerView.SCROLL_STATE_DRAGGING:

                        break;

                    case RecyclerView.SCROLL_STATE_SETTLING:

                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRows.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public  RecyclerView mRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView= (RecyclerView) itemView.findViewById(R.id.recyclerView_row);
        }
    }
}
