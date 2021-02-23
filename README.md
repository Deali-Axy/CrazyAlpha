# Crazy Alpha

WeChat Official Account | WeChat Official Account |
------- | ------ | 
![](https://gitee.com/deali/CodeZone/raw/master/images/coding_lab_logo.jpg) | ![](https://gitee.com/deali/CodeZone/raw/master/images/coding_lab_qr_code.jpg)   |


[中文版文档](https://github.com/Deali-Axy/CrazyAlpha/blob/master/README_CN.md)
## Welcome to communicate with me
- coding live room: [https://live.bilibili.com/11883038](https://live.bilibili.com/11883038)
- WeChat official account: DealiAxy
- Email: admin@deali.cn
- Zhihu community: [https://www.zhihu.com/people/dealiaxy](https://www.zhihu.com/people/dealiaxy)
- CNBlog：[https://www.cnblogs.com/deali/](https://www.cnblogs.com/deali/)

-----------
For details of the project, please [click](https://www.jianshu.com/p/2da7cfba1cbe)

## Features
1. The game engine XEngine is designed based on JavaFX.
2. Use the self-developed game engine XEngine to realize the complete game function.
3. Game Resource Management: Font, Picture, Audio Management
4. Overworld Administration, Multi-Map Switching
5. MVVM hierarchical design, code decoupling, module high cohesion
6. Model system design, modular design
7. Message System and Event Processing Implemented by Subscriber Mode

## Download and Run
First, [download](https://github.com/Deali-Axy/CrazyAlpha/releases) the latest version and double-click the jar package to run, 
or
```bash
java -jar crazyalpha.jar
```
### Build
the code was built on openjdk8, notice that there is no javafx support in jdk11,
so if you want to run or build the code, please use jdk8!

## Architecture
1. `Game.Engine`：Self-implemented game engine package
    1. `Annotation`：engine annotation package with engine-defined annotations
        1. `GameEventHandler`：Event Processor Annotations
        2. `GameManager`：Annotations on various managers in the game
        3. `GameModel`：Model annotations
        4. `GameObject`： Game Object Annotations
        5. `GameView`：View annotations for implementing MVVM layered design
    2. `Base`：Basic class package, contains various abstract base classes defined by the engine
        1. `BaseModel`：Basic model
        2. `ImageModel`：Basic graphic model
        3. `ShapeModel`：Basic shape model
        4. `TextModel`：Basic text model
    3. `Enum`：definition of various enumerations (omitted)
    4. `Utils`：Other tools (omitted)
    5. `GameObject`：Game object base class, all objects are derived from this class
    6. `GameView`：Other interfaces besides the main game interface are derived from the view base class
2. `Game.EventHandler`：Contains all event handlers
3. `Game.Manager`：Contains various managers, maps, resources, data, models, events, etc.
4. `Game.Object`：contains definitions of all game objects
    1. `Model`：contains definitions of all game models
    2. `GameMap`：Game Map Object
    3. `Generator`：Game Event Generator
5. `Game.View`：view package, which contains definitions for all views
6. `Game`：Global game object, created using singleton mode
7. `Main`：Game main class
8. `Render`：Core renderer, responsible for rendering the game

## Graphical interface implementation
In order to develop this game,
I first developed a game engine based on JavaFX.
The engine implements a series of functions from game resource management,
model definition, to screen rendering,
and is designed according to the design ideas of existing commercial game engines on the market. 
"The sparrow is small and complete," 
although it can't match other mature game engines,
it is fully functional and can meet the development needs of most 2D games.


## Screenshot of the interface
### Game splash screen:
The top left is the logo of the game,
the bottom left corner shows the highest score, 
and the following is the author information and version. 
In the lower right corner are two buttons, 
one to launch the game button and the other to exit the button. 
![](https://github.com/Deali-Axy/CrazyAlpha/raw/master/Screenshot/8869373-fbde64fc99f06c85.png)

### The main game interface:
The top left corner is the player's avatar, 
the current score, and the health value; 
directly above is the current level; 
the center of the picture is the big windmill, 
the big windmill emits letters following the music rhythm, 
the player needs to press the corresponding letter before the letters fly out of the screen to score. 
![](https://github.com/Deali-Axy/CrazyAlpha/raw/master/Screenshot/8869373-2ffef771e37c67c3.png)

### Game pause screen
![image.png](https://github.com/Deali-Axy/CrazyAlpha/raw/master/Screenshot/8869373-c90595ad9ef2f8f5.png)

### Game over screen
The bottom left shows the game scores and whether to break the highest score. 
The return to the main interface button is displayed in the lower right corner. 
![](https://github.com/Deali-Axy/CrazyAlpha/raw/master/Screenshot/8869373-bfabf0608d28e3eb.png)


## Development experience
This is my first time using Java to do game development. 
Previously, the understanding of Java language was limited to JavaEE. 
I recently learned about JavaFX. After using it for a while, 
I found out that JavaFX is so easy to use. 
GUI design framework, just use less people, 
I encountered a lot of problems in the development process, 
basically can not find domestic information, 
but also have a versatile StackOverFlow, in addition, 
Oracle official website documentation is quite detailed, thanks ~

For people like me who write a dynamic language like Python, 
the feeling of writing a lot of Java code is very cumbersome, 
but it is very worry-free, no need to brainstorm, 
no need to entangle the type of this parameter, 
you do not need to force Type checking, 
everything is clearly arranged by the IDE and JVM, 
I don't need to worry about the type of problem, 
and because the Java syntax is relatively rigid, 
there is not so much flexible writing, 
which is both a disadvantage and an advantage. 
The disadvantage is that for me. 
For those who like to show off their skills, 
writing Java code doesn't have much pleasure, 
but the advantage is the same function. 
The code that is implemented is basically the same. 
Different people will not have much difference in implementation. 
This ensures the code. Maintenance, 
you may now write a big project in Python, 
the code is all elegant, eclectic, very cool when writing,
 but when you refactor after the completion, 
 you can't read the previous code, even though there are comments,
 but not every line of code has a comment, 
 flexible writing increases the difficulty of understanding, 
 so Java is absolutely dominant in large projects. 
 The position is not a hole in the wind.

This game development has also deepened my understanding of the Java language, 
such as reflections, such as events. When I started doing event processing, 
I was a bit confused. Java is neither trusted nor really The concept of "event", 
everything is implemented with interfaces and classes. 
The implementation of interface functions is generally passed to the anonymous class that implements an event interface (of course, lambda expressions can be used in Java8), 
but this is not what it is. 
Big problems, these problems have other methods to solve, 
but it is more complicated to write. 
Maybe I used to write to C# before, so when I write Java code, I can't help but compare it, but in fact, the contrast between these two languages ​​is not high, because the age of birth is different, C# is indeed better than language design. Java is much more sophisticated. In recent years, new versions of Java have been or are not mimicking the features of C# many years ago, such as Java8 lambda expressions, Java11 var definition variables, and so on.

In addition, this development of the small game makes me realize that the syntax is very simple, not as complicated and complicated as C# and C++, so it is especially friendly for beginners, because one will match the best IDE. : IDEA, it is easy to write a variety of projects, there is a saying that because the Java language itself has too few functions, so there are various design patterns to complement the Java language.
