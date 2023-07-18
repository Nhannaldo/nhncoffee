package com.example.appandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    Context context;
    List<Movie> mData;
    private final MovieItemClick movieItemClick;
    public SearchAdapter(Context context, List<Movie> mData,MovieItemClick listener) {
        this.context= context;
        this.mData=mData;
        movieItemClick=listener;
    }
    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_search,parent,false);
        return new SearchAdapter.MyViewHolder(view,movieItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        Movie p= mData.get(position);
        String image= p.getImage();
//        int resId=((Activity)context).getResources().
//                getIdentifier(mane,"drawable",((Activity)context).getPackageName());

        int resId =context.getResources().getIdentifier(image,"drawable",context.getPackageName());
        holder.ImgMovie.setImageResource(resId);
        //holder.txtloai.setText(Integer.valueOf(String.valueOf(resId)));
        holder.txtloai.setText("Nguyen Nhan");
        holder.txttitle.setText(p.getTitle());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ImgMovie;
        private TextView txttitle,txtloai;
        public MyViewHolder(@NonNull View itemView,MovieItemClick movieItemClick) {
            super(itemView);
            txtloai=itemView.findViewById(R.id.txtLoai);
            txttitle=itemView.findViewById(R.id.txtTitle);
            ImgMovie=itemView.findViewById(R.id.imgshow);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //movieItemClick.onMovie(mData.get(getAdapterPosition()),ImgMovie);
                    //Intent intent = new Intent(context,MovieDetailActivity.class);
                    //intent.putExtra("urlimg", (CharSequence) mData);
                    if(movieItemClick!=null){
                        int pos= getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            movieItemClick.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
//    Context context;
//    List<Movie> ds;
//    public SearchAdapter(Context context,List<Movie>ds){
//        this.context=context;
//        this.ds=ds;
//    }
//    @Override
//    public int getCount() {
//        return ds.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return ds.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder holder;
//        if(view==null){
//            holder= new ViewHolder();
//            LayoutInflater inflater=((Activity)context).getLayoutInflater();
//            view= inflater.inflate(R.layout.item_movie_search,null);
//            holder.hinh=(ImageView) view.findViewById(R.id.imageView4);
//            holder.txtloai=(TextView) view.findViewById(R.id.txtLoai);
//            holder.txttitle=(TextView) view.findViewById(R.id.txtTitle);
//            view.setTag(holder);
//        }
//        else{
//            holder = (ViewHolder) view.getTag();
//        }
//        Movie p= ds.get(i);
//        String image= p.getImage();
////        int resId=((Activity)context).getResources().
////                getIdentifier(mane,"drawable",((Activity)context).getPackageName());
//        int resId =context.getResources().getIdentifier(image,"drawable",context.getPackageName());
//        holder.hinh.setImageResource(resId);;
//        holder.txtloai.setText("Nguyen Huu Nhan");
//        holder.txttitle.setText(p.getTitle());
//        return view;
//    }
//    private static class ViewHolder{
//        ImageView hinh;
//        TextView txtloai,txttitle;
//    }
}
