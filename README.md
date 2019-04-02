# Lighter
[![Download](https://api.bintray.com/packages/samlss/maven/lighter/images/download.svg)](https://bintray.com/samlss/maven/lighter/_latestVersion)   [![Api reqeust](https://img.shields.io/badge/API-14+-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14#l14)    [![Apache License 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/samlss/Lighter/blob/master/LICENSE)  [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss) <a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu"></a>

 [中文](https://github.com/samlss/Lighter/blob/master/README_CN.md)

### Support use in:
- Android native layout(Such as RelativeLayout, FragmentLayout)
- RecyclerView & ListView & GridView & ScrollView
- ViewPager
- Dialog
- Fragment

### Features:

- One or more highlighted views can be displayed at one time
- Customizing the paint for highlighting the view
- Customize the shape of the highlighted view and the size of the shape
- Customized tip view display animation
- Custom relative position of the tip view
- Chained display code, simple to use

### Screenshots

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
### Usage

#### Gradle
Add it in your app build.gradle at the end of repositories:
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

#### Code

You can specify that the highlighted root view is attached to the activity or viewgroup,

you can use the below code to specify:

```java
Lighter with(Activity activity) //will use activity.getWindow().getDecorView as the root view, so it will display in full screen 
    
Lighter with(ViewGroup rootView) //will use the 'rootView' as the root view
```

Complete call:

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



When you need to display multiple highlight views at once, call **addHighlight(LighterParameter...lighterParameters)**

```java
Lighter.with(activity)
                .addHighlight(
                        //Show two at a time
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

#### About [LighterParameter.Builder](https://github.com/samlss/Lighter/blob/master/lighter/src/main/java/me/samlss/lighter/parameter/LighterParameter.java)


| Method                      | Description                                                  |
| :-------------------------- | :----------------------------------------------------------- |
| setHighlightedViewId        | Set id the highlighted view                                  |
| setHighlightedView          | Set the highlighted view                                     |
| setTipLayoutId              | Set the layout id of the tip layout                          |
| setTipView                  | Set the tip view                                             |
| setLighterShape             | Set the shape of the wrapped highlight view                  |
| setShapeXOffset             | Set the x-axis offset of the shape rect                      |
| setShapeYOffset             | Set the y-axis offset of the shape rect                      |
| setTipViewRelativeDirection | Set the direction of the tip view relative to the highlighted view |
| setTipViewRelativeOffset    | Set the offset of the tip view's margin relative to the highlighted view |
| setTipViewDisplayAnimation  | Set animation of the tip view when display                   |
| build                       | To create a [LighterParameter](https://github.com/samlss/Lighter/blob/master/lighter/src/main/java/me/samlss/lighter/parameter/LighterParameter.java) object                      |

##### Note:

- setHighlightedViewId & setHighlightedView 
- setTipLayoutId & setTipView

For the above two methods, you only need to use one. If you don't use it,  an exception will be throwing.

#### About [Shape](https://github.com/samlss/Lighter/tree/master/lighter/src/main/java/me/samlss/lighter/shape)

| Shape  | Description                                              |
| ------ | -------------------------------------------------------- |
| Rect   | A rectangular shape with rounded corners and blur radius |
| Circle | A circle shape with blur radius                          |
| Oval   | A oval shape with blur radius                            |



##### ShapeXOffset & ShapeYOffset

Refer to the below picture :

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot6.png)



#### About parameter of TipView

##### Direction

Refer to the below picture :

![Lighter](https://github.com/samlss/Lighter/blob/master/screenshots/screenshot7.png)

##### Offset

When you specify a direction(the default is left),  the offset distance will be based on the direction you specify.

E.g:

Left: The values of topMargin & rightMargin will take effect

Right: The values of topMargin & leftMargin will take effect

Top:  If the highlighted view is on the left side of the screen, the values of leftMargin & bottomMargin will take effect. Otherwise, the values of rightMargin & bottomMargin will take effect.

Bottom: If the highlighted view is on the left side of the screen, the values of leftMargin & topMargin will take effect. Otherwise, the values of rightMargin & topMargin will take effect.

##### Animation

You can set any animation for the tip view.

##### 


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
