package com.yekong.exercise_1.entity;

import java.util.List;

/**
 * 课表
 * Created by xigua on 2017/9/6.
 */

public class ClassCourseEntity {


    public static String data = "[\n" +
            "    {\"classItem\": [\n" +
            "        {\n" +
            "            \"className\": \"语文\",\n" +
            "            \"classSpan\": 2,\n" +
            "            \"classStart\": 1,\n" +
            "            \"index\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"数学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 3,\n" +
            "            \"index\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"化学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 4,\n" +
            "            \"index\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"历史\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 5,\n" +
            "            \"index\": 3\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"高数\",\n" +
            "            \"classSpan\": 2,\n" +
            "            \"classStart\": 6,\n" +
            "            \"index\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"物理\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 8,\n" +
            "            \"index\": 5\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"晚自习(电学)\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 9,\n" +
            "            \"index\": 6\n" +
            "        }\n" +
            "    ]},\n" +
            "    {\"classItem\": [\n" +
            "        {\n" +
            "            \"className\": \"英语\",\n" +
            "            \"classSpan\": 2,\n" +
            "            \"classStart\": 1,\n" +
            "            \"index\": 7\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"语文\",\n" +
            "            \"classSpan\": 2,\n" +
            "            \"classStart\": 3,\n" +
            "            \"index\": 8\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"音乐\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 5,\n" +
            "            \"index\": 9\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"电学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 6,\n" +
            "            \"index\": 10\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"白学\",\n" +
            "            \"classSpan\": 2,\n" +
            "            \"classStart\": 7,\n" +
            "            \"index\": 11\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"晚自习(白学)\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 9,\n" +
            "            \"index\": 12\n" +
            "        }\n" +
            "    ]},\n" +
            "    {\"classItem\": [\n" +
            "        {\n" +
            "            \"className\": \"空间学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 1,\n" +
            "            \"index\": 13\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"高数\",\n" +
            "            \"classSpan\": 2,\n" +
            "            \"classStart\": 2,\n" +
            "            \"index\": 14\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"化学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 4,\n" +
            "            \"index\": 15\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"飞行学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 5,\n" +
            "            \"index\": 16\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"神学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 6,\n" +
            "            \"index\": 17\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"风学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 7,\n" +
            "            \"index\": 18\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"夏学\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 8,\n" +
            "            \"index\": 19\n" +
            "        },\n" +
            "        {\n" +
            "            \"className\": \"晚自习(夏学)\",\n" +
            "            \"classSpan\": 1,\n" +
            "            \"classStart\": 8,\n" +
            "            \"index\": 20\n" +
            "        }\n" +
            "    ]}\n" +
            "]";

    private List<ClassItemBean> classItem;

    public List<ClassItemBean> getClassItem() {
        return classItem;
    }

    public void setClassItem(List<ClassItemBean> classItem) {
        this.classItem = classItem;
    }

    public static class ClassItemBean {
        /**
         * className : 语文
         * classSpan : 2
         * classStart : 1
         */

        private String className;
        private int classSpan;
        private int classStart;
        private int index;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getClassSpan() {
            return classSpan;
        }

        public void setClassSpan(int classSpan) {
            this.classSpan = classSpan;
        }

        public int getClassStart() {
            return classStart;
        }

        public void setClassStart(int classStart) {
            this.classStart = classStart;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
    /**

          [
                {classItem:[
                       {
                           className:"语文",
                           classSpan:2,
                           classStart:1,
     index:0
                       },
                       {
                           className:"数学",
                           classSpan:1,
                           classStart:3,
     index:1
                       },
                       {
                           className:"化学",
                           classSpan:1,
                           classStart:4,
     index:2
                       },
                       {
                           className:"历史",
                           classSpan:1,
                           classStart:5,
     index:3
                       },
                       {
                           className:"高数",
                           classSpan:2,
                           classStart:6,
     index:4
                       },
                       {
                            className:"物理",
                           classSpan:1,
                           classStart:8,
     index:5
                       },
                         {
                         className:"晚自习(电学)",
                         classSpan:1,
                         classStart:9,
     index:6
                         }
                        ]
                },
                {classItem:[
                     {
                     className:"英语",
                     classSpan:2,
                     classStart:1,
     index:7
                     },
                     {
                     className:"语文",
                     classSpan:2,
                     classStart:3,
     index:8
                     },
                     {
                     className:"音乐",
                     classSpan:1,
                     classStart:5,
     index:9
                     },
                     {
                     className:"电学",
                     classSpan:1,
                     classStart:6,
     index:10
                     },
                     {
                     className:"白学",
                     classSpan:2,
                     classStart:7,
     index:11
                     },
                     {
                     className:"晚自习(白学)",
                     classSpan:1,
                     classStart:9,
     index:12
                     }

                ]},
                 {classItem:[
                 {
                 className:"空间学",
                 classSpan:1,
                 classStart:1,
     index:13
                 },
                 {
                 className:"高数",
                 classSpan:2,
                 classStart:2,
     index:14
                 },
                 {
                 className:"化学",
                 classSpan:1,
                 classStart:4,
     index:15
                 },
                 {
                 className:"飞行学",
                 classSpan:1,
                 classStart:5,
     index:16
                 },
                 {
                 className:"神学",
                 classSpan:1,
                 classStart:6,
     index:17
                 },
                 {
                 className:"风学",
                 classSpan:1,
                 classStart:7,
     index:18
                 },
                 {
                 className:"夏学",
                 classSpan:1,
                 classStart:8,
     index:19
                 },
                 {
                 className:"晚自习(夏学)",
                 classSpan:1,
                 classStart:8,
     index:20
                 }
                 ]},
          ]

     */

}
