# MifGenerator
Transforms an image file (.png,jpeg,.jpg) in an Altera Memory Initialization File (.mif)

Created by Emanuele Giuseppe Esposito on 07/21/2016 

This tool creates an Altera .mif (Memory Initialization File ) from an image file.
More informations about .mif file can be found here
http://quartushelp.altera.com/15.0/mergedProjects/reference/glossary/def_mif.htm

The file generated can be read from any text editor. The mif file will have as address radix and data radix UNS.

HOW TO RUN.

YOU MUST HAVE JAVA JDK 6+ INSTALLED.

1) Unzip the file.

2) With your terminal, go to the folder path

3) write java MifGenerator image_name.extension image_width image_height number_word q-output.

4) If you do not understand these parameter, you can see them in Quartus 2 by going on Tools -> MegaWizard Plug-In Manager
   -> Next (choose your options) -> Next (choose your megafunction and give a name to the ram/rom) -> (here).
   If you forget the order or what to inser, you can write (going with the terminal in the folder path)
java MifGenerator -help

5) The output will be a file generated with called the same nome of the image .mif .

6) Enjoy 

PS If you are really lazy, and don’t want to set every time these number, there is a bash script called run that you can edit in order to automatically get the size of the memory or get the same memory size and word parameters.

To use it, first you have to give it the permission to run (chmod ) and then you run it writing ./run.sh (if you are in the path of where it is).

It only requires as argument an image file. For example

./run.sh strawberry.png

I am not responsible of any use you will do of this tool.
