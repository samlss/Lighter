# Lighter
[![Download](https://api.bintray.com/packages/samlss/maven/lighter/images/download.svg)](https://bintray.com/samlss/maven/lighter/_latestVersion)   [![Api reqeust](https://img.shields.io/badge/API-14+-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14#l14)    [![Apache License 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/samlss/Lighter/blob/master/LICENSE)  [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss) <a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu"></a>

### 支持使用:
- 安卓原生布局(例如RelativeLayout, FragmentLayout)
- RecyclerView & ListView & GridView & ScrollView
- ViewPager
- Dialog
- Fragment

### 功能:

- 可一次显示单个或多个高亮的view
- 描绘高亮view时可设置自定义画笔(paint)
- 自定义高亮view的形状和形状的大小
- 自定义提示view的显示动画
- 提示view的相对位置
- 链式调用，简单使用

### 效果图

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot1.gif)

<br>

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot2.gif)

<br>

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot3.gif)

<br>

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot4.gif)

<br>

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot5.png)

------
### 使用

#### Gradle
添加到app的build.gradle:
  ```java
  dependencies {
      implementation 'me.samlss:lighter:1.0.3'
  }
  ```

#### Maven
```java
<dependency>
  <groupId>me.samlss</groupId>
  <artifactId>lighter</artifactId>
  <version>1.0.3</version>
  <type>pom</type>
</dependency>
```

#### 代码

你可以指定highlight view所依附的父view,

通过使用以下代码来指定:

```java
Lighter with(Activity activity) //使用 activity.getWindow().getDecorView 作为父view, 所以会全屏显示，参考图1
    
Lighter with(ViewGroup rootView) //使用 'rootView' 作为父view
```

完整调用:

```java

 Lighter.with(activity)
                .addHighlight(new LighterParameter.Builder()
                                .setHighlightedViewId(R.id.vp_btn_1)
                                .setTipLayoutId(R.layout.layout_tip_1)
                                .setLighterShape(new RectShape(5, 5, 30))
                                .setTipViewRelativeDirection(Direction.BOTTOM)
                                .setTipViewRelativeOffset(new MarginOffset(150, 0, 30, 0))
                                .build())
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.vp_btn_2)
                        .setTipLayoutId(R.layout.layout_tip_2)
                        .setLighterShape(new RectShape(5, 5, 30))
                        .setTipViewRelativeDirection(Direction.TOP)
                        .setTipViewRelativeOffset(new MarginOffset(-400, 0, 0, 30))
                        .build())
                .show();
```



当你需要一次显示多个高亮的时候, 可调用**addHighlight(LighterParameter...lighterParameters)**

```java
Lighter.with(activity)
                .addHighlight(
                        //一次显示两个高亮
                        new LighterParameter.Builder()
                                .setHighlightedViewId(R.id.vp_btn_1)
                                .setTipLayoutId(R.layout.layout_tip_1)
                                .setLighterShape(new RectShape(5, 5, 30))
                                .setTipViewRelativeDirection(Direction.BOTTOM)
                                .setTipViewRelativeOffset(new MarginOffset(150, 0, 30, 0))
                                .build(),

                        new LighterParameter.Builder()
                                .setHighlightedViewId(R.id.vp_btn_2)
                                .setTipLayoutId(R.layout.layout_tip_2)
                                .setLighterShape(new RectShape(5, 5, 30))
                                .setTipViewRelativeDirection(Direction.TOP)
                                .setTipViewRelativeOffset(new MarginOffset(-400, 0, 0, 30))
                                .build())
                .show();
```

#### 关于 [LighterParameter.Builder](https://github.com/samlss/Lighter/blob/master/lighter/src/main/java/me/samlss/lighter/parameter/LighterParameter.java)


| Method                      | Description                      |
| :-------------------------- | :------------------------------- |
| setHighlightedViewId        | 设置需要高亮的view id            |
| setHighlightedView          | 设置需要高亮的view               |
| setTipLayoutId              | 设置提示view的layout id          |
| setTipView                  | 设置提示view                     |
| setLighterShape             | 设置围着高亮view的形状           |
| setShapeXOffset             | 设置形状的x偏移量                |
| setShapeYOffset             | 设置形状的y偏移量                |
| setTipViewRelativeDirection | 设置提示view相对高亮view的方向   |
| setTipViewRelativeOffset    | 设置提示view相对高亮view的便宜量 |
| setTipViewDisplayAnimation  | 设置提示view的显示动画           |
| build                       | 创建LighterParameter对象         |

##### 注意:

- setHighlightedViewId & setHighlightedView 
- setTipLayoutId & setTipView

上面两组方法中，只需使用其中一个方法，若都不使用的话，则会抛出异常

#### 关于 [Shape](https://github.com/samlss/Lighter/tree/master/lighter/src/main/java/me/samlss/lighter/shape)

| Shape  | Description                  |
| ------ | ---------------------------- |
| Rect   | 可设置圆角和模糊度的矩形图形 |
| Circle | 可设置模糊度的圆形           |
| Oval   | 可设置模糊度的椭圆形         |



##### ShapeXOffset & ShapeYOffset

参考下面图片 :

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot6.png)



#### 关于提示view的参数

##### 方向

参考下面图片:

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot7.png)

##### 偏移量

当指定方向后(默认为左边),  偏移量计算基于方向

例如:

Left: topMargin 和 rightMargin生效

Right: topMargin 和 leftmargin生效

Top:  当高亮view处于屏幕的右边的时候, teftMargin & bottomMargin生效. 否则, rightMargin & bottomMargin生效

Bottom: 当高亮view处于屏幕的左边的时候, leftMargin 和 topMargin生效. 否则, rightMargin & topMargin生效

##### Animation

可使用任何动画


### License

```
Copyright 2018 samlss

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
