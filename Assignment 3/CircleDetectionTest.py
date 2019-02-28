import cv2
import numpy as np
import math
import time
from tkinter import *

# Global variables

escape_key_id = 27

green = (0, 255, 0)
yellow = (255, 255, 0)
red = (0, 0, 255)
colors = (green, yellow, red)


# Utility functions

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
    print("Blue  : ", colorFrequencies[0])
    print("Green : ", colorFrequencies[1])
    print("Red   : ", colorFrequencies[2])
    print("---------")


# Program starts here

gui = Tk()
minDistSlider = Scale(
    gui,
    from_ = 50,
    to = 160,
    orient=HORIZONTAL)
minDistSlider.set(79)
minDistSlider.pack()

param1Slider = Scale(
    gui,
    from_ = 10,
    to = 100,
    orient=HORIZONTAL)
param1Slider.set(27)
param1Slider.pack()
param2Slider = Scale(
    gui,
    from_ = 10,
    to = 60,
    orient=HORIZONTAL)
param2Slider.set(49)
param2Slider.pack()

minRadiusSlider = Scale(
    gui,
    from_ = 0,
    to = 20,
    orient=HORIZONTAL)
minRadiusSlider.set(13)
minRadiusSlider.pack()
maxRadiusSlider = Scale(
    gui,
    from_ = 59,
    to = 100,
    orient=HORIZONTAL)
maxRadiusSlider.set(66)
maxRadiusSlider.pack()
"""
    79 - 27 - 49 - 13 - 66   Works well for Candy3D.jpg
    This doesn't work for Candy2D.jpg
"""

original_image = cv2.imread('Candy2D.jpg')

while True:
    gui.update()
    image = original_image.copy()
    grayscale_image = cv2.cvtColor(
        image,
        cv2.COLOR_BGR2GRAY)
    
    circles = cv2.HoughCircles(
        grayscale_image,
        cv2.HOUGH_GRADIENT,
        1,
        minDistSlider.get(),
        param1=param1Slider.get(),
        param2=param2Slider.get(),
        minRadius=minRadiusSlider.get(),
        maxRadius=maxRadiusSlider.get())
    circles = np.uint16(np.around(circles))
    colorFrequencies = np.zeros(len(colors))
    
    for i in circles[0,:]:
        x = i[1]
        y = i[0]
        # draw the outer circle
        cv2.circle(image,(y,x),i[2],(0,255,0),2)
        # draw the center of the circle
        cv2.circle(image,(y,x),2,(0,0,255),3)
        colorBGR = original_image[x][y]
        matchedColorIndex = findClosestColorIndex(colorBGR)
        colorFrequencies[matchedColorIndex] = colorFrequencies[matchedColorIndex] + 1
    printColors(colorFrequencies)
    
    cv2.imshow(
        "Circles",
        image)
    
    selectedKeyID = cv2.waitKey(1)
    if selectedKeyID == escape_key_id:
        break

cv2.destroyAllWindows()