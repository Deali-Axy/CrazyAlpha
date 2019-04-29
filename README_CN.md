## About
![](https://upload-images.jianshu.io/upload_images/8869373-901590e019f6f85b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Learn more on my WeChat Official Account：DealiAxy

Every post was in my blog：[blog.deali.cn](http://blog.deali.cn)

-----------
[了解项目详情请点击](https://www.jianshu.com/p/2da7cfba1cbe)

## Features
1. 基于JavaFX设计了游戏引擎XEngine
2. 使用自行开发的游戏引擎XEngine实现完整游戏功能
3. 游戏资源管理：字体、图片、音频管理
4. 游戏地图管理，多地图切换
5. MVVM分层设计，代码解耦合，模块高内聚
6. Model模型系统设计，模块化设计
7. 使用订阅者模式实现的消息系统和事件处理

## 下载运行
首先，[下载最新版本](https://github.com/Deali-Axy/CrazyAlpha/releases)
然后双击jar包运行，或者
```bash
java -jar crazyalpha.jar
```


## 架构
1. `Game.Engine`：自行实现的游戏引擎包
    1. `Annotation`：引擎注解包，包含引擎定义的注解
        1. `GameEventHandler`：事件处理器注解
        2. `GameManager`：游戏中各类管理器的注解
        3. `GameModel`：模型注解
        4. `GameObject`：游戏对象注解
        5. `GameView`：视图注解，用于实现MVVM分层设计
    2. `Base`：基础类包，包含了引擎定义的各种抽象基类
        1. `BaseModel`：基础模型
        2. `ImageModel`：基础图形模型
        3. `ShapeModel`：基础形状模型
        4. `TextModel`：基础文本模型
    3. `Enum`：各类枚举的定义（略）
    4. `Utils`：其他工具（略）
    5. `GameObject`：游戏对象基类，所有对象都从这个类派生
    6. `GameView`：除了游戏主界面外的其他界面都由视图基类派生
2. `Game.EventHandler`：包含所有事件处理器
3. `Game.Manager`：包含了各类管理器，地图、资源、数据、模型、事件等管理器
4. `Game.Object`：包含所有游戏对象的定义
    1. `Model`：包含所有游戏模型的定义
    2. `GameMap`：游戏地图对象
    3. `Generator`：游戏事件发生器
5. `Game.View`：视图包，包含所有视图的定义
6. `Game`：全局游戏对象，使用单例模式创建
7. `Main`：游戏主类
8. `Render`：核心渲染器，负责游戏的渲染工作


## 图形界面实现
为了开发这个游戏，我先开发了一个基于JavaFX的游戏引擎，引擎实现了从游戏资源管理、模型定义，到画面渲染等一系列功能，按照市面上现有的商业游戏引擎的设计思路进行设计，“麻雀虽小，五脏俱全”，虽然无法匹敌其他成熟的游戏引擎，但是就功能来说，是一应俱全的，能够满足大部分2D小游戏的开发需要。


## 界面截图
### 游戏启动画面：
左上方是游戏的logo，左下角显示最高分记录，下面是作者信息和版本。
右下角是两个按钮，一个启动游戏按钮，另一个退出按钮。
![](https://upload-images.jianshu.io/upload_images/8869373-fbde64fc99f06c85.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 游戏主界面：
左上角是玩家头像，当前得分，以及生命值；正上方是当前关卡；
画面中央是大风车，大风车跟随音乐节奏发射字母，玩家需要在字母飞出屏幕之前按下对应的字母才能得分。
![](https://upload-images.jianshu.io/upload_images/8869373-2ffef771e37c67c3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 游戏暂停画面
![image.png](https://upload-images.jianshu.io/upload_images/8869373-c90595ad9ef2f8f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 游戏结束画面
左下方显示本局游戏得分，以及是否打破最高分记录。右下角显示返回主界面按钮。
![](https://upload-images.jianshu.io/upload_images/8869373-bfabf0608d28e3eb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 开发心得
这是我第一次使用Java做游戏开发，之前对Java这门语言的理解仅限于JavaEE做Web开发这方面，最近学习了JavaFX，用了一下，才发现原来JavaFX这么好用，确实是专门为GUI设计的框架，只是用的人比较少，我在开发过程中遇到了很多问题，基本都找不到国内的资料，还好有万能的StackOverFlow，另外，Oracle官网的文档也是挺详细的，感谢～

对于我这样写习惯了Python这类动态语言的人来说，写大量Java代码的感受就是，很繁琐，但是很省心，不需要怎么动脑，不用纠结这个参数是什么类型，需不需要做强制类型检查，一切都由IDE和JVM安排的明明白白，不需要我再去操心类型的问题，另外因为Java语法比较死板，没有那么多灵活的写法，这既是缺点又是优点，缺点就是对于我这种喜欢炫技的人来说，写Java代码没有多少快感，但是优点是同样的功能，实现的代码基本都差不多，不同的人来实现也不会有多大的差别，这就保证了代码的可维护性，可能现在用Python写一个大项目，代码全都是挥洒飘逸，不拘一格，写的时候很爽，但是等到后面重构的时候，完蛋了，看不懂之前的代码了，尽管有注释，但不可能每一行代码都有注释吧，灵活的写法增加了理解难度，因此Java在大型项目的绝对统治地位不是空穴来风。

这次游戏开发也加深了我对Java这门语言的理解，比如说反射，比如说事件，一开始在做事件处理部分的时候，让我感到有点困惑的就是，Java既没有委托、也没有真正的“事件”这个概念，一切都是用接口和类实现，实现接口功能一般都是通过传入实现某个事件接口的匿名类（当然在Java8里可以使用lambda表达式），不过这也不是什么大问题，这些问题都有其他方法可以解决，只不过写起来比较繁琐而已。可能我之前写习惯了C#，所以写Java代码的时候总是不由自主拿来对比，但是其实这两种语言的可对比性不高，因为出生的年代都不一样，C#在语言设计上确实是比Java高明得多，近年来Java的新版本或有或无的在模仿C#多年前的特性，比如说Java8的lambda表达式，Java11的var定义变量等。

此外，此次开发小游戏让我对Java的体会就是，语法很简单，不像C#和C++那样多而复杂，所以对于初学者而言特别友好，因为一学就会，配合上最好的IDE：IDEA，轻轻松松写出各种项目，有一个说法是，因为Java语言本身的功能太少，所以出现了各种设计模式来补全Java语言的不足。

