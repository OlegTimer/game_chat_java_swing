ALWAYS ON TOP JAVA SWING CHAT ON WINDOWS EVEN IN FULL SCREEN

I tried to make a transparent text always on top of any window - partial success. 
The problem is the Windows understand only one window on top, and in fullscreen it may be a problem. 
Also we don’t want if the window updates to steal focus from the main window (and fold the main app). 
And Java is not easy to catch if keyboard buttons outside of app – so I used AutoHotKey to get keyboard event.
// 
Compile all *java to *jar
Configure and compile ahk to exe
Write a BAT to launch all together
If  ahk_listener_input.txt is absent, it would be created at first java launch. Update options, if needed.
Run BAT
Chat with 1 or 2 players, pressing the buttons (m,./ by default).
Close app and java.

//
Java at close closes ahk_listener.exe (you needn't close it manually).

Ahk listener can be replaced by jni, and chat can be rewritten.

If you cannot compile ahk to exe, you can simply rename ahk_listener_back_to_exe.txt to ahk_listener.exe
It listens to 4 buttons: m,./

Tested on Win7 and WinXP. Works good on a desktop, but in fullscreen depends on video system 
(on some machines possibly not showing java window or app in the same time in fullscreen)
//

You may use non-Latin symbols in command1-4, but ahk_listener_input.txt needs to be saved as ANSI on Windows.
Encoding errors are possible. Names of files and folders are better in Latin and no spaces.

//
Code examples between lines:


ahk_listener.ahk code start:
=====================================================
vk4D::
{
FileAppend, 1, d1
Sleep 100
}
Return

vkBC::
{
FileAppend, 1, d2
Sleep 100
}
Return

vkBE::
{
FileAppend, 1, d3
Sleep 100
}
Return

vkBF::
{
FileAppend, 1, d4
Sleep 100
}
Return
=====================================================
ahk_listener.ahk code end.
//ahk. uses virtual codes of 4 buttons: m,./

BAT example to launch ahk_listener_java.jar, ahk_listener.exe and app.exe in the same directory:
(ahk_listener_java.jar and ahk_listener.exe should not be renamed!)
BAT code start:
=====================================================
@echo off
start .\ahk_listener.exe
start .\ahk_listener_java.jar
start .\app.exe
exit
=====================================================
BAT code end.

//makes the top window and not stealing focus on Java Swing:
frame.setAlwaysOnTop(false);
frame.setAlwaysOnTop(true);

// Always on top Java Swing on Windows even in fullscreen by OlegTim
