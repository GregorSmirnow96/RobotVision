import cv2
import numpy as np
import math
import time
from tkinter import *

# Global variables

escape_key_id = 27

green   = (122, 202, 187)
yellow  = ( 89, 223, 241)
red     = ( 90,   5, 190)
orange  = ( 56, 131, 226)
blue    = (160, 140,  50)
black   = ( 71,  83, 143)
colors = (green, yellow, red, orange, blue, black)

dimension = 11
blurring_kernel = np.ones((dimension,dimension),np.float32)/(dimension*dimension)


# Utility functions

def detectMnMs(
    original_image,
    video_title,
    should_print_color_counts=False):
    image = original_image.copy()
    blurred_image = cv2.filter2D(image,-1,blurring_kernel)
    hsv_image = cv2.cvtColor(
        blurred_image,
        cv2.COLOR_BGR2HSV)
    grayscale_image = cv2.cvtColor(
        image,
        cv2.COLOR_BGR2GRAY)
    
    blurred_grayscale_image = cv2.filter2D(grayscale_image,-1,blurring_kernel)
    edges = cv2.Canny(
        blurred_grayscale_image,
        74,
        176)
    
    circles = cv2.HoughCircles(
        edges,
        cv2.HOUGH_GRADIENT,
        1,
        minDistSlider.get(),
        param1=param1Slider.get(),
        param2=param2Slider.get(),
        minRadius=minRadiusSlider.get(),
        maxRadius=maxRadiusSlider.get())
    
    circles = np.uint16(np.around(circles))
    colorFrequencies = np.zeros(len(colors))
    for circle in circles[0,:]:
        x = circle[1]
        y = circle[0]
        center = (y, x)
        cv2.circle(image,center,circle[2],(0,255,0),2)
        cv2.circle(image,center,2,(0,0,255),3)
        colorBGR = image[x][y]
        
        matchedColorIndex = findClosestColorIndex(colorBGR)
        colorFrequencies[matchedColorIndex] =\
            colorFrequencies[matchedColorIndex] + 1
    
    if (should_print_color_counts):
        printColors(colorFrequencies)
    
    cv2.imshow(
        video_title,
        hsv_image)

def findClosestColorIndex(colorBGR):
    colorIndex = 0
    shortestDistance = 9999999
    shortestDistanceIndex = -1
    for color in colors:
        b1 = colorBGR[0]
        g1 = colorBGR[1]
        r1 = colorBGR[2]
        b2 = color[0]
        g2 = color[1]
        r2 = color[2]
        bDif = b1 - b2
        gDif = g1 - g2
        rDif = r1 - r2
        bDifSquare = bDif * bDif
        gDifSquare = gDif * gDif
        rDifSquare = rDif * rDif
        distance = math.sqrt(
            bDifSquare +
            gDifSquare +
            rDifSquare)
        
        if (shortestDistance > distance):
            shortestDistance = distance
            shortestDistanceIndex = colorIndex
        
        colorIndex = colorIndex + 1
    return shortestDistanceIndex
    
def printColors(colorFrequencies):
    """
    Must add more colors.
    The colors are hard coded above in Global Variables.
    """
    print("---------")
    print("Green  : ", colorFrequencies[0])
    print("Yellow : ", colorFrequencies[1])
    print("Red    : ", colorFrequencies[2])
    print("Orange : ", colorFrequencies[3])
    print("Blue   : ", colorFrequencies[4])
    print("Black  : ", colorFrequencies[5])
    print("---------")


# Program starts here

gui = Tk()
minDistSlider = Scale(
    gui,
    from_ = 30,
    to = 160,
    orient=HORIZONTAL)
#minDistSlider.set(79)
minDistSlider.pack()

param1Slider = Scale(
    gui,
    from_ = 10,
    to = 100,
    orient=HORIZONTAL)
#param1Slider.set(27)
param1Slider.pack()
param2Slider = Scale(
    gui,
    from_ = 10,
    to = 60,
    orient=HORIZONTAL)
#param2Slider.set(49)
param2Slider.pack()

minRadiusSlider = Scale(
    gui,
    from_ = 0,
    to = 20,
    orient=HORIZONTAL)
#minRadiusSlider.set(13)
minRadiusSlider.pack()
maxRadiusSlider = Scale(
    gui,
    from_ = 59,
    to = 100,
    orient=HORIZONTAL)
#maxRadiusSlider.set(66)
maxRadiusSlider.pack()
"""
    79 - 27 - 49 - 13 - 66   Works well for Candy3D.jpg
    This doesn't work for Candy2D.jpg
    59 - 10 - 10 - 13 - 59
"""

original_image2D = cv2.imread('Images/Candy2D.jpg')
original_image3D = cv2.imread('Images/Candy3D.jpg')
original_image1  = cv2.imread('Images/one.jpg')
original_image2  = cv2.imread('Images/two.jpg')
original_image3  = cv2.imread('Images/three.jpg')
original_image4  = cv2.imread('Images/four.jpg')

while True:
    gui.update()
    
    detectMnMs(
        original_image1,
        "MnMs1",
        True)
    detectMnMs(
        original_image2,
        "MnMs2")
    detectMnMs(
        original_image3,
        "MnMs3")
    detectMnMs(
        original_image4,
        "MnMs4")
    
    selectedKeyID = cv2.waitKey(1)
    if selectedKeyID == escape_key_id:
        break

cv2.destroyAllWindows()