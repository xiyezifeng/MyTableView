package com.yekong.exercise_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yekong.exercise_1.entity.ClassCourseEntity;
import com.yekong.exercise_1.entity.MyItem;
import com.yekong.exercise_1.view.MyView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private MyView view;
    private List<MyItem> items = new ArrayList<>();
    private int tabWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (MyView) findViewById(R.id.view);
        tabWidth = view.getTabWidth();
        testClassList();
//        testClassSchedule();
//        testClass();
//        testTime24();
//        testPic();
        view.setListener(new MyView.OnItemClickListener() {
            @Override
            public void onItemClick(MyItem item) {
//                Toast.makeText(MainActivity.this, item.getTextDest(), Toast.LENGTH_SHORT).show();
                int index = item.getIndex();
                if (index == -1) return;
                Toast.makeText(MainActivity.this, item.getTextDest(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this,"XXX老师  :  "+  itemBeanList.get(index).getClassName(), Toast.LENGTH_SHORT).show();
                /*AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setNegativeButton("知道了",null)
                        .create();
                dialog.setTitle(itemBeanList.get(index).getClassName());
                dialog.setMessage("这节课由XXX老师来上");
                dialog.show();*/
            }
        });
    }

    private void testPic() {
        MyItem item;
        item = view.createItem(-1, 0 , 0 , 0, 1 , 0);
        item.setResouceId(R.mipmap.icon);
        item.setDrawType(1);
        item.setSpanLine(2);
        items.add(item);

        item = view.createItem(-1, 2 , 0 , 0, 1 , 0);
        item.setResouceId(R.mipmap.ic_launcher);
        item.setDrawType(1);
        item.setSpanLine(2);
        items.add(item);

        item = view.createItem(-1, 4 , 0 , 0, 1 , 0);
        item.setResouceId(R.mipmap.logo_m);
        item.setDrawType(1);
        item.setSpanLine(2);
        items.add(item);

        item = view.createItem(-1, 0 , 1 , 0, 3 , 0);
        item.setTextDest("一");
        items.add(item);

        item = view.createItem(-1, 1 , 1 , 10, 2 , 9);
        item.setTextDest("二");
        items.add(item);
        item = view.createItem(-1, 1 , 2 , 35,3 , 33);
        item.setTextDest("三");
        items.add(item);

        view.setMyItem(items,4 , 4);//5排15行

    }

    private List<ClassCourseEntity> courseEntitys;
    private List<ClassCourseEntity.ClassItemBean> itemBeanList;
    private void testClassList() {

        courseEntitys = JSON.parseArray(ClassCourseEntity.data, ClassCourseEntity.class);
        itemBeanList = new ArrayList<>();
        MyItem item;
        item = view.createItem(-1, 0 , 0 , 0, 5 , 0);
        item.setTextDest("XXX班XXXX年第X学期课表");
        items.add(item);

        item = view.createItem(-1, 1 , 0 , 0, 1 , 0);
        item.setTextDest("星期一");
        items.add(item);
        item = view.createItem(-1, 1 , 1 , 0, 2 , 0);
        item.setTextDest("星期二");
        items.add(item);
        item = view.createItem(-1, 1 , 2 , 0, 3 , 0);
        item.setTextDest("星期三");
        items.add(item);
        item = view.createItem(-1, 1 , 3 , 0, 4 , 0);
        item.setTextDest("星期四");
        items.add(item);
        item = view.createItem(-1, 1 , 4 , 0, 5 , 0);
        item.setTextDest("星期五");
        items.add(item);

        item = view.createItem(-1, 2 , 0 , 0, 5 , 0);
        item.setTextDest("上午");
        items.add(item);
        item = view.createItem(-1, 7 , 0 , 0, 5 , 0);
        item.setTextDest("下午");
        items.add(item);
        item = view.createItem(-1, 12 , 0 , 0, 5 , 0);
        item.setTextDest("晚自习");
        items.add(item);

        // 3 4 5 6    8 9 10 11   13
        ClassCourseEntity entity;
        for (int j = 0; j < courseEntitys.size(); j++) {
            entity = courseEntitys.get(j);
            for (int i = 0; i < entity.getClassItem().size(); i++) {
                item = view.createItem(entity.getClassItem().get(i).getIndex(), getClass(entity.getClassItem().get(i).getClassStart()) , j , 0, j+1 , 0);
                item.setSpanLine(entity.getClassItem().get(i).getClassSpan());
                item.setTextDest(entity.getClassItem().get(i).getClassName());
                itemBeanList.add(entity.getClassItem().get(i));
                items.add(item);
            }
        }

        view.setMyItem(items, 5 , 14);//5排15行
        List<Integer> number = new ArrayList<>();
        number.add(3);
        number.add(4);
        number.add(5);
        number.add(6);

        number.add(8);
        number.add(9);
        number.add(10);
        number.add(11);
        number.add(13);
        view.setLineItem(number);
    }

    private int getClass(int classId){
        int id = 0;
        switch (classId) {
            case 1:
                id = 3;
                break;
            case 2:
                id = 4;
                break;
            case 3:
                id = 5;
                break;
            case 4:
                id = 6;
                break;
            case 5:
                id = 8;
                break;
            case 6:
                id = 9;
                break;
            case 7:
                id = 10;
                break;
            case 8:
                id = 11;
                break;
            case 9:
                id = 13;
                break;
        }
        return id;
    }

    private void testClassSchedule(){
        MyItem item;
        item = view.createItem(0, 0 , 0 , 0, 5 , 0);
        item.setTextDest("XXX班XXXX年第X学期课表");
        items.add(item);

        item = view.createItem(0, 1 , 0 , 0, 1 , 0);
        item.setTextDest("星期一");
        items.add(item);
        item = view.createItem(0, 1 , 1 , 0, 2 , 0);
        item.setTextDest("星期二");
        items.add(item);
        item = view.createItem(0, 1 , 2 , 0, 3 , 0);
        item.setTextDest("星期三");
        items.add(item);
        item = view.createItem(0, 1 , 3 , 0, 4 , 0);
        item.setTextDest("星期四");
        items.add(item);
        item = view.createItem(0, 1 , 4 , 0, 5 , 0);
        item.setTextDest("星期五");
        items.add(item);

        item = view.createItem(0, 2 , 0 , 0, 5 , 0);
        item.setTextDest("上午");
        items.add(item);
        item = view.createItem(0, 7 , 0 , 0, 5 , 0);
        item.setTextDest("下午");
        items.add(item);

        //周一
        item = view.createItem(0, 3 , 0 , 0, 1 , 0);
        item.setTextDest("语文");
        item.setSpanLine(2);
        items.add(item);
        item = view.createItem(0, 5 , 0 , 0, 1 , 0);
        item.setTextDest("数学");
        items.add(item);
        item = view.createItem(0, 6 , 0 , 0, 1 , 0);
        item.setTextDest("体育");
        items.add(item);
        item = view.createItem(0, 8 , 0 , 0, 1 , 0);
        item.setTextDest("英语");
        items.add(item);
        item = view.createItem(0, 9 , 0 , 0, 1 , 0);
        item.setTextDest("历史");
        items.add(item);
        item = view.createItem(0, 10 , 0 , 0, 1 , 0);
        item.setTextDest("化学");
        item.setSpanLine(2);
        items.add(item);
        //周二
        item = view.createItem(0, 3 , 1 , 0, 2 , 0);
        item.setTextDest("英语");
        items.add(item);
        item = view.createItem(0, 4 , 1 , 0, 2 , 0);
        item.setTextDest("物理");
        items.add(item);
        item = view.createItem(0, 5 , 1 , 0, 2 , 0);
        item.setTextDest("生物");
        items.add(item);
        item = view.createItem(0, 6 , 1 , 0, 2 , 0);
        item.setTextDest("历史");
        items.add(item);
        item = view.createItem(0, 8 , 1 , 0, 2 , 0);
        item.setTextDest("政治");
        items.add(item);
        item = view.createItem(0, 9 , 1 , 0, 2 , 0);
        item.setTextDest("高数");
        items.add(item);
        item = view.createItem(0, 10 , 1 , 0, 2 , 0);
        item.setTextDest("动力学");
        items.add(item);
        item = view.createItem(0, 11 , 1 , 0, 2 , 0);
        item.setTextDest("音乐");
        items.add(item);

        view.setMyItem(items, 5 , 12);//5排12行

        List<Integer> number = new ArrayList<>();
        number.add(3);
        number.add(4);
        number.add(5);
        number.add(6);

        number.add(8);
        number.add(9);
        number.add(10);
        number.add(11);
        view.setLineItem(number);
    }

    private void testClass() {
        MyItem item;

        item = view.createItem(0, 0 , 1 , 0, 3 , 0);
        item.setTextDest("老师");
        items.add(item);

        item = view.createItem(0, 1 , 0 , 0, 1 , 0);
        item.setTextDest("小明");
        item.setSpanLine(4);
        items.add(item);
        item = view.createItem(1, 1 , 1 , 0, 2 , 0);
        item.setTextDest("小花");
        items.add(item);
        item = view.createItem(2, 1 , 2  , 0, 3 , 0);
        item.setTextDest("小黄");
        item.setSpanLine(2);
        items.add(item);
        item = view.createItem(3, 1 , 3 , 0, 4, 0);
        item.setTextDest("小磊");
        items.add(item);
        item = view.createItem(4, 1 ,4 , 0, 5, 0);
        item.setTextDest("小申");
        items.add(item);


//        item = view.createItem(0, 2 , 0 , 0, 1 , 0);
//        item.setTextDest("小明");
//        items.add(item);
        item = view.createItem(1, 2 , 1 , 0, 2 , 0);
        item.setTextDest("小花");
        items.add(item);
        /*item = view.createItem(2, 2 , 2  , 0, 3 , 0);
        item.setTextDest("小黄");
        items.add(item);*/
        item = view.createItem(3, 2 ,3 , 0, 4, 0);
        item.setTextDest("小磊");
        items.add(item);
        item = view.createItem(4, 2 ,4 , 0, 5, 0);
        item.setTextDest("小申");
        items.add(item);

        item = view.createItem(1, 3 , 1 , 0, 2 , 0);
        item.setTextDest("小花");
        items.add(item);
        item = view.createItem(2, 3 , 2  , 0, 3 , 0);
        item.setTextDest("小黄");
        items.add(item);
        item = view.createItem(3, 3 ,3 , 0, 4, 30);
        item.setTextDest("小磊");
        items.add(item);
        item = view.createItem(4, 3 ,4 ,30, 5, 0);
        item.setTextDest("小申");
        items.add(item);

        item = view.createItem(1, 4 , 1 , 0, 2 , 0);
        item.setTextDest("小花");
        items.add(item);
        item = view.createItem(2, 4 , 2  , 0, 3 , 0);
        item.setTextDest("小黄");
        items.add(item);
        item = view.createItem(3, 4 ,3 , 0, 4, 0);
        item.setTextDest("小磊");
        items.add(item);
        item = view.createItem(4, 4 ,4 , 0, 5, 0);
        item.setTextDest("小申");
        items.add(item);

        view.setMyItem(items, 5 , 5);//4排5行
    }

    private void testTime24(){
        MyItem item;
        for (int i = 0; i <= 22; ) {
            item = view.createItem(i,0, i, 0, i+2, 0);
            item.setTextDest("今日(きょう）は、风(かぜ）が騒(さわ）がしいなあ");
            items.add(item);
            i += 2;
        }
        for (int i = 0; i <= 23; ) {
            item = view.createItem(i,1, i, 0, i+1, 0);
            item.setTextDest("バスケがしたいんです");
            items.add(item);
            i += 1;
        }
        for (int i = 0; i <= 21; ) {
            item = view.createItem(i,2, i, 0, i+3, 0);
            item.setTextDest("そして、仆(ぼく）は新世界(しんせかい）の神(かみ）となる！");
            items.add(item);
            i += 3;
        }
        for (int i = 0; i <= 22; ) {
            item = view.createItem(i,3, i, 0, i+2, 0);
            item.setTextDest("私(わたし）と契约(けいやく）を结(むす）んで魔法少女(まほうしょうじょ）になってください");
            items.add(item);
            i += 2;
        }
        for (int i = 0; i <= 23; ) {
            item = view.createItem(i,4, i, 0, i+1, 0);
            item.setTextDest("戦闘力(せんとうりょく）ただ５か。。ゴミめ");
            items.add(item);

            item = view.createItem(i,5, i, 0, i+1, 0);
            item.setTextDest("だが、我(わが）ドイツの○○（医学薬学(いがくやくがく））は世界一(せかいいち）！！！！！");
            items.add(item);

            item = view.createItem(i,6, i, 0, i+1, 0);
            item.setTextDest("ごめんなさい、こういう时どんな颜をすればいいのか、わからないの");
            items.add(item);

            item = view.createItem(i,7, i, 0, i+1, 0);
            item.setTextDest("诸君（しょくん）、私は戦争（せんそう）が好きだ！");
            items.add(item);

            item = view.createItem(i,8, i, 0, i+1, 0);
            item.setTextDest("私(わたし）と契约(けいやく）を结(むす）んで魔法少女(まほうしょうじょ）になってください");
            items.add(item);

            item = view.createItem(i,9, i, 0, i+1, 0);
            item.setTextDest("そして、仆(ぼく）は新世界(しんせかい）の神(かみ）となる！");
            items.add(item);
            i += 1;
        }

        item = view.createItem(0, 10, 0, 10, 1, 5);
        item.setTextDest("00:10~01:05");
        items.add(item);
        item = view.createItem(0, 10, 3, 25, 3, 26);
        item.setTextDest("3:25~3:26");
        items.add(item);
        item = view.createItem(0, 10, 3, 50, 4, 15);
        item.setTextDest("3:50~4:15");
        items.add(item);
        item = view.createItem(0, 10, 6, 0, 8, 50);
        item.setTextDest("6:00~8:50");
        items.add(item);
        item = view.createItem(0, 10, 10, 30, 11, 30);
        item.setTextDest("10:30~11:30");
        items.add(item);
        item = view.createItem(0, 10, 11, 35, 12, 0);
        item.setTextDest("11:35~12:0");
        items.add(item);
        item = view.createItem(0, 10, 12, 00, 12, 30);
        item.setTextDest("12:00~12:30");
        items.add(item);
        item = view.createItem(0, 10, 13, 20, 15, 20);
        item.setTextDest("00:10~01:05");
        items.add(item);
        item = view.createItem(0, 10, 17, 00, 18, 50);
        item.setTextDest("00:10~01:05");
        items.add(item);
        item = view.createItem(0, 10, 19, 00, 19, 30);
        item.setTextDest("19:00~19:30");
        items.add(item);
        item = view.createItem(0, 10, 23, 00, 24, 00);
        item.setTextDest("23:00~24:00");
        items.add(item);
        view.setMyItem(items, 24 , 11);
    }

}
