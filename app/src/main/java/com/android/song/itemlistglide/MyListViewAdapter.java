package com.android.song.itemlistglide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> arrayList = null;
    ViewHolder viewHolder;

    /**
     * 생성자를 통해서 ListView에 나타낼 아이템들을 받아온다
     * @param context context
     * @param arrayList ListView에 나타낼 아이템
     */
    public MyListViewAdapter(Context context, ArrayList<Item> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    /**
     * ListView의 아이템 전체 개수를 리턴
     * @return ListView의 아이템 전체 개수
     */
    @Override
    public int getCount() {
        return (arrayList != null)
                ? arrayList.size()
                : 0;
    }

    /**
     * arraylist에 저장되있는 아이템 중 position에 해당하는 것을 가져옴
     * @param position
     * @return 해당 position 아이템
     */
    @Override
    public Object getItem(int position) {
        return (arrayList != null && (0 <= position && position < arrayList.size()))
                ? arrayList.get(position)
                : null;
    }

    /**
     * 현재 position을 반환
     * @param position
     * @return 해당 position
     */
    @Override
    public long getItemId(int position) {
        return (arrayList != null && (0 <= position && position < arrayList.size()))
                ? position
                : 0;
    }

    /**
     * 받아온 아이템들을 ListView의 아이템으로 출력해주는 부분
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 이 부분이 리스트뷰에서 아이템과 xml을 연결하여 화면에 표시해주는 중요한 부분
        // 이 메소드(getView)에서 반복문이 실행되어 순차적으로 화면에 한칸씩 뿌려주는 역할
        // convertView > 만든 item.xml을 불러와야 함

        // convertView가 한번도 생성된 적이 없을 경우
        if(convertView == null){
            // LayoutFlater.from(context).inflate("레이아웃.xml", null);   >> View 클래스를 리턴해줌
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);

            // viewHolder를 초기화
            // 한번 해두면 이 position의 convertView는 다시 정의할 필요가 없음
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);

            // convertView에 Tag로 지정
            convertView.setTag(viewHolder);
        }else{
            // convertView가 생성된 적이 있다면 Tag로 지정해둔 viewHolder를 가져온다
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 받아온 아이템 데이터들을 viewHolder에 출력
        viewHolder.name.setText(arrayList.get(position).getName());
        viewHolder.content.setText(arrayList.get(position).getContent());
        viewHolder.date.setText(arrayList.get(position).getDate().toString());

        // 아이템의 icon url이 없을 경우 Glide을 사용하여 기본이미지 출력
        // icon url이 있을 경우 해당 url의 이미지 출력
        // gradle에 Glide dependency 추가
        // Glide.with(Context).load(“파일경로”).into(이미지뷰);
        if(arrayList.get(position).getImage_url() == null){
            Glide.with(context).load("data:image/png;base64,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").into(viewHolder.icon);
        }else{
            Glide.with(context).load(arrayList.get(position).getImage_url()).into(viewHolder.icon);
        }

        return convertView;
    }


    /**
     *  ListView의 아이템들을 담아주기 위한 Holder Class
     */
    public class ViewHolder{
        TextView name, content, date;
        ImageView icon;
    }


}
