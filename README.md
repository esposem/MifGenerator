# MifGenerator
## Created by Emanuele Giuseppe Esposito on 07/21/2016

This tool creates an Altera .mif (Memory Initialization File ) from an image file (.png,jpeg,.jpg).

### Features :
- Resize the image
- Choice on deleting the resized image
- Edit a mif file
- Choice to get the image in grayscale or colors (Not yet implemented)**.

** Right now, the image will be converted in grayscale therefore will be displayed in grayscale on the screen.

More informations about .mif extension can be found [here](http://quartushelp.altera.com/15.0/mergedProjects/reference/glossary/def_mif.htm)

[http://quartushelp.altera.com/15.0/mergedProjects/reference/glossary/def_mif.htm](http://quartushelp.altera.com/15.0/mergedProjects/reference/glossary/def_mif.htm)

The file generated can be read from any text editor, and  will have as address radix and data radix UNS.

###HOW TO RUN.

 By default, any image should be in this folder in order to be read, but if you want to use any image in other directories, just remember not to add the first slash.

 For example, java MifGenerator EXAMPLE/linux_pengui.mif

**YOU MUST HAVE JAVA JDK 6+ INSTALLED.**

If you want to create a new mif file:

 1. Unzip these files.

 2. With your terminal, go to the folder path

 3. write ```java MifGenerator image_name.extension image_width image_height number_word q-output```

 4. If you do not understand these parameter, you can see them in Quartus 2 by going on Tools -> MegaWizard Plug-In Manager
    -> Next (choose your options) -> Next (choose your megafunction and give a name to the ram/rom)  -> (here).
    If you forget the order or not sure on what to insert, you can write (going with the terminal in the folder path)
    ```java MifGenerator -help```

 5. The output will be a file generated with called the same nome of the image .mif .

 6. Enjoy


If you want to modify a mif file to add another image in its free memory words :

Make sure you have the free space declared in the following way : ```[2900..3000] : 0``` where 0 can be any number.

 1. go to step 1 and 2

 2. write ```java mif_file_name.mif``` (this is your mif file).

 3. Then you will be asked what image do you want to insert and on which size

 4. Insert a name for the resulting mif file

 5. Enjoy 



 PS If you are really lazy, and don’t want to set every time these number, there is a bash script called run that you can edit in order to automatically get the size of the memory or get the same memory size and word parameters, or just insert the mif file to modify.

 To use it, first you have to give it the permission to run (chmod ) and then you run it by writing ./run.sh (if you are in the path of where it is).

 It only requires as argument an image file or mif file. For example

 ```./run.sh EXAMPLE/linux_penguin.png```

 As always, returns the mif file in the folder where the script is (MifGenerator)

 The default values for this script are ```160 120 32768 8 ```(What I used to generate my 160 x 120 image to show in my 640 x 480 screen.)



Any help/correction of this code is appreciated. If you want to contact me, send me a mail.

I am not responsible of any use you will do of this tool.
